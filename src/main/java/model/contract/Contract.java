package model.contract;

import com.google.gson.JsonObject;

public class Contract {

    private String content;


    public Contract() {
    }

    public Contract(String content) {
        this.content = content;
    }

    public static Contract fromJson(JsonObject jsonObject) {

        return new Contract();
    }
}
