package com.totalwar.warhammer.dataapp.models

import org.jetbrains.exposed.sql.Table

data class Faction(val id: Int, val name: String, val description: String)

object Factions: Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 248)
    val description = varchar("description",2048)
    override val primaryKey = PrimaryKey(id)
}
