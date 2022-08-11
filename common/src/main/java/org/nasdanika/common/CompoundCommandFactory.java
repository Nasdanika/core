package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class CompoundCommandFactory implements CommandFactory {

	protected String name;
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
	public Command create(Context context) {
		CompoundCommand ret = createCompoundCommand(context);
		for (CommandFactory e: elements) {
			ret.add(e.create(context));
		}
		return ret;
	}

	/**
	 * Override to customize {@link CompoundCommand} to which sub-commands will be added.
	 * @param context
	 * @return
	 */
	protected CompoundCommand createCompoundCommand(Context context) {
		return new CompoundCommand(name, context.get(ExecutorService.class));
	}
	
	public void add(CommandFactory element) {
		elements.add(element);
	}

}
