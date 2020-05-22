package org.nasdanika.texttospeech;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams.Builder;

/**
 * Uses Google text-to-speech (https://cloud.google.com/text-to-speech).
 * Use of text to speech requires GOOGLE_APPLICATION_CREDENTIALS environment variable to be set to the location of the private key JSON file. See https://developers.google.com/accounts/docs/application-default-credentials for more information.
 * @author Pavel
 *
 */
public class GoogleCloudTextToSpeechSynthesizer implements SpeechSynthesizer {

	TextToSpeechClient textToSpeechClient;
	
	public GoogleCloudTextToSpeechSynthesizer() throws IOException {
		 textToSpeechClient = TextToSpeechClient.create();
	}

	@Override
	public InputStream synthesizeSpeech(
			String language, 
			String voice, 
			boolean ssml, 
			String text,
			ProgressMonitor progressMonitor) throws Exception {

		com.google.cloud.texttospeech.v1.SynthesisInput.Builder inputBuilder = SynthesisInput.newBuilder();
		if (ssml) {
			inputBuilder.setSsml(text);
		} else {
			inputBuilder.setText(text);
		}
		// Build the voice request; languageCode = "en_us"
		Builder voiceBuilder = VoiceSelectionParams.newBuilder();
		voiceBuilder.setLanguageCode(language);
		if (!Util.isBlank(voice)) {
			voiceBuilder.setName(voice);
		}
		SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(
				inputBuilder.build(), 
				voiceBuilder.build(), 
				AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build());

		return new ByteArrayInputStream(response.getAudioContent().toByteArray());						
	}

	@Override
	public void close() throws Exception {
		textToSpeechClient.close();
	}

}
