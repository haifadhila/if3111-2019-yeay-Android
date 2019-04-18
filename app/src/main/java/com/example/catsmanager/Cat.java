package com.example.catsmanager;

import android.os.Parcel;
import android.os.Parcelable;

public class Cat implements Parcelable {

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
    public Cat(String title, String info, String dateAcquired, double latitude, double longitude, int imageResource) {
        this.title = title;
        this.info = info;
        this.dateAcquired = dateAcquired;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageResource = imageResource;
    }

    protected Cat(Parcel in) {
        this.title = in.readString();
        this.info = in.readString();
        this.dateAcquired = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.imageResource = in.readInt();
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

    public static final Creator<Cat> CREATOR = new Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel in) {
            return new Cat(in);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(info);
        dest.writeString(dateAcquired);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeInt(imageResource);
    }

}

