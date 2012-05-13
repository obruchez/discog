package org.keithjarrett.discog.model

import net.liftweb.mapper._
import net.liftweb.common._

object TestDB {
  val tables = List[BaseMetaMapper](Ensemble, Person, EnsemblePerson)

  val vendor = new StandardDBVendor(
    driverName = "org.h2.Driver",
    dbUrl = "jdbc:h2:mem:lift;DB_CLOSE_DELAY=-1",
    dbUser = Empty,
    dbPassword = Empty)

  Logger.setup = Full(net.liftweb.util.LoggingAutoConfigurer())
  Logger.setup.foreach(_.apply())

  def init() {
    DB.defineConnectionManager(DefaultConnectionIdentifier, vendor)
    Schemifier.destroyTables_!!(Schemifier.infoF _, tables: _*)
    Schemifier.schemify(true, Schemifier.infoF _, tables: _*)
  }

  def shutdown() {}
}
