package com.totalwar.warhammer.dataapp

import io.ktor.server.application.*
import com.totalwar.warhammer.dataapp.dao.DatabaseFactory
import com.totalwar.warhammer.dataapp.plugins.configureRouting
import com.totalwar.warhammer.dataapp.plugins.configureTemplating

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init(environment.config)
    configureRouting()
    configureTemplating()
}
