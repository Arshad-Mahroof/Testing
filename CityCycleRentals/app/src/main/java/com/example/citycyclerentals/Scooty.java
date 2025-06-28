package com.example.citycyclerentals;

public class Scooty {
    private String name;
    private int imageResId;
    private String price;
    private String location;

    public Scooty(String name, int imageResId, String price, String location) {
        this.name = name;
        this.imageResId = imageResId;
        this.price = price;
        this.location = location;
    }

    public String getName() { return name; }
    public int getImageResId() { return imageResId; }
    public String getPrice() { return price; }
    public String getLocation() { return location; }
}
