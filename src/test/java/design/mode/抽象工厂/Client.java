package design.mode.抽象工厂;

public class Client {
    public static void main(String[] args) {
        AbstractProductA pa;
        AbstractProductB pb;

        AbstractFactory fa1 = new ConcreteFactory1();
        pa = fa1.CreateProductA();
        pb = fa1.CreateProductB();
        pa.use();
        pb.use();

        AbstractFactory fa2 = new ConcreteFactory2();
        pa = fa2.CreateProductA();
        pb = fa2.CreateProductB();
        pa.use();
        pb.use();

    }
}