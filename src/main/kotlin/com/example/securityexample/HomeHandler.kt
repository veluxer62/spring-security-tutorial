package com.example.securityexample

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class HomeHandler {
    suspend fun home(request: ServerRequest) =
            ServerResponse.ok().buildAndAwait()
}