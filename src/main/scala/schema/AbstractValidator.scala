package oe.core.schema

import com.fasterxml.jackson.databind.JsonNode
import com.github.fge.jsonschema.core.processing.Processor
import com.github.fge.jsonschema.core.report.ProcessingReport
import com.github.fge.jsonschema.keyword.validator.AbstractKeywordValidator
import com.github.fge.jsonschema.processors.data.FullData
import com.github.fge.msgsimple.bundle.MessageBundle

class AbstractValidator(digest: JsonNode) extends AbstractKeywordValidator("abstract") {
  def validate(processor: Processor[FullData, FullData], report: ProcessingReport, bundle: MessageBundle, data: FullData) = {
    report.error(newMsg(data, bundle, "ouch"))
  }

  override def toString(): String = keyword + ": " + digest
}
