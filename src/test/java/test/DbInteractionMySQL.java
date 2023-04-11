package test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbInteractionMySQL {
    public DbInteractionMySQL() throws SQLException {
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
        var statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";

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

    @Test
    @SneakyThrows
    void shouldShowTheLastCreditRequest() {
        var statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";

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
}

