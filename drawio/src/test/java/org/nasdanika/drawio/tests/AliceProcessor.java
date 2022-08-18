package org.nasdanika.drawio.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Function;

import org.nasdanika.drawio.Node;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.OutgoingEndpoint;
import org.nasdanika.graph.processor.OutgoingHandler;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;

public class AliceProcessor extends BobHouseProcessor {
	
	@ProcessorElement
	private Node aliceNode;
	
	@OutgoingHandler("target.label == 'Bob'")
	private Function<String,String> replyToBob = request -> request + System.lineSeparator() + "[" + aliceNode.getLabel() + "] My name is " + aliceNode.getLabel() + ".";
	
	@OutgoingEndpoint("target.label == 'Bob'")
	private Function<String,String> bobEndpoint;
	
	public void talkToBob(String str) {
		bobEndpoint.apply("[" + aliceNode.getLabel() + "] Hello!");
	}
	
	
//	Optional<ProcessorInfo<Object>> aliceProcessorInfoOptional = registry.entrySet().stream().filter(e -> e.getKey() instanceof Node && "Alice".equals(((Node) e.getKey()).getLabel())).map(Map.Entry::getValue).findAny();
//	assertThat(aliceProcessorInfoOptional.isPresent()).isTrue();
//	ProcessorInfo<Object> aliceProcessorInfo = aliceProcessorInfoOptional.get();
//	NodeProcessorConfig<Object, Function<String, String>, Function<String, String>> aliceNodeProcessorConfig = (NodeProcessorConfig<Object, Function<String, String>, Function<String, String>>) aliceProcessorInfo.getConfig();
//	assertThat(aliceNodeProcessorConfig.getChildProcessorsInfo() == null || aliceNodeProcessorConfig.getChildProcessorsInfo().isEmpty()).isTrue();
//	assertThat(aliceNodeProcessorConfig.getIncomingEndpoints()).isEmpty();
//	assertThat(aliceNodeProcessorConfig.getIncomingHandlerConsumers()).isEmpty();
//	assertThat(aliceNodeProcessorConfig.getOutgoingEndpoints().size()).isEqualTo(1);
//	assertThat(aliceNodeProcessorConfig.getOutgoingHandlerConsumers().size()).isEqualTo(1);
//	
//	aliceNodeProcessorConfig.getOutgoingHandlerConsumers().forEach((connection, handlerConsumer) -> {
//		handlerConsumer.accept(request -> {
//			String myName = ((Node) connection.getSource()).getLabel();
//			return request + System.lineSeparator() + "[" + myName + "] My name is " + myName + ".";
//		});
//	});
//
//	for (Entry<org.nasdanika.graph.Connection, Function<String, String>> outcomingEndpoint: aliceNodeProcessorConfig.getOutgoingEndpoints().entrySet()) {
//		String dialog = outcomingEndpoint.getValue().apply("[" + ((Node) outcomingEndpoint.getKey().getSource()).getLabel() + "] Hello!");
//		System.out.println(dialog);
//	}
	

}
