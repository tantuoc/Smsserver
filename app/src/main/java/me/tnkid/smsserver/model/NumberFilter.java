package me.tnkid.smsserver.model;

import java.io.Serializable;

/**
 * Created by tom on 12/11/2017.
 */

public class NumberFilter implements Serializable {
    private int id;
    private String name;
    private String number;

    public NumberFilter(int id,String name, String number) {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        this.id = _id;
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
