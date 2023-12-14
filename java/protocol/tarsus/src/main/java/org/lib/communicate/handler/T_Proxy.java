package org.lib.communicate.handler;

import org.lib.category.*;
import org.lib.communicate.base.T_InvokeBody;
import org.lib.communicate.base.T_ProxyHandler;
import org.lib.stream.T_WStream;

import java.util.HashMap;
import java.util.UUID;

public class T_Proxy {
    public static HashMap<String, T_ProxyHandler> Servants = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }
}
