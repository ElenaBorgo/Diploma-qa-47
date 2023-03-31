package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private SelenideElement heading = $("h2.heading");
    private SelenideElement paymentButton = $x("//*[text()='Купить']");
    private SelenideElement creditButton = $x("//*[text()='Купить в кредит']");

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public PaymentPage paymentPage() {
        paymentButton.click();
        return new PaymentPage();
    }

    public CreditPage creditPage() {
        creditButton.click();
        return new CreditPage();
    }
}
