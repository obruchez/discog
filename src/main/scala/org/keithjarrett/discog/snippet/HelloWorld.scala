package org.keithjarrett.discog.snippet

import java.util.Date
import net.liftweb.common._
import net.liftweb.util.Helpers._
import org.keithjarrett.discog.lib._

class HelloWorld {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date

  // replace the contents of the element with id "time" with the date
  def howdy = "#time *" #> date.map(_.toString)

  /*
  lazy val date: Date = DependencyFactory.time.vend // create the date via factory

  def howdy = "#time *" #> date.toString
   */
}
