package org.keithjarrett.discog.model

import net.liftweb.mapper._

class Person extends LongKeyedMapper[Person] with IdPK with CreatedUpdated {
  def getSingleton = Person

  object uriSlug extends MappedString(this, 255)
  object commonName extends MappedString(this, 255)
  object firstNames extends MappedString(this, 255)
  object lastNames extends MappedString(this, 255)
  object pseudonym extends MappedString(this, 255)
  object birthDate extends MappedDate(this)
  object birthPlace extends MappedString(this, 255)
  object deathDate extends MappedDate(this)
  object deathPlace extends MappedString(this, 255)
  object notes extends MappedText(this)

  // + links to EnsemblePerson
}

object Person extends Person with LongKeyedMetaMapper[Person] with CRUDify[Long, Person] {
  override def dbTableName = "persons"

  //override def pageWrapper(body: NodeSeq) = <lift:surround with="admin" at="content">{body}</lift:surround>
  //override def calcPrefix = List("admin", _dbTableNameLC)
  override def displayName = "Person"
  //override def showAllMenuLocParams = LocGroup("admin") :: Nil
  //override def createMenuLocParams = LocGroup("admin") :: Nil
}
