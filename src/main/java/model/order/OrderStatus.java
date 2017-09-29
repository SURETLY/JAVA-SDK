package model.order;

import com.google.gson.JsonObject;

public class OrderStatus {

    private String id;
    private int status;
    private int payment_status;
    private boolean isPublic;
    private float cost;
    private float sum;
    private int bids_count;
    private int bids_sum;
    private long stop_time;

    public OrderStatus(String id, int status, int payment_status, boolean isPublic, float cost, float sum, int bids_count, int bids_sum, long stop_time) {
        this.id = id;
        this.status = status;
        this.payment_status = payment_status;
        this.isPublic = isPublic;
        this.cost = cost;
        this.sum = sum;
        this.bids_count = bids_count;
        this.bids_sum = bids_sum;
        this.stop_time = stop_time;
    }

    public String getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public int getPayment_status() {
        return payment_status;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public float getCost() {
        return cost;
    }

    public int getBids_count() {
        return bids_count;
    }

    public int getBids_sum() {
        return bids_sum;
    }

    public long getStop_time() {
        return stop_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderStatus that = (OrderStatus) o;

        if (status != that.status) return false;
        if (payment_status != that.payment_status) return false;
        if (isPublic != that.isPublic) return false;
        if (Float.compare(that.cost, cost) != 0) return false;
        if (Float.compare(that.sum, sum) != 0) return false;
        if (bids_count != that.bids_count) return false;
        if (bids_sum != that.bids_sum) return false;
        if (stop_time != that.stop_time) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + status;
        result = 31 * result + payment_status;
        result = 31 * result + (isPublic ? 1 : 0);
        result = 31 * result + (cost != +0.0f ? Float.floatToIntBits(cost) : 0);
        result = 31 * result + (sum != +0.0f ? Float.floatToIntBits(sum) : 0);
        result = 31 * result + bids_count;
        result = 31 * result + bids_sum;
        result = 31 * result + (int) (stop_time ^ (stop_time >>> 32));
        return result;
    }

    public static OrderStatus fromJson(JsonObject jsonObject) {
        String id = jsonObject.get("id").getAsString();
        int status = jsonObject.get("status").getAsInt();
        int payment_status = jsonObject.get("payment_status").getAsInt();
        boolean isPublic = jsonObject.get("public").getAsBoolean();
        float cost = jsonObject.get("cost").getAsFloat();
        float sum = jsonObject.get("sum").getAsFloat();
        int bids_count = jsonObject.get("bids_count").getAsInt();
        int bids_sum = jsonObject.get("bids_sum").getAsInt();
        long stop_time = jsonObject.get("stop_time").getAsLong();
        return new OrderStatus(id, status, payment_status, isPublic, cost, sum, bids_count, bids_sum, stop_time);
    }
}
