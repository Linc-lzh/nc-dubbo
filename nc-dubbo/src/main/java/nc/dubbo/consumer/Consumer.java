package nc.dubbo.consumer;


import nc.dubbo.core.ProxyFactory;
import nc.dubbo.provider.api.HelloService;

public class Consumer {
    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("Linc test");

        System.out.println("test result="+ result);
    }
}
