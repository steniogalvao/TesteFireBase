package br.com.vsgdev.firebase.models;

import java.util.List;

/**
 * Created by Stênio Galvão on 24/05/16 10:26.
 */

public class Circle {

    private int id;
    private String name;
    private List<Indicator> indicators;

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

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }
}
