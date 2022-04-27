package com.example.lab11;

public class Day {
    private final String info;
    private final String currentTemp;
    private final String feeltemp;
    private final String description;
    private final String humidity;
    private final String speed;
    private final String sunrise;
    private final String sunset;
    private final String icon;

    public Day(String info, String currentTemp, String feeltemp, String description, String humidity, String speed, String sunrise, String sunset, String icon) {
        this.info = info;
        this.currentTemp = currentTemp;
        this.feeltemp = feeltemp;
        this.description = description;
        this.humidity = humidity;
        this.speed = speed;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.icon = icon;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public String getFeeltemp() {
        return feeltemp;
    }

    public String getDescription() {
        return description;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getSpeed() {
        return speed;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getIcon() {
        return icon;
    }

    public String getInfo() {
        return info;
    }
}
