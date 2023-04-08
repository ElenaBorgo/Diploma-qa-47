package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTourTest {
    DashboardPage dashboardPage;

    @BeforeEach
    void setUp() {
        dashboardPage = open("http://localhost:8080", DashboardPage.class);
    }

    @Test
    void shouldSuccessTransaction() {
        var paymentPage = dashboardPage.paymentPage();
        var approvedPayment = paymentPage.validTransaction();
        var successTransaction  = approvedPayment.successTransaction();
//        var actual = $x("//*[@id=\"root\"]/div/div[2]")
//                .shouldBe(visible, Duration.ofSeconds(15));
//        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldApprovedCredit() {
        var creditPage = dashboardPage.creditPage();
        var approvedCredit = creditPage.validTransaction();
        var successTransaction = approvedCredit.successTransaction();
    }

    @Test
    void shouldShowErrorMassageIfWrongMonth() {
        var paymentPage = dashboardPage.paymentPage();
        paymentPage.makeTransactionWithInvalidMonth();
    }

    @Test
    void shouldShowErrorMessageIfWrongYear() {
        var creditPage = dashboardPage.creditPage();
        creditPage.makeTransactionWithInvalidYear();
    }

    @Test
    void shouldDeclinedCredit() {
        var creditPage = dashboardPage.creditPage();
        var declinedCredit = creditPage.declinedTransaction();
        var errorTransaction = declinedCredit.errorTransaction();
//        var actual = $x("//*[@id=\"root\"]/div/div[2]")
//                .shouldBe(visible, Duration.ofSeconds(15));
//        assertEquals(expected, actual);
    }
}