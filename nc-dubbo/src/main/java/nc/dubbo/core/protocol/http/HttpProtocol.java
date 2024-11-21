package nc.dubbo.core.protocol.http;

import nc.dubbo.core.Invoker;
import nc.dubbo.core.Protocol;
import nc.dubbo.core.URL;
import nc.dubbo.core.register.LocalRegister;
import nc.dubbo.core.register.RemoteMapRegister;

public class HttpProtocol implements Protocol {
    @Override
    public void export(URL url) {
        LocalRegister.regist(url.getInterfaceName(), url.getImplClass());
        RemoteMapRegister.regist(url.getInterfaceName(), url);
        new HttpServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public Invoker refer(URL url) {
        return new HttpInvoker(url);
    }
}
