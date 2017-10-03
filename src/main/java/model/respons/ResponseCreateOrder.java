package model.respons;

import com.google.gson.JsonObject;

public class ResponseCreateOrder {

    private String id;

    public ResponseCreateOrder(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static ResponseCreateOrder fromJson(JsonObject jsonObject) {
        String id = jsonObject.get("id").getAsString();

        return new ResponseCreateOrder(id);
    }
}
