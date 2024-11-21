package nc.dubbo.core.protocol.dubbo;


import nc.dubbo.core.Invocation;
import nc.dubbo.core.Invoker;
import nc.dubbo.core.URL;

public class DubboInvoker implements Invoker {
    private URL url;

    public DubboInvoker(URL url){
        this.url = url;
    }

    @Override
    public String invoke(Invocation invocation) {
        return new NettyClient<>().send(url.getHostname(), url.getPort(), invocation);
    }
}
