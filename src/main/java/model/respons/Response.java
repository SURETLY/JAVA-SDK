package model.respons;

import com.google.gson.JsonObject;

public class Response {

    private int code;
    private String msg;

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static Response fromJson(JsonObject jsonObject) {
        int code = jsonObject.get("code").getAsInt();
        String msg = jsonObject.get("msg").getAsString();

        return new Response(code, msg);
    }
}
