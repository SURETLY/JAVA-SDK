package model.borrower;

import com.google.gson.JsonObject;

public class Birth {
    private long date;
    private String place;

    public Birth() {
    }

    public Birth(long date, String place) {
        this.date = date;
        this.place = place;
    }

    public long getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public static Birth fromJson(JsonObject objectBirth) {
        long date = objectBirth.get("date").getAsLong();
        String place = objectBirth.get("place").getAsString();
        return new Birth(date, place);
    }
}
