package org.nasdanika.texttospeech;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.nasdanika.cli.DelegatingCommand;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.CompoundCommand;
import org.nasdanika.common.CompoundCommandFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
		description = "Generates voice from text using Google Text-To-Speech and local cache. Text is interpolated with context entries.",
		name = "speak",
		versionProvider = TextToSpeechBundleVersionProvider.class,
		defaultValueProvider = TextToSpeechCommandDefaultValueProvider.class
		)
public class TextToSpeechCommand extends DelegatingCommand {

	@Parameters(
			paramLabel = "LANGUAGE", 
			index = "0",
			description = "Language and locale code, e.g. en-US. See https://cloud.google.com/text-to-speech/docs/voices for a list of supported languages.")
	protected String language;	
	
	@Parameters(
			paramLabel = "FILE",
			index = "1..*",
			arity = "0..*",
			description = "A list of files to generate speech from. "
					+ "File type is determined by its extension .txt for plain text and .ssml for SSML (https://cloud.google.com/text-to-speech/docs/ssml) unless explicitly specified by the SSML option. "
					+ "If FILE argument is not provided then text is taken from the standard input and is treated as text or SSML based on the SSML option value. "
					+ "If a file is a directory then it is processed processed recursively generating speach from .txt and .ssml files. "
					+ "In this case if SSML option is set to true only ssml files are processed, if it is set to false then only text files are processed.")
	protected List<File> files = new ArrayList<>();	
		
	@Option(names = {
			"-o", "--output"}, 
			description = "Output file for a single input (file or standard input) "
					+ "or an output directory for multiple inputs. "
					+ "If not provided, generated sound is output to the standard output for single input and to the location of inputs for multiple inputs. "
					+ "In the latter case output file name is formed by adding .mp3 extension to the source file name. E.g. my.txt -> my.txt.mp3")
	private File output;		
	
	@Option(
			names = {"-s", "--ssml"}, 
			negatable = true, 
			description = "Input is SSML.")
	private Boolean ssml;
		
	@Option(
			names = {"-v", "--voice"}, 
			description = "Voice. See https://cloud.google.com/text-to-speech/docs/voices for a list of supported voices.")
	private String voice;
		
	@Option(
			names = "--cache", 
			negatable = true, 
			description = "Indicates whether to use cache, default is true.", 
			defaultValue = "true")
	private boolean cache;
	
	@Option(
			names = "--cache-dir", 
			description = "Cache directory, default value is ${DEFAULT-VALUE}")
	private File cacheDir;
		
	@Option(
			names = {"-e", "--charset"}, 
			description = "Input charset, default value: ${DEFAULT-VALUE}")
	private String charset;	
	
	@Override
	protected Context createContext() throws IOException {
		SpeechSynthesizer speechSynthesizer = new GoogleCloudTextToSpeechSynthesizer();
		if (cache) {
			speechSynthesizer = new CachingSpeechSynthesizer(speechSynthesizer, cacheDir);
		}
		return super.createContext().compose(Context.singleton(SpeechSynthesizer.class, speechSynthesizer));
	}

	@Override
	protected CommandFactory getCommandFactory() {
		return new CompoundCommandFactory("Speak", getElements()) {
			
			@Override
			protected CompoundCommand createCompoundCommand(Context context) {
				return new CompoundCommand(name, context.get(ExecutorService.class)) {
					
					@Override
					public void close() throws Exception {
						super.close();
						SpeechSynthesizer speechSynthesizer = context.get(SpeechSynthesizer.class);
						if (speechSynthesizer != null) {
							speechSynthesizer.close();
						}
					}
					
				};
			}
			
		};
	}
	
	private Collection<CommandFactory> getElements() {
		List<CommandFactory> ret = new ArrayList<>();
		if (files.isEmpty()) {
			ret.add(createStandardInputCommandFactory()); 
		} else if (files.size() == 1 && files.get(0).isFile()) {
			File file = files.get(0);
			if (ssml == null) {
				// Infer defaulting to text
				if (file.getName().endsWith(".ssml")) {
					ret.add(createSsmlFileCommandFactory(file, null));
				} else {
					ret.add(createTextFileCommandFactory(file, null));
				}				
			} else if (Boolean.TRUE.equals(ssml)) {
				ret.add(createSsmlFileCommandFactory(file, null));				
			} else {
				ret.add(createTextFileCommandFactory(file, null));				
			}
		} else {
			for (File file: files) {
				if (file.isDirectory()) {
					for (File child: file.listFiles()) {
						ret.addAll(collectFiles(child, null));
					}					
				} else if (file.isFile()) {
					ret.addAll(collectFiles(file, null));
				}
			}
		}
		return ret;
	}

	/**
	 * Collects files to process.
	 * @param file
	 * @param path
	 * @return
	 */
	private Collection<CommandFactory> collectFiles(File file, String path) {
		List<CommandFactory> ret = new ArrayList<>();
		if (file.isFile()) {
			if (file.getName().endsWith(".txt") && (ssml == null || Boolean.FALSE.equals(ssml))) {				
				ret.add(createTextFileCommandFactory(file, path));
			} else if (file.getName().endsWith(".ssml") && (ssml == null || Boolean.TRUE.equals(ssml))) {				
				ret.add(createSsmlFileCommandFactory(file, path));
			}
		} else if (file.isDirectory()) {
			for (File child: file.listFiles()) {
				ret.addAll(collectFiles(child, path == null ? file.getName() : path + File.separator + file.getName()));
			}
		}
		return ret;
	}
	
	private CommandFactory createStandardInputCommandFactory() {
		return new CommandFactory() {
			
			@Override
			public org.nasdanika.common.Command create(Context context) throws Exception {
				return new org.nasdanika.common.Command() {
					
					@Override
					public double size() {
						return 1;
					}
					
					@Override
					public String name() {
						return "Speaking system input";
					}
					
					@Override
					public void execute(ProgressMonitor progressMonitor) throws Exception {
						speak(context, Boolean.TRUE.equals(ssml), null, null, progressMonitor);						
					}
				};
			}
		};
	}

	private CommandFactory createSsmlFileCommandFactory(File file, String path) {
		return new CommandFactory() {
			
			@Override
			public org.nasdanika.common.Command create(Context context) throws Exception {
				return new org.nasdanika.common.Command() {
					
					@Override
					public double size() {
						return 1;
					}
					
					@Override
					public String name() {
						return "Speaking SSML file " + file.getAbsolutePath();
					}
					
					@Override
					public void execute(ProgressMonitor progressMonitor) throws Exception {
						speak(context, true, file, path, progressMonitor);						
					}
				};
			}
		};
	}

	private CommandFactory createTextFileCommandFactory(File file, String path) {
		return new CommandFactory() {
			
			@Override
			public org.nasdanika.common.Command create(Context context) throws Exception {
				return new org.nasdanika.common.Command() {
					
					@Override
					public double size() {
						return 1;
					}
					
					@Override
					public String name() {
						return "Speaking text file " + file.getAbsolutePath();
					}
					
					@Override
					public void execute(ProgressMonitor progressMonitor) throws Exception {
						speak(context, false, file, path, progressMonitor);						
					}
				};
			}
		};
	}
	
	private String inputText(InputStream input) throws IOException {
		StringWriter sw = new StringWriter();
		try (Reader reader = new BufferedReader(new InputStreamReader(input, charset))) {
			int ch;
			while ((ch = reader.read()) != -1) {
				sw.write(ch);
			}
		}
		sw.close();
		return sw.toString();
	}
	
	/**
	 * Creates output stream for audio depending on options. 
	 * @param file Input file or null if standard input is used.
	 * @param path File path in the input files directory.
	 * @return
	 * @throws IOException 
	 */	
	private OutputStream audioOutput(File file, String path) throws IOException {
		if (files.isEmpty() || files.size() == 1 && files.get(0).isFile()) {
			// Single input - file or standard input. 
			if (output == null) {
				return System.out;
			}
			
			return new FileOutputStream(output);
		}
		
		File outputDir;
		// Multiple files		
		if (output == null) {
			outputDir = file.getParentFile();
		} else {		
			outputDir = path == null ? output : new File(output, path);
			outputDir.mkdirs();
		}
		
		return new FileOutputStream(new File(outputDir, file.getName()+".mp3"));
	}
		
	private void speak(
			Context context, 
			boolean isSsml, 
			File file, 
			String path, 
			ProgressMonitor progressMonitor) throws Exception {
		
		String text = context.interpolate(inputText(file == null ? System.in : new FileInputStream(file)));
		SpeechSynthesizer synthesizer = context.get(SpeechSynthesizer.class);
		try (InputStream audio = new BufferedInputStream(synthesizer.synthesizeSpeech(language, voice, isSsml, text, progressMonitor)); OutputStream out = new BufferedOutputStream(audioOutput(file, path))) {
			int b;
			while ((b = audio.read()) != -1) {
				out.write(b);
			}
		}
	}

}
