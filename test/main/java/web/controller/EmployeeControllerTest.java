package web.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Anastasia on 11.11.2017.
 */

@RunWith(SpringRunner.class)
public class EmployeeControllerTest extends Assert {

    private EmployeeController controller = new EmployeeController();

    @Test
    public void welcomePageTest() {
        String str = controller.welcomePage();

        assertEquals("index", str);
    }
}
