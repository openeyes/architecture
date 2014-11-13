package oe.core.schema

import com.github.fge.jsonschema.core.exceptions.ProcessingException
import com.github.fge.jsonschema.core.load.download.URIDownloader

class SchemaLoader extends URIDownloader {
  def fetch(source: java.net.URI) = {
    getClass.getClassLoader.getResourceAsStream("schema/" + source.getSchemeSpecificPart.replaceAll("\\.", "/") + ".json") match {
      case null => throw new ProcessingException(s"Failed to load schema: '$source'")
      case s => s
    }
  }
}
