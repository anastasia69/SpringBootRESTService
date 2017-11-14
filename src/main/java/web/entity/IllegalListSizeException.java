package web.entity;

/**
 * Created by Anastasia on 05.11.2017.
 */

public class IllegalListSizeException extends Exception {

    public IllegalListSizeException(String message) {
        super(message);
    }

    public String toString(){
        return "IllegalListSizeException";
    }

}
