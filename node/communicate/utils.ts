import { T_Container } from "../category";
import { JceStruct } from "../type";

/**
 * @desc
 * 为Buffer 写入4位大小的值
 */
export function $WriteHead(taget:Buffer){
    const bytes = taget.byteLength;
    const head = Buffer.alloc(bytes + 4)
    head.writeInt32BE(bytes,0)
    taget.copy(head,4)
    return head;
}

export function $ReadHead(target:Buffer):number{
    const byteLength = target.readInt32BE(4)
    return byteLength;
}

export const CONSTANT = {
    TarsInovke:"tarsinvoke"
}

export class CommunicateBase{
    $ReflectGetClass(clazz:string):JceStruct{
        return T_Container.Get(clazz)
    }

    $ReflectGetResponse(invokeMethod):string{
        return T_Container.GetRpcMethod(invokeMethod)[1]
    }
}

// 检查函数是否是异步函数
export function isAsyncFunction(func) {
    return func.constructor === async function(){}.constructor;
}

export function TimesCall(cb,times){
    let i = 0;
    while(i <= times){
        cb();
        i++;
    }
}