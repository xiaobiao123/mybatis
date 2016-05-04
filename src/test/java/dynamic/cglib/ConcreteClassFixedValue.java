package dynamic.cglib;

import net.sf.cglib.proxy.FixedValue;

public class ConcreteClassFixedValue implements FixedValue {
    public Object loadObject() throws Exception {
        System.out.println("ConcreteClassFixedValue loadObject ...");
        Object object = 999;
        return object;
    }
}