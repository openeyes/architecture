package oe.core

import com.github.fge.jsonschema.main.JsonSchemaFactory
import com.github.fge.jsonschema.core.load.configuration.LoadingConfiguration

package object schema {
  lazy val schemaFactory =
    JsonSchemaFactory.newBuilder
      .setLoadingConfiguration(LoadingConfiguration.newBuilder.addScheme("oe", new SchemaLoader).freeze())
      .freeze
}
