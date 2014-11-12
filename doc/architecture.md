Architecture
============

Terms
-----

- OpenEyes (OE) server: The main OpenEyes server which provides the API to the OE client application
- Configuration (Conf) server: Stores OE configuration data
- Shared Value Set (SVS) server: Provides named lists of values for use validation and form generation on OE
- Terminology server: Stores coding schemes, codes and their meanings

Value Sets (VS)
--------------
- Value sets will be administered through the OE admin screens
	- created, edited and deleted
- Value sets are only stored on the SVS server
- Each value set has:
	- ID (UUID?): Unique identifier for the set
	- Name (String): Unique human readable label for the set
	- Version (Int): Incremental version number for the VS __is this necessary?__
	- Values (Coding): One or more codings which are made up of a coding scheme, a code and a name
- Value sets will be Extensional at this stage
	- Maybe Intensional sets will be added later
- When building a value set, codings are selected from the Terminology server
	- If a coding is required which is not available on the Terminology server then the user can either:
		- Added it to an existing coding scheme (e.g. local SNOMED codes)
		- Create a new coding scheme and add the coding to that

Rules
-----
- Rules will be administered through the OE admin screens
	- created, edited and deleted
- Rules are stored on Conf server
- Rules apply to properties in the OE Schema
	- e.g. Anaesthetic Complications
- Where multiple rules apply to the same property, the first matching rule applies
- Each property has a default rule
- A matching rule returns a value set __Does it always return a set or can it return something else or null?__
- The parameters for a rule are determined by the property's rule schema
	- If a property has no explicit rule schema then it uses the default rule schema (e.g. Site, Firm etc.)
- If using a rule parameter which identifies another schema property that is a coding, the UI should allow the user to pick
 the value(s) from the value set(s) currently defined for that property

Forms
-----
-

API
---