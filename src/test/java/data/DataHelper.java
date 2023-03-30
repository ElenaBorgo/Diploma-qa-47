package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    public static String getApprovedCard() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCard() {
        return "4444 4444 4444 4442";
    }

    public static String generateMonth(int monthsToAdd) {
        return LocalDate.now().plusMonths(monthsToAdd).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String invalidMonth(int monthsToSubtract) {
        return LocalDate.now().minusMonths(monthsToSubtract).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateYear(int yearsToAdd) {
        return LocalDate.now().plusYears(yearsToAdd).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String invalidYear(int yearsToSubtract) {
        return LocalDate.now().minusYears(yearsToSubtract).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String owner() {
        var faker = new Faker();
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String CVC() {
        return new Random().nextInt();
    }

}


