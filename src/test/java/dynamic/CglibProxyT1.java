package dynamic;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyT1 implements MethodInterceptor {

    private Object obj;

    public Object createProxy(Object object) {
        this.obj = object;
        // Enhancer类是CGLib中的一个字节码增强器
        Enhancer enhancer = new Enhancer(); // Enhancer用来生成一个原有类的子类
        // 首先将被代理类-------设置成父类
        enhancer.setSuperclass(this.obj.getClass()); // 进行代理
        // 然后设置拦截器(CglibProxyT1)
        enhancer.setCallback(this);// 设置织入逻辑
        enhancer.setClassLoader(this.obj.getClass().getClassLoader());
        // 最后执行enhancer.create()动态生成一个代理类，并从Object强制转型成父类型。HelloWorld(this.obj.getClass())
        return enhancer.create();// 生成代理实例

    }

    /**
     * obj -----------CGLib动态生成的代理实例
     * <p>
     * method --------实体类所调用的被代理的方法引用
     * <p>
     * parms ---------参数
     * 
     * methodProxy----生成代理类对方法的引用
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] parms, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
        // 调用代理类实例上的proxy方法的父类方法（即实体类HelloWorld中对应的方法）
        result = methodProxy.invokeSuper(obj, parms);
        System.out.println("ddddddddddddddddddddddddddddddd");
        return result;
    }
}
