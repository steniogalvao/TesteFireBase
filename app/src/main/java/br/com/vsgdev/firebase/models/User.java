package br.com.vsgdev.firebase.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stênio Galvão on 24/05/16 10:18.
 */

@IgnoreExtraProperties
public class User {

    private String key;
    private String email;
    private List<Circle> circles;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public User(String email, List<Circle> circles) {

        this.email = email;
        this.circles = circles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Circle> getCircles() {
        return circles;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> mapCircles = new HashMap<>();
        result.put("email", email);
        result.put("circles", circles);
        return result;
    }
}
