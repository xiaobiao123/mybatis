package dynamic.cglibDispatcher;

import net.sf.cglib.proxy.Dispatcher;
import dynamic.cglibLazy.PropertyBean;

public class ConcreteClassDispatcher implements Dispatcher {
    public Object loadObject() throws Exception {
        System.out.println("Dispatcher loadObject ...");
        PropertyBean object = new PropertyBean();
        object.setPropertyName("PropertyBeanName!");
        object.setPropertyValue(1);
        return object;
    }
}