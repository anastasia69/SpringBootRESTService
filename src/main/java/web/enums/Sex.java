package web.enums;

import web.entity.IllegalListSizeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Anastasia on 31.10.2017.
 */
public enum Sex {

    MALE("Male"),
    FEMALE("Female");

    private static final Sex[] VALUES = values();
    private static final int SIZE = VALUES.length;
    public static final Random RANDOM = new Random();
    private static int malePart;
    private static int femalePart;

    private String label;

    Sex(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    private static Sex getRandomSex()
    {
        return VALUES[RANDOM.nextInt(SIZE)];
    }

    public static List<Sex> getRandomSex60_40(int listSize) throws IllegalListSizeException {
        if (listSize < 3) throw new IllegalListSizeException("We need more people! " +
                "Better more then 3, if we need 60/40 condition.");

        List<Sex> list = new ArrayList<>();
        malePart = (int) Math.round(listSize * 0.6);
        femalePart = listSize - malePart;
        Sex current;

        do {
            current = getRandomSex();

            if (current.equals(MALE) & malePart > 0) {
                malePart--;
                list.add(current);
            }

            if (current.equals(FEMALE) & femalePart > 0){
                femalePart--;
                list.add(current);
            }

        } while (malePart > 0 | femalePart > 0); //true

        return list;
    }

}
