package model.borrower;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Borrower {

    private Name name;
    private String gender;
    private Birth birth;
    private String email;
    private String phone;
    private String ip;
    private String photo_url;
    private String profile_url;
    private Passport passport;
    private Registration registration;
    private Residential residential;


    public Borrower(Name name, String gender, Birth birth, String email, String phone, String ip, String photo_url, String profile_url, Passport passport, Registration registration, Residential residential) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.ip = ip;
        this.photo_url = photo_url;
        this.profile_url = profile_url;
        this.passport = passport;
        this.registration = registration;
        this.residential = residential;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Birth getBirth() {
        return birth;
    }

    public void setBirth(Birth birth) {
        this.birth = birth;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Residential getResidential() {
        return residential;
    }

    public void setResidential(Residential residential) {
        this.residential = residential;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public static Borrower fromJson(JsonObject obBorrower) {

        JsonObject objectName = obBorrower.getAsJsonObject("name");
        String gender = obBorrower.get("gender").getAsString();
        JsonObject objectBirth = obBorrower.getAsJsonObject("birth");
        String email = obBorrower.get("email").getAsString();
        String phone = obBorrower.get("phone").getAsString();
        String ip = obBorrower.get("ip").getAsString();
        String profileurl = obBorrower.get("profile_url").getAsString();
        String photourl = obBorrower.get("photo_url").getAsString();

        JsonObject pasport = obBorrower.get("passport").getAsJsonObject();

        JsonObject obRegistration = obBorrower.getAsJsonObject("registration");
        JsonObject obResidential = obBorrower.getAsJsonObject("residential");

        return new Borrower(Name.fromJson(objectName), gender, Birth.fromJson(objectBirth), email, phone, ip, photourl, profileurl, Passport.fromJson(pasport), Registration.fromJson(obRegistration), Residential.fromJson(obResidential));

    }
}
