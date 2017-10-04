package model.currency;

import com.google.gson.JsonObject;
import model.country.Country;

public class Currency {

    private String code;
    private String name;


    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static Currency fromJson(JsonObject jsonObject) {
        String name = jsonObject.get("name").getAsString();
        String code = jsonObject.get("code").getAsString();

        return new Currency(name, code);
    }
}
