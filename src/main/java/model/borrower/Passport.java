package model.borrower;

import com.google.gson.JsonObject;

public class Passport {

    private String series;
    private String number;
    private String issue_date;
    private String issue_place;
    private String issue_code;


    public String getSeries() {
        return series;
    }

    public String getNumber() {
        return number;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public String getIssue_place() {
        return issue_place;
    }

    public String getIssue_code() {
        return issue_code;
    }

    public Passport(String series, String number, String issue_date, String issue_place, String issue_code) {
        this.series = series;
        this.number = number;
        this.issue_date = issue_date;
        this.issue_place = issue_place;
        this.issue_code = issue_code;
    }

    public static Passport fromJson(JsonObject jsonPassport) {
        String series = jsonPassport.get("series").getAsString();
        String number = jsonPassport.get("number").getAsString();
        String issue_date = jsonPassport.get("issue_date").getAsString();
        String issue_place = jsonPassport.get("issue_place").getAsString();
        String issue_code = jsonPassport.get("issue_code").getAsString();

        return new Passport(series, number, issue_date, issue_place, issue_code);
    }
}
