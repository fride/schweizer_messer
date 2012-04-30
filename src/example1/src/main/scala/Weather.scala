package de.itemis.scala

import scala.io.Source
import xml.{NodeSeq, XML}
import scala.actors.Actor
import scala.actors.Actor._


/**
 * XML, Functions as Factories, Concurrency
 */
object Weather extends App {
  type WeatherInfo = (NodeSeq, NodeSeq, NodeSeq)


  def takeTime(message: String)(action: => Unit) {
    val now = System.currentTimeMillis()
    action
    val stop = System.currentTimeMillis()
    println(message + " Took %d ms".format(stop - now))
  }

  val weatherUrl: (Int) => String =
    woeid => "http://weather.yahooapis.com/forecastrss?w=%d".format(woeid)

  def getWeatherInformation(woeid: Int): WeatherInfo = {
    val response = Source.fromURL(weatherUrl(woeid)).mkString
    //println(response)
    val xmlResponse = XML.loadString(response)

    (xmlResponse \\ "location" \\ "@city",
      xmlResponse \\ "condition" \\ "@temp",
      xmlResponse \\ "units" \\ "@temperature")
  }

  /**
   * Make a function that gets the weater.
   * @param woeid
   * @return a function that get's the weather.
   */
  def makeWeatherFactory(woeid: Int): () => String =
    () => {
      val weatherInfo = getWeatherInformation(woeid)
      "We have %s °%s in %s".format(weatherInfo._2, weatherInfo._3, weatherInfo._1)
    }

  val infoGetters = List(2442047, 2459115, 640161).map(makeWeatherFactory)


  takeTime("Parallel collections") {
    infoGetters.par.foreach(f => println(f()))
  }

  class PrintActor extends Actor {


    def act() {
      val start: Long = System.currentTimeMillis()
      println("Starting actor")
      loop {
        react {
          case weatherInfo: WeatherInfo =>
            println("--> We have %s °%s in %s".format(weatherInfo._2, weatherInfo._3, weatherInfo._1))
          case "Die!" =>
            println("Actor took Took %d ms".format(System.currentTimeMillis() - start))
            exit()
          case x => println (x)
        }
      }
    }
  }

  val caller = new PrintActor
  caller.start()


  for (woeid <- List(2442047, 2459115, 640161)){
    caller ! getWeatherInformation(woeid)
  }
  caller ! "Die!"


  takeTime("Sequential collections") {
    infoGetters.foreach(f => println(f()))
  }


}


