package 设计模式.单列模式;

public class Singleton {
    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static final Singleton INSTANCE = new Singleton();
    }

    /**
     * 私有化构造方法
     */
    private Singleton() {
    }

    public static final Singleton getInstance() {
        System.out.println("xxxxxxxxxxxxxxx");
        return SingletonHolder.INSTANCE;
    }
}