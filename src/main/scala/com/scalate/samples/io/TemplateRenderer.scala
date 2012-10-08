package com.scalate.samples.io

import org.fusesource.scalate.DefaultRenderContext
import java.io.StringWriter
import org.fusesource.scalate.TemplateEngine
import java.io.PrintWriter
import java.io.File

object TemplateRenderer {
  val SspDirectory = "src/main/resources/"
  val SspExtension = ".ssp"
  val engine = new TemplateEngine

  /**
   * @see http://blog.codeborne.com/2012/01/reducing-memory-usage-of-scalascalate.html
   */
  def preCompile() = {
    val workdir = new File(SspDirectory)
    val precompiler = new org.fusesource.scalate.support.Precompiler
    precompiler.sources = Array(workdir)
    precompiler.workingDirectory = workdir
    precompiler.execute()
  }

  def render(templateName: String, attributes: Map[String, AnyRef]): String = {
    val buffer = new StringWriter()
    val context = new DefaultRenderContext("???", engine, new PrintWriter(buffer))

    for (attribute <- attributes) {
      context.attributes(attribute._1) = attribute._2
    }

    val template = engine.load(SspDirectory + templateName + SspExtension)
    template.render(context)

    buffer.toString
  }

}
