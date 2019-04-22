package com.example.catsmanager;

public class Cat  {
    private Number pic;
    private String date;
    private Number focustime;
    private LocationPosition location;
    private String email;

    public Cat() {
        //nothing
    }

    public Cat(Number pic, String date, Number focustime, LocationPosition location, String email) {
        this. pic = pic;
        this.date = date;
        this.focustime = focustime;
        this.location = location;
        this.email = email;
    }

    public Number getFocustime() {
        return focustime;
    }

    public Number getPic() {
        return pic;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public LocationPosition getLocation() {
        return location;
    }
}

