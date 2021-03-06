package 设计模式.单列模式;

public class Singleton1 {
    // 饱汉模式，声明时就创建实例对象
    public static final Singleton1 instance = new Singleton1();

    // 单类模式的构造方法必须为private，以避免通过构造方法创建对象实例，
    // 并且必须显示声明构造方法，以防止使用默认构造方法
    private Singleton1() {
    }

    // 单类模式必须对外提供获取实例对象的方法
    public static Singleton1 geInstance() {
        return instance;
    }

}