package org.nasdanika.drawio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.drawio.DrawioResourceFactory.ElementEntry;
import org.nasdanika.drawio.DrawioResourceFactory.UpdateAdapter;
import org.xml.sax.SAXException;

/**
 * Loads EMF classes from Drawio documents. 
 * Saving is supported via {@link UpdateAdapter}. All contents is iterated and for each element with UpdateAdapter the adapter's update method is invoked.
 * The load logic is responsible for attaching adapters to model elements. 
 * @author Pavel
 *
 */
abstract class DrawioResource<T> extends ResourceImpl {
	
	protected Document document;
	private ConnectionBase connectionBase;
	
	public DrawioResource(URI uri, ConnectionBase connectionBase) {
		super(uri);
		this.connectionBase = connectionBase;
	}

	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		try {
			document = Document.load(inputStream, getURI());
			ElementEntry<T> docEntry = document.accept(this::createEntry, connectionBase);
			resolve(document, docEntry, e -> resolveSemanticElement(e, document, docEntry));			
		} catch (ParserConfigurationException | SAXException e) {
			throw new NasdanikaException(e);
		}
	}
	
	/**
	 * Finds semantic element by recursively traversing entries.
	 * @param element
	 * @param elementEntry
	 * @return
	 */
	protected void resolve(Element element, ElementEntry<T> elementEntry, Function<Predicate<Element>,T> resolver) {
		resolve(element, elementEntry.getSemanticElement(), elementEntry.getChildEntries(), resolver);		
		for (Entry<Element, ElementEntry<T>> ee: elementEntry.getChildEntries().entrySet()) {
			resolve(ee.getKey(), ee.getValue(), resolver);
		}
	}
	
	protected abstract void resolve(
			Element element, 
			T semanticElement, 
			Map<Element,ElementEntry<T>> childEntries, 
			Function<Predicate<Element>, T> resolver);
	
	/**
	 * Finds semantic element by recursively traversing entries.
	 * @param element
	 * @param elementEntry
	 * @return
	 */
	protected T resolveSemanticElement(Predicate<Element> predicate, Element element, ElementEntry<T> elementEntry) {
		if (predicate.test(element)) {
			return elementEntry.getSemanticElement();
		}
		for (Entry<Element, ElementEntry<T>> ee: elementEntry.getChildEntries().entrySet()) {
			T ret = resolveSemanticElement(predicate, ee.getKey(), ee.getValue());
			if (ret != null) {
				return ret;
			}
		}
		return null;
	}
	
	protected abstract ElementEntry<T> createEntry(Element element, Map<Element, ElementEntry<T>> childMappings);

	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		if (document == null) {
			try {
				document = Document.create(false, getURI());
			} catch (ParserConfigurationException e) {
				throw new NasdanikaException(e);
			}
		}
		update(document);
		try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
			try {
				writer.write(document.save(null));
			} catch (TransformerException e) {
				throw new NasdanikaException(e);
			}
		}
	}

	protected void update(Document document) {
		TreeIterator<EObject> cit = getAllContents();
		while (cit.hasNext()) {
			Adapter adapter = EcoreUtil.getRegisteredAdapter(cit.next(), UpdateAdapter.class);
			if (adapter instanceof UpdateAdapter) {
				((UpdateAdapter) adapter).update(document);
			}
		}
	};
	
}