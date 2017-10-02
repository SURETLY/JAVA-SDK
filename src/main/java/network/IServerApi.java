package network;

import com.google.gson.JsonElement;
import io.reactivex.Single;
import model.borrower.Borrower;
import retrofit2.http.*;

public interface IServerApi {

    @GET("options")
    Single<JsonElement> getOptions();


    /**
     * from   //  timestamp(sec)  дата, с которой берем заявки
     * to      //  timestamp(sec)  дата, по которую берем заявки
     * limit   // количество записей  для получения
     * skip  // сколько записей пропустить
     */
    @GET("orders")
    Single<JsonElement> getOrders(@Query("from") long from,
                                  @Query("to") long to,
                                  @Query("limit") int limit,
                                  @Query("skip") int skip);


    /**
     * "uid": "******",  //ваш внутренний id заявки, опциональный
     * "is_public": true/false  //доступна ли заявка публично
     * "borrower": BORROWER,
     * "user_credit_score":  123   //скоринг пользователя
     * "loan_sum":  45000.00  // сумма займа
     * "loan_term": 30             // срок в днях
     * "loan_rate": 56.34         // процентная ставка
     * "currency_code": "RUB"  //код валюты
     * "callback": "http://server.ru/?id=******"  //url callback
     */
    @FormUrlEncoded
    @POST("order/new")
    Single<JsonElement> createOrder(@Field("uid") String uid,
                                    @Field("is_public") boolean isPublic,
                                    @Field("borrower") Borrower borrower,
                                    @Field("user_credit_score") int user_credit_score,
                                    @Field("loan_sum") float loan_sum,
                                    @Field("loan_rate") float loan_rate,
                                    @Field("loan_term") float loan_term,
                                    @Field("currency_code") String currency_code,
                                    @Field("callback") String server_id);

    @GET("order/status")
    Single<JsonElement> getOrderStatus(@Query("id") String id);

    /**
     * Отменить заявку
     */
    @FormUrlEncoded
    @POST("order/stop")
    Single<JsonElement> stopOrder(@Field("id") String id);


    /**
     * Получить контракт для заемщика
     */
    @GET("contract/get")
    Single<String> getContract(@Query("id") String id);

    /**
     * Подтвердить что договор по заявке подписан заемщиком
     */
    @FormUrlEncoded
    @POST("contract/accept")
    Single<JsonElement> setAccept(@Field("id") String id);


    /**
     * Подтвердить что заявка оплачена и выдана
     */
    @FormUrlEncoded
    @POST("order/issued")
    Single<JsonElement> setOrderIssued(@Field("id") String id);

    /**
     * Отметить займ как выплаченный
     */
    @FormUrlEncoded
    @POST("order/paid")
    Single<JsonElement> setOrderPaid(@Field("id") String id);

    /**
     * Отметить займ как выплаченный частично
     */
    @FormUrlEncoded
    @POST("order/partialpaid")
    Single<JsonElement> setOrderPartialpaid(@Field("id") String id,
                                            @Field("sum") int sum);

    /**
     * Отметить займ как просроченный
     */
    @FormUrlEncoded
    @POST("order/unpaid")
    Single<JsonElement> setOrderUnPaid(@Field("id") String id);

    @GET("currencies")
    Single<JsonElement> getCurrencies();

    @GET("countries")
    Single<JsonElement> getCountries();
}
