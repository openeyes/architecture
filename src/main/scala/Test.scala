package openeyes

import com.fasterxml.jackson.databind.ObjectMapper

object Test {
  def main(args: Array[String]): Unit = {
    val testVal = (new ObjectMapper).readTree(
"""
{
    "numerator": 6,
    "denominator": 6
}
"""
    )

    val schema = openeyes.schema.schemaFactory.getJsonSchema("oe:VisualAcuityValueSnellenMetre")

    println(schema.validate(testVal))
  }
}
