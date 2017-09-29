package model.country;

public class Country {

    private String id;
    private String code;
    private String name;
    private String currency_code;


    public Country(String id, String code, String name, String currency_code) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.currency_code = currency_code;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

}
