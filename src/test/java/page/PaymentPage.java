package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PaymentPage {
    private SelenideElement heading = $x("//*[text()='Оплата по карте']");
    private SelenideElement cardNumber = $x("//*[@id='root']/div/form/fieldset/div[1]/span/span/span[2]/input");
    private SelenideElement month = $x("//*[@id='root']/div/form/fieldset/div[2]/span/span[1]/span/span/span[2]/input");
    private SelenideElement year = $x("//*[@id='root']/div/form/fieldset/div[2]/span/span[2]/span/span/span[2]/input");
    private SelenideElement owner = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    private SelenideElement cvc = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[2]/input");
    private SelenideElement button = $x("//*[text() ='Продолжить']");


    DataHelper data = new DataHelper();

    public PaymentPage() {
        heading.shouldBe(visible);
    }

    public ApprovedPayment validTransaction() {
        cardNumber.setValue(data.getApprovedCard());
        month.setValue(data.generateMonth(3));
        year.setValue(data.generateYear(2));
        owner.setValue(data.owner("ru"));
        cvc.setValue(data.CVC());
        button.click();
        return new ApprovedPayment();
    }

    public DeclinedPayment declinedTransaction() {
        cardNumber.setValue(data.getDeclinedCard());
        month.setValue(data.generateMonth(7));
        year.setValue(data.generateYear(4));
        owner.setValue(data.owner("ru"));
        cvc.setValue(data.CVC());
        button.click();
        return new DeclinedPayment();
    }

    public void makeTransactionWithInvalidMonth() {
        SelenideElement errorMessage = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]");

        cardNumber.setValue(data.getApprovedCard());
        month.setValue(data.invalidMonth(1));
        year.setValue(data.generateYear(0));
        owner.setValue(data.owner("ru"));
        cvc.setValue(data.CVC());
        button.click();
        errorMessage.shouldBe(visible);
    }

    public class ApprovedPayment {
        private SelenideElement successNotification = $x("//*[@id=\"root\"]/div/div[2]");

        public ApprovedPayment successTransaction() {
            successNotification.shouldBe(visible, Duration.ofSeconds(15));
            return new ApprovedPayment();
        }
    }

    public class DeclinedPayment {
        private SelenideElement errorNotification = $x("//*[@id=\"root\"]/div/div[3]");

        public DeclinedPayment errorTransaction() {
            errorNotification.shouldBe(hidden, Duration.ofSeconds(15));
            return new DeclinedPayment();
        }
    }
}
