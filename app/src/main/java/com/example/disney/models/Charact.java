package com.example.disney.models;

public class Charact {

    //Esta clase la uso para poner solo los datos que quiero poner de cada personaje
    private String name, id, imageUrl, url;

    public Charact(String name, String id, String imageUrl, String url) {
        this.name = name;
        this.id = id;
        this.imageUrl = imageUrl;
        this.url = url;
    }

    public Charact() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}