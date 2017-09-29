package model.borrower;

import com.google.gson.JsonObject;

public class Registration {

    private String country;
    private String zip;
    private String area;
    private String city;
    private String street;
    private String house;
    private String building;
    private String flat;


    public Registration(String country, String zip, String area, String city, String street, String house, String building, String flat) {
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

    public String getZip() {
        return zip;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getBuilding() {
        return building;
    }

    public String getFlat() {
        return flat;
    }

    public static Registration fromJson(JsonObject obRegistration) {

        String country = obRegistration.get("country").getAsString();
        String zipReg = obRegistration.get("zip").getAsString();
        String areaReg = obRegistration.get("area").getAsString();
        String cityReg = obRegistration.get("city").getAsString();
        String streetReg = obRegistration.get("street").getAsString();
        String houseReg = obRegistration.get("house").getAsString();
        String buildingReg = obRegistration.get("building").getAsString();
        String flatReg = obRegistration.get("flat").getAsString();

        return new Registration(country, zipReg, areaReg, cityReg, streetReg, houseReg, buildingReg, flatReg);
    }
}
