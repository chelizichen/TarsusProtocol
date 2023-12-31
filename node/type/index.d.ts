import T_Client from "../communicate/client";
import { T_WStream, T_RStream } from "../stream";

type Constructor<T> = new (...args:any[])=>T

type JceStruct = {
    Write:Constructor<T_WStream>,
    Read:Constructor<T_RStream>,
    _t_className:string;
}

type T_BASE = {
    _t_className : string;
}

type module = string;
type invokeMethod = string;
type invokeRequest = string;
type invokeResponse =  string;
type plain = Record<string,any>
type InvokeContext = {
    byteLength: string;
    moduleName: string;
    invokeMethod:string;
    invokeRequest: string;
    traceId: any;
    sendResponse ?:Function;
    responseUid?:string;
    invokeResponse?:string;
}

type ClinetProxy = {
    $InvokeRpc(module:string,method:string,request:string,body):any;
}
type Module<T = JceStruct> = Record<string, Record<string, T> | T>;

type T_GateWayClient = {
    Module:string; // 模块
    client:T_Client;
}

export {
    Constructor,
    JceStruct,
    T_BASE,
    module,
    invokeMethod,
    invokeRequest,
    invokeResponse,
    plain,
    InvokeContext,
    ClinetProxy,
    T_GateWayClient,
    Module
}