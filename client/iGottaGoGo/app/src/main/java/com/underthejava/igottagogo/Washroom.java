package com.underthejava.igottagogo;

/**
 * Created by Ryan on 2017-03-30.
 */

public class Washroom {

    private String title;
    private String description;
    private int floor;
    private int stalls;

    private boolean customersOnly;
    private boolean disability;
    private boolean family;
    private boolean male;
    private boolean female;
    private boolean hygiene;
    private boolean showers;
    private double lat;
    private double lang;

    private float rating;

    public Washroom(String title, String description, int floor, int stalls, boolean customersOnly, boolean disability,
                    boolean family, boolean male, boolean female, boolean hygiene, boolean showers, float rating, double lat, double lang)
    {
        this.title = title;
        this.description = description;
        this.floor = floor;
        this.stalls = stalls;
        this.customersOnly = customersOnly;
        this.disability = disability;
        this.family = family;
        this.male = male;
        this.female = female;
        this.hygiene = hygiene;
        this.showers = showers;
        this.rating = rating;
        this.lat = lat;
        this.lang = lang;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getStalls() {
        return stalls;
    }

    public void setStalls(int stalls) {
        this.stalls = stalls;
    }

    public boolean isCustomersOnly() {
        return customersOnly;
    }

    public void setCustomersOnly(boolean customersOnly) {
        this.customersOnly = customersOnly;
    }

    public boolean isDisability() {
        return disability;
    }

    public void setDisability(boolean disability) {
        this.disability = disability;
    }

    public boolean isFamily() {
        return family;
    }

    public void setFamily(boolean family) {
        this.family = family;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public boolean isHygiene() {
        return hygiene;
    }

    public void setHygiene(boolean hygiene) {
        this.hygiene = hygiene;
    }

    public boolean isShowers() {
        return showers;
    }

    public void setShowers(boolean showers) {
        this.showers = showers;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public double getLat(){return lat;}

    public void setLat(double lat){this.lat = lat;}

    public double getLang(){return lang;}

    public void setLang(double lang){this.lang = lang;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
