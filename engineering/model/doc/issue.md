assignees - roles (planning), members (execution). Maybe different names. Members must be in roles to be assigned. collections, the first in collection is primary - responsible.

issue type, status - transitions/workflow. Status examples - new, assigned, groomed/ready for sprint.

Issue extends Entity. May extend named element or define summary field or make title mandatory. Maybe hide title - will need to modify design for that so it doesn't show reflective title.

Attributes:

* ID - from Entity. Would be nice to have more meaningful ID's. E.g. as in Vinci actions - type + counter. Need a content adapter for this.
* Priority
* Increment - when to work on it.
* Planned for/Fixed in - Releases - may be more than one in general e.g. if there are different builds for different clients. Or a fix can be back-ported and in this case it will appear in an upcoming release and in a hotfix or service release. Do we need planned for/fixed in - may be different?
* Target date - must be within the increment.
* Relationships - parent/child, blocks, relates. project-management relationship types - start/finish - 4 types. Lag.
* Assignee(s) (see above) - implicit by containment.
* Reporter - party
* Estimate - original. Remaining are in notes. The same for expenditure/cost.
* Components - affected components/engineered elements. Implicit by containment. 
* Resources - play role of attachments. Model elements defined by resources palette. E.g. markdown text - embedded markdown, e.g. instructions, markdown resource - text stored in an external location like a file. Similarly plain text, HTML, URL/Reference. As resources will be used throughout the engineering model maybe it makes sense to create EngineeringElement extending ModelElement and having resources containment reference.
* Created/updated?
* Categories - may have more than one.
* Resolution - Open, Fixed, Cancelled, ...
* Publish or public - boolean flag, true by default or Private false by default. Also applied to notes. Public issues are published to (HTML) reports. Private issues/notes are not published to reports and visible only to people who have access to the models. One option is that categories may also be marked as private and issues with a private category are treated as private. Then it begs a question what about objectives? Perhaps just issue/note.
* Severity - crash, ... 
* Releases - releases affected by this issue.
* Benefit explicit - some number to compare with other issues, effective benefit - computed from explicit benefit, dependent issues and contribution to features, contribution to features. issue -> feature because feature is defined at a higher level and before issues.
* Duration - different from effort. E.g. to file for a passport may take a few hours of effort and a few weeks of duration.