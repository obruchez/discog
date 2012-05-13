package org.keithjarrett.discog.model

import net.liftweb.mapper._

class EnsemblePerson extends LongKeyedMapper[EnsemblePerson] with IdPK with CreatedUpdated {
  def getSingleton = EnsemblePerson

  object ensemble extends MappedLongForeignKey(this, Ensemble)
  object person extends MappedLongForeignKey(this, Person)
  object ensemblePosition extends MappedInt(this)
  object personPosition extends MappedInt(this)
  object role extends MappedString(this, 255)
  object startDate extends MappedDate(this)
  object endDate extends MappedDate(this)
}

object EnsemblePerson extends EnsemblePerson with LongKeyedMetaMapper[EnsemblePerson] {
  override def dbIndexes = Index(ensemble, personPosition) :: Index(person, ensemblePosition) :: super.dbIndexes
}
