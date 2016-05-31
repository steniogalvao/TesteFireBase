package br.com.vsgdev.firebase.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stênio Galvão on 24/05/16 10:26.
 */

@IgnoreExtraProperties
public class Circle {

    private String key;
    private String name;
    private String description;


    public Circle() {
    }

    public Circle(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //    public List<Indicator> getIndicators() {
//        return indicators;
//    }
//
//    public void setIndicators(List<Indicator> indicators) {
//        this.indicators = indicators;
//    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("description", description);
        return map;
    }


}
