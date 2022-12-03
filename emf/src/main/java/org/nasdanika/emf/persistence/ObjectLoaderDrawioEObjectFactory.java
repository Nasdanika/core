package org.nasdanika.emf.persistence;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.jsoup.Jsoup;
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
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.ncore.Marked;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.MarkerImpl;
import org.nasdanika.persistence.ObjectLoader;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.yaml.snakeyaml.error.MarkedYAMLException;

public abstract class ObjectLoaderDrawioEObjectFactory<T extends EObject> extends DrawioEObjectFactory<T> {
	
	private static final String EXPR_PREFIX = "expr/";
	private static final String PROPERTIES_PREFIX = "properties/";
	
	protected Context getContext() {
		return Context.EMPTY_CONTEXT;
	}
	
	protected MarkerFactory getMarkerFactory() {
		return MarkerFactory.INSTANCE;
	}
	
	@Override
	protected T createSemanticElement(ProcessorConfig<T> config, ProgressMonitor progressMonitor) {
		T semanticElement = super.createSemanticElement(config, progressMonitor);
		Element element = config.getElement();
		URI baseURI = getBaseURI();
		if (baseURI != null && semanticElement instanceof Marked) {
			org.nasdanika.ncore.Marker marker = getMarkerFactory().createMarker(EXPR_PREFIX, progressMonitor);
			marker.setLocation(baseURI.toString());
			marker.setPosition(getMarkerPosition(element));
			((Marked) semanticElement).getMarkers().add(marker);
		}
		
		if (element instanceof ModelElement) {
			ModelElement modelElement = (ModelElement) element;
			String tooltip = modelElement.getTooltip();
			if (!Util.isBlank(tooltip) && semanticElement instanceof org.nasdanika.ncore.ModelElement && !semanticElement.eIsSet(NcorePackage.Literals.MODEL_ELEMENT__DESCRIPTION)) {
				((org.nasdanika.ncore.ModelElement) semanticElement).setDescription(tooltip);				
			}
			
			String label = modelElement.getLabel();
			if (!Util.isBlank(label) && semanticElement instanceof NamedElement && !semanticElement.eIsSet(NcorePackage.Literals.NAMED_ELEMENT__NAME)) {
				((NamedElement) semanticElement).setName(label);				
			}	
			
			if (semanticElement instanceof org.nasdanika.ncore.ModelElement) {
				String semanticUuid = ((org.nasdanika.ncore.ModelElement) semanticElement).getUuid();
				if (!Util.isBlank(semanticUuid)) {
					modelElement.setProperty("semantic-uuid", semanticUuid);
				}
			}
		}
		
		return semanticElement;
	}
	
	protected String getMarkerPosition(Element element) {
		if (element instanceof Page) {
			return "name: " + ((Page) element).getName() + ", id: " + ((Page) element).getId();
		} else if (element instanceof ModelElement) {
			StringBuilder positionBuilder = new StringBuilder();
			ModelElement modelElement = (ModelElement) element;
			Page page = modelElement.getModel().getPage();
			positionBuilder.append("page-name: " + page.getName() + ", page-id: " + page.getId());
			String label = modelElement.getLabel();
			if (!Util.isBlank(label)) {
				positionBuilder.append(", label: "+ label);
			}
			String id = modelElement.getId();
			if (!Util.isBlank(id)) {
				positionBuilder.append(", id:" + id);
			}
			return positionBuilder.toString();
		}		
		return null;
	}
	
	protected Context createDiagramElementContext(ProcessorConfig<T> config) {
		MutableContext elementContext = getContext().fork();
		PropertyComputer propertyComputer = new PropertyComputer() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <PT> PT compute(Context context, String key, String path, Class<PT> type) {
				if (type == null || type.isAssignableFrom(String.class)) {
					if (Util.isBlank(path)) {
						return null;
					}
					
					// page name
					if (config.getElement() instanceof Page) {
						Page page = (Page) config.getElement();
						if ("name".equals(path)) {
							return (PT) page.getName();
						}
						if ("id".equals(path)) {
							return (PT) page.getId();
						}
					} else if (config.getElement() instanceof ModelElement) {
						ModelElement modelElement = (ModelElement) config.getElement();

						if ("id".equals(path)) {
							return (PT) modelElement.getId();
						}

						String label = modelElement.getLabel();
						if ("label".equals(path)) {
							return (PT) label;
						}

						if ("label-text".equals(path)) { // Plain text label							
							return (PT) (Util.isBlank(label) ? label : Jsoup.parse(label).text());
						}

						if ("link".equals(path)) {
							return (PT) modelElement.getLink();
						}

						if ("tooltip".equals(path)) {
							return (PT) modelElement.getTooltip();
						}

						if (path.startsWith(PROPERTIES_PREFIX)) {							
							return (PT) modelElement.getProperty(path.substring(PROPERTIES_PREFIX.length()));
						}

					}
					
					if (path.startsWith(EXPR_PREFIX)) {
						String expr = path.substring(EXPR_PREFIX.length());
						if (!org.nasdanika.common.Util.isBlank(expr)) {
							ExpressionParser parser = new SpelExpressionParser();
							Expression exp = parser.parseExpression(expr);
							EvaluationContext evaluationContext = createEvaluationContext();
							evaluationContext.setVariable("context", context);
							Object result = exp.getValue(evaluationContext, config.getElement());
							return result == null ? null : (PT) String.valueOf(result);
						}								
					}
				}
				return null;
			}
		};
		
		elementContext.put("diagram-element", propertyComputer);
		return elementContext;
	}
	
	/**
	 * Specification format. yaml or json. If not specified defaults to YAML unless the specBase URI has .json extension. 
	 * @return
	 */
	protected String getSpecFormatPropertyName() {
		return "spec-format";
	}		
	
	protected abstract ObjectLoader getLoader();
	
	@SuppressWarnings("unchecked")
	@Override
	protected T load(String spec, URI specBase, ProcessorConfig<T> config, ProgressMonitor progressMonitor) {
		try {
			boolean isYaml = true;
			String specFormat = getPropertyValue(config.getElement(), getSpecFormatPropertyName());
			if (Util.isBlank(specFormat)) {
				if (specBase != null) {
					String lastSegment = specBase.lastSegment();
					if (!Util.isBlank(lastSegment) && lastSegment.toLowerCase().endsWith(".json")) {
						isYaml = false;
					}
				}
			} else {
				isYaml = "yaml".equals(specFormat);
			}
			Object data = isYaml ? getLoader().loadYaml(spec, specBase, progressMonitor) : getLoader().loadJsonObject(spec, specBase, progressMonitor);
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
