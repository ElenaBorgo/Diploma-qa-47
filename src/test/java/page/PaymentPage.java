package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class PaymentPage {
    private SelenideElement heading = $x("//*[text()='Оплата по карте']");
    private SelenideElement cardNumber = $x("//*[contains(text(), 'Номер карты')]");
    private SelenideElement month = $x("//*[text() = 'Месяц']");
    private SelenideElement year = $x("//*[text() = 'Год']");
    private SelenideElement owner = $x("//*[text() = 'Владелец']");
    private SelenideElement cvc = $x("//*[text() ='CVC/CVV']");
    private SelenideElement button = $x("//*[text() ='Продолжить']");

    DataHelper data = new DataHelper();

    public PaymentPage() {
        heading.shouldBe(Condition.visible);
    }

    public ApprovedPayment validTransaction() {
        cardNumber.setValue(data.getApprovedCard());
        month.setValue(data.generateMonth(3));
        year.setValue(data.generateYear(2));
        owner.setValue(data.owner());
        cvc.setValue(data.CVC());
        button.click();
        return new ApprovedPayment();
    }

    public DeclinedPayment declinedTransaction() {
        cardNumber.setValue(data.getDeclinedCard());
        month.setValue(data.generateMonth(3));
        year.setValue(data.generateYear(2));
        owner.setValue(data.owner());
        cvc.setValue(data.CVC());
        button.click();
        return new DeclinedPayment();
    }

    public class ApprovedPayment {
        private SelenideElement successNotification = $x("//*[text()='Успешно']");

        public ApprovedPayment successTransaction() {
            successNotification.shouldBe(Condition.visible, Duration.ofSeconds(8));
            return new ApprovedPayment();
        }
    }

    public class DeclinedPayment {
        private SelenideElement errorNotification = $x("//*[text() = 'Ошибка']");

        public DeclinedPayment errorTransaction() {
            errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(8));
            return new DeclinedPayment();
        }
    }
}
