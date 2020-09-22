Nasdanika Engineering model aims to provide support of an engineering practice - designing and building something complex in a series of steps over a period of time.
The model is rather large, but it doesn't have to be used in its entirety and may be consumed gradually as you mature your engineering practice.
Put a stake in the familiar ground and expand.
For example, one person/team can start with just using issues attached to components, e.g. ${ecore-doc/vinci-app/ActionBase Vinci actions} because they want to track development of a web site.
Another team may start with persona modeling in order to better understand how their future offering or offerings can satisfy needs of their target audiences. 

As of the time of this writing not all model elements are fully functional, so this document is partially a TODO list.

The important part of engineering is planning and tracking execution such as creating and assigning issues and reporting progress on them.
Traditionally execution is tracked with issue trackers, which are typically implemented as servers. Spreadsheets or text files are sometimes used for relatively small efforts.  
The modeling approach to engineering is different in from the above mentioned approaches in the following ways:

* For software development efforts models "live" with the code and follow its lifecycle - branching, commits, merges. As some of engineering model elements are executable, e.g. already mentioned Vinci actions, the models are code by definition as they contain instructions to be executed by a computer. 
* Unlike text files or spreadsheets which can also "live" with the code, models provide more structure and functionality for the engineering domain.

"As-code" approach may be attractive in the following situations:

* Early phases of product life - conceptualization, R&D, discovery. Small teams or individuals, many ideas some of which may lay dormant for a period of time or be forked into different flavors. 
* Sensitive data - with as-code approach such data may be kept in a highly secure fashion and never cross the network.
* Reduced infrastructure requirements - there is no need for an issue tracking server. 

### Core concepts

Eat the elephant one bite at a time. 
Plan the work and work the plan. Feel good about what you and your organization are doing and **not** doing by knowing that what your are doing is more important than what you are not doing or is a prerequisite for it. 

The problem is broken down into [components](Component.html), things to be done with the components are broken down into [issues](Issue.html), and working time is broken down into [increments](Increment.html).

[Engineers](AbstractEngineer.html) work in increments on components and [products](Product.html) to build [releases](Release.html) which are [offered](Offering.html) to the target audience which can be modeled as [personas](Persona.html). 
Work is done in the order of dependency and importance. 
Issue importance (relative weight) can be entered explicitly or be computed from linked [strategic objectives](Objective.html) and [persona needs](Need.html). 
This means that the model provides an analytical way to work on issues in the order of delivering maximum value and strategic alignment. 
It also means that if the methodology is agreed upon in the organization it leaves little room for a debate and as such more effort for focused execution. 

### Organization engineering

The first step in engineering is to define an [engineering organization](EngineeringOrganization.html) or [engineering organizational unit](EngineeringOrganizationalUnit.html) as part of a larger organization model.

Even in a case of an individual engineer it is required to define an organization because:

* Engineering reference data such as issue types and categories are defined at the engineering organizational unit level.
* It is more likely than not that parties other than the individual engineer will be involved in the process. 
* Defining an organization right off the start allows to evolve and scale up in the future. It also and makes it easier to transition the engineering efforts to another person or a team.

Organization engineering includes creation and filling out an engineering organization or organizational unit model. 
One can start from a template, e.g. an engineering organizational unit with pre-defined issue types, roles, engineering guides, etc.
The model can also be gradually populated on as-needed basis. For example, small efforts might not need issue categories. Or may need just a few 
initially and new ones will be added as the organization evolves. 

### Strategic planning

There mights be hundreds and thousands of issues even in a relatively small undertaking. 
How to decide which issues to work on now and which ones shall be taken care of at some future point of time?

The Nasdanika engineering model uses issue dependency and issue importance/weight to help to answer this question.
First of all, if an issue is blocked by another issue then it can't be worked on. This is an easy part.
Computing issue weight and priority is more involved.

There are the following sources for computation of an issue **effective** weight:

* Explicitly assigned weight. For example, a team decides that doing A is twice as important as doing B.
* [Category](IssueCategory.html] weight. Issues may be assigned zero or more categories. Categories can be assigned weights. Also categories may compute their effective weights based on historical data, we may call it a category debt similar to [technical debt](https://en.wikipedia.org/wiki/Technical_debt). What it means is that closed and in-flight issue data is used to compute how many issues, effort-wise, have been already allocated to each category and an effective category weight is computed from that. This allows to keep track of situations where some things (like writing documentation) are never done because they are not as important as writing code.
* Objective weight. Issues can be linked to strategic objectives, which in turn can be linked to their parent objectives. Objectives are assigned weights and an effective weight of an objective it used in computing an effective weight of issues linked to it.
* Customer benefit. An issue may be linked to [features](Feature.html). Feature benefit is computed from persona needs. Feature benefit value is used to compute issue benefit value.
* Transient weights, i.e. weights of dependent issues. For example, an issue itself doesn't directly contribute to a feature. However, it is a pre-requisite/blocker for several issues which contribute to features and as such deliver customer value. In this case the issue customer benefit is computed from its dependent issues. The same applies to objectives.  

Issues require effort and sometimes money expenditure for their completion. 
Similarly to weights the model computes effective effort and effective cost of issue completion from issue's own effort and cost 
and the effort and cost of open blockers.

Finally, issue value is computed as benefit divided by total cost, which is computed from effort and cost.

Then issues are scheduled for implementation in increments.

#### Planning horizon

An organization may set a planning horizon, e.g. a quarter. 
The model estimates which issues can be worked on during the planning horizon based on organization's capacity and uses only those issues 
to calculate transitive weights and benefits of issues.
One reasoning for this is that a lot can change and some low-benefit high effort issues may never be worked on. 
Another way to thing about it is that some future benefit is less valuable than a present benefit in the same as future money are not as valuable as present money. 
Using this logic the planning horizon may be expressed as an "interest rate" on issue value, i.e. a "present issue value" is computed from the future issue value. 
In this case there is no cut-off horizon - the model performs rough scheduling and then calculates effective value depending on how far in the future
value will be delivered to customers. It means that there might be issue interactions. For example two issues contribute to the same feature, i.e. both of 
them must be completed to deliver the feature. If one of the issues can only be worked on and closed far in the future then the effective value of the other issue
shall take into account that the feature will be delivered at some future point in time.

There might be computational cycles in computation of issue values and priorities using planning horizon and value depreciation because issue value shall be used to prioritize issues and estimate feature delivery dates and then issue value is dependent on those delivery dates. More analysis and research is needed in this area.
One way to approach it might be to perform several iterations of computations until issue allocation to increments stops changing or maximum number of iterations is reached.

Planning horizon or "benefit interest rate" allow to capture any feature ideas and issues however insignificant they might seem at the moment without skewing effective weights
too much due to accumulation, i.e. some blocker issue gets a high weight because many low benefit issues depend on it.

Capturing everything might be important for a number of reasons. 
For example:

* Feature or persona weights may change.
* New people may join your team and take on low priority issues to get up to speed. 

### Persona engineering

Persona engineering includes defining personas, their needs, and [scenarios](Scenario.html) explaining how your [offerings](Offering.html] address persona needs.
Persona model contributes to computing issue effective weights.
It can also be used to group features into editions which address needs of specific personas as well as for price justification and negotiation using benefits delivered by offerings.

One possible scenario is pricing and licensing individual features. So instead of selecting from a set of pre-defined product editions customers build their own edition. 

### Product/offering engineering

In product engineering you define offerings - [products](Product.html), [editions](Edition.html), and [features](Feature.html). 
Feature is a unit of benefit delivery. 
Offerings may be linked to the persona model to compute their weights, or may be assigned weights explicitly.
Issue effective weights are computed from offering weights and offering effective cost and effort is computed from contributing issues.

Products define releases scheduled for increments and features are scheduled for releases.

### Increment planning

During increment planning issues are scheduled for increments to maximize delivered value, i.e. the sum of effective issue weights divided by the sum of issue costs.
The model shall assist in proportional allocation of issue to persona needs, categories and objectives.
It shall also assist in capacity management making sure that the organization is not over the capacity, but is not idling either.  

### Decision analysis

A number of model elements use weights as a measure of relative importance. 
E.g. one persona may have a higher weight than another persona. Or one persona need may have a higher weight than another need.

The model will support the following ways to compute weights:

* Manual - modelers just enter weights in the model. Such weights may be obtained in a number of ways e.g. team discussions, sales figures, surveys.
* [Multiple-criteria decision analysis](https://en.wikipedia.org/wiki/Multiple-criteria_decision_analysis) (MCDA) - weights are derived from criteria weights and how well an alternative meets the criteria.
    * [Weighted Sum Model](https://en.wikipedia.org/wiki/Weighted_sum_model) (WSM)
    * [Weighted Product Model](https://en.wikipedia.org/wiki/Weighted_product_model) (WPM)
    * [Analytic Hierarchy Process](https://en.wikipedia.org/wiki/Analytic_hierarchy_process) (AHP)
    
If a modeler decides to use one of MCDA methodologies to compute, say, need weights, they select a methodology to use and then define [criteria](Criterion.html).
For WSM and WPM criteria and alternative weights are entered directly into the model. For AHP they are computed from [pair-wise comparisons](Comparison.html).

### Risk management

Issues may have risks associated with them. 
Risks have probability, impact and effect - schedule, cost, show-stopper. 
Risks can be used to compute "issue expectancy" and expectancy may be used to compute issue effective weight.
Also it is possible to define organization risk policies. 
E.g. to filter out high risk issues or to allocate issues to increments is such a way that the overall risk is below a specified threshold.

### Capability management

This section outlines a general approach to capability management which might be added to the model in the future.

Initial releases will support built-in capabilities such as effort and cost.
Issues will have capability requirements in terms of effort and cost, increments will have provided capabilities in terms of availability and budget.

Definitions:

* Capability - something that is needed to complete a task. E.g. skills, money, tools. Capabilities can be:
   * Binary - capability is either present or not. Binary capabilities cannot be used up. E.g. administrator access to create new users.
   * Numeric - capability is present in some amount that can be consumed. E.g. number of hours. Numeric capabilities can be cumulative or not. Unused cumulative capabilities are carried over to the next increment. E.g. time is not a cumulative capability, but money is.
   * Composite.
* Capability requirement. E.g. so many of Java development hours, or so many dollars.   
* Capability provider. E.g. a Java developer provides 160 hours of Java development capability per month.
* Capability chaining - conversion of one capability to another. I.e. capability provider with its own capability requirements. E.g. 
    * Hiring of a Java developer is a conversion of Java development capability requirement to money requirement.
    * Java developer training is a conversion of time requirement in one increment into Java development capability in subsequent increments.
* Provisioning plan - explains a possible way how a system (organization) can satisfy capability requirements. There might be multiple plans with different attributes. E.g. a requirement for Java development may be satisfied by using one Java developer over a course of several increments, hiring an additional Java developer, or training somebody on the team to be a Java developer. Provisioning plans may be automatically computed by a model and then one of provisioning plans can be selected for execution. Selection can be manual or automated.    

Capability management allows to plan in terms of capabilities and requirements and leverage multiple capability providers to come up with optimal provisioning plans.

### Example

This section explains a possible scenario of applying the engineering model.

A company needs to solve a problem. For example, improve an internal process or build an internal product or service.
It forms a cross-functional team to tackle the problem.
The team creates an engineering model where it captures:

* Team structure - roles, people in those roles, their availability. 
* Internal customers for whom a new product or service is going to be built.

Internal customers can be grouped into one persona or multiple personas. 
Then their needs are defined via a series of interviews.
The team comes up with a list of product features and scenarios showing how these features satisfy customer needs.
They work with the internal customers to prioritize features, define issues/tasks to implement the features.
Based on the effective weights computed by the model the team organizes issues into increments and features into releases.
As they deliver product functionality they collect feedback and capture it as issues to make sure that everybody's voice is heard.
Then they present result of their work to the project sponsors. The model provides analytical data supporting the team decisions. 
The data is derived from feedback from the internal customers. 
This gives the project sponsors confidence that the team has done the right thing and also has done in the right order.
 

