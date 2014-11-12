package openeyes.schema

import com.github.fge.jackson.JsonLoader
import com.github.fge.jsonschema.core.report.LogLevel
import com.github.fge.jsonschema.main.{JsonSchema,JsonSchemaFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.support.ResourcePatternResolver
import org.springframework.stereotype.Component

@Component
class SchemaRepository @Autowired() (resourceFinder: ResourcePatternResolver) {
  private val schemaFactory = JsonSchemaFactory.byDefault
  private val schemaValidator = schemaFactory.getSyntaxValidator

  private var schemas = Map[String, JsonSchema]()

  def loadSchemas() = {
    schemas = resourceFinder.getResources("/openeyes/schema/*.json") map (res => {
      res.getFilename.replaceAll("""\.json$""", "") -> schemaFactory.getJsonSchema(JsonLoader.fromFile(res.getFile()))
    }) toMap
  }
}
