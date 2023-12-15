package org.example.InitGateway.controllers;

import org.example.InitGateway.modules.TarsusModules;
import org.example.InitServer.structs.QueryId;
import org.example.InitServer.structs.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sample")
public class Ample {

//    @Autowired
//    TarsusModules tarsusModules;

//    @GetMapping("/getUserById")
//    User getUserById(@RequestBody QueryId data) throws Exception {
//        User user = tarsusModules.getSample().getUserById(null, data);
//        return user;
//    }

    @GetMapping("/test")
    public String getUserById() throws Exception {
        return "hello world";
    }

}
