package nl.han.aim.oosevt.lamport.data.entity;

public class Area {

    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private int radius;

    public Area(int id, String name, double latitude, double longitude, int radius) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public Area() {
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getRadius() {
        return radius;
    }
}
