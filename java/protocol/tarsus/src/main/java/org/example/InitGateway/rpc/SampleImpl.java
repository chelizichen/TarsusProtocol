package org.example.InitGateway.rpc;

import org.example.InitGateway.structs.QueryId;
import org.example.InitGateway.structs.User;
import org.jdeferred2.DoneCallback;
import org.jdeferred2.Promise;
import org.jdeferred2.impl.DeferredObject;
import org.lib.category.T_Base;
import org.lib.communicate.base.T_Context;
import org.lib.communicate.base.T_InvokeBody;
import org.lib.communicate.T_Client;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SampleImpl extends Sample {
    static T_Client Servants = T_Client.Servants.get("Sample");

    @Override
    public User getUserById(T_Context ctx, QueryId req) throws Exception {
        T_InvokeBody<T_Base> body = new T_InvokeBody<>();
        body.invokeRequest = "Sample.Struct<QueryId>";
        body.methodName = "getUserById";
        body.moduleName = "Sample";
        body.RequestBody = req;
        DeferredObject deferredObject = Servants.InvokeToServer(body);
        Promise<User, Object, Object> promise = deferredObject.promise();
        CountDownLatch latch = new CountDownLatch(1);
        final User[] user = {null};
        promise.done(new DoneCallback<User>() {
            @Override
            public void onDone(User o) {
                user[0] = o;
                latch.countDown();
            }
        });
        latch.await(10, TimeUnit.SECONDS);
//        deferredObject.promise().
        return user[0];
    }
}
