package de.itemis.scala.types

import de.itemis.scala.types.Units.TimeBuilder

/**
 *
 * Date: 11.04.12
 * Time: 21:57
 *
 * @author Friderici
 */

object Units {

  case class Time(millis: Long) {
    def +(other: Time) = copy(millis = this.millis + other.millis)

    def -(other: Time) = copy(millis = this.millis - other.millis)

    def *(n: Long) = copy(millis = n * this.millis)

    def ms      = this
    def seconds = this.millis / 1000
    def minutes = this.millis / (1000 * 60)
    def hours   = this.millis / (1000 * 60 * 60)

    override def toString = "%d:%d:%s - %s".format(hours, minutes, seconds, millis)
  }

  class TimeBuilder(val millis:Long) {
    def ms       = Time(millis)
    def seconds  = Time(millis = this.millis * 1000)
    def minutes  = Time(millis = this.millis * (1000 * 60))
    def hours    = Time(millis = this.millis * (1000 * 60 * 60))
    def days     = Time(millis = this.millis * (1000 * 60 * 60))
  }

  implicit def long2Time(l:Long) = new TimeBuilder(l)

  def main(args: Array[String]) {
    val time = (10 hours) + (20 minutes) + (10 seconds) + (500 ms)
    println(time)
  }
}
