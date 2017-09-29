import model.borrower.*;
import model.options.Options;
import model.order.OrderStatus;
import model.respons.Response;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class SuretlyTest {

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void init() throws Exception {
        Suretly suretly = new Suretly();
        suretly.init("123", "123");
    }


    @Test
    public void getShouldReturnValidInstance() throws Exception {
        Suretly suretly =  Suretly.get();
        Suretly suretly2 =  Suretly.get();

        assertEquals(suretly,suretly2);
    }



    @Test
    public void getOption() throws Exception {
//        SessionManager.setId("59ca108acea0997574cef789");
//        SessionManager.setToken("123qweasd");
        Suretly suretly = new Suretly();
        suretly.init("59ca108acea0997574cef789", "123qweasd");
        Options optionsTest = suretly.getOption().blockingGet();
        assertEquals(optionsTest.getMax_sum(), 1);
        assertEquals(optionsTest.getMax_term(), 1);
        assertEquals(optionsTest.getMin_sum(), 1);
        assertEquals(optionsTest.getMin_term(), 1);
    }

    @Test
    public void getOrders() throws Exception {

    }

    @Test
    public void createOrder() throws Exception {
        Suretly suretly = new Suretly();
        suretly.init("59ca108acea0997574cef789", "123qweasd");
        Name name = new Name("Shredernivich", "Pikachu", "EnesenniuVetrom", "NaDne");
        Birth birth = new Birth(713808000000L, "xz");
        Passport passport = new Passport("123123", "3211312", "234123", "1321", "234123");
        Residential residential = new Residential("Narnia", "5303487", "Velicanov", "Tashbaan", "Tash", "0", "0", "0");
        Registration registration = new Registration("Narnia", "5303487", "Velicanov", "Tashbaan", "Tash", "0", "0", "0");

        Borrower borrower = new Borrower(name, "1", birth, "rabadash@tsar.tsar", "golub'", "000.000.0.1", "https://vignette.wikia.nocookie.net/narnia/images/e/e6/%D0%A0%D0%B0%D0%B1%D0%B0%D0%B4%D0%B0%D1%88.jpg/revision/latest?cb=20150723202518&path-prefix=ru", "asdlk", passport, registration, residential);
        Response response = suretly.createOrder("59ca108acea0997574cef789", false, borrower, 10000, 0, 0, 0, "RUB", "xz").blockingGet();

        assertEquals(response.getCode(), 200);
        assertEquals(response.getMsg(), "order added");
    }

    @Test
    public void getOrderStatus() throws Exception {
        Suretly suretly = new Suretly();
        suretly.init("59ca108acea0997574cef789", "123qweasd");
        OrderStatus orderStatus = suretly.getOrderStatus("59cb5c49cea09911939d7f2b").blockingGet();
        OrderStatus orderStatusTest = new OrderStatus("59cb5c49cea09911939d7f2b", 0, 1, false, 0, 0, 0, 0, 0);
        assertEquals(orderStatus, orderStatusTest);
    }

    @Test
    public void stopOrder() throws Exception {

    }

    @Test
    public void getContract() throws Exception {

    }

    @Test
    public void setAccept() throws Exception {

    }

    @Test
    public void setOrderIssued() throws Exception {

    }

    @Test
    public void setOrderPaid() throws Exception {

    }

    @Test
    public void setOrderParialPaid() throws Exception {

    }

    @Test
    public void setOrderUnPaid() throws Exception {

    }
}