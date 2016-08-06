package com.github.uryyyyyyy.daos

import javax.inject.{Inject, Singleton}

import play.api.db.{Database, NamedDatabase}

@Singleton
class MyDaoImpl @Inject() (@NamedDatabase("mySample") db: Database) extends MyDao {

  def exec(str: String): String = {
    db.name + " " + str
  }
}

