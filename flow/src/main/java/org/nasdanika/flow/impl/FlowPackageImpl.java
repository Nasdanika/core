/**
 */
package org.nasdanika.flow.impl;

import static org.nasdanika.flow.FlowPackage.RESOURCE;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.nasdanika.diagram.DiagramPackage;
import org.nasdanika.flow.Activity;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.ArtifactParticipantResponsibility;
import org.nasdanika.flow.Call;
import org.nasdanika.flow.Choice;
import org.nasdanika.flow.End;
import org.nasdanika.flow.EntryPoint;
import org.nasdanika.flow.ExitPoint;
import org.nasdanika.flow.ExpansionInput;
import org.nasdanika.flow.ExpansionOutput;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowFactory;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Fork;
import org.nasdanika.flow.InputPin;
import org.nasdanika.flow.Join;
import org.nasdanika.flow.OutputPin;
import org.nasdanika.flow.PackageElement;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.ParticipantResponsibility;
import org.nasdanika.flow.PseudoState;
import org.nasdanika.flow.Relationship;
import org.nasdanika.flow.Resource;
import org.nasdanika.flow.Service;
import org.nasdanika.flow.ServiceProvider;
import org.nasdanika.flow.Start;
import org.nasdanika.flow.Transition;
import org.nasdanika.flow.util.FlowValidator;
import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FlowPackageImpl extends EPackageImpl implements FlowPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass representationEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceProviderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass participantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass participantEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass participantResponsibilityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactParticipantResponsibilityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationshipEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationshipEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass flowElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass flowElementEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass flowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pseudoStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass choiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass endEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entryPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exitPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expansionInputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expansionOutputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass forkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inputPinEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass joinEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outputPinEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass startEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.nasdanika.flow.FlowPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private FlowPackageImpl() {
		super(eNS_URI, FlowFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link FlowPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static FlowPackage init() {
		if (isInited) return (FlowPackage)EPackage.Registry.INSTANCE.getEPackage(FlowPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredFlowPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		FlowPackageImpl theFlowPackage = registeredFlowPackage instanceof FlowPackageImpl ? (FlowPackageImpl)registeredFlowPackage : new FlowPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		DiagramPackage.eINSTANCE.eClass();
		NcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theFlowPackage.createPackageContents();

		// Initialize created meta-data
		theFlowPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theFlowPackage,
			 new EValidator.Descriptor() {
				 @Override
				 public EValidator getEValidator() {
					 return FlowValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theFlowPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FlowPackage.eNS_URI, theFlowPackage);
		return theFlowPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPackageElement() {
		return packageElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackageElement_Prototype() {
		return (EReference)packageElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackageElement_Extensions() {
		return (EReference)packageElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackageElement_Extends() {
		return (EReference)packageElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPackageElement_Modifiers() {
		return (EAttribute)packageElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackageElement_Documentation() {
		return (EReference)packageElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackageElement_Representations() {
		return (EReference)packageElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackageElement_Properties() {
		return (EReference)packageElementEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getPackageElement__Create() {
		return packageElementEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getPackageElement__Apply__PackageElement() {
		return packageElementEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getPackageElement__Resolve__PackageElement() {
		return packageElementEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRepresentationEntry() {
		return representationEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRepresentationEntry_Key() {
		return (EAttribute)representationEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRepresentationEntry_Value() {
		return (EReference)representationEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPackage() {
		return packageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_SuperPackages() {
		return (EReference)packageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_SubPackages() {
		return (EReference)packageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_Participants() {
		return (EReference)packageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_Resources() {
		return (EReference)packageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_Artifacts() {
		return (EReference)packageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_Activities() {
		return (EReference)packageEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPackageEntry() {
		return packageEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPackageEntry_Key() {
		return (EAttribute)packageEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackageEntry_Value() {
		return (EReference)packageEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getServiceProvider() {
		return serviceProviderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getServiceProvider_Services() {
		return (EReference)serviceProviderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getParticipant() {
		return participantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipant_Participates() {
		return (EReference)participantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipant_Resources() {
		return (EReference)participantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipant_Artifacts() {
		return (EReference)participantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipant_Specializations() {
		return (EReference)participantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParticipant_BaseKeys() {
		return (EAttribute)participantEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipant_Bases() {
		return (EReference)participantEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipant_Responsible() {
		return (EReference)participantEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipant_Accountable() {
		return (EReference)participantEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipant_Consulted() {
		return (EReference)participantEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipant_Informed() {
		return (EReference)participantEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipant_Children() {
		return (EReference)participantEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getParticipantEntry() {
		return participantEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParticipantEntry_Key() {
		return (EAttribute)participantEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipantEntry_Value() {
		return (EReference)participantEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResource() {
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResource_Artifacts() {
		return (EReference)resourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResource_UsedIn() {
		return (EReference)resourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResource_UsedBy() {
		return (EReference)resourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResource_Children() {
		return (EReference)resourceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResourceEntry() {
		return resourceEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResourceEntry_Key() {
		return (EAttribute)resourceEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResourceEntry_Value() {
		return (EReference)resourceEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getParticipantResponsibility() {
		return participantResponsibilityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipantResponsibility_Responsible() {
		return (EReference)participantResponsibilityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParticipantResponsibility_ResponsibleKeys() {
		return (EAttribute)participantResponsibilityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipantResponsibility_Accountable() {
		return (EReference)participantResponsibilityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParticipantResponsibility_AccountableKeys() {
		return (EAttribute)participantResponsibilityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipantResponsibility_Consulted() {
		return (EReference)participantResponsibilityEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParticipantResponsibility_ConsultedKeys() {
		return (EAttribute)participantResponsibilityEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParticipantResponsibility_Informed() {
		return (EReference)participantResponsibilityEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParticipantResponsibility_InformedKeys() {
		return (EAttribute)participantResponsibilityEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getArtifact() {
		return artifactEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_Repositories() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifact_RepositoryKeys() {
		return (EAttribute)artifactEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_InputFor() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_OutputFor() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_PayloadFor() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_ResponseFor() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_UsedBy() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_Responsibilities() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_Children() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_OutboundRelationships() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_InboundRelationships() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifact_Partition() {
		return (EAttribute)artifactEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_Style() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifact_TemplateKeys() {
		return (EAttribute)artifactEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_Templates() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifact_Instances() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getArtifactParticipantResponsibility() {
		return artifactParticipantResponsibilityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifactParticipantResponsibility_ArtifactKey() {
		return (EAttribute)artifactParticipantResponsibilityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifactParticipantResponsibility_Artifact() {
		return (EReference)artifactParticipantResponsibilityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifactParticipantResponsibility_Suppress() {
		return (EAttribute)artifactParticipantResponsibilityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRelationship() {
		return relationshipEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRelationship_TargetKey() {
		return (EAttribute)relationshipEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRelationship_Target() {
		return (EReference)relationshipEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRelationship_Style() {
		return (EReference)relationshipEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRelationshipEntry() {
		return relationshipEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRelationshipEntry_Key() {
		return (EAttribute)relationshipEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRelationshipEntry_Value() {
		return (EReference)relationshipEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getArtifactEntry() {
		return artifactEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifactEntry_Key() {
		return (EAttribute)artifactEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifactEntry_Value() {
		return (EReference)artifactEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFlowElement() {
		return flowElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_Outputs() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_Inputs() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_Calls() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_Invocations() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFlowElement_InputArtifactKeys() {
		return (EAttribute)flowElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_InputArtifacts() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_OutputArtifacts() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFlowElement_OutputArtifactKeys() {
		return (EAttribute)flowElementEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_Participants() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFlowElement_ParticipantKeys() {
		return (EAttribute)flowElementEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_Resources() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFlowElement_ResourceKeys() {
		return (EAttribute)flowElementEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElement_ArtifactResponsibilities() {
		return (EReference)flowElementEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFlowElement_SortGroup() {
		return (EAttribute)flowElementEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFlowElementEntry() {
		return flowElementEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFlowElementEntry_Key() {
		return (EAttribute)flowElementEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlowElementEntry_Value() {
		return (EReference)flowElementEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getActivity() {
		return activityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getActivity_Services() {
		return (EReference)activityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getActivityEntry() {
		return activityEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getActivityEntry_Key() {
		return (EAttribute)activityEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getActivityEntry_Value() {
		return (EReference)activityEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getService() {
		return serviceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getService_Target() {
		return (EReference)serviceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getService_TargetKey() {
		return (EAttribute)serviceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTransition() {
		return transitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTransition_Payload() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTransition_PayloadKeys() {
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTransition_TargetKey() {
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTransition_Target() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTransitionEntry() {
		return transitionEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTransitionEntry_Key() {
		return (EAttribute)transitionEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTransitionEntry_Value() {
		return (EReference)transitionEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCall() {
		return callEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCall_Response() {
		return (EReference)callEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCall_ResponseKeys() {
		return (EAttribute)callEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCallEntry() {
		return callEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCallEntry_Key() {
		return (EAttribute)callEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCallEntry_Value() {
		return (EReference)callEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFlow() {
		return flowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFlow_Elements() {
		return (EReference)flowEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFlow_Partition() {
		return (EAttribute)flowEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPseudoState() {
		return pseudoStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPseudoState_Type() {
		return (EAttribute)pseudoStateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getChoice() {
		return choiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEnd() {
		return endEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEntryPoint() {
		return entryPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExitPoint() {
		return exitPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExpansionInput() {
		return expansionInputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExpansionOutput() {
		return expansionOutputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFork() {
		return forkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInputPin() {
		return inputPinEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getJoin() {
		return joinEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOutputPin() {
		return outputPinEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStart() {
		return startEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FlowFactory getFlowFactory() {
		return (FlowFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		packageElementEClass = createEClass(PACKAGE_ELEMENT);
		createEReference(packageElementEClass, PACKAGE_ELEMENT__PROTOTYPE);
		createEReference(packageElementEClass, PACKAGE_ELEMENT__EXTENSIONS);
		createEReference(packageElementEClass, PACKAGE_ELEMENT__EXTENDS);
		createEAttribute(packageElementEClass, PACKAGE_ELEMENT__MODIFIERS);
		createEReference(packageElementEClass, PACKAGE_ELEMENT__DOCUMENTATION);
		createEReference(packageElementEClass, PACKAGE_ELEMENT__REPRESENTATIONS);
		createEReference(packageElementEClass, PACKAGE_ELEMENT__PROPERTIES);
		createEOperation(packageElementEClass, PACKAGE_ELEMENT___CREATE);
		createEOperation(packageElementEClass, PACKAGE_ELEMENT___APPLY__PACKAGEELEMENT);
		createEOperation(packageElementEClass, PACKAGE_ELEMENT___RESOLVE__PACKAGEELEMENT);

		representationEntryEClass = createEClass(REPRESENTATION_ENTRY);
		createEAttribute(representationEntryEClass, REPRESENTATION_ENTRY__KEY);
		createEReference(representationEntryEClass, REPRESENTATION_ENTRY__VALUE);

		packageEClass = createEClass(PACKAGE);
		createEReference(packageEClass, PACKAGE__SUPER_PACKAGES);
		createEReference(packageEClass, PACKAGE__SUB_PACKAGES);
		createEReference(packageEClass, PACKAGE__PARTICIPANTS);
		createEReference(packageEClass, PACKAGE__RESOURCES);
		createEReference(packageEClass, PACKAGE__ARTIFACTS);
		createEReference(packageEClass, PACKAGE__ACTIVITIES);

		packageEntryEClass = createEClass(PACKAGE_ENTRY);
		createEAttribute(packageEntryEClass, PACKAGE_ENTRY__KEY);
		createEReference(packageEntryEClass, PACKAGE_ENTRY__VALUE);

		serviceProviderEClass = createEClass(SERVICE_PROVIDER);
		createEReference(serviceProviderEClass, SERVICE_PROVIDER__SERVICES);

		participantEClass = createEClass(PARTICIPANT);
		createEReference(participantEClass, PARTICIPANT__PARTICIPATES);
		createEReference(participantEClass, PARTICIPANT__RESOURCES);
		createEReference(participantEClass, PARTICIPANT__ARTIFACTS);
		createEReference(participantEClass, PARTICIPANT__SPECIALIZATIONS);
		createEAttribute(participantEClass, PARTICIPANT__BASE_KEYS);
		createEReference(participantEClass, PARTICIPANT__BASES);
		createEReference(participantEClass, PARTICIPANT__RESPONSIBLE);
		createEReference(participantEClass, PARTICIPANT__ACCOUNTABLE);
		createEReference(participantEClass, PARTICIPANT__CONSULTED);
		createEReference(participantEClass, PARTICIPANT__INFORMED);
		createEReference(participantEClass, PARTICIPANT__CHILDREN);

		participantEntryEClass = createEClass(PARTICIPANT_ENTRY);
		createEAttribute(participantEntryEClass, PARTICIPANT_ENTRY__KEY);
		createEReference(participantEntryEClass, PARTICIPANT_ENTRY__VALUE);

		resourceEClass = createEClass(RESOURCE);
		createEReference(resourceEClass, RESOURCE__ARTIFACTS);
		createEReference(resourceEClass, RESOURCE__USED_IN);
		createEReference(resourceEClass, RESOURCE__USED_BY);
		createEReference(resourceEClass, RESOURCE__CHILDREN);

		resourceEntryEClass = createEClass(RESOURCE_ENTRY);
		createEAttribute(resourceEntryEClass, RESOURCE_ENTRY__KEY);
		createEReference(resourceEntryEClass, RESOURCE_ENTRY__VALUE);

		participantResponsibilityEClass = createEClass(PARTICIPANT_RESPONSIBILITY);
		createEReference(participantResponsibilityEClass, PARTICIPANT_RESPONSIBILITY__RESPONSIBLE);
		createEAttribute(participantResponsibilityEClass, PARTICIPANT_RESPONSIBILITY__RESPONSIBLE_KEYS);
		createEReference(participantResponsibilityEClass, PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE);
		createEAttribute(participantResponsibilityEClass, PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE_KEYS);
		createEReference(participantResponsibilityEClass, PARTICIPANT_RESPONSIBILITY__CONSULTED);
		createEAttribute(participantResponsibilityEClass, PARTICIPANT_RESPONSIBILITY__CONSULTED_KEYS);
		createEReference(participantResponsibilityEClass, PARTICIPANT_RESPONSIBILITY__INFORMED);
		createEAttribute(participantResponsibilityEClass, PARTICIPANT_RESPONSIBILITY__INFORMED_KEYS);

		artifactEClass = createEClass(ARTIFACT);
		createEReference(artifactEClass, ARTIFACT__REPOSITORIES);
		createEAttribute(artifactEClass, ARTIFACT__REPOSITORY_KEYS);
		createEReference(artifactEClass, ARTIFACT__INPUT_FOR);
		createEReference(artifactEClass, ARTIFACT__OUTPUT_FOR);
		createEReference(artifactEClass, ARTIFACT__PAYLOAD_FOR);
		createEReference(artifactEClass, ARTIFACT__RESPONSE_FOR);
		createEReference(artifactEClass, ARTIFACT__USED_BY);
		createEReference(artifactEClass, ARTIFACT__RESPONSIBILITIES);
		createEReference(artifactEClass, ARTIFACT__CHILDREN);
		createEReference(artifactEClass, ARTIFACT__OUTBOUND_RELATIONSHIPS);
		createEReference(artifactEClass, ARTIFACT__INBOUND_RELATIONSHIPS);
		createEAttribute(artifactEClass, ARTIFACT__PARTITION);
		createEReference(artifactEClass, ARTIFACT__STYLE);
		createEAttribute(artifactEClass, ARTIFACT__TEMPLATE_KEYS);
		createEReference(artifactEClass, ARTIFACT__TEMPLATES);
		createEReference(artifactEClass, ARTIFACT__INSTANCES);

		artifactEntryEClass = createEClass(ARTIFACT_ENTRY);
		createEAttribute(artifactEntryEClass, ARTIFACT_ENTRY__KEY);
		createEReference(artifactEntryEClass, ARTIFACT_ENTRY__VALUE);

		artifactParticipantResponsibilityEClass = createEClass(ARTIFACT_PARTICIPANT_RESPONSIBILITY);
		createEAttribute(artifactParticipantResponsibilityEClass, ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT_KEY);
		createEReference(artifactParticipantResponsibilityEClass, ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT);
		createEAttribute(artifactParticipantResponsibilityEClass, ARTIFACT_PARTICIPANT_RESPONSIBILITY__SUPPRESS);

		relationshipEClass = createEClass(RELATIONSHIP);
		createEAttribute(relationshipEClass, RELATIONSHIP__TARGET_KEY);
		createEReference(relationshipEClass, RELATIONSHIP__TARGET);
		createEReference(relationshipEClass, RELATIONSHIP__STYLE);

		relationshipEntryEClass = createEClass(RELATIONSHIP_ENTRY);
		createEAttribute(relationshipEntryEClass, RELATIONSHIP_ENTRY__KEY);
		createEReference(relationshipEntryEClass, RELATIONSHIP_ENTRY__VALUE);

		flowElementEClass = createEClass(FLOW_ELEMENT);
		createEReference(flowElementEClass, FLOW_ELEMENT__OUTPUTS);
		createEReference(flowElementEClass, FLOW_ELEMENT__INPUTS);
		createEReference(flowElementEClass, FLOW_ELEMENT__CALLS);
		createEReference(flowElementEClass, FLOW_ELEMENT__INVOCATIONS);
		createEReference(flowElementEClass, FLOW_ELEMENT__INPUT_ARTIFACTS);
		createEAttribute(flowElementEClass, FLOW_ELEMENT__INPUT_ARTIFACT_KEYS);
		createEReference(flowElementEClass, FLOW_ELEMENT__OUTPUT_ARTIFACTS);
		createEAttribute(flowElementEClass, FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS);
		createEReference(flowElementEClass, FLOW_ELEMENT__PARTICIPANTS);
		createEAttribute(flowElementEClass, FLOW_ELEMENT__PARTICIPANT_KEYS);
		createEReference(flowElementEClass, FLOW_ELEMENT__RESOURCES);
		createEAttribute(flowElementEClass, FLOW_ELEMENT__RESOURCE_KEYS);
		createEReference(flowElementEClass, FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES);
		createEAttribute(flowElementEClass, FLOW_ELEMENT__SORT_GROUP);

		flowElementEntryEClass = createEClass(FLOW_ELEMENT_ENTRY);
		createEAttribute(flowElementEntryEClass, FLOW_ELEMENT_ENTRY__KEY);
		createEReference(flowElementEntryEClass, FLOW_ELEMENT_ENTRY__VALUE);

		transitionEClass = createEClass(TRANSITION);
		createEReference(transitionEClass, TRANSITION__PAYLOAD);
		createEAttribute(transitionEClass, TRANSITION__PAYLOAD_KEYS);
		createEAttribute(transitionEClass, TRANSITION__TARGET_KEY);
		createEReference(transitionEClass, TRANSITION__TARGET);

		transitionEntryEClass = createEClass(TRANSITION_ENTRY);
		createEAttribute(transitionEntryEClass, TRANSITION_ENTRY__KEY);
		createEReference(transitionEntryEClass, TRANSITION_ENTRY__VALUE);

		callEClass = createEClass(CALL);
		createEReference(callEClass, CALL__RESPONSE);
		createEAttribute(callEClass, CALL__RESPONSE_KEYS);

		callEntryEClass = createEClass(CALL_ENTRY);
		createEAttribute(callEntryEClass, CALL_ENTRY__KEY);
		createEReference(callEntryEClass, CALL_ENTRY__VALUE);

		activityEClass = createEClass(ACTIVITY);
		createEReference(activityEClass, ACTIVITY__SERVICES);

		activityEntryEClass = createEClass(ACTIVITY_ENTRY);
		createEAttribute(activityEntryEClass, ACTIVITY_ENTRY__KEY);
		createEReference(activityEntryEClass, ACTIVITY_ENTRY__VALUE);

		serviceEClass = createEClass(SERVICE);
		createEReference(serviceEClass, SERVICE__TARGET);
		createEAttribute(serviceEClass, SERVICE__TARGET_KEY);

		flowEClass = createEClass(FLOW);
		createEReference(flowEClass, FLOW__ELEMENTS);
		createEAttribute(flowEClass, FLOW__PARTITION);

		pseudoStateEClass = createEClass(PSEUDO_STATE);
		createEAttribute(pseudoStateEClass, PSEUDO_STATE__TYPE);

		choiceEClass = createEClass(CHOICE);

		endEClass = createEClass(END);

		entryPointEClass = createEClass(ENTRY_POINT);

		exitPointEClass = createEClass(EXIT_POINT);

		expansionInputEClass = createEClass(EXPANSION_INPUT);

		expansionOutputEClass = createEClass(EXPANSION_OUTPUT);

		forkEClass = createEClass(FORK);

		inputPinEClass = createEClass(INPUT_PIN);

		joinEClass = createEClass(JOIN);

		outputPinEClass = createEClass(OUTPUT_PIN);

		startEClass = createEClass(START);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		NcorePackage theNcorePackage = (NcorePackage)EPackage.Registry.INSTANCE.getEPackage(NcorePackage.eNS_URI);
		DiagramPackage theDiagramPackage = (DiagramPackage)EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI);

		// Create type parameters
		ETypeParameter packageElementEClass_T = addETypeParameter(packageElementEClass, "T");
		ETypeParameter serviceProviderEClass_T = addETypeParameter(serviceProviderEClass, "T");
		ETypeParameter participantResponsibilityEClass_T = addETypeParameter(participantResponsibilityEClass, "T");
		ETypeParameter flowElementEClass_T = addETypeParameter(flowElementEClass, "T");
		ETypeParameter activityEClass_T = addETypeParameter(activityEClass, "T");

		// Set bounds for type parameters
		EGenericType g1 = createEGenericType(this.getPackageElement());
		EGenericType g2 = createEGenericType(packageElementEClass_T);
		g1.getETypeArguments().add(g2);
		packageElementEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(this.getPackageElement());
		g2 = createEGenericType(serviceProviderEClass_T);
		g1.getETypeArguments().add(g2);
		serviceProviderEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(this.getPackageElement());
		g2 = createEGenericType(participantResponsibilityEClass_T);
		g1.getETypeArguments().add(g2);
		participantResponsibilityEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(this.getFlowElement());
		g2 = createEGenericType(flowElementEClass_T);
		g1.getETypeArguments().add(g2);
		flowElementEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(this.getActivity());
		g2 = createEGenericType(activityEClass_T);
		g1.getETypeArguments().add(g2);
		activityEClass_T.getEBounds().add(g1);

		// Add supertypes to classes
		packageElementEClass.getESuperTypes().add(theNcorePackage.getNamedElement());
		g1 = createEGenericType(this.getPackageElement());
		g2 = createEGenericType(this.getPackage());
		g1.getETypeArguments().add(g2);
		packageEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getPackageElement());
		g2 = createEGenericType(serviceProviderEClass_T);
		g1.getETypeArguments().add(g2);
		serviceProviderEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getServiceProvider());
		g2 = createEGenericType(this.getParticipant());
		g1.getETypeArguments().add(g2);
		participantEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getServiceProvider());
		g2 = createEGenericType(this.getResource());
		g1.getETypeArguments().add(g2);
		resourceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getPackageElement());
		g2 = createEGenericType(participantResponsibilityEClass_T);
		g1.getETypeArguments().add(g2);
		participantResponsibilityEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getParticipantResponsibility());
		g2 = createEGenericType(this.getArtifact());
		g1.getETypeArguments().add(g2);
		artifactEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getServiceProvider());
		g2 = createEGenericType(this.getArtifact());
		g1.getETypeArguments().add(g2);
		artifactEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getParticipantResponsibility());
		g2 = createEGenericType(this.getArtifactParticipantResponsibility());
		g1.getETypeArguments().add(g2);
		artifactParticipantResponsibilityEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getPackageElement());
		g2 = createEGenericType(this.getRelationship());
		g1.getETypeArguments().add(g2);
		relationshipEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getParticipantResponsibility());
		g2 = createEGenericType(flowElementEClass_T);
		g1.getETypeArguments().add(g2);
		flowElementEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getPackageElement());
		g2 = createEGenericType(this.getTransition());
		g1.getETypeArguments().add(g2);
		transitionEClass.getEGenericSuperTypes().add(g1);
		callEClass.getESuperTypes().add(this.getTransition());
		g1 = createEGenericType(this.getFlowElement());
		g2 = createEGenericType(activityEClass_T);
		g1.getETypeArguments().add(g2);
		activityEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getFlowElement());
		g2 = createEGenericType(this.getService());
		g1.getETypeArguments().add(g2);
		serviceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getActivity());
		g2 = createEGenericType(this.getFlow());
		g1.getETypeArguments().add(g2);
		flowEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getFlowElement());
		g2 = createEGenericType(this.getPseudoState());
		g1.getETypeArguments().add(g2);
		pseudoStateEClass.getEGenericSuperTypes().add(g1);
		choiceEClass.getESuperTypes().add(this.getPseudoState());
		endEClass.getESuperTypes().add(this.getPseudoState());
		entryPointEClass.getESuperTypes().add(this.getPseudoState());
		exitPointEClass.getESuperTypes().add(this.getPseudoState());
		expansionInputEClass.getESuperTypes().add(this.getPseudoState());
		expansionOutputEClass.getESuperTypes().add(this.getPseudoState());
		forkEClass.getESuperTypes().add(this.getPseudoState());
		inputPinEClass.getESuperTypes().add(this.getPseudoState());
		joinEClass.getESuperTypes().add(this.getPseudoState());
		outputPinEClass.getESuperTypes().add(this.getPseudoState());
		startEClass.getESuperTypes().add(this.getPseudoState());

		// Initialize classes, features, and operations; add parameters
		initEClass(packageElementEClass, PackageElement.class, "PackageElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(packageElementEClass_T);
		initEReference(getPackageElement_Prototype(), g1, null, "prototype", null, 0, 1, PackageElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(packageElementEClass_T);
		initEReference(getPackageElement_Extensions(), g1, this.getPackageElement_Extends(), "extensions", null, 0, -1, PackageElement.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(packageElementEClass_T);
		initEReference(getPackageElement_Extends(), g1, this.getPackageElement_Extensions(), "extends", null, 0, -1, PackageElement.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageElement_Modifiers(), ecorePackage.getEString(), "modifiers", null, 0, -1, PackageElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageElement_Documentation(), ecorePackage.getEObject(), null, "documentation", null, 0, -1, PackageElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageElement_Representations(), this.getRepresentationEntry(), null, "representations", null, 0, -1, PackageElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageElement_Properties(), theNcorePackage.getProperty(), null, "properties", null, 0, -1, PackageElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getPackageElement_Properties().getEKeys().add(theNcorePackage.getProperty_Name());

		EOperation op = initEOperation(getPackageElement__Create(), null, "create", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(packageElementEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getPackageElement__Apply__PackageElement(), null, "apply", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(packageElementEClass_T);
		addEParameter(op, g1, "instance", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getPackageElement__Resolve__PackageElement(), null, "resolve", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(packageElementEClass_T);
		addEParameter(op, g1, "instance", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(representationEntryEClass, Map.Entry.class, "RepresentationEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRepresentationEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRepresentationEntry_Value(), theDiagramPackage.getDiagram(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageEClass, org.nasdanika.flow.Package.class, "Package", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPackage_SuperPackages(), this.getPackage(), null, "superPackages", null, 0, -1, org.nasdanika.flow.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackage_SubPackages(), this.getPackageEntry(), null, "subPackages", null, 0, -1, org.nasdanika.flow.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackage_Participants(), this.getParticipantEntry(), null, "participants", null, 0, -1, org.nasdanika.flow.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackage_Resources(), this.getResourceEntry(), null, "resources", null, 0, -1, org.nasdanika.flow.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackage_Artifacts(), this.getArtifactEntry(), null, "artifacts", null, 0, -1, org.nasdanika.flow.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackage_Activities(), this.getActivityEntry(), null, "activities", null, 0, -1, org.nasdanika.flow.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageEntryEClass, Map.Entry.class, "PackageEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPackageEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageEntry_Value(), this.getPackage(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceProviderEClass, ServiceProvider.class, "ServiceProvider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getServiceProvider_Services(), this.getActivityEntry(), null, "services", null, 0, -1, ServiceProvider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(participantEClass, Participant.class, "Participant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(this.getFlowElement());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getParticipant_Participates(), g1, this.getFlowElement_Participants(), "participates", null, 0, -1, Participant.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getParticipant_Resources(), this.getResource(), this.getResource_UsedBy(), "resources", null, 0, -1, Participant.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getParticipant_Artifacts(), this.getArtifact(), this.getArtifact_UsedBy(), "artifacts", null, 0, -1, Participant.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getParticipant_Specializations(), this.getParticipant(), null, "specializations", null, 0, -1, Participant.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getParticipant_BaseKeys(), ecorePackage.getEString(), "baseKeys", null, 0, -1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParticipant_Bases(), this.getParticipant(), null, "bases", null, 0, -1, Participant.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getParticipantResponsibility());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getParticipant_Responsible(), g1, this.getParticipantResponsibility_Responsible(), "responsible", null, 0, -1, Participant.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getParticipantResponsibility());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getParticipant_Accountable(), g1, this.getParticipantResponsibility_Accountable(), "accountable", null, 0, -1, Participant.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getParticipantResponsibility());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getParticipant_Consulted(), g1, this.getParticipantResponsibility_Consulted(), "consulted", null, 0, -1, Participant.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getParticipantResponsibility());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getParticipant_Informed(), g1, this.getParticipantResponsibility_Informed(), "informed", null, 0, -1, Participant.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getParticipant_Children(), this.getParticipantEntry(), null, "children", null, 0, -1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(participantEntryEClass, Map.Entry.class, "ParticipantEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParticipantEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParticipantEntry_Value(), this.getParticipant(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResource_Artifacts(), this.getArtifact(), null, "artifacts", null, 0, -1, Resource.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getFlowElement());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getResource_UsedIn(), g1, null, "usedIn", null, 0, -1, Resource.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getResource_UsedBy(), this.getParticipant(), this.getParticipant_Resources(), "usedBy", null, 0, -1, Resource.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getResource_Children(), this.getResourceEntry(), null, "children", null, 0, -1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceEntryEClass, Map.Entry.class, "ResourceEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResourceEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceEntry_Value(), this.getResource(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(participantResponsibilityEClass, ParticipantResponsibility.class, "ParticipantResponsibility", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParticipantResponsibility_Responsible(), this.getParticipant(), this.getParticipant_Responsible(), "responsible", null, 0, -1, ParticipantResponsibility.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getParticipantResponsibility_ResponsibleKeys(), ecorePackage.getEString(), "responsibleKeys", null, 0, -1, ParticipantResponsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParticipantResponsibility_Accountable(), this.getParticipant(), this.getParticipant_Accountable(), "accountable", null, 0, -1, ParticipantResponsibility.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getParticipantResponsibility_AccountableKeys(), ecorePackage.getEString(), "accountableKeys", null, 0, -1, ParticipantResponsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParticipantResponsibility_Consulted(), this.getParticipant(), this.getParticipant_Consulted(), "consulted", null, 0, -1, ParticipantResponsibility.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getParticipantResponsibility_ConsultedKeys(), ecorePackage.getEString(), "consultedKeys", null, 0, -1, ParticipantResponsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParticipantResponsibility_Informed(), this.getParticipant(), this.getParticipant_Informed(), "informed", null, 0, -1, ParticipantResponsibility.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getParticipantResponsibility_InformedKeys(), ecorePackage.getEString(), "informedKeys", null, 0, -1, ParticipantResponsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(artifactEClass, Artifact.class, "Artifact", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArtifact_Repositories(), this.getResource(), null, "repositories", null, 0, -1, Artifact.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_RepositoryKeys(), ecorePackage.getEString(), "repositoryKeys", null, 0, -1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getFlowElement());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getArtifact_InputFor(), g1, this.getFlowElement_InputArtifacts(), "inputFor", null, 0, -1, Artifact.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getFlowElement());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getArtifact_OutputFor(), g1, this.getFlowElement_OutputArtifacts(), "outputFor", null, 0, -1, Artifact.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_PayloadFor(), this.getTransition(), this.getTransition_Payload(), "payloadFor", null, 0, -1, Artifact.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_ResponseFor(), this.getCall(), this.getCall_Response(), "responseFor", null, 0, -1, Artifact.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_UsedBy(), this.getParticipant(), this.getParticipant_Artifacts(), "usedBy", null, 0, -1, Artifact.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_Responsibilities(), this.getArtifactParticipantResponsibility(), this.getArtifactParticipantResponsibility_Artifact(), "responsibilities", null, 0, -1, Artifact.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_Children(), this.getArtifactEntry(), null, "children", null, 0, -1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_OutboundRelationships(), this.getRelationshipEntry(), null, "outboundRelationships", null, 0, -1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_InboundRelationships(), this.getRelationship(), this.getRelationship_Target(), "inboundRelationships", null, 0, -1, Artifact.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_Partition(), ecorePackage.getEBoolean(), "partition", null, 0, 1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_Style(), theDiagramPackage.getDiagramElement(), null, "style", null, 0, 1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_TemplateKeys(), ecorePackage.getEString(), "templateKeys", null, 0, -1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_Templates(), this.getArtifact(), this.getArtifact_Instances(), "templates", null, 0, -1, Artifact.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_Instances(), this.getArtifact(), this.getArtifact_Templates(), "instances", null, 0, -1, Artifact.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(artifactEntryEClass, Map.Entry.class, "ArtifactEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArtifactEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArtifactEntry_Value(), this.getArtifact(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(artifactParticipantResponsibilityEClass, ArtifactParticipantResponsibility.class, "ArtifactParticipantResponsibility", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArtifactParticipantResponsibility_ArtifactKey(), ecorePackage.getEString(), "artifactKey", null, 1, 1, ArtifactParticipantResponsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArtifactParticipantResponsibility_Artifact(), this.getArtifact(), this.getArtifact_Responsibilities(), "artifact", null, 0, 1, ArtifactParticipantResponsibility.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifactParticipantResponsibility_Suppress(), ecorePackage.getEBoolean(), "suppress", null, 0, 1, ArtifactParticipantResponsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relationshipEClass, Relationship.class, "Relationship", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRelationship_TargetKey(), ecorePackage.getEString(), "targetKey", null, 1, 1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelationship_Target(), this.getArtifact(), this.getArtifact_InboundRelationships(), "target", null, 0, 1, Relationship.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getRelationship_Style(), theDiagramPackage.getConnection(), null, "style", null, 0, 1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relationshipEntryEClass, Map.Entry.class, "RelationshipEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRelationshipEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelationshipEntry_Value(), this.getRelationship(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(flowElementEClass, FlowElement.class, "FlowElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFlowElement_Outputs(), this.getTransitionEntry(), null, "outputs", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_Inputs(), this.getTransition(), null, "inputs", null, 0, -1, FlowElement.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_Calls(), this.getCallEntry(), null, "calls", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_Invocations(), this.getCall(), null, "invocations", null, 0, -1, FlowElement.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_InputArtifacts(), this.getArtifact(), this.getArtifact_InputFor(), "inputArtifacts", null, 0, -1, FlowElement.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFlowElement_InputArtifactKeys(), ecorePackage.getEString(), "inputArtifactKeys", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_OutputArtifacts(), this.getArtifact(), this.getArtifact_OutputFor(), "outputArtifacts", null, 0, -1, FlowElement.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFlowElement_OutputArtifactKeys(), ecorePackage.getEString(), "outputArtifactKeys", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_Participants(), this.getParticipant(), this.getParticipant_Participates(), "participants", null, 0, -1, FlowElement.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFlowElement_ParticipantKeys(), ecorePackage.getEString(), "participantKeys", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_Resources(), this.getResource(), null, "resources", null, 0, -1, FlowElement.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFlowElement_ResourceKeys(), ecorePackage.getEString(), "resourceKeys", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlowElement_ArtifactResponsibilities(), this.getArtifactParticipantResponsibility(), null, "artifactResponsibilities", null, 0, -1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getFlowElement_ArtifactResponsibilities().getEKeys().add(this.getArtifactParticipantResponsibility_ArtifactKey());
		initEAttribute(getFlowElement_SortGroup(), ecorePackage.getEString(), "sortGroup", null, 0, 1, FlowElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(flowElementEntryEClass, Map.Entry.class, "FlowElementEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFlowElementEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getFlowElement());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getFlowElementEntry_Value(), g1, null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionEClass, Transition.class, "Transition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransition_Payload(), this.getArtifact(), this.getArtifact_PayloadFor(), "payload", null, 0, -1, Transition.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransition_PayloadKeys(), ecorePackage.getEString(), "payloadKeys", null, 0, -1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransition_TargetKey(), ecorePackage.getEString(), "targetKey", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getFlowElement());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getTransition_Target(), g1, null, "target", null, 0, 1, Transition.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(transitionEntryEClass, Map.Entry.class, "TransitionEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransitionEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransitionEntry_Value(), this.getTransition(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(callEClass, Call.class, "Call", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCall_Response(), this.getArtifact(), this.getArtifact_ResponseFor(), "response", null, 0, -1, Call.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getCall_ResponseKeys(), ecorePackage.getEString(), "responseKeys", null, 0, -1, Call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(callEntryEClass, Map.Entry.class, "CallEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCallEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCallEntry_Value(), this.getCall(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityEClass, Activity.class, "Activity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActivity_Services(), this.getService(), this.getService_Target(), "services", null, 0, -1, Activity.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(activityEntryEClass, Map.Entry.class, "ActivityEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActivityEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getActivity());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getActivityEntry_Value(), g1, null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceEClass, Service.class, "Service", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(this.getActivity());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getService_Target(), g1, this.getActivity_Services(), "target", null, 0, 1, Service.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getService_TargetKey(), ecorePackage.getEString(), "targetKey", null, 1, 1, Service.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(flowEClass, Flow.class, "Flow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFlow_Elements(), this.getFlowElementEntry(), null, "elements", null, 0, -1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFlow_Partition(), ecorePackage.getEBoolean(), "partition", null, 0, 1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pseudoStateEClass, PseudoState.class, "PseudoState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPseudoState_Type(), ecorePackage.getEString(), "type", null, 0, 1, PseudoState.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(choiceEClass, Choice.class, "Choice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(endEClass, End.class, "End", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(entryPointEClass, EntryPoint.class, "EntryPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(exitPointEClass, ExitPoint.class, "ExitPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(expansionInputEClass, ExpansionInput.class, "ExpansionInput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(expansionOutputEClass, ExpansionOutput.class, "ExpansionOutput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(forkEClass, Fork.class, "Fork", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(inputPinEClass, InputPin.class, "InputPin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(joinEClass, Join.class, "Join", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(outputPinEClass, OutputPin.class, "OutputPin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(startEClass, Start.class, "Start", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// urn:org.nasdanika
		createUrnorgAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
	}

	/**
	 * Initializes the annotations for <b>urn:org.nasdanika</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createUrnorgAnnotations() {
		String source = "urn:org.nasdanika";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "diagram", "navigation"
		   });
		addAnnotation
		  (getPackageElement_Prototype(),
		   source,
		   new String[] {
			   "loadable", "false"
		   });
		addAnnotation
		  (getPackageElement_Documentation(),
		   source,
		   new String[] {
			   "load-doc-reference", "doc/package-element--documentation.md"
		   });
		addAnnotation
		  (getPackageElement_Representations(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "load-doc-reference", "doc/package-element--representations.md"
		   });
		addAnnotation
		  (getPackageElement_Properties(),
		   source,
		   new String[] {
			   "reference-type", "map: \n  ns-uri: urn:org.nasdanika.ncore\n  name: MapProperty\nlist:\n  ns-uri: urn:org.nasdanika.ncore\n  name: ListProperty\nstring:\n  ns-uri: urn:org.nasdanika.ncore\n  name: StringProperty\n",
			   "value-feature", "value",
			   "load-doc-reference", "doc/package-element--properties.md"
		   });
		addAnnotation
		  (getRepresentationEntry_Value(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (packageEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/package.md",
			   "load-doc-reference", "doc/package-load-doc.md"
		   });
		addAnnotation
		  (getPackage_SuperPackages(),
		   source,
		   new String[] {
			   "feature-key", "extends"
		   });
		addAnnotation
		  (getPackageEntry_Value(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (serviceProviderEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/service-provider.md"
		   });
		addAnnotation
		  (participantEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/participant.md",
			   "load-doc-reference", "doc/participant-load-doc.md"
		   });
		addAnnotation
		  (getParticipant_BaseKeys(),
		   source,
		   new String[] {
			   "feature-key", "bases"
		   });
		addAnnotation
		  (getParticipant_Bases(),
		   source,
		   new String[] {
			   "opposite", "specializations"
		   });
		addAnnotation
		  (getParticipantEntry_Value(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (resourceEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/resource.md",
			   "load-doc-reference", "doc/participant-load-doc.md"
		   });
		addAnnotation
		  (getResource_Artifacts(),
		   source,
		   new String[] {
			   "opposite", "repositories"
		   });
		addAnnotation
		  (getResource_UsedIn(),
		   source,
		   new String[] {
			   "opposite", "resources"
		   });
		addAnnotation
		  (getResource_UsedBy(),
		   source,
		   new String[] {
			   "opposite", "resources"
		   });
		addAnnotation
		  (getResourceEntry_Value(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getParticipantResponsibility_ResponsibleKeys(),
		   source,
		   new String[] {
			   "feature-key", "responsible"
		   });
		addAnnotation
		  (getParticipantResponsibility_AccountableKeys(),
		   source,
		   new String[] {
			   "feature-key", "accountable"
		   });
		addAnnotation
		  (getParticipantResponsibility_ConsultedKeys(),
		   source,
		   new String[] {
			   "feature-key", "consulted"
		   });
		addAnnotation
		  (getParticipantResponsibility_InformedKeys(),
		   source,
		   new String[] {
			   "feature-key", "informed"
		   });
		addAnnotation
		  (artifactEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/artifact.md",
			   "load-doc-reference", "doc/artifact-load-doc.md"
		   });
		addAnnotation
		  (getArtifact_RepositoryKeys(),
		   source,
		   new String[] {
			   "feature-key", "repositories"
		   });
		addAnnotation
		  (getArtifact_OutboundRelationships(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "strict-containment", "true",
			   "feature-key", "relationships"
		   });
		addAnnotation
		  (getArtifact_Style(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "load-doc-reference", "doc/artifact--style-load-doc.md"
		   });
		addAnnotation
		  (getArtifact_TemplateKeys(),
		   source,
		   new String[] {
			   "feature-key", "templates"
		   });
		addAnnotation
		  (getArtifactEntry_Value(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (artifactParticipantResponsibilityEClass,
		   source,
		   new String[] {
			   "load-doc-reference", "doc/artifact-participant-responsibility-load-doc.md"
		   });
		addAnnotation
		  (getArtifactParticipantResponsibility_ArtifactKey(),
		   source,
		   new String[] {
			   "feature-key", "artifact"
		   });
		addAnnotation
		  (relationshipEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/relationship.md",
			   "load-doc-reference", "doc/relationship-load-doc.md"
		   });
		addAnnotation
		  (getRelationship_TargetKey(),
		   source,
		   new String[] {
			   "feature-key", "target",
			   "default-feature", "true"
		   });
		addAnnotation
		  (getRelationship_Style(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getRelationshipEntry_Value(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "strict-containment", "true"
		   });
		addAnnotation
		  (flowElementEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow-element.md"
		   });
		addAnnotation
		  (getFlowElement_Outputs(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "strict-containment", "true"
		   });
		addAnnotation
		  (getFlowElement_Calls(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "strict-containment", "true"
		   });
		addAnnotation
		  (getFlowElement_InputArtifactKeys(),
		   source,
		   new String[] {
			   "feature-key", "input-artifacts"
		   });
		addAnnotation
		  (getFlowElement_OutputArtifactKeys(),
		   source,
		   new String[] {
			   "feature-key", "output-artifacts"
		   });
		addAnnotation
		  (getFlowElement_ParticipantKeys(),
		   source,
		   new String[] {
			   "feature-key", "participants"
		   });
		addAnnotation
		  (getFlowElement_ResourceKeys(),
		   source,
		   new String[] {
			   "feature-key", "resources"
		   });
		addAnnotation
		  (getFlowElement_ArtifactResponsibilities(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (transitionEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/transition.md",
			   "load-doc-reference", "doc/transition-load-doc.md"
		   });
		addAnnotation
		  (getTransition_PayloadKeys(),
		   source,
		   new String[] {
			   "feature-key", "payload"
		   });
		addAnnotation
		  (getTransition_TargetKey(),
		   source,
		   new String[] {
			   "feature-key", "target",
			   "default-feature", "true"
		   });
		addAnnotation
		  (getTransitionEntry_Value(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "strict-containment", "true"
		   });
		addAnnotation
		  (callEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/call.md",
			   "load-doc-reference", "doc/call-load-doc.md"
		   });
		addAnnotation
		  (getCall_ResponseKeys(),
		   source,
		   new String[] {
			   "feature-key", "response"
		   });
		addAnnotation
		  (getCallEntry_Value(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (activityEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/activity.md",
			   "load-doc-reference", "doc/activity-load-doc.md"
		   });
		addAnnotation
		  (serviceEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/service.md",
			   "load-doc-reference", "doc/service-load-doc.md"
		   });
		addAnnotation
		  (getService_TargetKey(),
		   source,
		   new String[] {
			   "feature-key", "target"
		   });
		addAnnotation
		  (flowEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/flow.md",
			   "load-doc-reference", "doc/flow-load-doc.md"
		   });
		addAnnotation
		  (pseudoStateEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/pseudo-state.md"
		   });
		addAnnotation
		  (choiceEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/choice.md",
			   "load-doc-reference", "doc/choice-load-doc.md"
		   });
		addAnnotation
		  (endEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/end.md",
			   "load-doc-reference", "doc/end-load-doc.md"
		   });
		addAnnotation
		  (entryPointEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/entry-point.md"
		   });
		addAnnotation
		  (exitPointEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/exit-point.md"
		   });
		addAnnotation
		  (expansionInputEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/expansion-input.md"
		   });
		addAnnotation
		  (expansionOutputEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/expansion-output.md"
		   });
		addAnnotation
		  (forkEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/fork.md"
		   });
		addAnnotation
		  (inputPinEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/input-pin.md"
		   });
		addAnnotation
		  (joinEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/join.md"
		   });
		addAnnotation
		  (outputPinEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/output-pin.md"
		   });
		addAnnotation
		  (startEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/start.md",
			   "load-doc-reference", "doc/start-load-doc.md"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGenModelAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/GenModel";
		addAnnotation
		  (packageElementEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for elements which can be contained in a package and as such inherited, overridden, and suppressed."
		   });
		addAnnotation
		  (getPackageElement__Create(),
		   source,
		   new String[] {
			   "documentation", "Creates a new package element of the same type as this element with ``prototype`` reference to this package element.\nFor top-level packages this method also calls ``apply()`` and then ``resolve()``.\n"
		   });
		addAnnotation
		  (getPackageElement__Apply__PackageElement(),
		   source,
		   new String[] {
			   "documentation", "Applies configuration of this element, including inherited configuration, to the argument. \nThis method shall be called after ``create()`` and shall create contained elements.\nCross-reference resolution shall be done in ``resolve()`` which is called after ``apply()`` and as such contained elements are already created.\n"
		   });
		addAnnotation
		  (getPackageElement__Resolve__PackageElement(),
		   source,
		   new String[] {
			   "documentation", "Resolves cross-references."
		   });
		addAnnotation
		  (getPackageElement_Prototype(),
		   source,
		   new String[] {
			   "documentation", "This reference is set by ``create()`` method and points to a package element which created and configured this element."
		   });
		addAnnotation
		  (getPackageElement_Extensions(),
		   source,
		   new String[] {
			   "documentation", "Derived reference - known elements extending this element.\nKnown means elements in the same resource set."
		   });
		addAnnotation
		  (getPackageElement_Extends(),
		   source,
		   new String[] {
			   "documentation", "Elements can  extend other elements and inherit their configuration - set attributes and references. \nFor top-level packages inheritance shall be explicitly set using ``superPackages`` reference.\nFor nested packages and other package elements ``extends`` is derived from containing packages.\nFor nested packages super-packages derived from containment precede explicitly specified super-packages, \ni.e. the explicitly specified super-packages (mix-ins) can override configuration inherited via containment.\n"
		   });
		addAnnotation
		  (getPackageElement_Modifiers(),
		   source,
		   new String[] {
			   "documentation", "A collection of boolean flags:\n\n* ``abstract`` - Specifies that this package element is abstract. For packages and flows it means that they contain abstract elements and must be extended to become concrete. If a package or a flow contains abstract elements and does not have abstract modifier, it is diagnosed as an error. If concrete packages and flows extend abstract ones they must override (implement) all abstract elements.\n* ``explicit-end`` - Applies to [flows](Flow.html). Specifies that the [end](End.html) [pseudo-state](PseudoState.html) shall not be inferred by finding flow elements with no outputs. End will either be explicitly specified or the diagram will not have an end pseudo-state.\n* ``explicit-start`` - Applies to flows. Specifies that the [start](Start.html) pseudo-state shall not be inferred by finding flow elements with no inputs. Start will either be explicitly specified or the diagram will not have a start pseudo-state.\n* ``final`` - Specifies that this flow element cannot be overridden in flows extending this flow. Overriding a final elemen will be diagnosed as an error. For example, in an organization some processes can be defined as flows at higher levels of the orgnization and extended at lower levels. ``final`` modifier allows to specify what can be extended and what cannot. Specifying a top-level flow as final indicates that it cannot have extensions.\n* ``optional`` - Specifies that this element is optional. Optional elements have different apperance on diagrams.\n* ``extension`` - Specifies that this element is an extension for an element in one of extended packages/flows. If this modifier is present and ``extends`` reference is empty, then it results in a diagnostic error.\n* ``partition`` - Applies to flows and specifies that a flow shall be rendered as a partition (e.g. a composite state) on a diagram.\n"
		   });
		addAnnotation
		  (getPackageElement_Documentation(),
		   source,
		   new String[] {
			   "documentation", "Element documentation. Documentation elements are adapted to ``SupplierFactory<InputStream>`` during generation. \n[Exec content](https://docs.nasdanika.org/modules/core/modules/exec/modules/model/content/package-summary.html) classes such as [Markdown](https://docs.nasdanika.org/modules/core/modules/exec/modules/model/content/Markdown.html) and [Interpolator](https://docs.nasdanika.org/modules/core/modules/exec/modules/model/content/Interpolator.html) can be used as documentation elements. \n"
		   });
		addAnnotation
		  (getPackageElement_Representations(),
		   source,
		   new String[] {
			   "documentation", "Mapping of representation names to values - [Diagrams](https://docs.nasdanika.org/modules/core/modules/diagram/modules/model/Diagram.html) which serve as templates for generating diagram content from the package element."
		   });
		addAnnotation
		  (getPackageElement_Properties(),
		   source,
		   new String[] {
			   "documentation", "Properties can be used to customize the documentation generation process, e.g. provide configuration for generation of representation diagram elements."
		   });
		addAnnotation
		  (representationEntryEClass,
		   source,
		   new String[] {
			   "documentation", "Mapping of representation key to a representation (diagram). Null value suppresses inherited entry."
		   });
		addAnnotation
		  (getRepresentationEntry_Key(),
		   source,
		   new String[] {
			   "documentation", "Representation key."
		   });
		addAnnotation
		  (getRepresentationEntry_Value(),
		   source,
		   new String[] {
			   "documentation", "Representation (diagram)."
		   });
		addAnnotation
		  (getPackage_SuperPackages(),
		   source,
		   new String[] {
			   "documentation", "Package can extend other packages and inherit their elements. \nThis reference is required because ``extends`` reference is already defined in [PackageElement](PackageElement.html) as derived and immutable.\nFor top-level packages ``extends`` is the same as this reference. For nested packages ``extends`` is a union of containment-derived extensions and this reference.\n\nPackage own elements override inherited elements with the same keys. \nTo suppress an inheriIted element define an element with the same key and ``null`` value.\n\nMultiple inheritance allows to have \"mix-in\" packages. \nFor example, the primary lineage can follow the organizational hierarchy with base packages defining generic flows \nand then specializing at the lower levels of the organization, say to specific tools. \nThen there can be a technology tree, for example different cloud platforms. \nA set of flows defining development processes for a particular organization and a particular cloud technology would be a mix of the two inheritance hierarchies.\n\n"
		   });
		addAnnotation
		  (getPackage_SubPackages(),
		   source,
		   new String[] {
			   "documentation", "Packages contained in this package."
		   });
		addAnnotation
		  (getPackage_Participants(),
		   source,
		   new String[] {
			   "documentation", "Flow participants."
		   });
		addAnnotation
		  (getPackage_Resources(),
		   source,
		   new String[] {
			   "documentation", "Flow resources."
		   });
		addAnnotation
		  (getPackage_Artifacts(),
		   source,
		   new String[] {
			   "documentation", "Flow artifacts."
		   });
		addAnnotation
		  (getPackage_Activities(),
		   source,
		   new String[] {
			   "documentation", "Activities and flows."
		   });
		addAnnotation
		  (packageEntryEClass,
		   source,
		   new String[] {
			   "documentation", "Mapping of a sub-package key to a sub-package. Null value suppresses inherited entry."
		   });
		addAnnotation
		  (getPackageEntry_Key(),
		   source,
		   new String[] {
			   "documentation", "Sub-package key."
		   });
		addAnnotation
		  (getPackageEntry_Value(),
		   source,
		   new String[] {
			   "documentation", "Sub-package. Use ``null`` to suppress inherited sub-package."
		   });
		addAnnotation
		  (getServiceProvider_Services(),
		   source,
		   new String[] {
			   "documentation", "Services provided by a this service provider. "
		   });
		addAnnotation
		  (getParticipant_Participates(),
		   source,
		   new String[] {
			   "documentation", "Flow elements this participant participates in."
		   });
		addAnnotation
		  (getParticipant_Resources(),
		   source,
		   new String[] {
			   "documentation", "Resources which this participant uses in their activities."
		   });
		addAnnotation
		  (getParticipant_Artifacts(),
		   source,
		   new String[] {
			   "documentation", "Artifacts which this participant uses in their activities."
		   });
		addAnnotation
		  (getParticipant_Specializations(),
		   source,
		   new String[] {
			   "documentation", "Specializations of this participant."
		   });
		addAnnotation
		  (getParticipant_BaseKeys(),
		   source,
		   new String[] {
			   "documentation", "Keys of base participants, which this participant is a specialization of, relative to the containing package ``participants/`` reference."
		   });
		addAnnotation
		  (getParticipant_Bases(),
		   source,
		   new String[] {
			   "documentation", "Base participants, which this participant is a specialization of. E.g. Junior Developer and Senior Developer can be specializations of Developer, which in turn can be a specialization of Agile Team Member."
		   });
		addAnnotation
		  (getParticipant_Responsible(),
		   source,
		   new String[] {
			   "documentation", "Flow elements (activities, flows) this participant is responsible for, i.e. does the work to complete them. E.g. Product Owner is responsible for backlog grooming."
		   });
		addAnnotation
		  (getParticipant_Accountable(),
		   source,
		   new String[] {
			   "documentation", "Flow elements (activities, flows) this participant is ultimately answerable for the correct and thorough completion."
		   });
		addAnnotation
		  (getParticipant_Consulted(),
		   source,
		   new String[] {
			   "documentation", "Flow elements (activities, flows) for which this participant\'s opinions are sought, e.g. the participant is a subject-matter expert."
		   });
		addAnnotation
		  (getParticipant_Informed(),
		   source,
		   new String[] {
			   "documentation", "Flow elements (activities, flows) about which this participant is kept up-to-date on progress."
		   });
		addAnnotation
		  (getParticipant_Children(),
		   source,
		   new String[] {
			   "documentation", "Participants can be organized into a hierarchy (Organizational Structure)."
		   });
		addAnnotation
		  (participantEntryEClass,
		   source,
		   new String[] {
			   "documentation", "Mapping of a participant key to a participant. Null value suppresses inherited entry."
		   });
		addAnnotation
		  (getParticipantEntry_Key(),
		   source,
		   new String[] {
			   "documentation", "Participant key."
		   });
		addAnnotation
		  (getParticipantEntry_Value(),
		   source,
		   new String[] {
			   "documentation", "Participant. Use ``null`` to suppress inherited participant."
		   });
		addAnnotation
		  (getResource_Artifacts(),
		   source,
		   new String[] {
			   "documentation", "Artifacts which can be stored in this resource. E.g. source code can be stored in a source control system. Derived opposite to Artifact.repositories."
		   });
		addAnnotation
		  (getResource_UsedIn(),
		   source,
		   new String[] {
			   "documentation", "Flow elements which use this resource."
		   });
		addAnnotation
		  (getResource_UsedBy(),
		   source,
		   new String[] {
			   "documentation", "Participants which use this resource in their activities."
		   });
		addAnnotation
		  (getResource_Children(),
		   source,
		   new String[] {
			   "documentation", "Resources can be organized into a hierarchy (Domains)."
		   });
		addAnnotation
		  (resourceEntryEClass,
		   source,
		   new String[] {
			   "documentation", "Mapping of resource key to a resource. Null value suppresses inherited entry."
		   });
		addAnnotation
		  (getResourceEntry_Key(),
		   source,
		   new String[] {
			   "documentation", "Resource key."
		   });
		addAnnotation
		  (getResourceEntry_Value(),
		   source,
		   new String[] {
			   "documentation", "Resource. Use ``null`` to suppress inherited resource."
		   });
		addAnnotation
		  (participantResponsibilityEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for elements for which participants can responsible for, accountable, consulted, or informed. See [Responsibility Assignment Matrix](https://en.wikipedia.org/wiki/Responsibility_assignment_matrix) for more details."
		   });
		addAnnotation
		  (getParticipantResponsibility_Responsible(),
		   source,
		   new String[] {
			   "documentation", "Participant(s) responsible for the element, e.g. for completion of an activity."
		   });
		addAnnotation
		  (getParticipantResponsibility_ResponsibleKeys(),
		   source,
		   new String[] {
			   "documentation", "Keys of responsible participants relative to the containing package ``participants/`` reference."
		   });
		addAnnotation
		  (getParticipantResponsibility_Accountable(),
		   source,
		   new String[] {
			   "documentation", "Participant(s) accountable for the element, i.e. ultimately answerable for the correct and thorough completion of an activity or artifact. Accountable must sign off (approve) work that _responsible_ provides."
		   });
		addAnnotation
		  (getParticipantResponsibility_AccountableKeys(),
		   source,
		   new String[] {
			   "documentation", "Keys of accountable participants relative to the containing package ``participants/`` reference."
		   });
		addAnnotation
		  (getParticipantResponsibility_Consulted(),
		   source,
		   new String[] {
			   "documentation", "Participant(s) whose opinions are sought, typically subject-matter experts."
		   });
		addAnnotation
		  (getParticipantResponsibility_ConsultedKeys(),
		   source,
		   new String[] {
			   "documentation", "Keys of consulted participants relative to the containing package ``participants/`` reference."
		   });
		addAnnotation
		  (getParticipantResponsibility_Informed(),
		   source,
		   new String[] {
			   "documentation", "Participant(s) who are kept up-to-date on progress and with whom there is one-way communication."
		   });
		addAnnotation
		  (getParticipantResponsibility_InformedKeys(),
		   source,
		   new String[] {
			   "documentation", "Keys of informed participants relative to the containing package ``participants/`` reference."
		   });
		addAnnotation
		  (getArtifact_Repositories(),
		   source,
		   new String[] {
			   "documentation", "Resources in which this artifact can be stored. E.g. source code artifact can be stored in Git resource, and jar artifact can be stored in Maven repository resource."
		   });
		addAnnotation
		  (getArtifact_RepositoryKeys(),
		   source,
		   new String[] {
			   "documentation", "Keys of artifact\'s repositories relative to the containing package ``resources/`` reference."
		   });
		addAnnotation
		  (getArtifact_InputFor(),
		   source,
		   new String[] {
			   "documentation", "Flow elements which take this artifact as an input."
		   });
		addAnnotation
		  (getArtifact_OutputFor(),
		   source,
		   new String[] {
			   "documentation", "Flow elements which output this artifact."
		   });
		addAnnotation
		  (getArtifact_PayloadFor(),
		   source,
		   new String[] {
			   "documentation", "Transitions for which this artifact is a part of their payload. Derived opposite to Transition payload."
		   });
		addAnnotation
		  (getArtifact_ResponseFor(),
		   source,
		   new String[] {
			   "documentation", "Calls for which this artifact is a part of their response. Derived opposite to Call response."
		   });
		addAnnotation
		  (getArtifact_UsedBy(),
		   source,
		   new String[] {
			   "documentation", "Participants which use this artifact in their activities."
		   });
		addAnnotation
		  (getArtifact_Responsibilities(),
		   source,
		   new String[] {
			   "documentation", "Responsibilities for this artifact at a flow element level if different from the flow element responsibilities. E.g. a participant responsible for an activity may delegate work on some artifact to another participant. In this case the activity responsible becomes accountable for the artifact and the delegate participant becomes responsible."
		   });
		addAnnotation
		  (getArtifact_Children(),
		   source,
		   new String[] {
			   "documentation", "Artifacts can be organized into a hierarchy (Product Breakdown Structure)."
		   });
		addAnnotation
		  (getArtifact_OutboundRelationships(),
		   source,
		   new String[] {
			   "documentation", "Outbound relationships to other artifacts. Artifact relationships can be used for modeling composite artifacts, e.g. modular/distributed systems such as cloud applications."
		   });
		addAnnotation
		  (getArtifact_InboundRelationships(),
		   source,
		   new String[] {
			   "documentation", "Relationships which have this artifact as a target."
		   });
		addAnnotation
		  (getArtifact_Partition(),
		   source,
		   new String[] {
			   "documentation", "If true, this composite artifact shall be displayed as a partition on the parent component diagram."
		   });
		addAnnotation
		  (getArtifact_Style(),
		   source,
		   new String[] {
			   "documentation", "Diagram element style for component diagrams. If specified, the style diagram element is used as a template for a diagram element created to represent this artiact on a diagram."
		   });
		addAnnotation
		  (getArtifact_TemplateKeys(),
		   source,
		   new String[] {
			   "documentation", "Keys of artifact\'s templates relative to the artifact URI."
		   });
		addAnnotation
		  (getArtifact_Templates(),
		   source,
		   new String[] {
			   "documentation", "Artifacts can be organized into an inheritance hierarchy using templates reference. Artifacts inherit visual representation of templates. E.g. there might be an artifact \"Cloud Cache\" with multiple instances in different cloud solutions."
		   });
		addAnnotation
		  (getArtifact_Instances(),
		   source,
		   new String[] {
			   "documentation", "Artifacts for which this artifact is a template. Derived opposite to templates."
		   });
		addAnnotation
		  (artifactEntryEClass,
		   source,
		   new String[] {
			   "documentation", "A mapping of an artifact key to an artifact. Null value suppresses inherited entry."
		   });
		addAnnotation
		  (getArtifactEntry_Key(),
		   source,
		   new String[] {
			   "documentation", "Artifact key."
		   });
		addAnnotation
		  (getArtifactEntry_Value(),
		   source,
		   new String[] {
			   "documentation", "Flow artifact. Use ``null`` to suppress inherited artifact."
		   });
		addAnnotation
		  (artifactParticipantResponsibilityEClass,
		   source,
		   new String[] {
			   "documentation", "Responsibility assignments for an artifact at the activity level if different from the activity responsibility assignments. "
		   });
		addAnnotation
		  (getArtifactParticipantResponsibility_ArtifactKey(),
		   source,
		   new String[] {
			   "documentation", "Key of the responsibility artifact relative to the containing package ``artifacts/`` reference."
		   });
		addAnnotation
		  (getArtifactParticipantResponsibility_Artifact(),
		   source,
		   new String[] {
			   "documentation", "Artifact for which responsibilities are assigned."
		   });
		addAnnotation
		  (getArtifactParticipantResponsibility_Suppress(),
		   source,
		   new String[] {
			   "documentation", "If true, suppresses inherited responsibility for this responsibility\'s artifact."
		   });
		addAnnotation
		  (getRelationship_TargetKey(),
		   source,
		   new String[] {
			   "documentation", "Key of the relationship target relative to the containing artifact."
		   });
		addAnnotation
		  (getRelationship_Target(),
		   source,
		   new String[] {
			   "documentation", "Relationship target."
		   });
		addAnnotation
		  (getRelationship_Style(),
		   source,
		   new String[] {
			   "documentation", "Connection style (template) for component diagrams."
		   });
		addAnnotation
		  (relationshipEntryEClass,
		   source,
		   new String[] {
			   "documentation", "Mapping of relationship keys (qualifiers) to a relationships. Null value suppresses inherited entry."
		   });
		addAnnotation
		  (getRelationshipEntry_Key(),
		   source,
		   new String[] {
			   "documentation", "Relationship key."
		   });
		addAnnotation
		  (getRelationshipEntry_Value(),
		   source,
		   new String[] {
			   "documentation", "Outbound relationshipt. Use ``null`` to suppress inherited relationship."
		   });
		addAnnotation
		  (getFlowElement_Outputs(),
		   source,
		   new String[] {
			   "documentation", "Outbound transitions to other flow elements."
		   });
		addAnnotation
		  (getFlowElement_Inputs(),
		   source,
		   new String[] {
			   "documentation", "Transitions which have this flow element as their target."
		   });
		addAnnotation
		  (getFlowElement_Calls(),
		   source,
		   new String[] {
			   "documentation", "Calls to other flow elements."
		   });
		addAnnotation
		  (getFlowElement_Invocations(),
		   source,
		   new String[] {
			   "documentation", "Calls which have this flow element as their target."
		   });
		addAnnotation
		  (getFlowElement_InputArtifacts(),
		   source,
		   new String[] {
			   "documentation", "Input artifacts for this flow element. E.g. artifacts required to start working on an activity."
		   });
		addAnnotation
		  (getFlowElement_InputArtifactKeys(),
		   source,
		   new String[] {
			   "documentation", "Keys of input artifacts resolved relative to the containing package ``artifacts/`` reference."
		   });
		addAnnotation
		  (getFlowElement_OutputArtifacts(),
		   source,
		   new String[] {
			   "documentation", "Output artifacts of this flow element."
		   });
		addAnnotation
		  (getFlowElement_OutputArtifactKeys(),
		   source,
		   new String[] {
			   "documentation", "Keys of output artifacts resolved relative to the containing package ``artifacts/`` reference."
		   });
		addAnnotation
		  (getFlowElement_Participants(),
		   source,
		   new String[] {
			   "documentation", "Participants of this flow element, e.g. people working on an activity. "
		   });
		addAnnotation
		  (getFlowElement_ParticipantKeys(),
		   source,
		   new String[] {
			   "documentation", "Keys of participants resolved relative to the containing package ``participants/`` reference."
		   });
		addAnnotation
		  (getFlowElement_Resources(),
		   source,
		   new String[] {
			   "documentation", "Resources used by participants of this flow element. E.g. tools used to complete an activity."
		   });
		addAnnotation
		  (getFlowElement_ResourceKeys(),
		   source,
		   new String[] {
			   "documentation", "Keys of resources resolved relative to the containing package ``resources/`` reference."
		   });
		addAnnotation
		  (getFlowElement_ArtifactResponsibilities(),
		   source,
		   new String[] {
			   "documentation", "Artifact responsibility assignments if different from the flow element responsibility assignments. E.g. a person responsible for an activity may delegate responsibility for some artifact to another participant. In this case another participant becomes responsible and the participant responsible for the activity becomes accounable for the artifact."
		   });
		addAnnotation
		  (getFlowElement_SortGroup(),
		   source,
		   new String[] {
			   "documentation", "During documentation generation flow elements in the flow are sorted in the order of dependency and then alphabetically.\nI.e. if there is a transition from \"Plan\" to \"Execute\" then \"Plan\" will appear in the list of flow elements before \"Execute\".\nSort groups can be used to customize the default sorting behavior by scoping sorting to a particular group of elements.\nGroups themselves are sorted alphabetically. Elements without sort group appear before elements with an assigned sort group.\nFor full control of sorting assign a different sort group for each flow element."
		   });
		addAnnotation
		  (flowElementEntryEClass,
		   source,
		   new String[] {
			   "documentation", "Mapping of a flow element key to a flow element. Null value suppresses inherited entry."
		   });
		addAnnotation
		  (getFlowElementEntry_Key(),
		   source,
		   new String[] {
			   "documentation", "Flow element key."
		   });
		addAnnotation
		  (getFlowElementEntry_Value(),
		   source,
		   new String[] {
			   "documentation", "Flow element. Use ``null`` to suppress inherited flow element."
		   });
		addAnnotation
		  (getTransition_Payload(),
		   source,
		   new String[] {
			   "documentation", "Artifacts passed from the source element to the target element."
		   });
		addAnnotation
		  (getTransition_PayloadKeys(),
		   source,
		   new String[] {
			   "documentation", "Keys of payload artifacts relative to the containing package ``artifacts/`` reference."
		   });
		addAnnotation
		  (getTransition_TargetKey(),
		   source,
		   new String[] {
			   "documentation", "Key of transition target relative to the containing flow ``elements/`` reference."
		   });
		addAnnotation
		  (getTransition_Target(),
		   source,
		   new String[] {
			   "documentation", "Transition target."
		   });
		addAnnotation
		  (transitionEntryEClass,
		   source,
		   new String[] {
			   "documentation", "Mapping of an output key to a transition. Null value suppresses inherited entry."
		   });
		addAnnotation
		  (getTransitionEntry_Key(),
		   source,
		   new String[] {
			   "documentation", "Output key."
		   });
		addAnnotation
		  (getTransitionEntry_Value(),
		   source,
		   new String[] {
			   "documentation", "Output transition. Use ``null`` to suppress inherited transition."
		   });
		addAnnotation
		  (getCall_Response(),
		   source,
		   new String[] {
			   "documentation", "Response artifacts passed back from the target to the source activity."
		   });
		addAnnotation
		  (getCall_ResponseKeys(),
		   source,
		   new String[] {
			   "documentation", "Keys of response artifacts relative to the containing package ``artifacts/`` reference."
		   });
		addAnnotation
		  (callEntryEClass,
		   source,
		   new String[] {
			   "documentation", "Mapping of a call key to a call. Null value suppresses inherited entry."
		   });
		addAnnotation
		  (getCallEntry_Key(),
		   source,
		   new String[] {
			   "documentation", "Call key."
		   });
		addAnnotation
		  (getCallEntry_Value(),
		   source,
		   new String[] {
			   "documentation", "Call. Use ``null`` to suppress inherited call."
		   });
		addAnnotation
		  (getActivity_Services(),
		   source,
		   new String[] {
			   "documentation", "Derived opposite to Service target."
		   });
		addAnnotation
		  (activityEntryEClass,
		   source,
		   new String[] {
			   "documentation", "Mapping of activity key to an activity or a flow. Null value suppresses inherited entry."
		   });
		addAnnotation
		  (getActivityEntry_Key(),
		   source,
		   new String[] {
			   "documentation", "Activity key."
		   });
		addAnnotation
		  (getActivityEntry_Value(),
		   source,
		   new String[] {
			   "documentation", "Activity or flow. Use ``null`` to suppress inherited activity."
		   });
		addAnnotation
		  (getService_Target(),
		   source,
		   new String[] {
			   "documentation", "Target activity of the service."
		   });
		addAnnotation
		  (getService_TargetKey(),
		   source,
		   new String[] {
			   "documentation", "Key of service target activity relative to the containing package."
		   });
		addAnnotation
		  (getFlow_Elements(),
		   source,
		   new String[] {
			   "documentation", "Elements of this flow."
		   });
		addAnnotation
		  (getFlow_Partition(),
		   source,
		   new String[] {
			   "documentation", "If true, this flow shall be displayed as a partition on the parent flow diagram."
		   });
		addAnnotation
		  (getPseudoState_Type(),
		   source,
		   new String[] {
			   "documentation", "Pseudo-state type provided by concrete sub-classes."
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";
		addAnnotation
		  (packageElementEClass,
		   source,
		   new String[] {
			   "constraints", "final extension"
		   });
		addAnnotation
		  (packageEClass,
		   source,
		   new String[] {
			   "constraints", "test"
		   });
	}

} //FlowPackageImpl
