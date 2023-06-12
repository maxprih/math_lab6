package com.example

import com.example.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(CORS) {
        allowNonSimpleContentTypes = true
        allowCredentials = true
        maxAgeInSeconds = 1728000
        allowMethod(io.ktor.http.HttpMethod.Get)
        allowMethod(io.ktor.http.HttpMethod.Post)
        allowMethod(io.ktor.http.HttpMethod.Options)
        allowMethod(io.ktor.http.HttpMethod.Put)
        allowMethod(io.ktor.http.HttpMethod.Patch)
        allowMethod(io.ktor.http.HttpMethod.Delete)
        allowMethod(io.ktor.http.HttpMethod.Head)
        allowHeader(io.ktor.http.HttpHeaders.Authorization)
        allowHeader(io.ktor.http.HttpHeaders.ContentType)
        allowHeader(io.ktor.http.HttpHeaders.Accept)
        allowHeader(io.ktor.http.HttpHeaders.Origin)
        allowHeader(io.ktor.http.HttpHeaders.UserAgent)
        allowHeader(io.ktor.http.HttpHeaders.CacheControl)
        allowHeader(io.ktor.http.HttpHeaders.IfModifiedSince)
        allowHeader(io.ktor.http.HttpHeaders.Cookie)
        allowHeader(io.ktor.http.HttpHeaders.AcceptEncoding)
        allowHeader(io.ktor.http.HttpHeaders.AcceptLanguage)
        allowHeader(io.ktor.http.HttpHeaders.Connection)
        allowHeader(io.ktor.http.HttpHeaders.Host)
        allowHeader(io.ktor.http.HttpHeaders.Referrer)
        allowHeader(io.ktor.http.HttpHeaders.Pragma)
        allowHeader("X-Request-ID")
        allowHeadersPrefixed("sec-")
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }
    configureSerialization()
    configureRouting()
}
