package org.example.InitGateway.modules;

import lombok.Getter;
import org.example.InitGateway.rpc.SampleImpl;
import org.lib.decorator.TarsusServant;

@Getter

//@Service
public class TarsusModules {

    @TarsusServant
    private SampleImpl sample;
}
