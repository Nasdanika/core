package org.nasdanika.emf.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.URIEncodable;
import org.nasdanika.ncore.NcoreFactory;

/**
 * Loads text files as resources with a single {@link org.nasdanika.ncore.String} object.
 * @author Pavel
 */
public class TextResourceFactory extends ResourceFactoryImpl {
	
	private class TextResource extends ResourceImpl implements URIEncodable {
		
		TextResource(URI uri) {
			super(uri);
		}
		
		public void load(java.util.Map<?,?> options) throws IOException {
			if (isDataURI(getURI())) {
				String uriStr = getURI().toString();
				uriStr = uriStr.substring("data:text,".length());				
				uriStr = URLDecoder.decode(uriStr, StandardCharsets.UTF_8);
				org.nasdanika.ncore.String str = NcoreFactory.eINSTANCE.createString();
				str.setValue(uriStr);
				getContents().clear();
				getContents().add(str);
			} else {
				super.load(options);
			}			
		};
		
		@Override
		protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
			String text = DefaultConverter.INSTANCE.toString(inputStream);
			org.nasdanika.ncore.String str = NcoreFactory.eINSTANCE.createString();
			str.setValue(text);
			getContents().clear();
			getContents().add(str);
		}

		@Override
		public URI encode() {
			StringBuilder payloadBuilder = new StringBuilder();
			for (EObject eObj: getContents()) {
				if (eObj instanceof org.nasdanika.ncore.String) {
					String text = ((org.nasdanika.ncore.String) eObj).getValue();
					if (text != null) {
						payloadBuilder.append(text);
					}
				} else {
					throw new UnsupportedOperationException("Unsupported contents: " + eObj);
				}
			}
			String uriStr = "data:text," + URLEncoder.encode(payloadBuilder.toString(), StandardCharsets.UTF_8);
			return URI.createURI(uriStr);
		}

		@Override
		protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
			try {
				for (EObject eObj: getContents()) {
					if (eObj instanceof org.nasdanika.ncore.String) {
						String text = ((org.nasdanika.ncore.String) eObj).getValue();
						if (text != null) {
							outputStream.write(text.getBytes(StandardCharsets.UTF_8));
						}
					} else {
						throw new UnsupportedOperationException("Unsupported contents: " + eObj);
					}
				}
			} finally {
				outputStream.close();
			}
		}
		
	}
	
	@Override
	public Resource createResource(URI uri) {
		return new TextResource(uri);
	}
	
	/**
	 * @param uri
	 * @return true if the argument URI is drawio data URI, i.e. is starts with data:drawio[;base64],
	 */
	public static boolean isDataURI(URI uri) {
		return uri != null && "data".equals(uri.scheme()) && uri.toString().startsWith("data:text,");
	}	

}
