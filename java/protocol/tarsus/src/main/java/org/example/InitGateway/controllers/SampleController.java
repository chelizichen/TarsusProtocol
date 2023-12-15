package org.example.InitGateway.controllers;

import org.example.InitGateway.modules.TarsusModules;
import org.example.InitGateway.rpc.SampleImpl;
import org.example.InitGateway.structs.QueryId;
import org.example.InitGateway.structs.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {

//    @Autowired
//    TarsusModules tarsusModules;

    @RequestMapping("/getUserById")
    User getUserById(@RequestBody QueryId data) throws Exception {
        SampleImpl sample = new SampleImpl();
        System.out.println(data.id.GetValue());
        User user = sample.getUserById(null, data);
        return user;
    }

    @RequestMapping("/test")
    public String getUserById() throws Exception {
        return "hello world";
    }

}
