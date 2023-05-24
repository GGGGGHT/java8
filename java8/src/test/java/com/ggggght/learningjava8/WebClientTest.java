package com.ggggght.learningjava8;

import com.ggggght.learningjava8.mvc.HelloWorldController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class WebClientTest {
    @MockBean private ApplicationContext context;
    @MockBean private HelloWorldController controller;

    @Test
    void bind2Server() {
        WebTestClient testClient = WebTestClient.bindToServer().baseUrl("http://localhost:9999").build();
        testClient.get().uri("/resource").exchange().expectStatus().isOk().expectBody().isEmpty();
    }

    @Test
    void bind2Route() {
        RouterFunction<ServerResponse> route = RouterFunctions.route(RequestPredicates.GET("/resource"),
                                                                     request -> ServerResponse.ok().build());
        // @formatter:off
        WebTestClient.bindToRouterFunction(route)
                     .build()
                     .get()
                     .uri("/resource")
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody().isEmpty();
        // @formatter:on
    }

    @Test
    void bind2WebHandler() {
        // @formatter:off
        WebTestClient.bindToWebHandler(h -> Mono.empty())
                     .build().get().uri("/resource")
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody().isEmpty();
        // @formatter:on
    }

    @Test
    void sendRequest() {
        WebTestClient.bindToServer().baseUrl("http://localhost:8080").build().post().uri("/resource").exchange()
                     .expectStatus().isCreated().expectHeader().valueEquals("Content-Type", "application/json")
                     .expectBody().jsonPath("field").isEqualTo("value");
    }

    @Test
    void bind2Context() {
        WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void bind2Controller() {
        WebTestClient.bindToController(controller).build();
    }
}
