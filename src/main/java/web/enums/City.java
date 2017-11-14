package web.enums;

import java.util.Random;

/**
 * Created by Anastasia on 31.10.2017.
 */

public enum City {

    KIEV("Kiev"), BROVARY("Brovary"), UMAN("Uman"),
    IRPIN("Irpin"), BUCHA("Bucha"), KHARKIV("Kharkiv"),
    ODESSA("Odessa"), CHERKASY("Cherkasy"), LVIV("Lviv"),
    MYKOLAIV("Mykolaiv"), ZAPORIZHIA("Zaporizhia"), POLTAVA("Poltava"),
    CHERNIHIV("Chernihiv"), DNIPRO("Dnipropetrovsk"), ZHYTOMYR("Zhytomyr");

    private static final City[] VALUES = values();
    private static final int SIZE = VALUES.length;
    public static final Random RANDOM = new Random();

    private String label;

    City(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static City getRandomCity() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
