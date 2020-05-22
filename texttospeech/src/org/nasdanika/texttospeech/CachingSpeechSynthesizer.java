package org.nasdanika.texttospeech;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.resources.BinaryContainer;
import org.nasdanika.common.resources.FileSystemContainer;

/**
 * {@link SpeechSynthesizer} which caches synthesized speech in a container and returns a cached result if one is found.
 * @author Pavel
 *
 */
public class CachingSpeechSynthesizer implements SpeechSynthesizer {
	
	private SpeechSynthesizer chain;
	private BinaryContainer cache;
	
	/**
	 * Creates a cache which stores generated sound files in user home .nasdanika/speech-synthesizer/cache directory 
	 * @param chain
	 */
	public CachingSpeechSynthesizer(SpeechSynthesizer chain) {
		this.chain = chain;
		
		File cacheDir = getDefaultCacheDir();
		if (!cacheDir.exists()) {
			cacheDir.mkdirs();
		}
		
		cache = new FileSystemContainer(cacheDir).stateAdapter();		
	}

	public static File getDefaultCacheDir() {
		File userHome = new File(System.getProperty("user.home"));
		File cacheDir = new File(userHome, ".nasdanika" + File.separator + "speech-synthesizer" + File.separator + "cache");
		return cacheDir;
	}
	
	/**
	 * Creates a cache which stores generated sound files in the specified cache directory 
	 * @param chain
	 */	
	public CachingSpeechSynthesizer(SpeechSynthesizer chain, File cacheDir) {
		this.chain = chain;
		
		if (!cacheDir.exists()) {
			cacheDir.mkdirs();
		}
		
		cache = new FileSystemContainer(cacheDir).stateAdapter();		
	}	

	public CachingSpeechSynthesizer(SpeechSynthesizer chain, BinaryContainer cache) {
		this.chain = chain;
		this.cache = cache;
	}

	@Override
	public InputStream synthesizeSpeech(String language, String voice, boolean ssml, String text, ProgressMonitor progressMonitor) throws Exception {
		String cachePath = language + "/" + voice + "/" + (ssml ? "S" : "T") + Hex.encodeHexString(MessageDigest.getInstance("SHA-256").digest(text.getBytes(StandardCharsets.UTF_8))) + ".mp3";
		InputStream ret = (InputStream) cache.find(cachePath, progressMonitor);
		if (ret == null) {
			cache.put(cachePath, chain.synthesizeSpeech(language, voice, ssml, text, progressMonitor), progressMonitor);
		}
		return (InputStream) cache.find(cachePath, progressMonitor);
	}

	@Override
	public void close() throws Exception {
		chain.close();		
	}

}
