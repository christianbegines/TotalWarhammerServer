package com.totalwar.warhammer.dataapp.dao.faction.implementation

import com.totalwar.warhammer.dataapp.dao.DatabaseFactory.dbQuery
import com.totalwar.warhammer.dataapp.dao.faction.interfaces.DAOFaction
import com.totalwar.warhammer.dataapp.models.Faction
import com.totalwar.warhammer.dataapp.models.Factions
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOFactionImpl: DAOFaction{
    private fun resultToFaction(row: ResultRow) = Faction(
        id = row[Factions.id],
        name = row[Factions.name],
        description = row[Factions.description]
    )
    override suspend fun allFactions(): List<Faction> = dbQuery{
        Factions.selectAll().map(::resultToFaction)
    }

    override suspend fun faction(id: Int): Faction? =  dbQuery {
        Factions.select { Factions.id eq id }.map(::resultToFaction).singleOrNull()
    }

    override suspend fun createFaction(name: String, description: String): Faction? = dbQuery  {
        val insertStatement = Factions.insert {
            it[Factions.name] = name
            it[Factions.description] = description
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultToFaction)
    }

    override suspend fun editFaction(id: Int, name: String, description: String): Boolean = dbQuery {
        Factions.update({ Factions.id eq id }) {
            it[Factions.name] = name
            it[Factions.description] = description
        } > 0
    }

    override suspend fun deleteFaction(id: Int): Boolean = dbQuery {
        Factions.deleteWhere { Factions.id eq id } > 0
    }
}
val daoFaction: DAOFaction = DAOFactionImpl().apply {
    runBlocking {
        if (allFactions().isEmpty()) {
            createFaction("Khorne", "Khorne, the Blood God, rages upon his brass throne, resting atop a mountain of skulls, built from the countless heads of the great champions his followers have slain over an immeasurable number of aeons. He cares not from where blood flows so long as it is done in his name. It is not uncommon to see the warriors of Khorne fighting amongst themselves, battling to prove their skill in combat and rise higher in the eyes of their wrathful master.!")
        }
    }
}
