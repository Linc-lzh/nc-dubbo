package nc.dubbo.provider;

import nc.dubbo.core.Protocol;
import nc.dubbo.core.ProtocolFactory;
import nc.dubbo.core.URL;
import nc.dubbo.provider.api.HelloService;
import nc.dubbo.provider.impl.HelloServiceImpl;

public class Provider {
    public static void main(String[] args) {
        String protocolName = getProtocol();
        URL url = new URL(protocolName, "localhost", 8081, HelloService.class.getName(), HelloServiceImpl.class);
        Protocol protocol = ProtocolFactory.getProtocol(protocolName);
        protocol.export(url);
    }

    public static String getProtocol() {
        String protocolName = System.getProperty("protocolName");
        if (protocolName == null)
            return "http";
        return protocolName;
    }
}
