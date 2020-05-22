package org.nasdanika.texttospeech;

import java.nio.charset.StandardCharsets;

import picocli.CommandLine.IDefaultValueProvider;
import picocli.CommandLine.Model.ArgSpec;
import picocli.CommandLine.Model.OptionSpec;

public class TextToSpeechCommandDefaultValueProvider implements IDefaultValueProvider {

	@Override
	public String defaultValue(ArgSpec spec) throws Exception {
		if (spec instanceof OptionSpec) {
			String longestName = ((OptionSpec) spec).longestName();
			if ("--cache-dir".equals(longestName)) {
				return CachingSpeechSynthesizer.getDefaultCacheDir().getAbsolutePath();
			}
			
			if ("--charset".equals(longestName)) {
				return StandardCharsets.UTF_8.name();
			}
		}
		return spec.defaultValue();
	}

}
