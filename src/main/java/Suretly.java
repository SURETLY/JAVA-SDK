import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.reactivex.Single;
import model.borrower.Borrower;
import model.contract.Contract;
import model.options.Options;
import model.order.Order;
import model.order.OrderStatus;
import model.respons.Response;
import network.LenderClient;
import network.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class Suretly {

    private static final Suretly api = new Suretly();

    public static Suretly get() {
        return api;
    }

    public void init(String id, String token) {
        SessionManager.setId("59ca108acea0997574cef789");
        SessionManager.setToken("123qweasd");
    }


    public Single<Options> getOption() {
        return LenderClient.getApi().getOptions()
                .map(jsonElement -> Options.fromJson(jsonElement.getAsJsonObject())
                );
    }

    public Single<List<Order>> getOrders(long from, long to, int limit, int skip) {
        return LenderClient.getApi().getOrders(from, to, limit, skip)
                .map(jsonElement -> {
                    List<Order> orders = new ArrayList<>();
                    JsonArray jsonArray = jsonElement.getAsJsonObject().getAsJsonArray("list");
                    for (JsonElement element : jsonArray) {
                        JsonObject obData = element.getAsJsonObject();
                        orders.add(Order.fromJson(obData));
                    }
                    return orders;
                });
    }

    public Single<Response> createOrder(String uid, boolean isPublic, Borrower borrower, int user_credit_score, float loan_sum, float loan_rate, float loan_term, String currency_code, String server_id) {
        return LenderClient.getApi().createOrder(uid, isPublic, borrower, user_credit_score, loan_sum, loan_rate, loan_term, currency_code, server_id)
                .map(jsonElement -> Response.fromJson(jsonElement.getAsJsonObject()));
    }

    /**
     * "59cb5c49cea09911939d7f2b"
     */
    public Single<OrderStatus> getOrderStatus(String id) {
        return LenderClient.getApi().getOrderStatus(id)
                .map(jsonElement -> OrderStatus.fromJson(jsonElement.getAsJsonObject()));
    }

    public Single<Response> stopOrder(String id) {
        return LenderClient.getApi().stopOrder(id)
                .map(jsonElement -> Response.fromJson(jsonElement.getAsJsonObject()));
    }

    public Single<Contract> getContract(String id) {
        return LenderClient.getApi().getContract(id)
                .map(jsonElement -> Contract.fromJson(jsonElement.getAsJsonObject()));
    }

    public Single<Response> setAccept(String id) {
        return LenderClient.getApi().setAccept(id)
                .map(jsonElement -> Response.fromJson(jsonElement.getAsJsonObject()));
    }

    public Single<Response> setOrderIssued(String id) {
        return LenderClient.getApi().setOrderIssued(id)
                .map(jsonElement -> Response.fromJson(jsonElement.getAsJsonObject()));
    }

    public Single<Response> setOrderPaid(String id) {
        return LenderClient.getApi().setOrderPaid(id)
                .map(jsonElement -> Response.fromJson(jsonElement.getAsJsonObject()));
    }

    public Single<Response> setOrderParialPaid(String id, int sum) {
        return LenderClient.getApi().setOrderPartialpaid(id, sum)
                .map(jsonElement -> Response.fromJson(jsonElement.getAsJsonObject()));
    }

    public Single<Response> setOrderUnPaid(String id) {
        return LenderClient.getApi().setOrderUnPaid(id)
                .map(jsonElement -> Response.fromJson(jsonElement.getAsJsonObject()));
    }
}
