package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.CreditPage;
import page.DashboardPage;
import page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
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
        var successTransaction = approvedPayment.successTransaction();
//        var expected  = approvedPayment.successTransaction();
//        var actual = ?
//       Например, я написала ожидаемый результат - это метод successTransaction(), но что писать в актуальном? И нужны
//       ли в этом тесте ассерты?
    }

    @Test
    void shouldApprovedCredit() {
        var creditPage = dashboardPage.creditPage();
        var approvedCredit = creditPage.validTransaction();
        var successTransaction = approvedCredit.successTransaction();
    }

//    @Test
//    void shouldShowErrorMassage() {
//        var paymentPage = dashboardPage.paymentPage();
//        var errorMessage = paymentPage.makeTransactionWithInvalidMonth();
//    Я создала метод, где пользователь вводит неправильный месяц. Метод типа void. Я не могу его использовать в тесте
//    из-за этого. Какого типа он должен быть, чтобы я смогла его вызвать? Static не получается. Я не хочу для него
//    отдельный класс создавать.
//    }

//    @Test
//    void shouldDeclinedCredit() {
//        var creditPage = dashboardPage.creditPage();
//        var declinedCredit = creditPage.declinedTransaction();
//        var expected = declinedCredit.errorTransaction();
//        var actual = CreditPage.ApprovedCredit.class;
//        assertEquals(expected, actual);
//    }
//    Здесь не проходят ассерты. Во-первых, пришлось в методе errorTransaction(), сообщение сделать hidden, потому что оно не появляется.
//    Во-вторых, что писать в актуальном результате? Так как у меня одобренный и отклоненный кредит в разных классах, я не могу
//    в actual привести другой класс.
}
