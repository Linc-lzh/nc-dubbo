package nc.dubbo.core;

public interface Protocol {
    void export(URL url);
    Invoker refer(URL url);
}
