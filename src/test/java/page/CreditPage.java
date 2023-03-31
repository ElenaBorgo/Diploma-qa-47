package page;

import com.beust.ah.A;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class CreditPage {
    private SelenideElement heading = $x("//*[contains(text(), 'Кредит')]");
    private SelenideElement cardNumber = $x("//*[contains(text(), 'Номер карты')]");
    private SelenideElement month = $x("//*[text() = 'Месяц']");
    private SelenideElement year = $x("//*[text() = 'Год']");
    private SelenideElement owner = $x("//*[text() = 'Владелец']");
    private SelenideElement cvc = $x("//*[text() ='CVC/CVV']");
    private SelenideElement button = $x("//*[text() ='Продолжить']");

    DataHelper data = new DataHelper();

    public CreditPage() {
        heading.shouldBe(Condition.visible);
    }

    public ApprovedCredit validTransaction() {
        cardNumber.setValue(data.getApprovedCard());
        month.setValue(data.generateMonth(3));
        year.setValue(data.generateYear(2));
        owner.setValue(data.owner());
        cvc.setValue(data.CVC());
        button.click();
        return new ApprovedCredit();
    }

    public DeclinedCredit declinedTransaction() {
        cardNumber.setValue(data.getDeclinedCard());
        month.setValue(data.generateMonth(3));
        year.setValue(data.generateYear(2));
        owner.setValue(data.owner());
        cvc.setValue(data.CVC());
        button.click();
        return new DeclinedCredit();
    }

    public class ApprovedCredit {
        private SelenideElement successNotification = $x("//*[text()='Успешно']");

        public ApprovedCredit successTransaction() {
            successNotification.shouldBe(Condition.visible, Duration.ofSeconds(8));
            return new ApprovedCredit();
        }
    }

    public class DeclinedCredit {
        private SelenideElement errorNotification = $x("//*[text() = 'Ошибка']");

        public DeclinedCredit errorTransaction() {
            errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(8));
            return new DeclinedCredit();
        }
    }
}





