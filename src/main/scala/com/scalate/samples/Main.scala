package com.scalate.samples

import com.scalate.samples.io.CSVParser
import scala.io.Source
import java.io.File

object Main {

  def main(args: Array[String]) = {
    println("Start")
    CSVParser.output("src/main/resources/stations.csv")
    println("Done")
  }
}