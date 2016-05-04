package 设计模式.单列模式;

import org.junit.Test;

public class TestSing {

    private Singleton singleton2;

    @SuppressWarnings("static-access")
    @Test
    public void test() {
        singleton2.getInstance();
        singleton2.getInstance();
        // fail("Not yet implemented");
    }

}
