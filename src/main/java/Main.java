import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import model.borrower.*;
import model.options.Options;
import model.order.Order;
import model.order.OrderStatus;
import network.Build;
import network.LenderClient;
import network.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionManager.setId("59ce0aa9cea0991f9327cdf3");
        SessionManager.setToken("321dsaeqw");

        LenderClient.getApi().getOptions()
                .subscribe(jsonElement ->
                        Build.log(Main.class.getSimpleName(), jsonElement.toString()));

//        LenderClient.getApi().getCountries()
//                .subscribe(jsonElement ->
//                        Build.log(Main.class.getSimpleName(), jsonElement.toString()));
//
        Borrower borrower = new Borrower(null, null, null, null, null, null, null, null, null, null, null);
        LenderClient.getApi().createOrder("123", true, borrower, 3000, 200, 300, 300, "RUB", null)
                .subscribe(jsonElement ->
                        Build.log(Main.class.getSimpleName(), jsonElement.toString()));
//
//        LenderClient.getApi().getCountries()
//                .subscribe(jsonElement ->
//                        Build.log(Main.class.getSimpleName(), jsonElement.toString()));
//"id" -> ""59ce0ac8cea0991f9327cdf6""
        LenderClient.getApi().getOrders(0, System.currentTimeMillis() * 1000, 100, 0)
                .subscribe(jsonElement ->
                {
                    List<Order> orders = new ArrayList<>();
                    JsonArray jsonArray = jsonElement.getAsJsonObject().getAsJsonArray("list");
                    for (JsonElement element : jsonArray) {
                        JsonObject obData = element.getAsJsonObject();
                        orders.add(Order.fromJson(obData));
                    }
                    Build.log(Main.class.getSimpleName(), orders.get(0).getId());
                });
//
//        LenderClient.getApi().getOrderStatus("59c9dc72cea0997574cef6a3")
//                .subscribe(jsonElement -> {
//                    Build.log(Main.class.getSimpleName(), OrderStatus.fromJson(jsonElement.getAsJsonObject()).toString());
//                });

//        LenderClient.getApi().stopOrder("59cb5c49cea09911939d7f2b")
//                .subscribe(jsonElement ->
//                        Build.log(Main.class.getSimpleName(), jsonElement.toString()));
//
//        Suretly.get().getOrderStatus("59cb5c49cea09911939d7f2b")
//                .subscribe(new Consumer<OrderStatus>() {
//                    @Override
//                    public void accept(OrderStatus orderStatus) throws Exception {
//                        Build.log(Main.class.getSimpleName(), orderStatus.toString());
//                    }
//                });

        LenderClient.getApi().getContract("59ce0ac8cea0991f9327cdf6")
                .subscribe(jsonElement ->
                        Build.log(Main.class.getSimpleName(), jsonElement.toString()));

//        LenderClient.getApi().setAccept("59cb5c49cea09911939d7f2b")
//                .subscribe(jsonElement ->
//                        Build.log(Main.class.getSimpleName(), jsonElement.toString()));
//
//        LenderClient.getApi().setOrderIssued("59cb5c49cea09911939d7f2b")
//                .subscribe(jsonElement ->
//                        Build.log(Main.class.getSimpleName(), jsonElement.toString()));
//
//        LenderClient.getApi().setOrderPaid("59cb5c49cea09911939d7f2b")
//                .subscribe(jsonElement ->
//                        Build.log(Main.class.getSimpleName(), jsonElement.toString()));
//
//        LenderClient.getApi().setOrderPartialpaid("59cb5c49cea09911939d7f2b", 3000)
//                .subscribe(jsonElement ->
//                        Build.log(Main.class.getSimpleName(), jsonElement.toString()));
//
//        LenderClient.getApi().setOrderUnPaid("59cb5c49cea09911939d7f2b")
//                .subscribe(jsonElement ->
//                        Build.log(Main.class.getSimpleName(), jsonElement.toString()));

    }
}
