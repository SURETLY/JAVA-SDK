package model.order;

import com.google.gson.JsonObject;
import model.borrower.*;

public class Order {

    private String id;
    private String uid;
    private int status;
    private int payment_status;
    private Borrower borrower;
    private String risk_group;
    private float cost;
    private float loan_sum; //сумма займа
    private float loan_term; //срок займа в днях
    private float loan_rate; //процентная ставка
    private String currency_code; //код валюты
    private int max_wait_time;//время поиска поручителей(сек)
    private int created_at;
    private int modify_at;
    private int close_at;
    private int bids_count;
    private int bids_sum;
    private String callback;

    public String getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public int getStatus() {
        return status;
    }

    public int getPayment_status() {
        return payment_status;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public String getRisk_group() {
        return risk_group;
    }

    public float getCost() {
        return cost;
    }

    public float getLoan_sum() {
        return loan_sum;
    }

    public float getLoan_term() {
        return loan_term;
    }

    public float getLoan_rate() {
        return loan_rate;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public int getMax_wait_time() {
        return max_wait_time;
    }

    public int getCreated_at() {
        return created_at;
    }

    public int getModify_at() {
        return modify_at;
    }

    public int getClose_at() {
        return close_at;
    }

    public int getBids_count() {
        return bids_count;
    }

    public int getBids_sum() {
        return bids_sum;
    }

    public String getCallback() {
        return callback;
    }

    public Order(String id, String uid, int status, int payment_status, Borrower borrower, String risk_group, float cost, float loan_sum, float loan_term, float loan_rate, String currency_code, int max_wait_time, int created_at, int modify_at, int close_at, int bids_count, int bids_sum, String callback) {
        this.id = id;
        this.uid = uid;
        this.status = status;
        this.payment_status = payment_status;
        this.borrower = borrower;
        this.risk_group = risk_group;
        this.cost = cost;
        this.loan_sum = loan_sum;
        this.loan_term = loan_term;
        this.loan_rate = loan_rate;
        this.currency_code = currency_code;
        this.max_wait_time = max_wait_time;
        this.created_at = created_at;
        this.modify_at = modify_at;
        this.close_at = close_at;
        this.bids_count = bids_count;
        this.bids_sum = bids_sum;
        this.callback = callback;
    }

    public static Order fromJson(JsonObject obData) {

        String id = obData.get("id").getAsString();
        String uid = obData.get("uid").getAsString();
        int status = obData.get("status").getAsInt();
        int payStatus = obData.get("payment_status").getAsInt();
        JsonObject obBorrower = obData.getAsJsonObject("borrower");
        Borrower borrower = Borrower.fromJson(obBorrower);
        String user_credit_score = obData.get("user_credit_score").getAsString();
        float cost = obData.get("cost").getAsFloat();
        float loan_sum = obData.get("loan_sum").getAsFloat();
        float loan_term = obData.get("loan_term").getAsFloat();
        float loan_rate = obData.get("loan_rate").getAsFloat();
        String currency_code = obData.get("currency_code").getAsString();
        int max_wait_time = obData.get("max_wait_time").getAsInt();
        int bids_count = obData.get("bids_count").getAsInt();
        int bids_sum = obData.get("bids_sum").getAsInt();
        int created_at = obData.get("created_at").getAsInt();
        int modify_at = obData.get("modify_at").getAsInt();
        int closed_at = obData.get("closed_at").getAsInt();
        String callback = obData.get("callback").getAsString();

        return new Order(id, uid, status, payStatus, borrower, user_credit_score, cost, loan_sum, loan_term, loan_rate,
                currency_code, max_wait_time, created_at, modify_at, closed_at, bids_count, bids_sum, callback);

    }
}
