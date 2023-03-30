package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class CreditPage {
    private SelenideElement heading = $x("//*[contains(text(), 'Кредит')]");
    private SelenideElement cardNumber = $x("//*[contains(text(), 'Номер карты')]");
    private SelenideElement month = $x("//*[text() = 'Месяц']");
    private SelenideElement year = $x("//*[text() = 'Год']");
    private SelenideElement owner = $x("//*[text() = 'Владелец']");
    private SelenideElement cvc = $x("//*[text() ='CVC/CVV']");
    private SelenideElement button = $x("//*[text() ='Продолжить']");

    public CreditPage() {
        heading.shouldBe(Condition.visible);
    }

    public ApprovedCredit validTransaction(DataHelper dataHelper) {
        cardNumber.setValue(dataHelper.getApprovedCard());
        month.setValue(dataHelper.generateMonth(3));
        year.setValue(dataHelper.generateYear(2));
        owner.setValue(dataHelper.owner());
        cvc.setValue(dataHelper.CVC());
        button.click();
        return new ApprovedCredit();
    }

    public DeclinedCredit declinedTransaction(DataHelper dataHelper) {
        cardNumber.setValue(dataHelper.getDeclinedCard());
        month.setValue(dataHelper.invalidMonth(7));
        year.setValue(dataHelper.invalidYear(3));
        owner.setValue(dataHelper.owner());
        cvc.setValue(dataHelper.CVC());
        button.click();
        return new DeclinedCredit();
    }
}

