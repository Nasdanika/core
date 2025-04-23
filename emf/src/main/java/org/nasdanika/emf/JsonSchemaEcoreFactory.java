package org.nasdanika.emf;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.ncore.util.NcoreUtil;

/**
 * Generates {@link EClassifier}s from JSON schema definitions.
 */
public class JsonSchemaEcoreFactory {
	
	private static final String CONST_KEY = "const";
	private static final String DEFAULT_КЕY = "default";
	private static final String ITEMS_KEY = "items";
	private static final String TYPE_KEY = "type";
	private static final String REQUIRED_KEY = "required";
	private static final String DESCRIPTION_KEY = "description";
	private static final String DEFS_PREFIX = "#/$defs/";
	private static final String REF_KEY = "$ref";
	private static final String ONE_OF_KEY = "oneOf";
	private static final String ANY_OF_KEY = "anyOf";
	private static final String PROPERTIES_KEY = "properties";
	private static final String ENUM_KEY = "enum";
	private JSONObject defs;
	private EPackage ePackage;
	private EcoreFactory factory = EcoreFactory.eINSTANCE;
	private File docDir;

	/**
	 * 
	 * @param ePackage
	 * @param defs
	 * @param docBase Documentation directory
	 */
	public JsonSchemaEcoreFactory(EPackage ePackage, JSONObject defs, File docDir) {
		this.ePackage = ePackage;
		this.defs = defs;
		this.docDir = docDir;
		document(null, ePackage, "package-summary.md");			
	}
	
	@org.nasdanika.common.Transformer.Factory(type=String.class)
	public EClassifier createEClassifier(
			String key,
			boolean parallel,
			BiConsumer<String, BiConsumer<EClassifier,ProgressMonitor>> eClassifierProvider, 
			Consumer<BiConsumer<Map<String, EClassifier>,ProgressMonitor>> registryProvider,
			ProgressMonitor progressMonitor) {
		
		JSONObject def = defs.getJSONObject(key);
		
		Collection<Object> required = new HashSet<>();
		if (def.has(REQUIRED_KEY)) {
			JSONArray jRequired = def.getJSONArray(REQUIRED_KEY);
			jRequired.forEach(required::add);
		}
		
		EClassifier eClassifier;
		if (def.has(ENUM_KEY)) {
			EEnum eEnum = factory.createEEnum();
			JSONArray literals = def.getJSONArray(ENUM_KEY);
			for (int i = 0; i < literals.length(); ++i) {
				String literalString = literals.getString(i);
				EEnumLiteral literal = factory.createEEnumLiteral();
				literal.setLiteral(literalString);
				literal.setName(literalString);
				literal.setValue(i);
				eEnum.getELiterals().add(literal);
			}
			eClassifier = eEnum;
		} else {
			EClass eClass = factory.createEClass();
			if (def.has(PROPERTIES_KEY)) {
				JSONObject properties = def.getJSONObject(PROPERTIES_KEY);
				for (String pName: properties.keySet()) {
					JSONObject featureSpec = properties.getJSONObject(pName);
					EStructuralFeature feature = generateFeature(
						pName, 
						featureSpec,
						required.contains(pName),
						eClassifierProvider,
						registryProvider,
						progressMonitor);

					EClassifier featureType = feature.getEType();
					if (featureType != null && Util.isBlank(featureType.getName())) {
						featureType.setName(key + StringUtils.capitalize(pName));
					}
					document(featureSpec, feature, key + File.separator + pName + ".md");
					NcoreUtil.setNasdanikaAnnotationDetail(feature, "generated", "true");
					eClass.getEStructuralFeatures().add(feature);
				}
			}
			if (def.has(ONE_OF_KEY) ) {
				JSONArray subClasses = def.getJSONArray(ONE_OF_KEY);
				for (int i = 0; i < subClasses.length(); ++i) {
					JSONObject subClass = subClasses.getJSONObject(i);
					if (subClass.has(REF_KEY)) {
						String refVal = subClass.getString(REF_KEY);
						if (refVal.startsWith(DEFS_PREFIX)) {
							eClassifierProvider.accept(refVal.substring(DEFS_PREFIX.length()), (refTarget, pm) -> {
								((EClass) refTarget).getESuperTypes().add(eClass);
							});
						}
					}
				}
			}
			if (def.has(ANY_OF_KEY) ) {
				JSONArray subClasses = def.getJSONArray(ANY_OF_KEY);
				for (int i = 0; i < subClasses.length(); ++i) {
					JSONObject subClass = subClasses.getJSONObject(i);
					if (subClass.has(REF_KEY)) {
						String refVal = subClass.getString(REF_KEY);
						if (refVal.startsWith(DEFS_PREFIX)) {
							eClassifierProvider.accept(refVal.substring(DEFS_PREFIX.length()), (refTarget, pm) -> {
								((EClass) refTarget).getESuperTypes().add(eClass);
							});
						}
					}
				}
			}
			eClassifier = eClass;
		}
		eClassifier.setName(key);
		ePackage.getEClassifiers().add(eClassifier);
						
		document(def, eClassifier, eClassifier.getName() + ".md");			
		
		NcoreUtil.setNasdanikaAnnotationDetail(eClassifier, "generated", "true");
		return eClassifier;		
	}
	
	protected String loadDoc(String docResource) {
		if (docDir == null) {
			return null;
		}
		File docFile = new File(docDir, docResource);
		if (!docFile.isFile()) {
			return null;
		}
		try (InputStream in = new FileInputStream(docFile)) {			
			return DefaultConverter.INSTANCE.toString(in);
		} catch (Exception e) {
			return "Error - failed to load documentation from " + docFile.getAbsolutePath() + ": " + e;
		}		
	}

	protected void document(JSONObject spec, EModelElement eModelElement, String docResource) {
		StringBuilder docBuilder = new StringBuilder();
		if (spec != null) {
			if (spec.has(DESCRIPTION_KEY)) {
				docBuilder
					.append(spec.getString(DESCRIPTION_KEY))
					.append(System.lineSeparator())
					.append(System.lineSeparator());
			}
		}
		
		String doc = loadDoc(docResource);
		if (!Util.isBlank(doc)) {
			docBuilder
			.append(doc)
			.append(System.lineSeparator())
			.append(System.lineSeparator());						
		}
		
		if (spec != null) {
			docBuilder.append(
					"""
					## Specification
					
					```json
					%s
					```
									
					""".formatted(spec.toString(2)));
		}
				
		EcoreUtil.setDocumentation(eModelElement, docBuilder.toString());
	}	
	
	protected void setType(
			JSONObject spec, 
			EStructuralFeature feature,
			BiConsumer<String, BiConsumer<EClassifier,ProgressMonitor>> eClassifierProvider) {
		if (spec.has(TYPE_KEY)) {
			String typeName = spec.getString(TYPE_KEY);			
			switch (typeName) {
			case "array":
				if (spec != null && spec.has(ITEMS_KEY)) {
					JSONObject itemsSpec = spec.getJSONObject(ITEMS_KEY);
					setType(itemsSpec, feature, eClassifierProvider);
					feature.setUpperBound(-1);
					feature.setLowerBound(0);
				}
				break;
			case "string":
				feature.setEType(EcorePackage.Literals.ESTRING);
				break;
			case "integer":
				feature.setEType(EcorePackage.Literals.EINTEGER_OBJECT);
				break;
			case "number":
				feature.setEType(EcorePackage.Literals.EDOUBLE_OBJECT);
				break;
			case "object":
				feature.setEType(EcorePackage.Literals.EOBJECT);
				break;
			case "boolean":
				feature.setEType(EcorePackage.Literals.EBOOLEAN_OBJECT);
				break;
			case "null": 
				feature.setLowerBound(0);
				break;				
			};
		} else if (spec.has(REF_KEY)) {
			String refVal = spec.getString(REF_KEY);
			if (refVal.startsWith(DEFS_PREFIX)) {
				eClassifierProvider.accept(refVal.substring(DEFS_PREFIX.length()), (refType, pm) -> {
					feature.setEType(refType);
				});
			}
		} else if (spec.has(ANY_OF_KEY)) {
			JSONArray anyOf = spec.getJSONArray(ANY_OF_KEY);
			Collection<String> refs = new ArrayList<>();
			if (feature instanceof EReference) {
				// Checking for multiple $ref to create a synthetic superclass
				for (int i = 0; i < anyOf.length(); ++i) {
					JSONObject anyOfElement = anyOf.getJSONObject(i);
					if (anyOfElement.has(REF_KEY)) {
						refs.add(anyOfElement.getString(REF_KEY));
					}
				}
			}
			if (refs.size() > 1) {
				// Synthetic super-type
				EClass featureType = factory.createEClass();
				ePackage.getEClassifiers().add(featureType);
				feature.setEType(featureType);
				for (String refVal: refs) {
					if (refVal.startsWith(DEFS_PREFIX)) {
						eClassifierProvider.accept(refVal.substring(DEFS_PREFIX.length()), (refType, pm) -> {
							((EClass) refType).getESuperTypes().add(featureType);
						});
					}
				}								
			} else {				
				for (int i = 0; i < anyOf.length(); ++i) {
					JSONObject anyOfElement = anyOf.getJSONObject(i);
					setType(anyOfElement, feature, eClassifierProvider);
				}
			}
		} else if (spec.has(CONST_KEY)) {
			Object constVal = spec.get(CONST_KEY);
			if (constVal instanceof Integer) {
				feature.setEType(EcorePackage.Literals.EINTEGER_OBJECT);
			} else if (constVal instanceof Number) {
				feature.setEType(EcorePackage.Literals.EDOUBLE_OBJECT);				
			} else if (constVal instanceof Boolean) {
				feature.setEType(EcorePackage.Literals.EBOOLEAN_OBJECT);				
			} else {
				feature.setEType(EcorePackage.Literals.ESTRING); // Default				
			}			
		}
		
		if (spec.has(DEFAULT_КЕY) && !spec.isNull(DEFAULT_КЕY)) {			
			Object defaultValue = spec.get(DEFAULT_КЕY);
			if (defaultValue != null) {
				feature.setDefaultValueLiteral(defaultValue.toString());
			}
		}
	}
	
	protected EStructuralFeature generateFeature(
			String name,
			JSONObject spec,
			boolean required,
			BiConsumer<String, BiConsumer<EClassifier,ProgressMonitor>> eClassifierProvider, 
			Consumer<BiConsumer<Map<String, EClassifier>,ProgressMonitor>> registryProvider,
			ProgressMonitor progressMonitor) {

		EStructuralFeature feature;
		if (isReference(spec)) {
			feature = generateReference(spec, eClassifierProvider, progressMonitor);
		} else {
			feature = generateAttribute(spec, progressMonitor);
		}
		feature.setName(name);
		if (required) {
			feature.setLowerBound(1);
		}
		
		setType(spec, feature, eClassifierProvider);
		
		return feature;
	}

	private EStructuralFeature generateReference(JSONObject spec,
			BiConsumer<String, BiConsumer<EClassifier, ProgressMonitor>> eClassifierProvider,
			ProgressMonitor progressMonitor) {		
		EReference ref = factory.createEReference();
		ref.setContainment(true);
		if (spec.has(REF_KEY)) {
			String refVal = spec.getString(REF_KEY);
			if (refVal.startsWith(DEFS_PREFIX)) {
				eClassifierProvider.accept(refVal.substring(DEFS_PREFIX.length()), (refType, pm) -> {
					ref.setEType(refType);
					ref.setLowerBound(1);
					ref.setUpperBound(1);
				});
			}
		}
		
		return ref;
	}

	private EStructuralFeature generateAttribute(JSONObject spec, ProgressMonitor progressMonitor) {
		EAttribute attr = factory.createEAttribute();
		
		return attr;
	}

	private boolean isReference(JSONObject spec) {
		if (spec.has(REF_KEY)) {
			String refVal = spec.getString(REF_KEY);
			if (refVal.startsWith(DEFS_PREFIX)) {
				String typeKey = refVal.substring(DEFS_PREFIX.length());
				if (defs.has(typeKey)) {
					JSONObject typeSpec = defs.getJSONObject(typeKey);
					return !typeSpec.has(ENUM_KEY);
				}
			}
			return true;
		}
		if (spec.has(ANY_OF_KEY)) {
			JSONArray anyOf = spec.getJSONArray(ANY_OF_KEY);			
			for (int i = 0; i < anyOf.length(); ++i) {
				if (isReference(anyOf.getJSONObject(i))) {
					return true;
				}
			}
		}
		
		EStructuralFeature testFeature = factory.createEAttribute();
		BiConsumer<String, BiConsumer<EClassifier, ProgressMonitor>> ecProvider = (typeName, setter) -> {
			setter.accept(EcorePackage.Literals.EOBJECT, new NullProgressMonitor());
		};
		setType(spec, testFeature, ecProvider);
		
		return testFeature.getEType() == EcorePackage.Literals.EOBJECT;
	}

}
