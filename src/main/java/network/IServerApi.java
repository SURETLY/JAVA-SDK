package network;

import com.google.gson.JsonElement;
import io.reactivex.Flowable;
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
    Single<JsonElement> orderNew(@Field("uid") String uid,
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
    @POST("order/stop")
    Single<JsonElement> setOrderStop(@Query("id") String id);


    /**
     * Получить контракт для заемщика
     */
    @GET("contract/get")
    Single<String> getContract(@Query("id") String id);

    /**
     * Подтвердить что договор по заявке подписан заемщиком
     */
    @POST("contract/accept")
    Single<JsonElement> setContractAccept(@Query("id") String id);


    /**
     * Подтвердить что заявка оплачена и выдана
     */
    @POST("order/issued")
    Single<JsonElement> setOrderIssued(@Query("id") String id);

    /**
     * Отметить займ как выплаченный
     */
    @POST("order/paid")
    Single<JsonElement> setOrderPaid(@Query("id") String id);

    /**
     * Отметить займ как выплаченный частично
     */
    @POST("order/partialpaid")
    Single<JsonElement> setOrderPartialpaid(@Query("id") String id,
                                            @Query("sum") int sum);

    /**
     * Отметить займ как просроченный
     */
    @POST("order/unpaid")
    Single<JsonElement> setOrderUnPaid(@Field("id") String id);

    @GET("currency")
    Single<JsonElement> getCurrencies();

    @GET("country")
    Single<JsonElement> getCountries();
}
