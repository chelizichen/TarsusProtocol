package org.example.InitServer;

import org.example.InitServer.impl.SampleImpl;
import org.lib.communicate.T_Server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("初始化服务 :" + 24511);
        new T_Server(SampleImpl.class);
    }
}
