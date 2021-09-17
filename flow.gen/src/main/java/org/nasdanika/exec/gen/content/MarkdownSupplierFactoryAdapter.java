package org.nasdanika.exec.gen.content;

import org.nasdanika.common.Context;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.exec.content.Markdown;

public class MarkdownSupplierFactoryAdapter extends FilterSupplierFactoryAdapter<Markdown> {

	protected MarkdownSupplierFactoryAdapter(Markdown markdown) {
		super(markdown);
	}

	@Override
	protected String filter(Context context, String input) {
		String html = context.computingContext().get(MarkdownHelper.class, MarkdownHelper.INSTANCE).markdownToHtml(input);
		return getTarget().isStyle() ? "<div class=\"markdown-body\">" + html + "</div>" : html;
	}

}
