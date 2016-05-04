package design.mode.简单工厂;

public class OperationImp1 implements OperationInterface {

    @Override
    public void getResult(Operation operation) {
        System.out.println(operation.getAge() + operation.getName());
    }

}
