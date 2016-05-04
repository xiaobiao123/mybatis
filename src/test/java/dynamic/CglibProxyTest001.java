package dynamic;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyTest001 implements MethodInterceptor {

    private Object target;

    public Object createProxy(Object obj) {

        this.target = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        enhancer.setClassLoader(this.target.getClass().getClassLoader());
        return enhancer.create();
    }

    @Override
    public Object intercept(Object object, Method method, Object[] arg, MethodProxy methodProxy) throws Throwable {
        Object obj = null;
        obj = methodProxy.invokeSuper(object, arg);
        return obj;
    }
}
