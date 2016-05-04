package testM;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxyFactory;
import org.springframework.aop.framework.DefaultAopProxyFactory;

@SuppressWarnings("serial")
public class ProxyCreatorSupporTest extends AdvisedSupport {
    // AOPProxy工厂
    private AopProxyFactory aopProxyFactory;

    public AopProxyFactory getAopProxyFactory() {
        return this.aopProxyFactory;
    }

    // 默认使用DefaultAopProxyFactory作用AOP代理工厂
    public ProxyCreatorSupporTest() {
        this.aopProxyFactory = new DefaultAopProxyFactory();
    }

}