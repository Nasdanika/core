package org.nasdanika.texttospeech;

import java.io.ByteArrayInputStream;
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

public class GoogleCloudTextToSpeechSynthesizer implements SpeechSynthesizer {

	@Override
	public InputStream synthesizeSpeech(
			String language, 
			String voice, 
			boolean ssml, 
			String text,
			ProgressMonitor progressMonitor) throws Exception {

		try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
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
	}

}
