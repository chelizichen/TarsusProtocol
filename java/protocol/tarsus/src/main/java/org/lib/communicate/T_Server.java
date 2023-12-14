package org.lib.communicate;


import org.lib.communicate.handler.T_ClientHandler;
import org.lib.communicate.handler.T_RPC;
import org.lib.decorator.TarsusModule;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class T_Server {
    private void Initialize(Class<?> clazz) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Step 1 先确定abstract类是否有Module注解
        boolean annotationPresent = clazz.getSuperclass().isAnnotationPresent(TarsusModule.class);
        if (!annotationPresent) {
            throw new ClassNotFoundException("TargetClass is not a Module");
        }
        // Step 2 创造类实例
        Object instance = clazz.getConstructor().newInstance();
        String moduleName = clazz.getSimpleName();
        System.out.println("Module: " + moduleName + " is Load Success");
        T_RPC.SetModule(moduleName, instance);
    }

    public T_Server(Class<?> clazz) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Initialize(clazz);
        int port = 24511; // 监听的端口号
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            // 等待客户端连接请求
            Socket clientSocket = serverSocket.accept();
            // 创建线程处理客户端请求
            new Thread(
                    new T_ClientHandler(clientSocket)
            ).start();
        }
    }


}
