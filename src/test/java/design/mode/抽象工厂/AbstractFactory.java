package design.mode.抽象工厂;

/**
 * 提供创建对象的接口。
 * 
 * @Description:
 * @author gwb
 * @date 2016年3月18日 下午5:03:03
 * 
 */
interface AbstractFactory {
    public AbstractProductA CreateProductA();

    public AbstractProductB CreateProductB();
}