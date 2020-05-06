package org.nasdanika.common;

import java.io.InputStream;

/**
 * Interface for speech synthesis.
 * @author Pavel
 *
 */
public interface SpeechSynthesizer {
	
	/**
	 * @param language Language
	 * @param voice Voice
	 * @param ssml If true, the text argument contains SSML.
	 * @param text Text/SSML to be spoken.
	 * @return MP3.
	 * @throws Exception
	 */
	InputStream synthesizeSpeech(String language, String voice, boolean ssml, String text, ProgressMonitor progressMonitor) throws Exception;

}
