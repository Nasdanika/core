package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CompoundCommandFactory implements CommandFactory {

	private String name;
	private List<CommandFactory> elements = new ArrayList<>();

	public CompoundCommandFactory(String name, Collection<CommandFactory> elements) {
		this.name = name;
		this.elements.addAll(elements);
	}
	
	@SafeVarargs
	public CompoundCommandFactory(String name, CommandFactory... elements) {
		this(name, Arrays.asList(elements));
	}

	@Override
	public Command create(Context context) throws Exception {
		CompoundCommand ret = new CompoundCommand(name);
		for (CommandFactory e: elements) {
			ret.add(e.create(context));
		}
		return ret;
	}
	
	public void add(CommandFactory element) {
		elements.add(element);
	}

}
