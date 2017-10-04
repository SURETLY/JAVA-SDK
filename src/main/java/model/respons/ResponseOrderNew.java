package model.respons;

import com.google.gson.JsonObject;

public class ResponseOrderNew {

    private String id;

    public ResponseOrderNew(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static ResponseOrderNew fromJson(JsonObject jsonObject) {
        String id = jsonObject.get("id").getAsString();

        return new ResponseOrderNew(id);
    }
}
