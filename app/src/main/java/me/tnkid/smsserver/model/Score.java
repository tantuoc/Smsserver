package me.tnkid.smsserver.model;

import java.io.Serializable;


/**
 * Created by tantuoc96 on 12/5/2017.
 */

public class Score {
    int MHS;
    String name;
    float dToan;
    float dLy;
    float dHoa;

    public Score(int MHS, String name, float dToan, float dLy, float dHoa) {
        this.MHS = MHS;
        this.name = name;
        this.dToan = dToan;
        this.dLy = dLy;
        this.dHoa = dHoa;
    }

    public int getMHS() {
        return MHS;
    }

    public void setMHS(int MHS) {
        this.MHS = MHS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getdToan() {
        return dToan;
    }

    public void setdToan(float dToan) {
        this.dToan = dToan;
    }

    public float getdLy() {
        return dLy;
    }

    public void setdLy(float dLy) {
        this.dLy = dLy;
    }

    public float getdHoa() {
        return dHoa;
    }

    public void setdHoa(float dHoa) {
        this.dHoa = dHoa;
    }
}
