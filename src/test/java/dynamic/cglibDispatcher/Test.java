package dynamic.cglibDispatcher;

import dynamic.cglibLazy.PropertyBean;

public class Test {
    public static void main(String[] args) {
        DispatcherBean dispatcherBean = new DispatcherBean();
        System.out.println(dispatcherBean.getName());
        System.out.println(dispatcherBean.getValue());

        PropertyBean pb = dispatcherBean.getPropertyBean();
        System.out.println(pb.getPropertyName());
        // 在每次访问时都要进行回调
        System.out.println(pb.getPropertyValue());
    }

}
