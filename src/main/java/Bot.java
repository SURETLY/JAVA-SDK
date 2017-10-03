import com.sun.tools.javac.comp.Flow;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import javafx.util.Pair;
import model.borrower.*;
import model.contract.Contract;
import model.order.OrderStatus;
import model.respons.Response;
import model.respons.ResponseCreateOrder;
import network.Build;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import sun.rmi.runtime.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Bot {

    private static Suretly suretly;

    public static void main(String[] args) {

        suretly = new Suretly();
        suretly.init("59d1c3efcea0993b18c2b042", "qweasd123");

        suretly.getOption()
                .subscribe(options -> Build.log("opt", String.valueOf(options.getServer_time())));

        Name name = new Name("Shredernivich", "Pikachu", "YnesenniuVetrom", "NaDne");
        Birth birth = new Birth(713808000000L, "xz");
        Passport passport = new Passport("123123", "3211312", "234123", "1321", "234123");
        Residential residential = new Residential("Narnia", "5303487", "Velicanov", "Tashbaan", "Tash", "0", "0", "0");
        Registration registration = new Registration("Narnia", "5303487", "Velicanov", "Tashbaan", "Tash", "0", "0", "0");

        Borrower borrower = new Borrower(name, "1", birth, "rabadash@tsar.tsar", "golub'", "000.000.0.1", "https://vignette.wikia.nocookie.net/narnia/images/e/e6/%D0%A0%D0%B0%D0%B1%D0%B0%D0%B4%D0%B0%D1%88.jpg/revision/latest?cb=20150723202518&path-prefix=ru", "asdlk", passport, registration, residential);


        Single<ResponseCreateOrder> responseCreateOrderSingle = suretly.createOrder("59ca108acea0997574cef789", false, borrower, 10000, 0, 0, 0, "RUB", "xz")
                .cache();

        responseCreateOrderSingle
                .flatMap((Function<ResponseCreateOrder, SingleSource<String>>) responseCreateOrder -> {
                    Build.log("responseCreateOrder= ", responseCreateOrder.getId());
                    return Single.just(responseCreateOrder.getId());
                })
                .flatMap((Function<String, SingleSource<Contract>>) s -> {
                    Build.log("getContract= ", s);
                    return suretly.getContract(s);
                })
                .flatMap((Function<Contract, Single<Pair<String, Single<Response>>>>) contract -> responseCreateOrderSingle.flatMap((Function<ResponseCreateOrder, SingleSource<Pair<String, Single<Response>>>>) responseCreateOrder -> Single.just(rollNextStep(suretly.stopOrder(responseCreateOrder.getId()), suretly.contractAccept(responseCreateOrder.getId())))))
                .flatMap((Function<Pair<String, Single<Response>>, Single<Boolean>>) stringSinglePair -> {
                    if (stringSinglePair.getKey().equals("0")) {
                        throw new RuntimeException("Stop order");
                    } else {
                        Single<Boolean> checkStatus = responseCreateOrderSingle
                                .flatMap(new Function<ResponseCreateOrder, SingleSource<OrderStatus>>() {
                                    @Override
                                    public SingleSource<OrderStatus> apply(ResponseCreateOrder responseCreateOrder) throws Exception {
                                        return suretly.getOrderStatus(responseCreateOrder.getId());
                                    }
                                })
                                .map(new Function<OrderStatus, Boolean>() {
                                    @Override
                                    public Boolean apply(OrderStatus orderStatus) throws Exception {
                                        Build.log("tag", orderStatus.toString());
                                        if (orderStatus.getStatus() == 4) {
                                            return true;
                                        } else if (orderStatus.getStatus() == 3 || orderStatus.getStatus() == 2) {
                                            throw new RuntimeException("Stop order");
                                        } else {
                                            return false;
                                        }
                                    }
                                });
                        Single<Long> timer = Single.timer(3, TimeUnit.SECONDS);
                        Single<Boolean> delayedCheck = timer.zipWith(checkStatus, (aLong, status) -> status);
                        return delayedCheck
                                .repeat()
                                .takeUntil(new Predicate<Boolean>() {
                                    @Override
                                    public boolean test(Boolean aBoolean) throws Exception {
                                        return aBoolean;
                                    }
                                })
                                .last(false);
                    }
                })
                .flatMap(new Function<Boolean, SingleSource<Response>>() {
                    @Override
                    public SingleSource<Response> apply(Boolean aBoolean) throws Exception {
                        return responseCreateOrderSingle
                                .flatMap(new Function<ResponseCreateOrder, SingleSource<Response>>() {
                                    @Override
                                    public SingleSource<Response> apply(ResponseCreateOrder responseCreateOrder) throws Exception {
                                        return suretly.setOrderIssued(responseCreateOrder.getId());
                                    }
                                });
                    }
                })
                .flatMap((Function<Response, Single<Response>>) response -> responseCreateOrderSingle
                        .flatMap((Function<ResponseCreateOrder, Single<Response>>) responseCreateOrder -> suretly.setOrderPaid(responseCreateOrder.getId())))
                .subscribe(response -> Build.log("tag", response.toString()));

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static Random random = new Random();

    private static Pair<String, Single<Response>> rollNextStep(Single<Response> single, Single<Response> single1) {
        switch (random.nextInt(2)) {
            case 0:
                Build.log(single.toString(), "0");
                return new Pair<>("1", single);
            case 1:
                Build.log(single1.toString(), "0");
                return new Pair<>("1", single1);
        }
        return null;
    }

    private static Single<Boolean> checkStatus(String id) {
        return suretly.getOrderStatus(id)
                .map(new Function<OrderStatus, Boolean>() {
                    @Override
                    public Boolean apply(OrderStatus orderStatus) throws Exception {
                        if (orderStatus.getStatus() == 4) {
                            return true;
                        } else if (orderStatus.getStatus() == 3 || orderStatus.getStatus() == 2) {
                            throw new RuntimeException("Stop order");
                        } else {
                            return false;
                        }
                    }
                });
    }
}
