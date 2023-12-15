package org.lib.communicate.handler;


import org.jdeferred2.impl.DeferredObject;
import org.lib.category.T_Base;
import org.lib.category.T_JceStruct;
import org.lib.communicate.base.T_Context;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class T_RPC {
    public static enum Handlers {
        Req,
        Res
    }

    public static HashMap<String, HashMap<Handlers, T_JceStruct>> METHODS = new HashMap<String, HashMap<Handlers, T_JceStruct>>();
    public static HashMap<String, DeferredObject<T_Base,Object,Object>> INVOKES = new HashMap();

    public static void SetMethod(String MethodName, T_JceStruct Req, T_JceStruct Res) {
        HashMap<Handlers, T_JceStruct> handler = new HashMap<>();
        handler.put(Handlers.Req, Req);
        handler.put(Handlers.Res, Res);
        T_RPC.METHODS.put(MethodName, handler);
    }


    public static HashMap<String, Object> Modules = new HashMap<String, Object>();

    public static void SetModule(String ModuleName, Object module) {
        T_RPC.Modules.put(ModuleName, module);
    }

    public static Object GetModule(String Module) {
        return T_RPC.Modules.get(Module);
    }

    public static <T extends T_Base> Method GetModuleMethod(String Module, String MethodName, Class<T> ResponseClass) throws NoSuchMethodException {
        Object INSTANCE = T_RPC.GetModule(Module);
        return INSTANCE.getClass().getDeclaredMethod(MethodName, T_Context.class, ResponseClass);
    }
}
