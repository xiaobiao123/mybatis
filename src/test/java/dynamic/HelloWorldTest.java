package dynamic;

public class HelloWorldTest {

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        CglibProxyTest001 cglibProxy = new CglibProxyTest001();
        HelloWorld hw = (HelloWorld) cglibProxy.createProxy(helloWorld);
        hw.sayHelloWorld();
    }
}