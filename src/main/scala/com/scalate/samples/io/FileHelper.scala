package com.scalate.samples.io

import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

/**
 * Code From the book "Beginning Scala"
 * http://www.amazon.com/Beginning-Scala-David-Pollak/dp/1430219890
 */
object FileHelper {

  def using[A <: { def close(): Unit }, B](param: A)(f: A => B): B =
    try { f(param) } finally { param.close() }

  def writeToFile(fileName: String, data: String) =
    using(new FileWriter(new File(fileName))) {
      fileWriter => fileWriter.write(data)
    }

  def appendToFile(fileName: String, textData: String) =
    using(new FileWriter(new File(fileName), true)) {
      fileWriter =>
        using(new PrintWriter(fileWriter)) {
          printWriter => printWriter.println(textData)
        }
    }

  def checkDir(directory: String): Unit = {
    val file = new File(directory)

    deleteFile(file)
    
    file.mkdir()
  }

  def deleteFile(file: File): Boolean = {
    if (file.isDirectory()) {
      file.listFiles.foreach { f => deleteFile(f) }
    }

    return file.delete()
  }
  
  def mkDirectory(directory: String, file: String): String = "%s%s%s".format(directory, File.separator, file)

}