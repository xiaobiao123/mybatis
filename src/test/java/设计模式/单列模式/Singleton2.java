package 设计模式.单列模式;

public class Singleton2 {
    // 饿汉模式，声明时不创建实例对象
    public static Singleton2 instance;

    // 单类模式的构造方法必须为private，以避免通过构造方法创建对象实例，
    // 并且必须显示声明构造方法，以防止使用默认构造方法
    private Singleton2() {
    }

    // 单类模式必须对外提供获取实例对象的方法，延迟初始化的单类模式必须使用synchronized同步关键字，否则多线程情况下很容易产生多个实例对象
    public static synchronized Singleton2 geInstance() {
        // 延迟初始化，只有当第一次使用时才创建对象实例
        if (instance == null) {
            System.out.println("Singleton2");
            return new Singleton2();
        }
        return instance;
    }
}