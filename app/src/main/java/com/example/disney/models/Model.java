package com.example.disney.models;

import java.util.ArrayList;

public class Model {

    //Esta es la clase que contiene todos los datos de la API (incluidos los que borró el que lleva la API que la cambia cada dos por tres)
    ArrayList<data> data;

    public ArrayList<Model.data> getData() {
        return data;
    }

    public void setData(ArrayList<Model.data> data) {
        this.data = data;
    }

    public class data{
        private String[] films;
        private String[] shortFilms;
        private String[] tvShows;
        private String[] videoGames;
        private String[] parkAtracctions;
        private String[] allies;
        private String[] enemies;
        private int _id;
        private String sourceUrl;
        private String name;
        private String imageUrl;
        private String createdAt;
        private String updatedAt;
        private String url;
        private int __v;

        public String[] getFilms() {
            return films;
        }

        public void setFilms(String[] films) {
            this.films = films;
        }

        public String[] getShortFilms() {
            return shortFilms;
        }

        public void setShortFilms(String[] shortFilms) {
            this.shortFilms = shortFilms;
        }

        public String[] getTvShows() {
            return tvShows;
        }

        public void setTvShows(String[] tvShows) {
            this.tvShows = tvShows;
        }

        public String[] getVideoGames() {
            return videoGames;
        }

        public void setVideoGames(String[] videoGames) {
            this.videoGames = videoGames;
        }

        public String[] getParkAtracctions() {
            return parkAtracctions;
        }

        public void setParkAtracctions(String[] parkAtracctions) {
            this.parkAtracctions = parkAtracctions;
        }

        public String[] getAllies() {
            return allies;
        }

        public void setAllies(String[] allies) {
            this.allies = allies;
        }

        public String[] getEnemies() {
            return enemies;
        }

        public void setEnemies(String[] enemies) {
            this.enemies = enemies;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }

}
