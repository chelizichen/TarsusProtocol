package org.example.InitGateway.modules;

import lombok.Getter;
import org.example.InitServer.rpc.Sample;
import org.lib.decorator.TarsusModule;
import org.lib.decorator.TarsusServant;
import org.springframework.stereotype.Service;

@Getter
@Service
public class TarsusModules {

    @TarsusServant
    private Sample sample;
}
