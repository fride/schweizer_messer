package de.itemis.scala.io

import java.io.{FileInputStream, IOException, InputStream}
import java.util.Properties


/**
 *
 * Date: 11.04.12
 * Time: 20:15
 *
 * @author Friderici
 */

object Exceptions {

  /**
   * Exceptions are for java. We try to acoid them here and make
   * the failure explicit by the signature Option[T]
   *
   * Read something from an inputstream and close the stream.
   * I an error occurs returns None.
   * @param in te inputstream
   * @param f the action to apply the stream to
   * @tparam T the return type.
   * @return Some(T) if no exception occurs, None else.º
   */
  def read[T](in: => InputStream)(f: (InputStream) => T): Option[T] =
    tryOr("Error reading stream.") {
      try {
        f(in)
      } finally {
        in.close()
      }
    }


  def tryOr[T](message: String)(action: => T): Option[T] =
    try {
      Some(action)
    } catch {
      case e: Exception => println(message + "\n" + e.getMessage);
      None
    }

  def propertyReader(in: InputStream): Properties = {
    val props = new Properties()
    props.load(in)
    props
  }

  lazy val defaultSettings = {
    val props = new Properties()
    props.setProperty("host", "localhost")
    props.setProperty("port", "8080")
    props
  }

  def main(args: Array[String]) {
    val settings =
      read(new FileInputStream("Does not exist"))(propertyReader _).getOrElse(defaultSettings);
    println(settings)

    val properties = tryOr("Couldn't read properties") {
      val props = new Properties()
      val in = new FileInputStream("Does_not_exist")
      try {
        props.load(in)
        props
      } finally {
        in.close()
      }
    }

    println(properties)
  }
}
