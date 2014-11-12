import openeyes.schema.SchemaRepository
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object Test {
  def main(args: Array[String]): Unit = {
    val ctx = new AnnotationConfigApplicationContext("openeyes")
    val repo = ctx.getBean(classOf[SchemaRepository])
    repo.loadSchemas()
  }
}
