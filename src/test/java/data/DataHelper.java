package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    public DataHelper() {
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

    public static String owner(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String CVC() {
        final Random random = new Random();
        int min = 99;
        int max = 1000;
        return String.valueOf(random.nextInt((max - min) + 1) + min);
    }
}


