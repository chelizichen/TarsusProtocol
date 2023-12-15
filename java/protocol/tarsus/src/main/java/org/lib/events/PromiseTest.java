package org.lib.events;


import org.jdeferred2.Deferred;
import org.jdeferred2.DoneCallback;
import org.jdeferred2.Promise;
import org.jdeferred2.impl.DeferredObject;

import java.util.HashMap;

public class PromiseTest {
    public static HashMap<String, DeferredObject> hs = new HashMap();

    public static void main(String[] args) throws InterruptedException {
        DeferredObject deferredObject = new DeferredObject<>();
        Promise promise = deferredObject.promise();
        promise.done(new DoneCallback() {
            @Override
            public void onDone(Object o) {
                System.out.println(o);
            }
        });
        hs.put("aaa", deferredObject);
        Deferred aaa = hs.get("aaa").resolve("222");
        System.out.println(aaa);
        aaa.waitSafely();
    }
}
