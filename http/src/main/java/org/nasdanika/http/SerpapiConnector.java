package org.nasdanika.http;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.nasdanika.common.Util;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;


public class SerpapiConnector {
	
	private static final String ORGANIC_RESULTS_KEY = "organic_results";
	public static final String BASE_URL = "https://serpapi.com";

	public record Link(String link, String title) {}
	
	static final String MARKDOWN_CONTENT_KEY = "markdownContent";
	static final String MAIN_CONTENT_KEY = "mainContent";
	static final String CONTENT_KEY = "content";
	static final String DATE_KEY = "date";
	static final String TITLE_KEY = "title";
	static final String SNIPPET_HIGLIGHTED_WORDS_KEY = "snippet_highlighted_words";
	static final String LINK_KEY = "link";
	static final String INLINE_KEY = "inline";
	static final String SITE_LINKS_KEY = "sitelinks";
	
	public record SearchResult(
		LocalDate date, 
		String snippet,
		String redirectLink,
		String displayedLink,
		String favicon,
		List<String> snippetHiglightedWords,
		String link,
		int position,
		List<Link> inlineSiteLinks,
		String source,
		String title,
		String content,
		String mainContent,
		String markdownMainContent				
	) {
		
		public SearchResult(JSONObject jsonObject) {
			this(
				getDate(jsonObject),
				jsonObject.optString("snippet"),
				jsonObject.optString("redirect_link"),
				jsonObject.optString("displayed_link"),
				jsonObject.optString("favicon"),
				getSnippetHighlightedWords(jsonObject),
				jsonObject.optString(LINK_KEY),
				jsonObject.optInt("position", -1),
				getInlineSiteLinks(jsonObject),
				jsonObject.optString("source"),
				jsonObject.optString(TITLE_KEY),
				jsonObject.optString(CONTENT_KEY),
				jsonObject.optString(MAIN_CONTENT_KEY),
				jsonObject.optString(MARKDOWN_CONTENT_KEY));
		}
		
		private static List<Link> getInlineSiteLinks(JSONObject jsonObject) {
			List<Link> ret = new ArrayList<>();
			if (jsonObject.has(SITE_LINKS_KEY)) {
				JSONObject jSiteLinks = jsonObject.getJSONObject(SITE_LINKS_KEY);
				if (jSiteLinks.has(INLINE_KEY)) {
					JSONArray jInlineLinks = jSiteLinks.getJSONArray(INLINE_KEY);
					for (int i = 0; i < jInlineLinks.length(); ++i) {
						JSONObject jInlineLink = jInlineLinks.getJSONObject(i);
						ret.add(new Link(jInlineLink.getString(LINK_KEY), jInlineLink.getString(TITLE_KEY)));
					}
				}
			}
			return ret;
		}

		private static List<String> getSnippetHighlightedWords(JSONObject jsonObject) {
			List<String> ret = new ArrayList<>();
			if (jsonObject.has(SNIPPET_HIGLIGHTED_WORDS_KEY)) {
				JSONArray jSnippetHighlightedWords = jsonObject.getJSONArray(SNIPPET_HIGLIGHTED_WORDS_KEY);
				for (int i = 0; i < jSnippetHighlightedWords.length(); ++i) {
					ret.add(jSnippetHighlightedWords.getString(i));
				}
			}
			return ret;
		}

		private static LocalDate getDate(JSONObject jsonObject) {
			if (jsonObject.has(DATE_KEY)) {				
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
		        try {
		        	return LocalDate.parse(jsonObject.getString(DATE_KEY), formatter);
		        } catch (DateTimeParseException e) {
		        	e.printStackTrace();
		        	return null; // TODO - parse dates like "5 days ago"
		        }
			}
			return null;
		}		
		
	}
	
	private String[] sites;
			
	private String apiKey;

	public SerpapiConnector(String apiKey, String... sites) {
		this.apiKey = apiKey;
		this.sites = sites;
	}
	
	public Flux<SearchResult> search(String query, int count, int offset) {
		return search(query, count, offset, null);
	}
		
	public Flux<SearchResult> search(String query, int count, int offset, BiFunction<String,Mono<String>,Mono<String>> cache) {
		StringBuilder queryBuilder = new StringBuilder(query);
		for (int i = 0; i < sites.length; ++i) {
			queryBuilder.append(" ");
			if (i > 0) {
				queryBuilder.append("OR ");
			}
			queryBuilder
				.append("site:")
				.append(sites[i]);
		}
		
        return HttpClient.create()
                .baseUrl("https://serpapi.com")
                .get()
                .uri(buildUrl(queryBuilder.toString()))
                .responseSingle((res, content) -> content.asString())
                .map(JSONObject::new)
                .flatMapMany(searchResult -> {
                	List<Mono<JSONObject>> fullResults = new ArrayList<>();
                	if (searchResult.has(ORGANIC_RESULTS_KEY)) {
	                	JSONArray organicResults = searchResult.getJSONArray(ORGANIC_RESULTS_KEY);
	                	for (int i = 0; i < organicResults.length(); ++i) {
	                		JSONObject pageResult = organicResults.getJSONObject(i);
	                		String pageUrl = pageResult.getString("link");
	                		Mono<String> fResStr = HttpClient
	                			.create()
	                			.get()
	                			.uri(pageUrl)
	                			.responseSingle((res, content) -> content.asString());                		
	                		if (cache != null) {
	                			fResStr = cache.apply(pageUrl, fResStr);
	                		}
	                		
	                		Mono<JSONObject> fRes = fResStr.map(rStr -> {
	                				pageResult.put("content", rStr);
	                				return pageResult; 
	                			});
	                		fullResults.add(fRes);
	                	}
                	}
                	
                	return Flux.merge(fullResults);
                }).map(jRes -> {
                	if (jRes.has(CONTENT_KEY)) {
                		String content = jRes.getString(CONTENT_KEY);
                		Document document = Jsoup.parse(content);
						Elements main = selectMainContent(jRes.optString(LINK_KEY), document);
						String mainContent = main.isEmpty() ? document.body().html() : main.html();
						jRes.put(MAIN_CONTENT_KEY, mainContent);
				    	String markdown = Util.isBlank(mainContent) ? "" : FlexmarkHtmlConverter.builder().build().convert(mainContent);
						jRes.put(MARKDOWN_CONTENT_KEY, markdown);
                	}
                	return new SearchResult(jRes);
                });
	}

	protected Elements selectMainContent(String link, Document document) {
		return document.body().select(getMainContentSelector(link));
	}

	protected String getMainContentSelector(String link) {
		return "main";
	}

	protected String getBaseUrl() {
		return BASE_URL;
	}

	protected String buildUrl(String query) {
		return "/search" +
                "?api_key=" + apiKey +
                "&engine=google" +
                "&q=" + encode(query) +
                "&google_domain=google.com" +
                "&gl=us" +
                "&hl=en";
	}

    private String encode(String value) {
        return java.net.URLEncoder.encode(value, java.nio.charset.StandardCharsets.UTF_8);
    }	
	
}
