package com.scalate.samples.io

import java.io.File

import scala.collection.mutable.ListBuffer
import scala.io.Source

import com.scalate.samples.model._
import com.scalate.samples.model.Station

object CSVParser {

  /**
   * Write the stations into the stations.ssp template.
   */
  def output(fileLocation: String) = {
    TemplateRenderer.preCompile
    
    val stations = parse(fileLocation, "ISO-8859-1")
    FileHelper.writeToFile("stations.xml",
      TemplateRenderer.render("stations", Map((("stations", stations)))))
  }

  /**
   * Parse stations from CSV location.
   */
  def parse(fileLocation: String, encoding: String): List[Station] = {
    var buffer: ListBuffer[Station] = ListBuffer()

    Source.fromFile(fileLocation, encoding).getLines().drop(1).foreach(
      line => {
        val split: Array[String] = line.split(";")
        buffer += new Station(split(0), split(3), split(4))
      })

    buffer.toList
  }

}