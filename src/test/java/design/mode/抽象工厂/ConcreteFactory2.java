package design.mode.抽象工厂;

/**
 * 提供真正创建对象的实现类，用于组合并创建不同的对象，实现一个产品族。
 * 
 * @Description:
 * @author gwb
 * @date 2016年3月18日 下午5:04:30
 * 
 */
class ConcreteFactory2 implements AbstractFactory {

    @Override
    public AbstractProductA CreateProductA() {
        return new ConcreteProductA2();
    }

    @Override
    public AbstractProductB CreateProductB() {
        return new ConcreteProductB2();
    }

}