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

    public PaymentPage() {
        heading.shouldBe(Condition.visible);
    }

    public ApprovedPayment validTransaction(DataHelper dataHelper) {
        cardNumber.setValue(dataHelper.getApprovedCard());
        month.setValue(dataHelper.generateMonth(3));
        year.setValue(dataHelper.generateYear(2));
        owner.setValue(dataHelper.owner());
        cvc.setValue(dataHelper.CVC());
        button.click();
        return new ApprovedPayment();
    }


    public class ApprovedPayment {
        private SelenideElement successNotification = $x("//*[text()='Успешно']");

        public void successTransaction() {
            successNotification.shouldBe(Condition.visible, Duration.ofSeconds(8));
        }
//        Не знаю, какого типа делать этот метод или оставить просто класс и дописать return new ApprovedPayment.
    }
}
