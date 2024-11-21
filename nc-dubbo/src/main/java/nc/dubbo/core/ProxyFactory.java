package nc.dubbo.core;


import nc.dubbo.core.register.RemoteMapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory<T> {

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {

            @Override
            public synchronized Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String mock = System.getProperty("mock");

                if (mock != null && mock.startsWith("return:")) {
                    String result = mock.replace("return:", "");
                    return result;
                }

                int retry = 3;
                URL url = null;
                List<URL> urls = RemoteMapRegister.get(interfaceClass.getName());
                while (retry > 0) {
                    try {
                        Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

                        if (urls.size() == 0) {
                            retry = 0;
                            throw new Exception();
                        }

                        Invoker invoker = ClusterInvoker.join(interfaceClass);
                        return invoker.invoke(invocation);
                    } catch (Exception e) {
                        --retry;
                        if (retry > 0 && url != null)
                            urls.remove(url);
                        else
                            return "Service error!";
                    }
                }
                return null;
            }
        });
    }
}
