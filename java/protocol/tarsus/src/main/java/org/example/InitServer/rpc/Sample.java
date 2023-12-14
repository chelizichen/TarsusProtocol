package org.example.InitServer.rpc;

import org.example.InitServer.structs.BasicInfo;
import org.example.InitServer.structs.QueryId;
import org.example.InitServer.structs.User;
import org.lib.category.T_Container;
import org.lib.category.T_JceStruct;
import org.lib.communicate.base.T_Context;
import org.lib.communicate.handler.T_RPC;
import org.lib.decorator.TarsusModule;

@TarsusModule
public abstract class Sample {
    static {
        T_Container.JCE_STRUCT.put(QueryId._t_className, new T_JceStruct(QueryId.Read.class, QueryId.Write.class, QueryId.class, QueryId._t_className));
        T_Container.JCE_STRUCT.put(User._t_className, new T_JceStruct(User.Read.class, User.Write.class, User.class, User._t_className));
        T_Container.JCE_STRUCT.put(BasicInfo._t_className, new T_JceStruct(BasicInfo.Read.class, BasicInfo.Write.class, BasicInfo.class, BasicInfo._t_className));
        T_RPC.SetMethod("getUserById", T_Container.JCE_STRUCT.get(QueryId._t_className), T_Container.JCE_STRUCT.get(User._t_className));
    }

    public abstract User getUserById(T_Context ctx, QueryId req);
}
