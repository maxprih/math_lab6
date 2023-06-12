package com.example.plugins

import com.example.dtos.OduRequest
import com.example.math.MainService
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.receive

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/odu") {
            val request = call.receive<OduRequest>()
            val service = MainService()
            val res = service.solve(request)
            call.respond(res)
        }
    }
}
