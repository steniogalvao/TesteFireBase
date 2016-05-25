package br.com.vsgdev.firebase.models;

/**
 * Created by Stênio Galvão on 24/05/16 10:18.
 */

public class User {

    private int id;
    private String email;
    private int password;
    private Circle circle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
}
