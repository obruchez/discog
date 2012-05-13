package org.keithjarrett.discog.model

import net.liftweb.mapper._

class Person extends LongKeyedMapper[Person] with IdPK with CreatedUpdated with OneToMany[Long, Person] {
  def getSingleton = Person

  object uriSlug extends MappedString(this, 255) with DBIndexed
  object commonName extends MappedString(this, 255)
  object firstNames extends MappedString(this, 255)
  object lastNames extends MappedString(this, 255)
  object pseudonym extends MappedString(this, 255)
  object birthDate extends MappedDate(this)
  object birthPlace extends MappedString(this, 255)
  object deathDate extends MappedDate(this)
  object deathPlace extends MappedString(this, 255)
  object notes extends MappedText(this)

  object ensemblePersons extends
    MappedOneToMany(EnsemblePerson, EnsemblePerson.person, OrderBy(EnsemblePerson.ensemblePosition, Ascending))
}

object Person extends Person with LongKeyedMetaMapper[Person] with CRUDify[Long, Person] {
  //override def pageWrapper(body: NodeSeq) = <lift:surround with="admin" at="content">{body}</lift:surround>
  override val calcPrefix = List("persons")
  override val displayName = "Person"
  //override def showAllMenuLocParams = LocGroup("admin") :: Nil
  //override def createMenuLocParams = LocGroup("admin") :: Nil
}
