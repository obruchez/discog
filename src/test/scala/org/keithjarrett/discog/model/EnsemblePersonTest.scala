package org.keithjarrett.discog.model

import net.liftweb.http._
import net.liftweb.common._
import net.liftweb.util.Helpers._
import org.specs._
import org.specs.matcher._
import org.specs.runner.JUnit4
import org.specs.runner.ConsoleRunner
import org.specs.specification._

object EnsemblePersonTest extends Specification {
  val dateFormat = new java.text.SimpleDateFormat("yyyy.MM.dd")

  val date1 = dateFormat.parse("1901.12.14")
  val date2 = dateFormat.parse("1975.5.31")

  "Ensemble" should {
    "be persisted correctly" in {
      TestDB.init()

      val ensemble = Ensemble.
        create.
        uriSlug("URI slug").
        name("Ensemble name").
        startDate(date1).
        endDate(date2).
        notes("Ensemble notes")

      ensemble.save() mustBe true
      ensemble.id.is must beGreaterThan(0L)

      val ensembleFromDb = Ensemble.find(ensemble.id)

      ensembleFromDb.isDefined mustBe true
      ensembleFromDb.get.uriSlug.is must beEqualTo("URI slug")
      ensembleFromDb.get.name.is must beEqualTo("Ensemble name")
      ensembleFromDb.get.startDate.is must beEqualTo(date1)
      ensembleFromDb.get.endDate.is must beEqualTo(date2)
      ensembleFromDb.get.notes.is must beEqualTo("Ensemble notes")

      ensembleFromDb.get.ensemblePersons.all.size must beEqualTo(0)

      TestDB.shutdown()
    }
  }

  "Person" should {
    "be persisted correctly" in {
      TestDB.init()

      val person = Person.
        create.
        uriSlug("URI slug").
        commonName("Common name").
        firstNames("First names").
        lastNames("Last names").
        pseudonym("Pseudonym").
        birthDate(date1).
        birthPlace("Birth place").
        deathDate(date2).
        deathPlace("Death place").
        notes("Notes")

      person.save() mustBe true
      person.id.is must beGreaterThan(0L)

      val personFromDb = Person.find(person.id)

      personFromDb.isDefined mustBe true
      personFromDb.get.uriSlug.is must beEqualTo("URI slug")
      personFromDb.get.commonName.is must beEqualTo("Common name")
      personFromDb.get.firstNames.is must beEqualTo("First names")
      personFromDb.get.lastNames.is must beEqualTo("Last names")
      personFromDb.get.pseudonym.is must beEqualTo("Pseudonym")
      personFromDb.get.birthDate.is must beEqualTo(date1)
      personFromDb.get.birthPlace.is must beEqualTo("Birth place")
      personFromDb.get.deathDate.is must beEqualTo(date2)
      personFromDb.get.deathPlace.is must beEqualTo("Death place")
      personFromDb.get.notes.is must beEqualTo("Notes")

      personFromDb.get.ensemblePersons.all.size must beEqualTo(0)

      TestDB.shutdown()
    }
  }
}
