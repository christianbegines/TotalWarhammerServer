package com.totalwar.warhammer.dataapp.dao.faction.interfaces

import com.totalwar.warhammer.dataapp.models.Faction

interface DAOFaction {
    suspend fun allFactions(): List<Faction>
    suspend fun faction(id: Int): Faction?
    suspend fun createFaction(name: String, description: String): Faction?
    suspend fun editFaction(id: Int, name: String, description: String): Boolean
    suspend fun deleteFaction(id: Int): Boolean
}