package org.nasdanika.texttospeech;

import org.nasdanika.cli.ContextCommand;

import picocli.CommandLine.Command;

@Command(
		description = "Generates voice from text",
		name = "speak",
		versionProvider = TextToSpeechBundleVersionProvider.class)

public class TextToSpeechCommand extends ContextCommand {

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
