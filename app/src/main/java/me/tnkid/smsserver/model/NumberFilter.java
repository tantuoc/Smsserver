package me.tnkid.smsserver.model;

/**
 * Created by tom on 12/11/2017.
 */

public class NumberFilter {
    private String name;
    private String number;

    public NumberFilter(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
