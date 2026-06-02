package org.nasdanika.capability.emf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.common.Composable;
import org.nasdanika.common.Transformer;

/**
 * A pipeline stage in a resource contents transformation chain.
 *
 * <p>A {@code ResourceContentsFilter} transforms the contents of an EMF
 * {@link org.eclipse.emf.ecore.resource.Resource Resource}, 
 * operating on already-loaded model objects rather than on byte
 * streams. Filters are registered as capabilities matched by file-extension
 * sub-extensions and are composed into chains by the
 * {@link org.eclipse.emf.ecore.resource.Resource.Factory Resource.Factory}
 * implementation when a filename declares a multi-stage pipeline. For
 * example, a file named {@code my-product.pm.md} loads as Markdown through
 * the {@code .md} factory, then is transformed to the Product Management
 * model by the {@code ResourceContentsFilter} registered against the
 * {@code .pm} sub-extension. The loading chain is read right-to-left:
 * the rightmost extension is the source format, the leftmost is the final target.
 * 
 * The save chain is the reverse: edits to the target model are propagated back through the filters in left-to-right order, 
 * then written to the source format by the rightmost factory.
 *
 * <p>The name follows the Unix sense of "filter" - a pipeline stage that
 * reads input and produces output, composable into chains via the host
 * environment's grammar (here, the filename). It is not a predicate filter
 * in the {@code Stream.filter} sense; a {@code ResourceContentsFilter}
 * typically transforms between distinct model types rather than selecting
 * elements of the same type.
 *
 * <p>Implementations may be unidirectional (load-only) or bi-directional
 * (load and save). Bi-directional implementations support round-tripping
 * edits back to the source format, with format-specific concerns handled
 * by the implementation - for example, geometry-preserving back-projection
 * for diagram filters, where edits to the target model that correspond to
 * existing diagram elements preserve those elements' positions while new
 * elements receive geometry from an auto-layout engine such as ELK.
 *
 * <p>Filters can be implemented as declarative transformations in
 * <a href="https://github.com/Nasdanika-Models/nasdanika-semantic-mapping-language">NSML</a>
 * rather than as hand-written Java, reducing the cost of adding new
 * (source-format, target-model) pairs.
 * 
 * <p>Another option is to use {@link Transformer} and target classes with annotated methods.
 * Targets can be collected by the capability framework making the tranformation process
 * customizable declaratively by adding dependencie to pom.xml
 *
 * @see org.eclipse.emf.ecore.resource.Resource
 * @see org.eclipse.emf.ecore.resource.Resource.Factory
 */
public interface ResourceContentsFilter extends Composable<ResourceContentsFilter> {
	
	/**
	 * The order of this filter in the loading/saving chain, relative to other filters within the same qualifier position. 
	 * Filters with lower order values are applied before filters with higher order values during loading
	 * and in reverse order during saving.
	 * The order is significant when multiple filters are registered against the same qualifier position,
	 * as it determines the sequence of transformations applied to the contents.
	 * For example, if two filters are registered for the ".pm" extension with orders 0 and 100,
	 * the filter with order 1 will be applied first during loading, 
	 * transforming the contents before it is passed to the filter with order 100 which may enrich or validate the contents.
	 * The default order value is 0; override to specify a different order.
	 * Recommended order ranges - 0-999 for transformers which produce contents, 
	 * 1000-1999 for filters which remove contents - produce a projection (for example, stripping out elements that don't match a certain profile).
	 * 2000-2999 for filters which enrich contents with information not present in the source (for example, resolving cross-references against a live catalog or invoking an LLM to produce derived contents),
	 * 3000+ for validators which enrich contents with diagnostics without changing it.
	 */
	default int order() {
		return 0;
	};
	
	/**
     * Transforms the argument contents.
     *
     * <p>This is the <em>forward</em> direction of the pipeline: the argument
     * holds the previous stage's contents (closer to the original file
     * format), the result contents is closer to the final model). 
     * For a file named {@code my-product.pm.md}, the
     * {@code .pm} filter's {@code load} populates a Product Management
     * model target from a Markdown model.
     *
     * <p>The implementation may enrich the target with data fetched from
     * external systems during the transformation - for example, resolving
     * cross-references against a live catalog, looking up authoritative
     * definitions from a federated model, or invoking an LLM to produce
     * derived contents. The {@code source} filename does not change; only
     * the result reflects the enrichment.
     *
     * <p>Diagnostics produced during the transformation (mapping rule
     * failures, unresolved references, validation violations) are attached
     * to the resource via the standard EMF
     * {@link org.eclipse.emf.ecore.resource.Resource#getErrors() errors}
     * and {@link org.eclipse.emf.ecore.resource.Resource#getWarnings() warnings}
     * collections, with source-position information preserved where the
     * source resource exposed it.
     * 
     * <p>This implementation returns the argument contents unmodified, effectively making it a no-op in the load direction.
     * 
     * @param resource the resource being saved
     * @param contents the previous-stage contents, never {@code null}
     * @param options resource-load options, including any filter-specific
     *                configuration; may be {@code null}
     * @param qualifiers parts of the file name being processed. For example in a file adams.family.xlsx
     * there are 3 qualifiers - "adams", "family" and "xlsx". For loading qualifiers are processed right-to-left -
     * "xlsx" first, "family" second and "adams" third. For saving the order is opposite.
     * @param qualifierPosition zero-based starting from the right. 
     * For example in a file adams.family.xlsx there are 3 qualifiers - "adams", "family" and "xlsx" with positions 2, 1 and 0 respectively.
     * @throws IOException if the transformation cannot complete due to
     *                     access, parsing, or external-system failures
     *                     that cannot be reported as diagnostics on
     *                     {@code target}
     * @return the next-stage contents. The contents argument if the filter doesn't match 
     */
	default List<EObject> load(
			Resource resource, 
			List<EObject> contents, 
			String[] qualifiers,
			int qualifierPosition,
			Map<?, ?> options) throws IOException {
		
		return contents;
	}

    /**
     * Transforms the contents for eventual saving to the original file, if supported by this filter.
     * Reverse of loading - projecting model objects from the next loading stage into
     * the previous loading stage's format.
     * The <em>reverse</em> direction of the
     * pipeline: edits made to the target model are propagated back to the
     * source representation, where they can be written to the original
     * file by {@link org.eclipse.emf.ecore.resource.Resource#save}.
     *
     * <p>Not every filter supports the reverse direction. This implementation 
     * throws {@link UnsupportedOperationException}; override to support saving.
     *
     * <p>Where the reverse direction is supported, the implementation is
     * responsible for preserving format-specific information that the
     * target model does not carry. For diagram filters, this typically
     * means preserving the geometry of existing diagram elements while
     * producing new geometry (via {@code ELK} or another auto-layout) for
     * elements introduced by the target-model edit.
     * 
     * <p>This implementation returns the argument contents unmodified, effectively making it a no-op in the save direction.
     * 
     * @param resource the resource being saved
     * @param contents the next-stage contents, never {@code null}
     * @param qualifiers parts of the file name being processed. For example in a file adams.family.xlsx
     * there are 3 qualifiers - "adams", "family" and "xlsx". For loading qualifiers are processed right-to-left -
     * "xlsx" first, "family" second and "adams" third. For saving the order is opposite.
     * @param qualifierPosition zero-based starting from the right. 
     * For example in a file adams.family.xlsx there are 3 qualifiers - "adams", "family" and "xlsx" with positions 2, 1 and 0 respectively.
     * @param options resource-save options, including any filter-specific
     *                configuration; may be {@code null}
     * @throws IOException if the transformation cannot complete due to
     *                     access, parsing, or external-system failures
     *                     that cannot be reported as diagnostics on
     *                     {@code target}
     * @return the previous-stage contents. The contents argument if the filter doesn't match
     * 
     */	
	default List<EObject> save(
			Resource resource, 
			List<EObject> contents, 
			String[] qualifiers,
			int qualifierPosition,			
			Map<?, ?> options) throws IOException {
		
		return contents;
	}
	
	/**
	 * For loading calls this load first and then the other load. For saving calls the other save first and then this save.
	 */
	@Override
	default ResourceContentsFilter compose(ResourceContentsFilter other) {
		if (other == null) {
			return this;
		}
				
		return new ResourceContentsFilter() {
			
			@Override
			public List<EObject> load(
					Resource resource, 
					List<EObject> contents, 
					String[] qualifiers, 
					int qualifierPosition,
					Map<?, ?> options) throws IOException {
				
				List<EObject> thisResult = ResourceContentsFilter.this.load(resource, contents, qualifiers, qualifierPosition, options);
				return other.load(resource, thisResult, qualifiers, qualifierPosition, options);
			}
			
			@Override
			public List<EObject> save(
					Resource resource, 
					List<EObject> contents, 
					String[] qualifiers,
					int qualifierPosition, 
					Map<?, ?> options) throws IOException {
				
				List<EObject> otherResult = other.save(resource, contents, qualifiers, qualifierPosition, options);
				return ResourceContentsFilter.this.save(resource, otherResult, qualifiers, qualifierPosition, options);
			}
			
			@Override
			public int order() {
				return Math.min(ResourceContentsFilter.this.order(), other.order());
			}
			
		};
	}

}
