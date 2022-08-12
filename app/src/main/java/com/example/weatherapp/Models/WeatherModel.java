package com.example.weatherapp.Models;

public class WeatherModel {

    String title, description, pubDate, latLng, link;

    public WeatherModel(String title, String description, String pubDate, String latLng, String link) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.latLng = latLng;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLatLng() {
        return latLng;
    }

    public void setLatLng(String latLng) {
        this.latLng = latLng;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}