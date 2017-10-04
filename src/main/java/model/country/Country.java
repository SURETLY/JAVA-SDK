package model.country;

import com.google.gson.JsonObject;

public class Country {

    private String name;
    private String code;
    private String currency_code;


    public Country(String name, String code, String currency_code) {
        this.name = name;
        this.code = code;
        this.currency_code = currency_code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public static Country fromJson(JsonObject jsonObject) {
        String name = jsonObject.get("name").getAsString();
        String code = jsonObject.get("code").getAsString();
        String currency_code = jsonObject.get("currency_code").getAsString();

        return new Country(name, code, currency_code);
    }
}
