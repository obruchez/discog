package org.keithjarrett.discog.model

import net.liftweb.mapper._

class Ensemble extends LongKeyedMapper[Ensemble] with IdPK with CreatedUpdated with OneToMany[Long, Ensemble] {
  def getSingleton = Ensemble

  object uriSlug extends MappedString(this, 255) with DBIndexed
  object name extends MappedString(this, 255)
  object startDate extends MappedDate(this)
  object endDate extends MappedDate(this)
  object notes extends MappedText(this)

  object ensemblePersons extends
    MappedOneToMany(EnsemblePerson, EnsemblePerson.ensemble, OrderBy(EnsemblePerson.personPosition, Ascending))
}

object Ensemble extends Ensemble with LongKeyedMetaMapper[Ensemble] with CRUDify[Long, Ensemble] {
  //override def pageWrapper(body: NodeSeq) = <lift:surround with="admin" at="content">{body}</lift:surround>
  override val calcPrefix = List("ensembles")
  override val displayName = "Ensemble"
  //override def showAllMenuLocParams = LocGroup("admin") :: Nil
  //override def createMenuLocParams = LocGroup("admin") :: Nil
}
