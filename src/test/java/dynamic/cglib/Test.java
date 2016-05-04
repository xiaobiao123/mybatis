package dynamic.cglib;

public class Test {
    public static void main(String[] args) {
        ConcreteClassNoInterface a = new ConcreteClassNoInterface();
        ConcreteClassInterceptor proxy = new ConcreteClassInterceptor();
        ConcreteClassNoInterface proxyObject = (ConcreteClassNoInterface) proxy.createProxy(a);

        System.out.println("*** NoOp Callback ***");
        proxyObject.getConcreteMethodA("abcde");

        System.out.println("*** MethodInterceptor Callback ***");
        proxyObject.getConcreteMethodB(1);

        System.out.println("*** FixedValue Callback ***");
        int fixed1 = proxyObject.getConcreteMethodFixedValue(128);
        System.out.println("fixedValue1:" + fixed1);
        int fixed2 = proxyObject.getConcreteMethodFixedValue(256);
        System.out.println("fixedValue2:" + fixed2);
    }
}
