package org.lib.communicate.base;

import org.lib.category.T_INT32;
import org.lib.category.T_String;
import org.lib.category.T_Vector;

public  class T_Context {
    public T_INT32 ByteLength;
    public T_String ModuleName;
    public T_String InvokeMethod;
    public T_String InvokeRequest;
    public T_String InvokeResponse;
    public T_Vector<T_String> TraceId;

    public T_Context(T_INT32 byteLength, T_String moduleName, T_String invokeMethod, T_String invokeRequest, T_String invokeResponse, T_Vector<T_String> traceId) {
        ByteLength = byteLength;
        ModuleName = moduleName;
        InvokeMethod = invokeMethod;
        InvokeRequest = invokeRequest;
        TraceId = traceId;
        InvokeResponse = invokeResponse;
    }
}