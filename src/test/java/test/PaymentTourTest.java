package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;

public class PaymentTourTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void shouldSuccessTransaction() {
        DashboardPage page = new DashboardPage();
        var paymentPage = page.paymentPage();
        var approvedPayment = paymentPage.validTransaction();
        var successTransaction = approvedPayment.successTransaction();
    }
}
