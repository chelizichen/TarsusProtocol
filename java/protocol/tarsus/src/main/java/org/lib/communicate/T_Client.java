package org.lib.communicate;

import lombok.Getter;
import lombok.Setter;
import org.lib.communicate.base.T_ProxyHandler;
import org.lib.communicate.handler.T_Proxy;

import java.io.*;
import java.net.Socket;

@Setter
@Getter
public class T_Client {

    public T_Client() throws IOException {
        // 创建Socket对象，指定服务器的IP地址和端口号
        Socket socket = new Socket("127.0.0.1", 24001);

//        // 获取输入流和输出流
//        InputStream inputStream = socket.getInputStream();
//        OutputStream outputStream = socket.getOutputStream();
//
//        // 从服务器接收数据
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        String line = reader.readLine();
//        System.out.println("从服务器接收到的数据：" + line);

        // 向服务器发送数据
//        String message = "Hello, server!";
//        outputStream.write(message.getBytes());
//        outputStream.flush();
//
//        // 关闭输入流和输出流以及Socket对象
//        inputStream.close();
//        outputStream.close();
//        socket.close();
    }

    public T_Client(T_ProxyHandler moduleInfo) throws Exception {
        moduleInfo.setSocket(new Socket(moduleInfo.getHost(), moduleInfo.getPort()));
        moduleInfo.setBufferedReader(new BufferedReader(new InputStreamReader(moduleInfo.getSocket().getInputStream())));
        moduleInfo.setBufferedWriter(new BufferedWriter(new OutputStreamWriter(moduleInfo.getSocket().getOutputStream())));
        T_Proxy.Servants.put(moduleInfo.getModuleName(), moduleInfo);
        moduleInfo.BufferReaderToStream();
    }
}
