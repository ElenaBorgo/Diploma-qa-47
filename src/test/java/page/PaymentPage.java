package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$x;

public class PaymentPage {
    private SelenideElement heading = $x("//*[text()='Оплата по карте']");
    private SelenideElement cardNumber = $x("//*[contains(text(), 'Номер карты')]");
    private SelenideElement month = $x("//*[text() = 'Месяц']");
    private SelenideElement year = $x("//*[text() = 'Год']");
    private SelenideElement owner = $x("//*[text() = 'Владелец']");
    private SelenideElement cvc = $x("//*[text() ='CVC/CVV']");
    private SelenideElement button = $x("//*[text() ='Продолжить']");
    private SelenideElement paymentButton = $x("//*[text()='Купить']");

    public PaymentPage() {
        heading.shouldBe(Condition.visible);
    }

    public ApprovedPayment validCard(DataHelper.CardInfo info) {
        cardNumber.setValue(info.getCardNumber());
//        Как мне сослаться конкретно на CardInfo.getApprovedCard в этом методе?
    }

    public class ApprovedPayment {}


}
