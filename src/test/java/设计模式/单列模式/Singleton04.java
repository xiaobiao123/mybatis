package 设计模式.单列模式;

public class Singleton04 {

    private static Singleton04 instance = null;
    static {
        System.out.println("xxxxxxxxxxxx");
        instance = new Singleton04();
    }

    private Singleton04() {
    }

    public static Singleton04 getInstance() {
        return instance;
    }
}