package design.mode.简单工厂;

public class Operation {

    private Double name;
    private Double age;

    private String symbol;

    public Operation(Double name, Double age, String symbol) {
        super();
        this.name = name;
        this.age = age;
        this.symbol = symbol;
    }

    public Double getName() {
        return name;
    }

    public void setName(Double name) {
        this.name = name;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
