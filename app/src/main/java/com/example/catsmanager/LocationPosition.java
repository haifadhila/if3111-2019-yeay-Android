package com.example.catsmanager;

public class LocationPosition {
    private Number longitude;
    private Number latitude;

    public LocationPosition(Number longitude, Number latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Number getLatitude() {
        return latitude;
    }

    public Number getLongitude() {
        return longitude;
    }
}

