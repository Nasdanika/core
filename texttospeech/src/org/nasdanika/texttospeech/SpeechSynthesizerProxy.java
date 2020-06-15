package org.nasdanika.texttospeech;

import java.io.InputStream;
import java.util.concurrent.Callable;

import org.nasdanika.common.ProgressMonitor;

/**
 * Creates a speech synthesizer on first invocation and then delegates to it.
 * @author Pavel
 *
 */
public class SpeechSynthesizerProxy implements SpeechSynthesizer {
	
	private Callable<SpeechSynthesizer> factory;
	private SpeechSynthesizer target;

	public SpeechSynthesizerProxy(Callable<SpeechSynthesizer> factory) {
		this.factory = factory;
	}

	@Override
	public void close() throws Exception {
		if (target != null) {
			target.close();
		}
	}
	
	private synchronized SpeechSynthesizer getTarget() throws Exception {
		if (target == null) {
			target = factory.call();
		}
		return target;
	}

	@Override
	public InputStream synthesizeSpeech(String language, String voice, boolean ssml, String text, ProgressMonitor progressMonitor) throws Exception {
		return getTarget().synthesizeSpeech(language, voice, ssml, text, progressMonitor);
	}

}
