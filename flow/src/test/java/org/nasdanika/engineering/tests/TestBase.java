package org.nasdanika.engineering.tests;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Util;
import org.nasdanika.engineering.ModelElement;
import org.nasdanika.engineering.NamedElement;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;

/**
 * Common methods for testing
 * @author Pavel
 *
 */
public class TestBase {

/* Packages, classes, features, operations to test
engineering
	representation
		ComponentDiagram
			depthOut
			depthIn
	flow
		JourneyElement
			outputs
			calls
			requirements
			deliverables
			personas
			overrides
			overridenBy
			modifiers
			getInputs()
			getInvocations()
			getAllInputs()
			getAllInvocations()
			getAllOutputs()
			getAllCalls()
			overrides()
		Activity
			features
			services
		Service
			target
		Transition
			payload
			suppress
			target
			lag
			getTarget()
		Call
			response
		Journey
			elements
			extends
			extensions
			root
			allElements
		PseudoState
			type
		Choice
		End
		EntryPoint
		ExitPoint
		ExpansionInput
		ExpansionOutput
		Fork
		InputPin
		Join
		OutputPin
		Start
	Adaptable
	ModelElement
		uri
		path
		description
		markdownDescription
		resources
		appearance
		tableOfContents
		sections
		representations
	TableOfContents
		role
		depth
		header
	Duration
	Instant
	Temporal
		instant
		base
		offset
		derivatives
		lowerBounds
		upperBounds
		after()
		before()
		coincides()
		normalize()
		minus()
		minus()
		plus()
	Period
		start
		end
		duration
	NamedElement
		name
	Event
	Endeavor
		completion
		benefit
		totalCost
		assignee
		allIssues
		capacity
		objectives
		linkedObjectives
		allObjectives
	Increment
		children
		issues
		releases
	IssueCategory
		weight
		issues
		cumulative
		allocations
	IssueStatus
		issues
		done
	IssuePriority
		issues
	IssueSeverity
		issues
	Issue
		children
		requires
		increment
		contributesTo
		notes
		categories
		target
		status
		workable
		releases
		effort
		cost
		remainingEffort
		remainingCost
		priority
		severity
	Note
		author
		date
		effort
		cost
		remainingEffort
		remainingCost
		status
	EngineeredElement
		issues
		owners
		experts
		allocations
		principles
		allIssues
	Document
		content
		markdownContent
	Persona
		journeyElements
		goals
		representatives
		extends
		extensions
	Engineer
		modules
		owns
		expertise
		increments
		services
		personas
		assignments
		issueCategories
		issueStatuses
		issuePriorities
		issueSeverities
		rate
		designations
		capacity
		represents
		messages
		objectives
	Organization
		engineers
	Module
		modules
		dependencies
		dependants
	Product
		releases
		features
		activities
	Capability
		requiredBy
		available
	EngineeredCapability
	Release
		increment
		features
		issues
	Feature
		releases
		activities
		issues
		uses
	Directory
		elements
	Capacity
		endeavor
		effort
		funds
		rate
	Allocation
		engineer
		category
	Alignable
		aligns
	Aim
		alignments
	Principle
		children
	Alignment
		aim
		weight
	Goal
		children
	Forum
		discussion
		topics
	Message
		date
		author
	Topic
		messages
	PackageAppearance
		modelElements
		subPackages
	PackageAppearanceEntry
		key
		value
	Appearance
		label
		icon
		description
		markdownDescription
		roles
		sectionStyle
	AppearanceEntry
		key
		value
	ModelElementAppearance
		features
		operations
		actions
	ModelElementAppearanceEntry
		key
		value
	MemberAppearanceEntry
		key
		value
	MemberAppearance
		category
	SectionStyle
	NamedElementReference
		target
	Link
		target
	KeyResult
		weight
		completion
		initiatives
	Objective
		endeavor
		keyResults
		children
		parent
		linkedObjectives
		subObjectives
	Decision
		effectiveStart
		effectiveEnd
		supercedes
		supercededBy
		resolution
 */
	
	public static void dumpToYaml(EObject eObject) {
		DumperOptions dumperOptions = new DumperOptions();
		dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK); dumperOptions.setIndent(4); 
		new Yaml(dumperOptions).dump(dump(eObject), new OutputStreamWriter(System.out));
	}
	
	/**
	 * Dumps {@link EObject} to {@link Map} for to further dump to YAML. 
	 * Outputs class, path, URI, and name.
	 * @param eObject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> dump(EObject eObject) {
		Map<String,Object> ret = new LinkedHashMap<>(); 
		ret.put("class", eObject.eClass().getName());
		if (eObject instanceof ModelElement) {
			String path = ((ModelElement) eObject).getPath();
			if (!Util.isBlank(path)) { 
				ret.put("path", path);
			}
			ret.put("uri", ((ModelElement) eObject) .getUri()); 
			if (ret instanceof NamedElement) {
				ret.put("path", ((NamedElement) eObject).getName());
			}
		}
		for (EReference ref: eObject.eClass().getEAllReferences()) {
			if (ref. isContainment()) { 
				if (ref.isMany()) {
					Collection<Map<String,Object>> elements = new ArrayList<>(); 
					for (EObject el: ((Collection<EObject>) eObject.eGet(ref))) { 
						if (el != null) {
							elements.add(dump(el));
						}
					}
					if (!elements.isEmpty()) {
						ret.put(ref.getName(), elements);
					}
				} else {
					EObject val = (EObject) eObject.eGet(ref); 
					if (val != null) {
						ret.put(ref.getName(), dump(val));
					}
				}
			}
		}
		return ret;
	}



}
