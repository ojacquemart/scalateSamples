package com.scalate.samples.test

import org.junit.runner.RunWith
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.junit.JUnitRunner

import com.scalate.samples.io.CSVParser

@RunWith(classOf[JUnitRunner])
class ScalateParse extends FunSuite {

  test("should have 3065 stations") {
    val stations = CSVParser.parse("src/main/resources/stations.csv", "ISO-8859-1")
    assert(!stations.isEmpty)
    assert(stations.size == 3065)
    assert("Abancourt" == stations(0).name)
  }

}

