package com.example.securityexample

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
class SecurityExampleApplicationTests {

    @Autowired
    private lateinit var client: WebTestClient

    @Test
    fun testUnauthorizedWhenNoCredentials() {
        client
                .get()
                .uri("/")
                .exchange()
                .expectStatus().isUnauthorized
    }

    @Test
    fun testOkWhenValidCredentials() {
        client
                .get()
                .uri("/")
                .headers {
                    it.setBasicAuth("user", "password")
                }
                .exchange()
                .expectStatus().isOk
    }

    @Test
    fun testUnauthorizedWhenInvalidCredentials() {
        client
                .get()
                .uri("/")
                .headers {
                    it.setBasicAuth("user", "invalid")
                }
                .exchange()
                .expectStatus().isUnauthorized
    }
}
