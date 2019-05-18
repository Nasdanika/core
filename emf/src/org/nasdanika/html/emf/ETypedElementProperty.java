package org.nasdanika.html.emf;

import java.util.Base64;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.html.app.Choice;
import org.nasdanika.html.app.Diagnostic;
import org.nasdanika.html.app.Property;

public abstract class ETypedElementProperty<T extends ETypedElement> implements Property {

	protected T typedElement;

	public ETypedElementProperty(T typedElement) {
		this.typedElement = typedElement;
	}
	
	protected abstract Object getValue(Object obj);

	@Override
	public Object getDisplayValue(Object obj) {
		Object value = getValue(obj);
		if (value == null) {
			return "";
		}
		
		if (value instanceof byte[]) {
			return Base64.getEncoder().encodeToString((byte[]) value);
		}
		
//		if (value instanceof Enumerator) {
//			Enumerator enumeratorValue = (Enumerator) value;
//			String ret = StringEscapeUtils.escapeHtml4(enumeratorValue.getLiteral());
//			EClassifier featureType = typedElement.getEType();
//			if (featureType instanceof EEnum) {
//				EEnum featureEnum = (EEnum) featureType;
//				EEnumLiteral enumLiteral = featureEnum.getEEnumLiteral(enumeratorValue.getName());
//				Object literalIcon = renderModelElementIcon(context, enumLiteral);
//				Tag literalDocumentationIcon = renderDocumentationIcon(context, enumLiteral, appConsumer, true);
//				return htmlFactory.fragment(literalIcon, " ", ret, literalDocumentationIcon);
//			}
//
//			return ret;
//		}

//		if (value instanceof Diagnostic) {
//			Diagnostic diagnostic = (Diagnostic) value;
//			Fragment rdf = htmlFactory.fragment();
//			switch (diagnostic.getSeverity()) {
//			case Diagnostic.ERROR:
//				rdf.content(htmlFactory.fontAwesome().webApplication(WebApplication.exclamation_circle).getTarget().bootstrap().text().color(Style.DANGER).style().margin().right("0.5em"));
//				break;
//			case Diagnostic.WARNING:
//				rdf.content(htmlFactory.fontAwesome().webApplication(WebApplication.exclamation_triangle).getTarget().bootstrap().text().color(Style.WARNING).style().margin().right("0.5em"));
//				break;
//			case Diagnostic.OK:
//				rdf.content(htmlFactory.fontAwesome().webApplication(WebApplication.check_square).getTarget().bootstrap().text().color(Style.SUCCESS).style().margin().right("0.5em"));
//				break;
//			case Diagnostic.INFO:
//				rdf.content(htmlFactory.fontAwesome().webApplication(WebApplication.check_circle_o).getTarget().bootstrap().text().color(Style.INFO));
//				break;				
//			}
//			if (!CoreUtil.isBlank(diagnostic.getMessage())) {
//				rdf.content(StringEscapeUtils.escapeHtml4(diagnostic.getMessage()));
//			}
//			if (diagnostic.getChildren().isEmpty()) {
//				return rdf;
//			}
//			if (diagnostic.getChildren().size() == 1 && CoreUtil.isBlank(diagnostic.getMessage())) {
//				return renderTypedElementValue(context, typedElement, diagnostic.getChildren().iterator().next(), appConsumer, false);
//			}
//			Tag ul = htmlFactory.tag(TagName.ul);
//			for (Diagnostic child: diagnostic.getChildren()) {
//				ul.content(htmlFactory.tag(TagName.li, renderTypedElementValue(context, typedElement, child, appConsumer, false)));
//			}
//			if (CoreUtil.isBlank(diagnostic.getMessage())) {
//				return ul;
//			}
//			rdf.content(ul);
//			return rdf;
//		}

//		if (value instanceof Date) {
//			Object formatAnnotation = getYamlRenderAnnotation(context, typedElement, RenderAnnotation.FORMAT);				
//			String format = null;
//			if (formatAnnotation instanceof String) {
//				format = (String) formatAnnotation;
//			} else if (formatAnnotation instanceof Map) {
//				format = (String) ((Map<String, Object>) formatAnnotation).get(forEditing ? "edit" : "display");
//			}
//			if (format == null) {
//				format = "yyyy-MM-dd"; // Default web format for dates.
//			}
//			SimpleDateFormat sdf = new SimpleDateFormat(format, getLocale(context));
//			return sdf.format((Date) value);
//			} else if (value instanceof Number) {
//			Object formatAnnotation = getYamlRenderAnnotation(context, typedElement, RenderAnnotation.FORMAT);				
//			String format = null;
//			if (formatAnnotation instanceof String) {
//				format = (String) formatAnnotation;
//			} else if (formatAnnotation instanceof Map) {
//				format = (String) ((Map<String, Object>) formatAnnotation).get(forEditing ? "edit" : "display");
//			}
//			if (format != null) {
//				DecimalFormat df = new DecimalFormat(format,  DecimalFormatSymbols.getInstance(getLocale(context)));
//				return df.format(value);
//			}
//		}	

		return isHTML() ? value.toString() : StringEscapeUtils.escapeHtml4(value.toString());
	}
	
	protected boolean isHTML() {
		return false;
	}

	@Override
	public Object getEditValue(Object obj) {
		// TODO - type-specific formatting
		return getValue(obj);
	}

	@Override
	public List<Choice> getChoices(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEditable(Object obj) {
		return false;
	}

	@Override
	public Diagnostic update(Object obj, Object originalValue, Object newValue) {
		throw new UnsupportedOperationException();
	}

}

