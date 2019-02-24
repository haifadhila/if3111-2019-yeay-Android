package com.example.catsmanager;

class Cat {

    // Member variables representing the title and information about the Cat.
    private String title;
    private String info;
    private String dateAcquired;
    private double latitude;
    private double longitude;
    private final int imageResource;

    /**
     * Constructor for the Cat data model.
     *  @param title The name if the Cat.
     * @param info Information about the Cat.
     * @param dateAcquired
     * @param latitude
     * @param longitude
     */
    Cat(String title, String info, String dateAcquired, double latitude, double longitude, int imageResource) {
        this.title = title;
        this.info = info;
        this.dateAcquired = dateAcquired;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageResource = imageResource;
    }

    /**
     * Gets the title of the Cat.
     *
     * @return The title of the Cat.
     */
    String getTitle() {
        return title;
    }

    /**
     * Gets the info about the Cat.
     *
     * @return The info about the Cat.
     */
    String getInfo() {
        return info;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getDateAcquired() {
        return dateAcquired;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

