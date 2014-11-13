Architecture
============

Terminology
-----------------------
- The Terminology Server is a repository for terminology
	- Coding schemes, codes and their metadata
- Codes and coding schemes will be administered through the OE admin screens
- API needs to provide
	- Get list of coding schemes
	- Get list of codes
		- Needs search filter to allow autocomplete and admin support
		- Filter syntax could form basic of intensional VSDs (see below)

Value Sets (VS)
--------------
- The Shared Value Set (SVS) server is a repository for Value Sets (VS)
	- Defined lists of values (coding + metadata) for use in validation and form generation on OE
- A Value Set Definition (VSD) is used to define the contents of a Value Set (VS)
	- Intensional VSDs define the content using expressions
		- The expression rules are dependent on the coding scheme being referenced
		- Example: http://www.hl7.org/implement/standards/fhir/valueset.html#6.13.7.1
	- Extensional VSDs define the content explicitly through a list of selected concepts
- VSDs will be administered through the OE admin screens
- Each VSD has:
	- ID: Unique identifier for the set
	- Name (String): Unique human readable label for the set
	- Metadata (TBD): Other information about the VSD
	- Definition: Either selected concepts or expressions, depending on intensional or extensional
- An Expanded Value Set (EVS) is the result of resolving a VSD using the Terminology server
	- It is read only
	- We may want to include additional metadata in the EVS to support optgroup for example
- Building an extensional VSD
	- A coding scheme, and concepts are selected from the Terminology server
	- If a concept is required which is not available on the Terminology server then the user should be able to add the
	concept
	- New coding schemes can also be created if required for local concepts
		- We should probably allow multiple "local" schemes to avoid a flat list of unrelated concepts on the Terminology
		server

Value Set Rules (VSR)
---------------------
- VSR will be administered through the OE admin screens
- VSR are stored on a Configuration server
- VSR apply to properties in the Data Schema
	- e.g. Anaesthetic Complications
- When a rule matches, it returns an EVS
- When multiple rules match, the first matching rule wins
- When no rule matches the rule engine returns null
	- This would result in just using the data schema to generate form field and validation
- Each property can have a default rule which always matches
- The available parameters when defining a rule depend on the property's rule schema
	- If a property has no explicit rule schema then it uses the default rule schema which provides access to common contextual
	 information
	 	- Site
	 	- Firm
	 	- Patient demographics
	 - __What are explicit rule schemas used for?__
- If using a rule parameter which identifies another schema property that is a coding, the UI should allow the user to pick
 the value(s) from the value set(s) currently defined for that property

Forms
-----
-

API
---

Value validation rule, e.g. Snellen value