package testjwebunit;

import org.junit.Before;
import org.junit.Test;
import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class TestJWebUnit {
    @Before
    public void prepare() {
        setBaseUrl("http://google.se");
    }

    @Test
    public void test1() {
        beginAt("/");
        setTextField("q", "jwebunit");
        submit("btnG");
        assertTextPresent("JWebUnit - JWebUnit");
    }
}
