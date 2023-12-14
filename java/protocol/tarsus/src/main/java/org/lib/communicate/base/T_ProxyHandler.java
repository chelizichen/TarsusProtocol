package org.lib.communicate.base;

import lombok.Getter;
import lombok.Setter;
import org.lib.category.*;
import org.lib.communicate.handler.T_Proxy;
import org.lib.communicate.handler.T_RPC;
import org.lib.stream.T_RStream;
import org.lib.stream.T_WStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
@Setter
public class T_ProxyHandler {
    private String host;
    private Integer port;
    private String moduleName;
    private String timeout;
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    private Integer WholeLength;
    private ByteBuffer WholeBuffer;

    public T_ProxyHandler(String host, Integer port, String moduleName, String timeout) {
        this.host = host;
        this.port = port;
        this.moduleName = moduleName;
        this.timeout = timeout;
    }

    public void BufferReaderToStream() throws Exception {
        String streams = "";
        StringBuilder stringBuffer = new StringBuilder();
        while ((streams = getBufferedReader().readLine()) != null) {
            stringBuffer.append(streams);
            {
                byte[] bytes = stringBuffer.toString().getBytes();
                ByteBuffer wrap = ByteBuffer.wrap(bytes);
                this.ReadResponseStream(wrap, bytes);
                stringBuffer.delete(0, stringBuffer.length()); // 每次清空
            }
        }
    }

    public void ReadResponseStream(ByteBuffer wrap, byte[] bytes) throws Exception {
        if (getWholeLength() == 0) { // 重制状态,有完整包
            int ByteLength = wrap.getInt(0);
            if (ByteLength + 4 <= bytes.length) { // 一次性发了多个包，需要拆分
                ByteBuffer buffer1 = ByteBuffer.wrap(bytes, 0, ByteLength);
                this.ReturnToGateway(buffer1);
                // 拆分包 然后继续调用
                if (ByteLength + 4 != bytes.length) {
                    ByteBuffer wrap1 = ByteBuffer.wrap(bytes, ByteLength + 4, bytes.length);
                    this.ReadResponseStream(wrap1, wrap1.array());
                    return;
                }
                return;
            }
            if (ByteLength + 4 > bytes.length) { // 给的不够 需要存起来
                setWholeLength(ByteLength);
                setWholeBuffer(wrap);
            }
        } else { // 不是完整包，先将bytes添加入临时变量里
            ByteBuffer put = getWholeBuffer().put(bytes);
            int length = put.array().length; // 加入之后的长度
            if (length >= getWholeLength()) { // 如果大于给定的，则代表是个完整包，重置后走Invoke逻辑
                this.Reset();
                this.ReadResponseStream(put, put.array());
            } else {
                setWholeBuffer(put);
            }
        }
    }

    public <T extends T_Base> void WriteToServer(T_InvokeBody<T> body) throws Exception {
        T_ProxyHandler tm = T_Proxy.Servants.get(body.moduleName);
        String traceId = UUID.randomUUID().toString();
        T_Vector<T_String> TraceIds = new T_Vector<>(T_String.class);
        TraceIds.push(traceId);
        T_JceStruct jceStruct = T_Container.JCE_STRUCT.get(body.invokeRequest);
        T_WStream ws = new T_WStream();
        ws.WriteString(0, body.moduleName);
        ws.WriteString(1, body.methodName);
        ws.WriteString(2, body.invokeRequest);
        ws.WriteVector(3, TraceIds);
        ws.WriteStruct(4, body.RequestBody, jceStruct.Write);
        T_WStream resp = new T_WStream();
        resp.WriteInt32(0, ws.position);
        resp.WriteBuf(-1, ws.originBuf.array(), ws.position);
        tm.getBufferedWriter().write(resp.toBuf().asCharBuffer().array());

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return traceId;
        });
        T_RPC.EVENTS.put(traceId, future);
    }

    public <T extends T_Base> void ReturnToGateway(ByteBuffer buffer) throws Exception {
        T_RStream rs = new T_RStream(buffer);
        T_INT32 ByteLength = rs.ReadInt32(0);
        T_String ModuleName = rs.ReadString(1);
        T_String InvokeMethod = rs.ReadString(2);
        System.out.println("ByteLength - " + ByteLength + " || ModuleName - " + ModuleName.GetValue() + " || InvokeMethod - " + InvokeMethod.GetValue());
        T_String InvokeResponse = rs.ReadString(3);
        T_Vector<T_String> TraceId = rs.ReadVector(4, T_String.class);
        T_JceStruct ResponseStruct = T_Container.JCE_STRUCT.get(InvokeResponse.GetValue());
        T_Base InvokeRequestBody = rs.ReadStruct(5, ResponseStruct.Base, ResponseStruct.Read);
        // 拿到 TraceId
        T_String traceId = TraceId.get(0);
        T_RPC.EVENTS.get(traceId.GetValue()).thenAccept(value -> {
            System.out.println("hello world" + value);
        });
    }

    public void Reset() {
        setWholeBuffer(null); // 标记不可达，gc
        setWholeLength(0);
    }

}
