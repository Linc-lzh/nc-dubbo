package nc.dubbo.provider.impl;


import nc.dubbo.provider.api.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String userName) {
        return "Hello:" + userName;
    }
}
