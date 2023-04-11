package test;

import com.codeborne.selenide.Selectors;
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
    void shouldDeclinedPayment() {
        var paymentPage = dashboardPage.paymentPage();
        var declinedPayment = paymentPage.declinedTransaction();
        var expected = $x("//*[@id=\"root\"]/div/div[2]")
                .shouldBe(visible, Duration.ofSeconds(15));
        var actual = declinedPayment.errorTransaction();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSuccessTransaction() {
        var paymentPage = dashboardPage.paymentPage();
        var approvedPayment = paymentPage.validTransaction();
        var expected  = $(Selectors.byText("Успешно Операция одобрена Банком."))
                .shouldBe(visible, Duration.ofSeconds(15));
        var actual = approvedPayment.successTransaction();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldApprovedCredit() {
        var creditPage = dashboardPage.creditPage();
        var approvedCredit = creditPage.validTransaction();
        var expected = approvedCredit.successTransaction();
        var actual = $x("//*[@id=\"root\"]/div/div[2]")
                .shouldBe(visible, Duration.ofSeconds(15));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldDeclinedCredit() {
        var creditPage = dashboardPage.creditPage();
        var declinedCredit = creditPage.declinedTransaction();
        var expected = $x("//*[@id=\"root\"]/div/div[2]")
                .shouldBe(visible, Duration.ofSeconds(15));
        var actual = declinedCredit.errorTransaction();
        assertEquals(expected, actual);
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
}