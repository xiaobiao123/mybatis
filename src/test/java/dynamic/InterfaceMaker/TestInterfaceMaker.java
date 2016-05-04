package dynamic.InterfaceMaker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TestInterfaceMaker {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        InterfaceMaker im = new InterfaceMaker();
        im.add(ConcreteClassNoInterface.class);
        Class interfaceOjb = im.create();
        System.out.println(interfaceOjb.isInterface());// true
        System.out.println(interfaceOjb.getName());

        Method[] methods = interfaceOjb.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        Object obj = Enhancer.create(Object.class, new Class[] { interfaceOjb }, new MethodInterceptor() {
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                return "intercept!";
            }
        });

        Method method = obj.getClass().getMethod("getConcreteMethodA", new Class[] { String.class });
        System.out.println(method.invoke(obj, new Object[] { "12345" }));
    }
}
