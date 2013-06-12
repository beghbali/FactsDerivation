Semantical facts derivation using grammatical parser and first order predicate logic resolution

*see the docs folder for more discussion and graphs*

example:
given predicates in plain language(scholarly articles):
TGF Beta-1 causes increase in PH level
increase in PH level contributes to development of cancer

it can be derived that:
TFG Beta-1 contributes to development of cancer

various parts of the system:

**Abstracts**: The abstracts that we are extracting information from (currently 100).
 
**Chart Parser**: It replaces compound nouns with their abbreviation and if an abbreviation is not specified, it creates an auxiliary abbreviation. It then does the parsing of the sentence to create a tree of the sentence structure.
 
**Mental Representation**: Given the sentence structure from Chart Parser, it derives the important parts of the sentence out and creates databases of relations.
 
**Fact Derivation**: Given the Ontology database it outputs 1) gammatical facts 2) derived facts
 
**Lexicon**: The dictionaries and thesauri in our case, WordNet, that provides word definitions, synonyms, antonyms, etc…
 
**Ontologies Database**: A database of ontology, relation of noun(s) by verb(s). Expands as new sentence information comes in.

**NOTE**: this project is very old and will not compile with latest java compilers. I hope to get arount to bridging it to the modern world once I have free cycles.

