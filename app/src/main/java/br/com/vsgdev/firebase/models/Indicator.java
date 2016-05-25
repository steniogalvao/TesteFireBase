package br.com.vsgdev.firebase.models;

/**
 * Created by Stênio Galvão on 24/05/16 10:27.
 */
public class Indicator {

    private int id;
    private String name;
    private Float value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
