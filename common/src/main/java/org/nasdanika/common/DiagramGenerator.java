package org.nasdanika.common;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.nasdanika.common.resources.Container;

/**
 * Generates PlantUML diagrams.
 * @author Pavel
 *
 */
public interface DiagramGenerator extends Composeable<DiagramGenerator> {
	
	/**
	 * PlantUML diagram dialect
	 * @author Pavel
	 *
	 */
	enum Dialect {

		/**
		 * For sequence, use case, class, activity, component, state, object, deployment, timing, and network diagrams.
		 */
		UML,
		
		/**
		 * For wireframe diagrams - https://plantuml.com/salt
		 */
		SALT,
		
		/**
		 * For Gantt charts - https://plantuml.com/gantt-diagram
		 */
		GANTT,
		
		/**
		 * For mind maps - https://plantuml.com/mindmap-diagram
		 */
		MINDMAP,
		
		/**
		 * For work breakdown structures - https://plantuml.com/wbs-diagram
		 */
		WBS,
		
		/**
		 * For generating drawio (https://diagrams.net/) embedded diagram HTML from diagram file contents as a spec. 
		 */
		DRAWIO,
		
		/**
		 * For generating Mermaid (https://mermaid-js.github.io/mermaid/#/) diagrams.
		 */
		MERMAID
		
	}
	
	/**
	 * @param dialect
	 * @return true if given dialect is supported by this generator
	 */
	boolean isSupported(Dialect dialect);
	
	/**
	 * Uses local PlantUML.
	 */
	DiagramGenerator INSTANCE = new DiagramGeneratorImpl();
	
	/**
	 * Creates an instance of {@link DiagramGenerator} which does a POST to the service URL postfixed with /&lt;dialect name&gt; with diagram text spec as payload
	 * and returns the payload as String. UTF-8 is used for character encoding. 
	 * The service shall call the INSTANCE's <code>generateDiagram()</code>. It can also perform caching of generated images. Return code 200 is treated as success. 
	 * Any other response code is treated as an error.
	 * Remoting can be used in situations where GrapViz (https://graphviz.org/) is not available on diagram generating machines or to improve performance via caching.
	 * 
	 * An example of server implementation with SpringBoot can be found here - https://github.com/Nasdanika/spring-exec/blob/main/src/main/java/org/nasdanika/spring/exec/controllers/ExecController.java#L344
	 * @param service Service URL with a trailing slash /. Must be an HTTP(s) URL.
	 * @return Diagram image and image map.
	 */
	static DiagramGenerator createClient(URL service) {
		
		return new DiagramGenerator() {

			@Override
			public String generateDiagram(String spec, Dialect dialect) throws IOException {
				URL dialectURL = new URL(service, dialect.name());
				URLConnection connection = dialectURL.openConnection();
				if (!(connection instanceof HttpURLConnection)) {
					throw new IllegalArgumentException("Not an HTTP(s) url: "+dialectURL);
				}
				
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				httpConnection.setRequestMethod("POST");
				httpConnection.setDoOutput(true);
				try (OutputStream out = connection.getOutputStream()) {
					out.write(spec.getBytes(StandardCharsets.UTF_8));
				}
				
				int responseCode = httpConnection.getResponseCode();
				if (responseCode == 200) {
					return DefaultConverter.INSTANCE.toString(httpConnection.getInputStream());
				}
				
				throw new IOException("HTTP Call to "+dialectURL+" has failed with response: "+responseCode+" "+httpConnection.getResponseMessage());
			}

			@Override
			public boolean isSupported(Dialect dialect) {
				try {
					URL dialectURL = new URL(service, dialect.name());
					URLConnection connection = dialectURL.openConnection();
					if (!(connection instanceof HttpURLConnection)) {
						throw new IllegalArgumentException("Not an HTTP(s) url: "+dialectURL);
					}				
					HttpURLConnection httpConnection = (HttpURLConnection) connection;				
					return httpConnection.getResponseCode() == 200; // GET OK indicates that dialect is supported. 
				} catch (Exception e) {
					throw new NasdanikaException(e);
				}
			}
			
		};
		
	}
	
	/**
	 * Generates a PlantUML diagram with an image map from text definition. The definition shall not contain start and end tags.
	 * @return
	 * @throws IOException 
	 */
	String generateDiagram(String spec, Dialect dialect) throws Exception;
			
	default String generateUmlDiagram(String spec) throws Exception {
		return generateDiagram(spec, Dialect.UML);
	}
		
	default String generateWireframeDiagram(String spec) throws Exception {
		return generateDiagram(spec, Dialect.SALT);
	}
	
	default String generateGanttDiagram(String spec) throws Exception {
		return generateDiagram(spec, Dialect.GANTT);
	}
	
	default String generateMindmapDiagram(String spec) throws Exception {
		return generateDiagram(spec, Dialect.MINDMAP);
	}
	
	default String generateWbsDiagram(String spec) throws Exception {
		return generateDiagram(spec, Dialect.WBS);
	}
	
	default String generateDrawioDiagram(String spec) throws Exception {
		return generateDiagram(spec, Dialect.DRAWIO);
	}
	
	/**
	 * Creates a diagram generator facading this one and cacheing generated diagrams by dialect and diagram text SHA-256 digest.
	 * @param container
	 * @param progressMonitor
	 * @return
	 */
	default DiagramGenerator cachingDiagramGenerator(Container<String> container, ProgressMonitor progressMonitor) {
		
		return new DiagramGenerator() {
			
			private int hits;
			private int misses;

			@Override
			public String generateDiagram(String spec, Dialect dialect) throws Exception {
				String cachePath = dialect.name() + "/" + Hex.encodeHexString(MessageDigest.getInstance("SHA-256").digest(spec.getBytes(StandardCharsets.UTF_8))) + ".html";
				Object ret = container.find(cachePath, progressMonitor);
				if (ret instanceof String) {
					++hits;
					return (String) ret;
				}
				String diagram = DiagramGenerator.this.generateDiagram(spec, dialect);
				container.put(cachePath, diagram, progressMonitor);
				++misses;
				return diagram;
			}
			
			@Override
			public String toString() {
				return super.toString() + " hits: " + hits + ", misses: " + misses;
			}

			@Override
			public boolean isSupported(Dialect dialect) {
				return DiagramGenerator.this.isSupported(dialect);
			}
			
		};
	}
	
	@Override
	default DiagramGenerator compose(DiagramGenerator other) {
		return new DiagramGenerator() {

			@Override
			public boolean isSupported(Dialect dialect) {
				return DiagramGenerator.this.isSupported(dialect) || other.isSupported(dialect);
			}

			@Override
			public String generateDiagram(String spec, Dialect dialect) throws Exception {
				return (DiagramGenerator.this.isSupported(dialect) ? DiagramGenerator.this : other).generateDiagram(spec, dialect);
			}
			
		};
	}
	
}
