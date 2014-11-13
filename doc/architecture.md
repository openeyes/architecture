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
- Used to define dynamic business rules not covered by schema
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
	- Explicit rule schemas can be used to define related properties within an entry or section or more complex algorithms
		- e.g. Valid anaesthetic Complications may depending on which Anaesthetic Type has been selected
- If using a rule parameter which identifies another schema property that is a coding, the UI should allow the user to pick
 the value(s) from the value set(s) currently defined for that property

Forms
-----
- Forms can be constructed using
	- Static schema (compiled)
		- The data schema
		- The form schema
	- Dynamic configuration
		- The VSR
		- Form configuration rules
- The data schema
	- Overall form structure and field types
- The form schema
	- Allows default field types to be overridden with alternative widgets
		- e.g. A dropdown may be replaced with a set of radio buttons or a textarea with a wysiwyg editor
	- Allows field order to be overridden
- The VSR
	- Defines the EVS used for coded fields
- Form configuration rules
	- Provide additional configuration of widgets not relevant to business rules
	- e.g. A diagnosis picker may allow any ophthalmic diagnosis to be selected (the VSR), but the widget my present an
	autocomplete field _and_ a dropdown list of "common" diagnoses. These common diagnoses my vary depending on subspecialty
	and firm

API
---
- When documents are submitted to the API, they are validated using
	- The data schema
	- The VSR
- The data schema
	- Validation for simple data types
- The VSR
	- Validation of coded fields (based on EVS)
- Also need a way of mapping polymorphic fields to schema objects
	- Any polymorphic fields _must_ send the corresponding class name for that field in the document
	- e.g. A VA reading value is a "VA Value" which is abstract. If the submitted value is a Snellen value then the class
	name for "Snellen VA Value" must be submitted with the value. This will require a method of defining the mapping between
	the VA reading scale codings and the corresponding schema classes so that this info can be included in the form schema