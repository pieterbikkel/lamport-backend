package nl.han.aim.oosevt.lamport.controllers.location.dto;

import nl.han.aim.oosevt.lamport.shared.RequestDTO;

public abstract class LocationRequestDTO extends RequestDTO {
    private String name;
    private int delay;
    private double longitude;
    private double latitude;
    private int radius;
    private int areaId;

    public LocationRequestDTO() {
    }

    public LocationRequestDTO(String name, int delay, double longitude, double latitude, int radius, int areaId) {
        this.name = name;
        this.delay = delay;
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    @Override
    protected void validateDTO() {
        if(name.isEmpty()) {
            addError("name", "Naam mag niet leeg zijn!");
        }
        if(delay == 0) {
            addError("delay", "Delay mag niet leeg zijn!");
        }
        if(longitude == 0) {
            addError("longitude", "Lengtegraad mag niet leeg zijn!");
        }
        if(latitude == 0) {
            addError("latitude", "Breedtegraad mag niet leeg zijn!");
        }
        if(radius < 0) {
            addError("radius", "Straal mag niet kleiner zijn dan 0");
        }
        if (areaId == 0) {
            addError("areaId", "AreaId mag niet leeg zijn!");
        }
        validateSpecificDto();
    }

    protected abstract void validateSpecificDto();
}