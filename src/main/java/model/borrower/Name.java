package model.borrower;

import com.google.gson.JsonObject;

public class Name {

    private String first;
    private String middle;
    private String last;
    private String maiden;

    public Name() {
    }

    public Name(String first, String middle, String last, String maiden) {
        this.first = first;
        this.middle = middle;
        this.last = last;
        this.maiden = maiden;
    }

    public String getFirst() {
        return first;
    }


    public String getMiddle() {
        return middle;
    }

    public String getLast() {
        return last;
    }

    public String getMaiden() {
        return maiden;
    }

    public static Name fromJson(JsonObject objectName) {
        String first = objectName.get("first").getAsString();
        String middle = objectName.get("middle").getAsString();
        String last = objectName.get("last").getAsString();
        String maiden = objectName.get("maiden").getAsString();

        return new Name(first, middle, last, maiden);
    }

}
