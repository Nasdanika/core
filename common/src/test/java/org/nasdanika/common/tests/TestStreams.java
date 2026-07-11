package org.nasdanika.common.tests;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Input;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Output;
import org.nasdanika.common.StreamInput;
import org.nasdanika.common.StreamOutput;
import org.nasdanika.common.Util;

public class TestStreams {

	/**
	 * Streams the org.nasdanika.common module, includes only resources under the
	 * descriptors package, excludes anything matching Query*.java, extracts the
	 * sub-path (part after the package prefix), and writes each resource to
	 * target/descrs/.
	 */
	@Test
	void testStreamInputToOutputWithFilters() throws IOException {
		String includePattern = "org/nasdanika/common/descriptors/**";

		File outputDir = new File("target/descrs");
		outputDir.mkdirs();
		// Trailing slash is required so EMF URI resolution treats it as a directory
		URI outputBase = URI.createFileURI(outputDir.getAbsolutePath()).appendSegment("");						
		Output<OutputStream> output = StreamOutput.INSTANCE.base(outputBase);
		try (Stream<StreamInput> inputs = StreamInput.of(TestStreams.class)) {
			inputs
				.filter(i -> !Util.isBlank(i.getURI().lastSegment()))
				.filter(Input.include(includePattern))
				.filter(Input.exclude("**/Query*.class"))
				.flatMap(Input.subpath(includePattern))
				.map(StreamInput::of)
				.forEach(input -> {
					System.out.println("Writing " + input.getURI() + " to " + input.getURI().resolve(outputBase));
					try {
						input.transferTo(output);
					} catch (IOException e) {
						throw new NasdanikaException(e);
					}
				});
		}

		// Verify that some files were written to target/descrs
		String[] written = outputDir.list();
		System.out.println("Written to " + outputDir.getAbsolutePath() + ":");
		if (written != null) {
			for (String name : written) {
				System.out.println("  " + name);
			}
		}
	}

}