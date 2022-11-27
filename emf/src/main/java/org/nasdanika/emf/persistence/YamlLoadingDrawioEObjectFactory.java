package org.nasdanika.emf.persistence;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.common.Status;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.DrawioEObjectFactory;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.MarkerImpl;
import org.nasdanika.persistence.ObjectLoader;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.yaml.snakeyaml.error.MarkedYAMLException;

public abstract class YamlLoadingDrawioEObjectFactory<T extends EObject> extends DrawioEObjectFactory<T> {
	
	private static final String EXPR_PREFIX = "expr/";
	private static final String PROPERTIES_PREFIX = "properties/";

	protected abstract ObjectLoader getLoader();
	
	protected Context getContext() {
		return Context.EMPTY_CONTEXT;
	}
	
	protected Context createDiagramElementContext(ProcessorConfig<T> config) {
		MutableContext elementContext = getContext().fork();
		PropertyComputer propertyComputer = new PropertyComputer() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <T> T compute(Context context, String key, String path, Class<T> type) {
				if (type == null || type.isAssignableFrom(String.class)) {
					if (Util.isBlank(path)) {
						return null;
					}
					
					// page name
					if (config.getElement() instanceof Page) {
						Page page = (Page) config.getElement();
						if ("name".equals(path)) {
							return (T) page.getName();
						}
						if ("id".equals(path)) {
							return (T) page.getId();
						}
					} else if (config.getElement() instanceof ModelElement) {
						ModelElement modelElement = (ModelElement) config.getElement();

						if ("id".equals(path)) {
							return (T) modelElement.getId();
						}

						if ("label".equals(path)) {
							return (T) modelElement.getLabel();
						}

						if ("link".equals(path)) {
							return (T) modelElement.getLink();
						}

						if ("tooltip".equals(path)) {
							return (T) modelElement.getTooltip();
						}

						if (path.startsWith(PROPERTIES_PREFIX)) {							
							return (T) modelElement.getProperty(path.substring(PROPERTIES_PREFIX.length()));
						}

					}
					
					if (path.startsWith(EXPR_PREFIX)) {
						String expr = path.substring(EXPR_PREFIX.length());
						if (!org.nasdanika.common.Util.isBlank(expr)) {
							ExpressionParser parser = new SpelExpressionParser();
							Expression exp = parser.parseExpression(expr);
							EvaluationContext evaluationContext = createEvaluationContext();
							Object result = exp.getValue(evaluationContext, config.getElement());
							return result == null ? null : (T) String.valueOf(result);
						}								
					}
				}
				return null;
			}
		};
		
		elementContext.put("diagram-element", propertyComputer);
		return elementContext;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected T load(String spec, URI specBase, ProcessorConfig<T> config, ProgressMonitor progressMonitor) {
		try {
			Object data = getLoader().loadYaml(spec, specBase, progressMonitor);
			if (data instanceof SupplierFactory) {
				return (T) Util.call(((SupplierFactory<EObject>) data).create(createDiagramElementContext(config)), progressMonitor, this::onDiagnostic);
			} 
			
			if (data instanceof EObject) {
				return (T) data;
			}
			
			throw new NasdanikaException("Not an instance of EObject or SupplierFactory: " + data);
		} catch (MarkedYAMLException e) {
			throw new ConfigurationException(e.getMessage(), e, new MarkerImpl(String.valueOf(specBase), e.getProblemMark()));
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NasdanikaException(e);
		}
	}
	
	/**
	 * Called by the supplier factory.
	 * @param diagnostic
	 */
	protected void onDiagnostic(org.nasdanika.common.Diagnostic diagnostic) {
		if (diagnostic.getStatus() == Status.FAIL || diagnostic.getStatus() == Status.ERROR) {
			System.err.println("***********************");
			System.err.println("*      Diagnostic     *");
			System.err.println("***********************");
			diagnostic.dump(System.err, 4, Status.FAIL, Status.ERROR);
		}
		if (diagnostic.getStatus() != Status.SUCCESS) {
			throw new DiagnosticException(diagnostic);
		};
	}

}
