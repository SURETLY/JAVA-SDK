package model.borrower;

import com.google.gson.JsonObject;

public class Residential {

    private String country;
    private String zip;
    private String area;
    private String city;
    private String street;
    private String house;
    private String building;
    private String flat;

    public Residential(String country, String zip, String area, String city, String street, String house, String building, String flat) {
        this.country = country;
        this.zip = zip;
        this.area = area;
        this.city = city;
        this.street = street;
        this.house = house;
        this.building = building;
        this.flat = flat;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public static Residential fromJson(JsonObject obRegistration) {

        String country = obRegistration.get("country").getAsString();
        String zipReg = obRegistration.get("zip").getAsString();
        String areaReg = obRegistration.get("area").getAsString();
        String cityReg = obRegistration.get("city").getAsString();
        String streetReg = obRegistration.get("street").getAsString();
        String houseReg = obRegistration.get("house").getAsString();
        String buildingReg = obRegistration.get("building").getAsString();
        String flatReg = obRegistration.get("flat").getAsString();

        return new Residential(country, zipReg, areaReg, cityReg, streetReg, houseReg, buildingReg, flatReg);
    }

}
