package com.example.citycyclerentals;

public class BikeBooking {
    private String bikeName;
    private String bikePrice;
    private String bikeLocation;
    private int bikeImage; // ✅ Add this field for the image

    // ✅ Empty constructor for Firebase
    public BikeBooking() {}

    public BikeBooking(String bikeName, String bikePrice, String bikeLocation, int bikeImage) {
        this.bikeName = bikeName;
        this.bikePrice = bikePrice;
        this.bikeLocation = bikeLocation;
        this.bikeImage = bikeImage;
    }

    public String getBikeName() {
        return bikeName;
    }

    public String getBikePrice() {
        return bikePrice;
    }

    public String getBikeLocation() {
        return bikeLocation;
    }

    public int getBikeImage() {  // ✅ Rename to match this getter method
        return bikeImage;
    }
}
