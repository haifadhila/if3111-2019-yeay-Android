package com.example.catsmanager;


public class CatData  {
    public String name;
    public Number pic;
    public String date;
    public String info;
    public Number focustime;
    public Number longitude;
    public Number latitude;

    public CatData() {
        //nothing
    }

    public CatData(String name, Number focustime, String date, Number longitude, Number latitude, Number pic) {
        this. pic = pic;
        this.date = date;
        this.info = "Spent " + focustime + " of focused time";
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
        this.focustime = focustime;
    }

    public Number getLongitude() {
        return longitude;
    }

    public Number getLatitude() {
        return latitude;
    }

    public int getPic() {
        return (int) pic;
    }

    public Number getFocustime() {
        return focustime;
    }

    public String getDate() {
        return date;
    }
}

