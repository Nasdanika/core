package org.nasdanika.common.persistence;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.representer.Representer;
import org.yaml.snakeyaml.resolver.Resolver;

/**
 * Snake YAML {@link Constructor} which creates {@link MarkedArrayList} and {@link MarkedLinkedHashMap} which allows to obtain locations of of elements in the source file using {@link Marker}s.
 * @author Pavel Vlasov
 *
 */
public class MarkingYamlConstructor extends Constructor {
	
	private String location;

	public MarkingYamlConstructor(String location) {
		this.location = location;
	}
	
	@Override
	protected List<? extends Object> constructSequence(SequenceNode node) {
		MarkedArrayList<? extends Object> ret = (MarkedArrayList<? extends Object>) super.constructSequence(node);
		node.getValue().forEach(n -> ret.getElementMarkers().add(Collections.singletonList(new MarkerImpl(location, n.getStartMark()))));
		Iterator<? extends Object> eit = ret.iterator();
		Iterator<List<? extends Marker>> mit = ret.getElementMarkers().iterator();
		while (eit.hasNext() && mit.hasNext()) {
			Object e = eit.next();
			List<? extends Marker> ml = mit.next();
			if (e instanceof Markable) {
				((Markable) e).mark(ml);
			}
		}
		return ret;
	}
	
	@Override
	protected List<Object> newList(SequenceNode node) {
		return new MarkedArrayList<Object>();
	}
				
	@Override
	protected Map<Object, Object> constructMapping(MappingNode node) {
		MarkedLinkedHashMap<Object, Object> ret = (MarkedLinkedHashMap<Object, Object>) super.constructMapping(node);
		node.getValue()
			.stream()
			.map(NodeTuple::getKeyNode)
			.filter(n -> n instanceof ScalarNode)
			.forEach(keyNode -> ret.markEntry(((ScalarNode) keyNode).getValue(), new MarkerImpl(location, keyNode.getStartMark())));
		
		for (Entry<Object, Object> e: ret.entrySet()) {
			if (e.getValue() instanceof Markable) {
				((Markable) e.getValue()).mark(ret.getEntryMarkers(e.getKey()));
			}
		}
		return ret;
	}
	
	@Override
	protected Map<Object, Object> newMap(MappingNode node) {
		return new MarkedLinkedHashMap<Object, Object>();
	}					
		
	/**
	 * @return Yaml which uses this {@link MarkingYamlConstructor} to create collections with markers.
	 */
	public static Yaml createMarkingYaml(String location) {
		LoaderOptions loaderOptions = new LoaderOptions();
		loaderOptions.setAllowDuplicateKeys(false);
		return new Yaml(new MarkingYamlConstructor(location), new Representer(), new DumperOptions(), loaderOptions, new Resolver());
	}

}
