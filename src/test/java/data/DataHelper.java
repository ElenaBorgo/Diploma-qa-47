package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Random;

public class DataHelper {
    private DataHelper() {}

    public static CardInfo getApprovedCard() {
        return new CardInfo("4444 4444 4444 4441");
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("4444 4444 4444 4442");
    }

    public static Month validMonth() {
        return new Month(new Random().nextInt(12));
    }

    public static Month invalidMonth() {
        return new Month(new Random().nextInt());
    }

    public static Year validYear() {
        return new Year(new Random().nextInt(28));
    }

    public static Year invalidYear() {
        return new Year(new Random().nextInt());
    }

    public static Owner validOwner() {
        var faker = new Faker();
        return new Owner(faker.name().lastName() + " " + faker.name().firstName());
    }
//    invalidOwner еще не придумала

    public static int CVC() {
        return new Random().nextInt();
    }

    @Value
    public static class CardInfo {
        String cardNumber;
    }

    @Value
    public static class Month {
        int month;
    }

    @Value
    public static class Year {
        int year;
    }

    @Value
    public static class Owner {
        String owner;
    }
//    Стоит ли объединить все поля в один класс? Если да, то каким образом мне возвращать Random().nextInt()?
//    Значение же должно быть int.
}
