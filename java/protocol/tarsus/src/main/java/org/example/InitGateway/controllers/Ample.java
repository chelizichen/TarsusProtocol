package org.example.InitGateway.controllers;

import org.example.InitGateway.modules.TarsusModules;
import org.example.InitServer.structs.QueryId;
import org.example.InitServer.structs.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class Ample {

    @Autowired
    TarsusModules tarsusModules;

    @GetMapping("/getUserById")
    User getUserById(@RequestBody Object data) {
        QueryId queryId = new QueryId();
        User userById = tarsusModules.getSample().getUserById(null, queryId);
        return userById;
    }
}
