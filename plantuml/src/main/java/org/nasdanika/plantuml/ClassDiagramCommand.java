package org.nasdanika.plantuml;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.cli.CommandGroup;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.common.EModelElementSupplier;
import org.nasdanika.common.ProgressMonitor;

import net.sourceforge.plantuml.BlockUml;
import net.sourceforge.plantuml.abel.Entity;
import net.sourceforge.plantuml.abel.LeafType;
import net.sourceforge.plantuml.abel.Link;
import net.sourceforge.plantuml.classdiagram.ClassDiagram;
import net.sourceforge.plantuml.cucadiagram.Bodier;
import net.sourceforge.plantuml.decoration.LinkDecor;
import net.sourceforge.plantuml.decoration.LinkType;
import net.sourceforge.plantuml.klimt.creole.Display;
import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;

@Command(
		description = "Converts class diagrams to Ecore model elements",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "class")
@ParentCommands(BlockUmlSupplier.class)
public class ClassDiagramCommand extends CommandGroup implements EModelElementSupplier<EModelElement> {
	
	private static final String VOID = "void";

	private static final String UNNAMED = "unnamed";

	@ParentCommand
	BlockUmlSupplier blockUmlSupplier;

	@Override
	public Collection<EModelElement> getEObjects(ProgressMonitor progressMonitor) {
		return blockUmlSupplier.get().stream()
				.map(BlockUml::getDiagram)
				.filter(ClassDiagram.class::isInstance)
				.map(ClassDiagram.class::cast)
				.map(this::loadClassDiagram)
				.toList(); 
	}
	
	// Pattern for parsing member declarations like "name : Type" or "name : Type[*]"
	private static final Pattern FIELD_PATTERN = Pattern.compile("^\\s*([+\\-#~])?\\s*(.+?)\\s*:\\s*(.+?)\\s*$");
	
	private static final Pattern TYPE_NAME_PATTERN = Pattern.compile("(\\S+)\\s+(\\S+)");	
	
	// Pattern for parsing method declarations like "name(params) : ReturnType"
	private static final Pattern METHOD_PATTERN = Pattern.compile("^\\s*([+\\-#~])?\\s*(.+?)\\s*\\((.*)\\)\\s*(?::\\s*(.+?))?\\s*$");
	
	// Pattern for parsing multiplicity like "Type[0..*]" or "Type[*]" or "Type[]"
	private static final Pattern MULTIPLICITY_PATTERN = Pattern.compile("^(.+?)\\s*\\[\\s*(\\*|\\d+(?:\\.\\.(\\*|\\d+))?)\\s*]\\s*$");

	/**
	 * Loads a PlantUML {@link ClassDiagram} and populates an {@link EPackage} with the corresponding
	 * Ecore model elements.
	 * <p>
	 * The mapping from PlantUML to Ecore is:
	 * <ul>
	 *   <li>{@code class} → {@link EClass}</li>
	 *   <li>{@code abstract class} → {@link EClass} with {@code isAbstract=true}</li>
	 *   <li>{@code interface} → {@link EClass} with {@code isInterface=true}</li>
	 *   <li>{@code enum} → {@link EEnum}</li>
	 *   <li>Fields → {@link EAttribute} or {@link EReference} (if the type is a known classifier)</li>
	 *   <li>Methods → {@link EOperation}</li>
	 *   <li>Inheritance links → {@link EClass#getESuperTypes()}</li>
	 *   <li>Composition links → {@link EReference} with {@code containment=true}</li>
	 *   <li>Association/aggregation links → {@link EReference}</li>
	 * </ul>
	 * 
	 * @param classDiagram the PlantUML class diagram to convert
	 * @return the populated EPackage
	 */
	protected EModelElement loadClassDiagram(ClassDiagram classDiagram) {
		EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
		
		// Use the diagram title or a default name
		Display title = classDiagram.getTitleDisplay();
		if (title != null && !Display.isNull(title) && !title.isWhite()) {
			String titleStr = title.toString().trim();
			if (!titleStr.isEmpty()) {
				ePackage.setName(titleStr);
			} else {
				// TODO - name
				ePackage.setName(UNNAMED);
				
			}
		} else {
			// TODO - name
			ePackage.setName(UNNAMED);
		}
		
		// TODO - namespace, prefix
		
		// Map from PlantUML Entity to EClassifier for cross-referencing
		Map<Entity, EClassifier> entityToClassifier = new LinkedHashMap<>();
		
		// First pass: create all EClassifiers from leaf entities
		for (Entity entity : classDiagram.getEntityFactory().leafs()) {
			EClassifier eClassifier = createEClassifier(entity);
			if (eClassifier != null) {
				ePackage.getEClassifiers().add(eClassifier);
				entityToClassifier.put(entity, eClassifier);
			}
		}
		
		// Second pass: populate members (attributes, operations, enum literals)
		for (Map.Entry<Entity, EClassifier> entry : entityToClassifier.entrySet()) {
			Entity entity = entry.getKey();
			EClassifier eClassifier = entry.getValue();
			
			if (eClassifier instanceof EClass eClass) {
				populateEClassMembers(entity, eClass, entityToClassifier);
			} else if (eClassifier instanceof EEnum eEnum) {
				populateEEnumLiterals(entity, eEnum);
			}
		}
		
		// Third pass: process links (inheritance, associations, compositions, etc.)
		for (Link link : classDiagram.getLinks()) {
			processLink(link, entityToClassifier);
		}
		
		return ePackage;
	}
	
	/**
	 * Creates an {@link EClassifier} from a PlantUML {@link Entity} based on its {@link LeafType}.
	 */
	protected EClassifier createEClassifier(Entity entity) {
		LeafType leafType = entity.getLeafType();
		if (leafType == null) {
			return null;
		}
		
		String name = getEntityName(entity);
		if (name == null || name.isEmpty()) {
			return null;
		}
		
		return switch (leafType) {
			case CLASS -> {
				EClass eClass = EcoreFactory.eINSTANCE.createEClass();
				eClass.setName(name);
				yield eClass;
			}
			case ABSTRACT_CLASS -> {
				EClass eClass = EcoreFactory.eINSTANCE.createEClass();
				eClass.setName(name);
				eClass.setAbstract(true);
				yield eClass;
			}
			case INTERFACE -> {
				EClass eClass = EcoreFactory.eINSTANCE.createEClass();
				eClass.setName(name);
				eClass.setAbstract(true);
				eClass.setInterface(true);
				yield eClass;
			}
			case ENUM -> {
				EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
				eEnum.setName(name);
				yield eEnum;
			}
			default -> null;
		};
	}
	
	/**
	 * Extracts the simple name from a PlantUML entity.
	 */
	protected String getEntityName(Entity entity) {
		String name = entity.getName();
		if (name != null && !name.isEmpty()) {
			// Handle qualified names - take the last segment
			int dotIdx = name.lastIndexOf('.');
			if (dotIdx >= 0 && dotIdx < name.length() - 1) {
				name = name.substring(dotIdx + 1);
			}
			return name;
		}
		
		// Fall back to display
		Display display = entity.getDisplay();
		if (display != null && !Display.isNull(display) && !display.isWhite()) {
			return display.toString().trim();
		}
		
		return null;
	}
	
	/**
	 * Populates an {@link EClass} with attributes and operations from the entity's body members.
	 */
	protected void populateEClassMembers(Entity entity, EClass eClass, Map<Entity, EClassifier> entityToClassifier) {
		Bodier bodier = entity.getBodier();
		if (bodier == null) {
			return;
		}
		
		// Process fields
		Display fields = bodier.getFieldsToDisplay();
		if (fields != null && !Display.isNull(fields) && !fields.isWhite()) {
			for (CharSequence field : fields) {
				String fieldStr = field.toString().trim();
				if (!fieldStr.isEmpty() && !fieldStr.startsWith("--") && !fieldStr.startsWith("==") && !fieldStr.startsWith("..")) {
					addFieldToEClass(fieldStr, eClass, entityToClassifier);
				}
			}
		}
		
		// Process methods
		Display methods = bodier.getMethodsToDisplay();
		if (methods != null && !Display.isNull(methods) && !methods.isWhite()) {
			for (CharSequence method : methods) {
				String methodStr = method.toString().trim();
				if (!methodStr.isEmpty() && !methodStr.startsWith("--") && !methodStr.startsWith("==") && !methodStr.startsWith("..")) {
					addOperationToEClass(methodStr, eClass, entityToClassifier);
				}
			}
		}
	}
	
	/**
	 * Parses a field string and adds it as an {@link EAttribute} or {@link EReference} to the EClass.
	 * Expected formats: {@code name : Type}, {@code name : Type[*]}, {@code +name : Type}
	 */
	protected void addFieldToEClass(String fieldStr, EClass eClass, Map<Entity, EClassifier> entityToClassifier) {
		Matcher matcher = FIELD_PATTERN.matcher(fieldStr);
		if (matcher.matches()) {
			String name = matcher.group(2).trim();
			String typeStr = matcher.group(3).trim();
			
			// Check for multiplicity in type
			int upperBound = 1;
			int lowerBound = 0;
			Matcher multMatcher = MULTIPLICITY_PATTERN.matcher(typeStr);
			if (multMatcher.matches()) {
				typeStr = multMatcher.group(1).trim();
				String multStr = multMatcher.group(2);
				int[] bounds = parseMultiplicity(multStr);
				lowerBound = bounds[0];
				upperBound = bounds[1];
			}
			
			// Check if the type references a known EClassifier
			EClassifier referencedClassifier = findClassifierByName(typeStr, entityToClassifier);
			
			if (referencedClassifier instanceof EClass referencedClass) {
				// Create an EReference
				EReference eReference = EcoreFactory.eINSTANCE.createEReference();
				eReference.setName(name);
				eReference.setEType(referencedClass);
				eReference.setLowerBound(lowerBound);
				eReference.setUpperBound(upperBound);
				eClass.getEStructuralFeatures().add(eReference);
			} else {
				// Create an EAttribute
				EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
				eAttribute.setName(name);
				eAttribute.setEType(resolveDataType(typeStr));
				eAttribute.setLowerBound(lowerBound);
				eAttribute.setUpperBound(upperBound);
				eClass.getEStructuralFeatures().add(eAttribute);
			}
		} else {
			String name = stripVisibility(fieldStr);
			
			// Handling <type> <name> case
			Matcher typeNameMatcher = TYPE_NAME_PATTERN.matcher(name);
			if (typeNameMatcher.matches()) {
				String typeStr = typeNameMatcher.group(1).trim();												
				int lowerBound = 0;
				int upperBound = 1;
				if (typeStr.endsWith("[]")) {
					typeStr = typeStr.substring(0, typeStr.length() - 2).trim();
					upperBound = -1;
				}
				
				name = typeNameMatcher.group(2).trim();
				EClassifier referencedClassifier = findClassifierByName(typeStr, entityToClassifier);
				
				if (referencedClassifier instanceof EClass referencedClass) {
					// Create an EReference
					EReference eReference = EcoreFactory.eINSTANCE.createEReference();
					eReference.setName(name);
					eReference.setEType(referencedClass);
					eReference.setLowerBound(lowerBound);
					eReference.setUpperBound(upperBound);
					eClass.getEStructuralFeatures().add(eReference);
				} else {
					// Create an EAttribute
					EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
					eAttribute.setName(name);
					eAttribute.setEType(resolveDataType(typeStr));
					eAttribute.setLowerBound(lowerBound);
					eAttribute.setUpperBound(upperBound);
					eClass.getEStructuralFeatures().add(eAttribute);
				}				
			} else {			
				// Fallback: treat the whole string as an attribute name with EString type
				EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
				eAttribute.setName(name);
				eAttribute.setEType(EcorePackage.Literals.ESTRING);
				eClass.getEStructuralFeatures().add(eAttribute);
			}
		}
	}
	
	/**
	 * Parses a method string and adds it as an {@link EOperation} to the EClass.
	 * Expected formats: {@code name(params) : ReturnType}, {@code +name()}, {@code name(p1 : T1, p2 : T2) : ReturnType}
	 */
	protected void addOperationToEClass(String methodStr, EClass eClass, Map<Entity, EClassifier> entityToClassifier) {
		Matcher matcher = METHOD_PATTERN.matcher(methodStr);
		if (matcher.matches()) {
			String name = matcher.group(2).trim();
			String paramsStr = matcher.group(3).trim();
			String returnTypeStr = matcher.group(4);
			
			EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
			eOperation.setName(name);
			
			// Set return type
			if (returnTypeStr != null && !returnTypeStr.trim().isEmpty() && !VOID.equalsIgnoreCase(returnTypeStr.trim())) {
				String retType = returnTypeStr.trim();
				EClassifier retClassifier = findClassifierByName(retType, entityToClassifier);
				if (retClassifier != null) {
					eOperation.setEType(retClassifier);
				} else {
					eOperation.setEType(resolveDataType(retType));
				}
			}
			
			// Parse parameters
			if (!paramsStr.isEmpty()) {
				String[] params = paramsStr.split(",");
				for (String param : params) {
					param = param.trim();
					if (!param.isEmpty()) {
						EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
						Matcher paramMatcher = FIELD_PATTERN.matcher(param);
						if (paramMatcher.matches()) {
							eParameter.setName(paramMatcher.group(2).trim());
							String paramType = paramMatcher.group(3).trim();
							EClassifier paramClassifier = findClassifierByName(paramType, entityToClassifier);
							if (paramClassifier != null) {
								eParameter.setEType(paramClassifier);
							} else {
								eParameter.setEType(resolveDataType(paramType));
							}
						} else {
							eParameter.setName(param);
							eParameter.setEType(EcorePackage.Literals.ESTRING);
						}
						eOperation.getEParameters().add(eParameter);
					}
				}
			}
			
			eClass.getEOperations().add(eOperation);
		}
	}
	
	/**
	 * Populates an {@link EEnum} with literals from the entity's body.
	 */
	protected void populateEEnumLiterals(Entity entity, EEnum eEnum) {
		Bodier bodier = entity.getBodier();
		if (bodier == null) {
			return;
		}
		
		int ordinal = 0;
		
		// Enum literals come from the raw body
		for (CharSequence line : bodier.getRawBody()) {
			String literal = line.toString().trim();
			if (!literal.isEmpty() && !literal.startsWith("--") && !literal.startsWith("==") && !literal.startsWith("..")) {
				EEnumLiteral eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
				eEnumLiteral.setName(literal);
				eEnumLiteral.setValue(ordinal++);
				eEnum.getELiterals().add(eEnumLiteral);
			}
		}
	}
	
	/**
	 * Processes a {@link Link} between two entities and creates the corresponding Ecore relationships.
	 */
	protected void processLink(Link link, Map<Entity, EClassifier> entityToClassifier) {
		Entity entity1 = link.getEntity1();
		Entity entity2 = link.getEntity2();
		
		EClassifier classifier1 = entityToClassifier.get(entity1);
		EClassifier classifier2 = entityToClassifier.get(entity2);
		
		if (classifier1 == null || classifier2 == null) {
			return;
		}
		
		LinkType linkType = link.getType();
		LinkDecor decor1 = linkType.getDecor1();
		LinkDecor decor2 = linkType.getDecor2();
		
		// Inheritance: entity1 <|-- entity2 means entity2 extends entity1
		// decor1=EXTENDS, decor2=NONE → entity2 extends entity1
		// decor2=EXTENDS, decor1=NONE → entity1 extends entity2
		if (decor1 == LinkDecor.EXTENDS && classifier1 instanceof EClass superClass && classifier2 instanceof EClass subClass) {
			subClass.getESuperTypes().add(superClass);
			return;
		}
		if (decor2 == LinkDecor.EXTENDS && classifier2 instanceof EClass superClass && classifier1 instanceof EClass subClass) {
			subClass.getESuperTypes().add(superClass);
			return;
		}
		
		// Composition: entity1 *-- entity2 
		if (decor1 == LinkDecor.COMPOSITION || decor2 == LinkDecor.COMPOSITION) {
			if (classifier1 instanceof EClass ownerClass && classifier2 instanceof EClass ownedClass) {
				EReference eReference = EcoreFactory.eINSTANCE.createEReference();
				eReference.setContainment(true);
				
				if (decor1 == LinkDecor.COMPOSITION) {
					// entity1 has composition diamond, entity1 contains entity2
					configureReference(eReference, ownerClass, ownedClass, link);
				} else {
					// entity2 has composition diamond, entity2 contains entity1
					configureReference(eReference, ownedClass, ownerClass, link);
				}
			}
			return;
		}
		
		// Aggregation: entity1 o-- entity2
		if (decor1 == LinkDecor.AGREGATION || decor2 == LinkDecor.AGREGATION) {
			if (classifier1 instanceof EClass sourceClass && classifier2 instanceof EClass targetClass) {
				EReference eReference = EcoreFactory.eINSTANCE.createEReference();
				eReference.setContainment(false);
				
				if (decor1 == LinkDecor.AGREGATION) {
					configureReference(eReference, sourceClass, targetClass, link);
				} else {
					configureReference(eReference, targetClass, sourceClass, link);
				}
			}
			return;
		}
		
		// Arrow association: entity1 --> entity2
		if (decor1 == LinkDecor.ARROW || decor2 == LinkDecor.ARROW) {
			if (classifier1 instanceof EClass sourceClass && classifier2 instanceof EClass targetClass) {
				EReference eReference = EcoreFactory.eINSTANCE.createEReference();
				eReference.setContainment(false);
				
				if (decor2 == LinkDecor.ARROW) {
					// Arrow points to entity2
					configureReference(eReference, sourceClass, targetClass, link);
				} else {
					// Arrow points to entity1
					configureReference(eReference, targetClass, sourceClass, link);
				}
			}
			return;
		}
		
		// Plain association (no arrows): entity1 -- entity2
		if (decor1 == LinkDecor.NONE && decor2 == LinkDecor.NONE) {
			if (classifier1 instanceof EClass sourceClass && classifier2 instanceof EClass targetClass) {
				EReference eReference = EcoreFactory.eINSTANCE.createEReference();
				eReference.setContainment(false);
				configureReference(eReference, sourceClass, targetClass, link);
			}
		}
	}
	
	/**
	 * Configures an {@link EReference} with name, multiplicity, and adds it to the owner class.
	 */
	protected void configureReference(EReference eReference, EClass ownerClass, EClass targetClass, Link link) {
		eReference.setEType(targetClass);
		
		// Derive name from label, or from target class name (lowercased first char)
		Display label = link.getLabel();
		if (label != null && !Display.isNull(label) && !label.isWhite()) {
			String labelStr = label.toString().trim();
			if (!labelStr.isEmpty()) {
				eReference.setName(labelStr);
			} else {
				eReference.setName(deriveReferenceName(targetClass.getName()));
			}
		} else {
			eReference.setName(deriveReferenceName(targetClass.getName()));
		}
		
		// Parse multiplicity from quantifiers
		// The owner side is entity1 if ownerClass matches entity1, otherwise entity2
		String quantifier;
		if (entityMatchesClassifier(link.getEntity1(), ownerClass)) {
			// owner is entity1 → target quantifier is quantifier2
			quantifier = link.getQuantifier2();
		} else {
			// owner is entity2 → target quantifier is quantifier1
			quantifier = link.getQuantifier1();
		}
		
		if (quantifier != null && !quantifier.isEmpty()) {
			int[] bounds = parseMultiplicity(quantifier.trim());
			eReference.setLowerBound(bounds[0]);
			eReference.setUpperBound(bounds[1]);
		}
		
		ownerClass.getEStructuralFeatures().add(eReference);
	}
	
	/**
	 * Checks if an entity corresponds to a given EClassifier by name.
	 */
	private boolean entityMatchesClassifier(Entity entity, EClassifier classifier) {
		String entityName = getEntityName(entity);
		return entityName != null && entityName.equals(classifier.getName());
	}
	
	/**
	 * Derives a reference name from a class name by lowercasing the first character.
	 * For example, "Customer" → "customer".
	 */
	protected String deriveReferenceName(String className) {
		if (className == null || className.isEmpty()) {
			return "ref";
		}
		if (className.length() == 1) {
			return className.toLowerCase();
		}
		return Character.toLowerCase(className.charAt(0)) + className.substring(1);
	}
	
	/**
	 * Strips a visibility modifier prefix ({@code +}, {@code -}, {@code #}, {@code ~}) from a member string.
	 */
	protected String stripVisibility(String member) {
		if (member != null && !member.isEmpty()) {
			char first = member.charAt(0);
			if (first == '+' || first == '-' || first == '#' || first == '~') {
				return member.substring(1).trim();
			}
		}
		return member;
	}
	
	/**
	 * Parses a multiplicity string and returns [lowerBound, upperBound].
	 * Examples: "*" → [0, -1], "1" → [1, 1], "0..*" → [0, -1], "1..5" → [1, 5]
	 */
	protected int[] parseMultiplicity(String multiplicity) {
		if (multiplicity == null || multiplicity.isEmpty()) {
			return new int[]{0, 1};
		}
		multiplicity = multiplicity.trim();
		if ("*".equals(multiplicity)) {
			return new int[]{0, -1};
		}
		int dotDotIdx = multiplicity.indexOf("..");
		if (dotDotIdx >= 0) {
			String lower = multiplicity.substring(0, dotDotIdx).trim();
			String upper = multiplicity.substring(dotDotIdx + 2).trim();
			int lb = lower.isEmpty() ? 0 : Integer.parseInt(lower);
			int ub = "*".equals(upper) ? -1 : Integer.parseInt(upper);
			return new int[]{lb, ub};
		}
		int val = Integer.parseInt(multiplicity);
		return new int[]{val, val};
	}
	
	/**
	 * Resolves a type name to an Ecore data type.
	 */
	protected EClassifier resolveDataType(String typeName) {
		if (typeName == null) {
			return EcorePackage.Literals.ESTRING;
		}
		return switch (typeName.trim().toLowerCase()) {
			case "string", "estring" -> EcorePackage.Literals.ESTRING;
			case "int", "eint", "integer", "eintegerobject" -> EcorePackage.Literals.EINT;
			case "long", "elong", "elongobject" -> EcorePackage.Literals.ELONG;
			case "boolean", "eboolean", "ebooleanobject" -> EcorePackage.Literals.EBOOLEAN;
			case "double", "edouble", "edoubleobject" -> EcorePackage.Literals.EDOUBLE;
			case "float", "efloat", "efloatobject" -> EcorePackage.Literals.EFLOAT;
			case "short", "eshort", "eshortobject" -> EcorePackage.Literals.ESHORT;
			case "byte", "ebyte", "ebyteobject" -> EcorePackage.Literals.EBYTE;
			case "char", "echar", "echaracterobject" -> EcorePackage.Literals.ECHAR;
			case "date", "edate" -> EcorePackage.Literals.EDATE;
			case "bigdecimal", "ebigdecimal" -> EcorePackage.Literals.EBIG_DECIMAL;
			case "biginteger", "ebiginteger" -> EcorePackage.Literals.EBIG_INTEGER;
			case "object", "ejavaobject" -> EcorePackage.Literals.EJAVA_OBJECT;
			case "class", "ejavaclass" -> EcorePackage.Literals.EJAVA_CLASS;
			default -> EcorePackage.Literals.ESTRING;
		};
	}
	
	/**
	 * Finds an EClassifier by name among the already-created classifiers.
	 */
	protected EClassifier findClassifierByName(String name, Map<Entity, EClassifier> entityToClassifier) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		String simpleName = name.trim();
		for (EClassifier classifier : entityToClassifier.values()) {
			if (simpleName.equals(classifier.getName())) {
				return classifier;
			}
		}
		return null;
	}

}
