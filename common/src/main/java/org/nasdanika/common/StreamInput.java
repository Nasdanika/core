package org.nasdanika.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.module.ModuleReader;
import java.lang.module.ModuleReference;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.eclipse.emf.common.util.URI;

public interface StreamInput extends Input<InputStream> {
	
	default long transferTo(Output<OutputStream> output) throws IOException {
		try (InputStream is = openInput()) {
			try (var os = output.openOutput(getURI())) {
				return is.transferTo(os);
			}
		}
	}
	
	static Stream<StreamInput> of(ModuleReference moduleReferece) {
		try {
			ModuleReader reader = moduleReferece.open();
			return reader
				.list()				
				.onClose(() -> {
					try {
						reader.close();
					} catch (IOException e) {
						throw new NasdanikaException(e);
					}
				})
				.map(name -> new StreamInput() {
					
					URI uri = URI.createURI(name);
							
					@Override
					public URI getURI() {
						return uri;
					}
	
					@Override
					public InputStream openInput() throws IOException {
						return reader.open(name).orElse(null);
					}
					
				});
		} catch (IOException e) {
			throw new NasdanikaException(e);
		}
	}
	
	static Stream<StreamInput> of(Module module) {
		return of(module.getLayer().configuration().findModule(module.getName()).orElseThrow().reference());
	}
	
	static Stream<StreamInput> of(Class<?> classFromModule) {
		return of(classFromModule.getModule());
	}	
	
	/**
	 * Recursively walks each file/directory and returns a StreamInput per regular file.
	 * URIs are absolute file URIs.
	 */
	static Stream<StreamInput> of(File... files) {
		return Arrays.stream(files).flatMap(file -> {
			if (file.isDirectory()) {
				try {
					return Files.walk(file.toPath())
						.filter(p -> !Files.isDirectory(p))
						.map(p -> new StreamInput() {
							@Override
							public URI getURI() {
								return URI.createFileURI(p.toFile().getAbsolutePath());
							}
							@Override
							public InputStream openInput() throws IOException {
								return Files.newInputStream(p);
							}
						});
				} catch (IOException e) {
					throw new NasdanikaException(e);
				}
			} else {
				return Stream.of(new StreamInput() {
					@Override
					public URI getURI() {
						return URI.createFileURI(file.getAbsolutePath());
					}
					@Override
					public InputStream openInput() throws IOException {
						return new FileInputStream(file);
					}
				});
			}
		});
	}

	/**
	 * Wraps a URL as a single StreamInput.
	 */
	static StreamInput of(URL url) {
		return new StreamInput() {
			@Override
			public URI getURI() {
				return URI.createURI(url.toString());
			}
			@Override
			public InputStream openInput() throws IOException {
				return url.openStream();
			}
		};
	}

	/**
	 * Reads all entries from a ZipInputStream into memory and returns them as a stream.
	 * The input stream is fully consumed and closed before the stream is returned.
	 */
	static Stream<StreamInput> ofZip(InputStream zipInputStream) {
		try (ZipInputStream zis = new ZipInputStream(zipInputStream)) {
			List<StreamInput> result = new ArrayList<>();
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				if (!entry.isDirectory()) {
					byte[] bytes = zis.readAllBytes();
					String name = entry.getName();
					result.add(new StreamInput() {
						@Override
						public URI getURI() {
							return URI.createURI(name);
						}
						@Override
						public InputStream openInput() {
							return new ByteArrayInputStream(bytes);
						}
					});
				}
				zis.closeEntry();
			}
			return result.stream();
		} catch (IOException e) {
			throw new NasdanikaException(e);
		}
	}

	/**
	 * Returns a stream of StreamInputs backed by the given ZipFile.
	 * The caller is responsible for closing the ZipFile (or use {@code onClose()}).
	 */
	static Stream<StreamInput> ofZip(ZipFile zipFile) {
		return Collections.list(zipFile.entries()).stream()
			.filter(entry -> !entry.isDirectory())
			.map(entry -> new StreamInput() {
				@Override
				public URI getURI() {
					return URI.createURI(entry.getName());
				}
				@Override
				public InputStream openInput() throws IOException {
					return zipFile.getInputStream(entry);
				}
			});
	}

	/**
	 * Opens the given zip File, streams its entries, and closes the ZipFile when the stream is closed.
	 */
	static Stream<StreamInput> ofZip(File file) {
		try {
			ZipFile zipFile = new ZipFile(file);
			return ofZip(zipFile).onClose(() -> {
				try {
					zipFile.close();
				} catch (IOException e) {
					throw new NasdanikaException(e);
				}
			});
		} catch (IOException e) {
			throw new NasdanikaException(e);
		}
	}

}