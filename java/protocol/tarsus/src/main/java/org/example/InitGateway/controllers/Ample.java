package org.example.InitGateway.controllers;

import org.example.InitGateway.modules.TarsusModules;
import org.example.InitServer.structs.QueryId;
import org.example.InitServer.structs.User;
import org.lib.category.T_INT32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sample")
public class Ample {

    @Autowired
    TarsusModules tarsusModules;

    @GetMapping("/getUserById")
    Flux<User> getUserById(@RequestBody Object data) {
        QueryId queryId = new QueryId();
        User user = tarsusModules.getSample().getUserById(null, queryId);
        return Flux.just(user);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, WebFlux !";
    }

    @GetMapping("/user")
    public Mono<User> getUser() {
        User user = new User();
        user.userId = new T_INT32(11);
        return Mono.just(user);
    }
}
