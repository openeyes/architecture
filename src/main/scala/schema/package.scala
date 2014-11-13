package oe.core

import com.github.fge.jackson.NodeType
import com.github.fge.jsonschema.main.JsonSchemaFactory
import com.github.fge.jsonschema.cfg.ValidationConfiguration
import com.github.fge.jsonschema.core.load.configuration.LoadingConfiguration
import com.github.fge.jsonschema.core.keyword.syntax.checkers.helpers.TypeOnlySyntaxChecker
import com.github.fge.jsonschema.library.Keyword

package object schema {
  val SchemaURI = "http://openeyes.org.uk/json-schema/draft-04#"
  val Differentiator = "type"

  def schemaFactory = {
    val defaultVc = ValidationConfiguration.byDefault

    JsonSchemaFactory.newBuilder
      .setLoadingConfiguration(LoadingConfiguration.newBuilder.addScheme("oe", new SchemaLoader).freeze)
      .setValidationConfiguration(defaultVc.thaw.setDefaultLibrary(SchemaURI,
        defaultVc.getDefaultLibrary.thaw.addKeyword(
          Keyword.newBuilder("abstract").withSyntaxChecker(new TypeOnlySyntaxChecker("abstract", NodeType.BOOLEAN))
            .withIdentityDigester(NodeType.BOOLEAN)
            .withValidatorClass(classOf[AbstractValidator]).freeze).freeze).freeze)
      .freeze
  }
}
