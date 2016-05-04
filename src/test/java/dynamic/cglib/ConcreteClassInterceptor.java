package dynamic.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

public class ConcreteClassInterceptor implements MethodInterceptor {

    private Object target;

    public Object createProxy(Object object) {
        this.target = object;
        // Enhancer enhancer = new Enhancer();
        // enhancer.setSuperclass(target.getClass());
        // enhancer.setCallback(this);
        // enhancer.setClassLoader(target.getClass().getClassLoader());
        // return enhancer.create();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteClassNoInterface.class);
        CallbackFilter filter = new ConcreteClassCallbackFilter();
        enhancer.setCallbackFilter(filter);

        Callback interceptor = new ConcreteClassInterceptor();// (1)
        Callback noOp = NoOp.INSTANCE;// (2)
        Callback fixedValue = new ConcreteClassFixedValue();// (3)
        Callback[] callbacks = new Callback[] { interceptor, noOp, fixedValue };
        enhancer.setCallbacks(callbacks);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
        System.out.println("Before:" + method);
        Object object = proxy.invokeSuper(obj, arg);
        System.out.println("After:" + method);
        return object;
    }

}