package org.example.InitServer.impl;

import org.example.InitServer.rpc.Sample;
import org.example.InitServer.structs.QueryId;
import org.example.InitServer.structs.User;
import org.lib.category.T_INT32;
import org.lib.category.T_INT8;
import org.lib.category.T_String;
import org.lib.communicate.base.T_Context;

public class SampleImpl extends Sample {
    @Override
    public User getUserById(T_Context ctx, QueryId req) {
        User user = new User();
        user.userId = new T_INT32(1);
        user.userAddress = new T_String("wuhan");
        user.userName = new T_String("cheng");
        user.phoneNumber = new T_String("13478812281");
        user.createTime = new T_String("2023-12-08");
        user.status = new T_INT8(1);
        return user;
    }
}
