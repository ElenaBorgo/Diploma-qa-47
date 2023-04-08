package test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbInteraction {
    public DbInteraction() throws SQLException {
    }

    @Test
    @SneakyThrows
    void shouldCheckNumberOfCards() {
        var numberSQL = "SELECT number FROM cards";

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://185.119.57.164:3306/app", "app", "pass");
                var numberStmt = conn.createStatement();
        ) {
            try (var rs = numberStmt.executeQuery(numberSQL)) {
                if (rs.next()) {
                    var number = rs.getString("number");
                    System.out.println(number);
                }
            }
        }
    }

    @Test
    @SneakyThrows
    void shouldShowStatusOfTheLastPayment() {
        var statusSQL = "SELECT status FROM payment_entity ORDER BY created LIMIT 1";

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://185.119.57.164:3306/app", "app", "pass");
                var statusStmt = conn.createStatement();
                ) {
            try (var rs = statusStmt.executeQuery(statusSQL)) {
                while (rs.next()) {
                    var status = rs.getString("status");
                    Assertions.assertEquals("APPROVED", status);
                }
            }
        }
    }

    @Test
    @SneakyThrows
    void shouldShowTheLastCreditRequest() {
        var statusSQL = "SELECT status FROM credit_request_entity ORDER BY created LIMIT 1";

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://185.119.57.164:3306/app", "app", "pass");
                var statusStmt = conn.createStatement();
        ) {
            try (var rs = statusStmt.executeQuery(statusSQL)) {
                while (rs.next()) {
                    var status = rs.getString("status");
                    Assertions.assertEquals("DECLINED", status);
                }
            }
        }
    }

//    @Test
//    @SneakyThrows
//    void stubTest() {
//        var countSQL = "SELECT COUNT(*) FROM users;";
//        var cardsSQL = "SELECT id, number, balance_in_kopecks FROM cards WHERE user_id = ?;";
//
//        try (
//                var conn = DriverManager.getConnection(
//                        "jdbc:mysql://185.119.57.164:3306/app", "app", "pass"
//                );
//                var countStmt = conn.createStatement();
//                var cardsStmt = conn.prepareStatement(cardsSQL);
//        ) {
//            try (var rs = countStmt.executeQuery(countSQL)) {
//                if (rs.next()) {
//                    var count = rs.getInt(1);
//                    System.out.println(count);
//                }
//            }
//            cardsStmt.setInt(1, 1);
//            try (var rs = cardsStmt.executeQuery()) {
//                while (rs.next()) {
//                    var id = rs.getInt("id");
//                    var number = rs.getString("number");
//                    var balanceInKopecks = rs.getInt("balance_in_kopecks");
//                    System.out.println(id + " " + number + " " + balanceInKopecks);
//                }
//            }
//        }
//    }
}

