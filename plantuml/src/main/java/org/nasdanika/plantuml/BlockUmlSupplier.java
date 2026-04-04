package org.nasdanika.plantuml;

import java.util.List;
import java.util.function.Supplier;

import net.sourceforge.plantuml.BlockUml;

/**
 * Supplier of PlantUML BlockUml objects.
 * Subcommands can bind to this type.
 */
public interface BlockUmlSupplier extends Supplier<List<BlockUml>> {

}
