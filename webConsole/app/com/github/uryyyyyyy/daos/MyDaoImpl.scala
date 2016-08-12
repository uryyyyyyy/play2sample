package com.github.uryyyyyyy.daos

import javax.inject.{Inject, Singleton}

import play.api.db.{Database, NamedDatabase}
import scalikejdbc._

@Singleton
class MyDaoImpl @Inject() (@NamedDatabase("mySample") db: Database) extends MyDao {

  def exec(): Option[String] = {
    using(db.getConnection(autocommit = false)) { conn =>
      val ss = DB(conn).readOnly { implicit session =>
        sql"select 2".map( rs => rs.long(1)).single.apply()
      }
      ss.map(_.toString)
    }
  }
}

