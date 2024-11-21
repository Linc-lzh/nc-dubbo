package nc.dubbo.core.protocol.http;


import nc.dubbo.core.Invocation;
import nc.dubbo.core.Invoker;
import nc.dubbo.core.URL;

import java.io.IOException;

public class HttpInvoker implements Invoker {

    private URL url;

    public HttpInvoker(URL url) {
        this.url = url;
    }

    @Override
    public String invoke(Invocation invocation) {
        try {
            return new HttpClient().send(url.getHostname(), url.getPort(),invocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
