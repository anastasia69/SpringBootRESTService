package web.entity;

/**
 * Created by Anastasia on 05.11.2017.
 */

public class NoMoreSurnamesException extends Exception {

    public NoMoreSurnamesException(String message) {
        super(message);
    }

    public String toString(){
        return "NoMoreSurnamesException";
    }

}
