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
import model.respons.ResponseOrderNew;
import network.LenderClient;
import network.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class Suretly {

    private static final Suretly api = new Suretly();

    public static Suretly get() {
        return api;
    }

    private LenderClient lenderClient;

    public void init(String id, String token) {
        lenderClient = new LenderClient(new SessionManager(id, token));
    }

    public Single<Options> getOption() {
        return lenderClient.getApi().getOptions()
                .map(jsonElement -> Options.fromJson(jsonElement.getAsJsonObject()));
    }

    public Single<List<Order>> getOrders(long from, long to, int limit, int skip) {
        return lenderClient.getApi().getOrders(from, to, limit, skip)
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

    public Single<ResponseOrderNew> orderNew(String uid, boolean isPublic, Borrower borrower, int user_credit_score, float loan_sum, float loan_rate, float loan_term, String currency_code, String server_id) {
        return lenderClient.getApi().orderNew(uid, isPublic, borrower, user_credit_score, loan_sum, loan_rate, loan_term, currency_code, server_id)
                .map(jsonElement -> ResponseOrderNew.fromJson(jsonElement.getAsJsonObject()));
    }

    /**
     * "59cb5c49cea09911939d7f2b"
     */
    public Single<OrderStatus> getOrderStatus(String id) {
        return lenderClient.getApi().getOrderStatus(id)
                .map(jsonElement -> OrderStatus.fromJson(jsonElement.getAsJsonObject()));
    }

    public Single<Response> setOrderStop(String id) {
        return lenderClient.getApi().setOrderStop(id)
                .map(jsonElement -> Response.fromJson(jsonElement.getAsJsonObject()));
    }

    public Single<Contract> getContract(String id) {
        return lenderClient.getApi().getContract(id)
                .map(str -> {
                    return new Contract(str);
                });
    }

    public Single<Response> setContractAccept(String id) {
        return lenderClient.getApi().setContractAccept(id)
                .map(jsonElement -> Response.fromJson(jsonElement.getAsJsonObject()));
    }

    public Single<Response> setOrderIssued(String id) {
        return lenderClient.getApi().setOrderIssued(id)
                .map(jsonElement -> Response.fromJson(jsonElement.getAsJsonObject()));
    }

    public Single<Response> setOrderPaid(String id) {
        return lenderClient.getApi().setOrderPaid(id)
                .map(jsonElement -> Response.fromJson(jsonElement.getAsJsonObject()));
    }

    public Single<Response> setOrderParialPaid(String id, int sum) {
        return lenderClient.getApi().setOrderPartialpaid(id, sum)
                .map(jsonElement -> Response.fromJson(jsonElement.getAsJsonObject()));
    }

    public Single<Response> setOrderUnPaid(String id) {
        return lenderClient.getApi().setOrderUnPaid(id)
                .map(jsonElement -> Response.fromJson(jsonElement.getAsJsonObject()));
    }
}
