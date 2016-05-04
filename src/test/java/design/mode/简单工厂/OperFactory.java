package design.mode.简单工厂;

public class OperFactory {

    private OperationInterface oper;

    public void SwitchOperation(Operation operation) {
        switch (operation.getSymbol()) {
        case "+":
            oper = new OperationImp1();
            oper.getResult(operation);
            break;

        case "-":
            break;

        case "*":
            break;
        case "/":

            break;

        default:
            break;
        }
    }

    public static void main(String[] args) {
        OperFactory operFactory = new OperFactory();
        operFactory.SwitchOperation(new Operation(12.0, 13.0, "+"));
    }
}
