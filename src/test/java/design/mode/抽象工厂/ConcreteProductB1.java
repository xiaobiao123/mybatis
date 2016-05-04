package design.mode.抽象工厂;

/**
 * 提供真正的适用对象，隐藏该对象的创建过程，是工厂创建的对象。
 * 
 * @Description:
 * @author gwb
 * @date 2016年3月18日 下午5:04:59
 * 
 */
class ConcreteProductB1 implements AbstractProductB {

    @Override
    public void use() {
        // TODO Auto-generated method stub
        System.out.println("use B1 product!");
    }

}