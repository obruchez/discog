package org.keithjarrett.discog.model

import net.liftweb.http._
import net.liftweb.common._
import net.liftweb.util.Helpers._
import org.specs._
import org.specs.runner.JUnit4
import org.specs.runner.ConsoleRunner
import org.specs.specification._

object EnsemblePersonTest extends Specification {
  val date1 = new java.util.Date(1L)
  val date2 = new java.util.Date(2L)

  "Emsemble/Person" should {
    "be persisted correctly" in {
      TestDB.init()

      val ensemble = Ensemble.
        create.
        name("Ensemble name").
        uriSlug("URI slug").
        notes("Ensemble notes").
        startDate(date1).
        endDate(date2)

      ensemble.save() mustBe true
      ensemble.id.toInt must be > 0 // @todo is a Long!

      // @todo add remaining tests

      TestDB.shutdown()
    }
  }
}
