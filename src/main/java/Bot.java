import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import javafx.util.Pair;
import model.borrower.*;
import model.contract.Contract;
import model.order.OrderStatus;
import model.respons.Response;
import model.respons.ResponseCreateOrder;
import network.Build;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Bot {


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Suretly suretly = new Suretly();
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
                                .flatMap((Function<ResponseCreateOrder, SingleSource<OrderStatus>>) responseCreateOrder -> suretly.getOrderStatus(responseCreateOrder.getId()))
                                .map(orderStatus -> {
                                    Build.log("tag", orderStatus.toString());
                                    if (orderStatus.getStatus() == 4) {
                                        return true;
                                    } else if (orderStatus.getStatus() == 3 || orderStatus.getStatus() == 2) {
                                        throw new RuntimeException("Stop order");
                                    } else {
                                        return false;
                                    }
                                });
                        Single<Long> timer = Single.timer(3, TimeUnit.SECONDS);
                        Single<Boolean> delayedCheck = timer.zipWith(checkStatus, (aLong, status) -> status);
                        return delayedCheck
                                .repeat()
                                .takeUntil((Predicate<Boolean>) aBoolean -> aBoolean)
                                .last(false);
                    }
                })
                .flatMap((Function<Boolean, SingleSource<Response>>) aBoolean -> responseCreateOrderSingle
                        .flatMap((Function<ResponseCreateOrder, SingleSource<Response>>) responseCreateOrder -> suretly.setOrderIssued(responseCreateOrder.getId())))
                .flatMap((Function<Response, Single<Response>>) response -> responseCreateOrderSingle
                        .flatMap((Function<ResponseCreateOrder, Single<Response>>) responseCreateOrder -> suretly.setOrderPaid(responseCreateOrder.getId())))
                .subscribe(response -> countDownLatch.countDown());
        try {
            countDownLatch.await();
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
}
