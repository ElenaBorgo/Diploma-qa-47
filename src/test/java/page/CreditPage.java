package page;

import com.beust.ah.A;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class CreditPage {
    private SelenideElement heading = $x("//*[contains(text(), 'Кредит')]");
    private SelenideElement cardNumber = $x("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[2]/input");
    private SelenideElement month = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[2]/input");
    private SelenideElement year = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[2]/input");
    private SelenideElement owner = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    private SelenideElement cvc = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[2]/input");
    private SelenideElement button = $x("//*[text() ='Продолжить']");
    private SelenideElement errorMessage = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]");
    private SelenideElement errorNotification = $x("//*[@id=\"root\"]/div/div[3]");

    DataHelper data = new DataHelper();

    public CreditPage() {
        heading.shouldBe(visible);
    }

    public ApprovedCredit validTransaction() {
        cardNumber.setValue(data.getApprovedCard());
        month.setValue(data.generateMonth(10));
        year.setValue(data.generateYear(2));
        owner.setValue(data.owner("ru"));
        cvc.setValue(data.CVC());
        button.click();
        return new ApprovedCredit();
    }

    public DeclinedCredit declinedTransaction() {
        cardNumber.setValue(data.getDeclinedCard());
        month.setValue(data.generateMonth(3));
        year.setValue(data.generateYear(5));
        owner.setValue(data.owner("ru"));
        cvc.setValue(data.CVC());
        button.click();
        return new DeclinedCredit();
    }

    public void makeTransactionWithInvalidYear() {
        cardNumber.setValue(data.getApprovedCard());
        month.setValue(data.generateMonth(8));
        year.setValue(data.invalidYear(2));
        owner.setValue(data.owner("ru"));
        cvc.setValue(data.CVC());
        button.click();
        errorMessage.shouldBe(visible);
    }

    public class ApprovedCredit {
        private SelenideElement successNotification = $x("//*[@id=\"root\"]/div/div[2]");

        public ApprovedCredit successTransaction() {
            successNotification.shouldBe(visible, Duration.ofSeconds(15));
            return new ApprovedCredit();
        }
    }

    public class DeclinedCredit {


        public DeclinedCredit errorTransaction() {
            errorNotification.shouldBe(hidden, Duration.ofSeconds(15));
            return new DeclinedCredit();
        }
    }
}





