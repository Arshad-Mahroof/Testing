package com.example.citycyclerentals;

public class ScootyBooking {
    private String scootyName;
    private int scootyImage;
    private String scootyPrice;
    private String scootyLocation;
    private long timestamp;

    // Default constructor (needed for Firestore)
    public ScootyBooking() {
        this.timestamp = System.currentTimeMillis();
    }

    public ScootyBooking(String scootyName, int scootyImage, String scootyPrice, String scootyLocation) {
        this.scootyName = scootyName;
        this.scootyImage = scootyImage;
        this.scootyPrice = scootyPrice;
        this.scootyLocation = scootyLocation;
        this.timestamp = System.currentTimeMillis();
    }

    public String getScootyName() {
        return scootyName;
    }

    public int getScootyImage() {
        return scootyImage;
    }

    public String getScootyPrice() {
        return scootyPrice;
    }

    public String getScootyLocation() {
        return scootyLocation;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
