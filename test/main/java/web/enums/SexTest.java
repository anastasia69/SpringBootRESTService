package web.enums;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import web.entity.IllegalListSizeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anastasia on 06.11.2017.
 */

@RunWith(SpringRunner.class)
public class SexTest extends Assert {

    private List<Sex> sexList;
    private List<Sex> males = new ArrayList<>();
    private List<Sex> females = new ArrayList<>();

    @Test
    public void getRandomSex60_40CatchExceptionTest() {
        try {
            sexList = Sex.getRandomSex60_40(2);
        }catch (IllegalListSizeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getRandomSex60_40Test() {
        try {
            sexList = Sex.getRandomSex60_40(10);

            for (int i = 0; i < sexList.size(); i++) {
                if (sexList.get(i).equals(Sex.FEMALE)) {
                    females.add(sexList.get(i));
                } else {
                    males.add(sexList.get(i));
                }
            }

            assertEquals(males.size(), 6);
            assertEquals(females.size(), 4);

        }catch (IllegalListSizeException e) {
            e.printStackTrace();
        }
    }

}
