package com.totalwar.warhammer.dataapp.plugins

import com.totalwar.warhammer.dataapp.dao.faction.implementation.daoFaction
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Application.configureRouting() {
    routing {
        static("/static") {
            resources("files")
        }
        get("/") {
            call.respondRedirect("factions")
        }

        route("factions") {
            get {
                call.respond(FreeMarkerContent("faction/index.ftl", mapOf("factions" to daoFaction.allFactions())))
            }
            get("new") {
                call.respond(FreeMarkerContent("faction/new.ftl", model = null))
            }
            post {
                val formParameters = call.receiveParameters()
                val name = formParameters.getOrFail("name")
                val description = formParameters.getOrFail("description")
                val faction = daoFaction.createFaction(name, description)
                call.respondRedirect("/factions/${faction?.id}")
            }
            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("faction/show.ftl", mapOf("faction" to daoFaction.faction(id))))
            }
            get("{id}/edit") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("faction/edit.ftl", mapOf("faction" to daoFaction.faction(id))))
            }
            post("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when (formParameters.getOrFail("_action")) {
                    "update" -> {
                        val name = formParameters.getOrFail("name")
                        val description = formParameters.getOrFail("description")
                        daoFaction.editFaction(id, name, description)
                        call.respondRedirect("/factions/$id")
                    }

                    "delete" -> {
                        daoFaction.deleteFaction(id)
                        call.respondRedirect("/factions")
                    }
                }
            }
        }
    }
}
