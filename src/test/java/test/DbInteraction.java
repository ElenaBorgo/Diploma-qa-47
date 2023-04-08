package test;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbInteraction {
    public DbInteraction() throws SQLException {
    }

    @BeforeEach
    @SneakyThrows
    void setUp() {
        var dataSQL = "INSERT INTO cards(id, user_id, number, balance_in_kopecks) VALUES (?, ?, ?, ?);";

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://185.119.57.164:3306/app", "app", "pass"
                );
                var dataStmt = conn.prepareStatement(dataSQL);
        ) {
            dataStmt.setInt(1, 1);
            dataStmt.setInt(2, 4);
            dataStmt.setString(3, "4444 4444 4444 4441");
            dataStmt.setInt(4, 700000);
            dataStmt.executeUpdate();
            dataStmt.setInt(1, 2);
            dataStmt.setInt(2, 2);
            dataStmt.setString(3, "4444 4444 4444 4442");
            dataStmt.setInt(4, 1000000);
            dataStmt.executeUpdate();
        }
    }

    @Test
    @SneakyThrows
    void test() {
        var numberSQL = "SELECT number FROM cards";
    }
    try(
    var conn = DriverManager.getConnection(
            "jdbc:mysql://185.119.57.164:3306/app", "app", "pass");
    var numberStmt = conn.createStatement();
            )

    {
        try (var rs = numberStmt.executeQuery(numberSQL)) {
            if (rs.next()) {
                var number = rs.getString(2);
                System.out.println(number);
            }
        }
    }


    @Test
    @SneakyThrows
    void stubTest() {
        var countSQL = "SELECT COUNT(*) FROM users;";
        var cardsSQL = "SELECT id, number, balance_in_kopecks FROM cards WHERE user_id = ?;";

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://185.119.57.164:3306/app", "app", "pass"
                );
                var countStmt = conn.createStatement();
                var cardsStmt = conn.prepareStatement(cardsSQL);
        ) {
            try (var rs = countStmt.executeQuery(countSQL)) {
                if (rs.next()) {
                    var count = rs.getInt(1);
                    System.out.println(count);
                }
            }
            cardsStmt.setInt(1, 1);
            try (var rs = cardsStmt.executeQuery()) {
                while (rs.next()) {
                    var id = rs.getInt("id");
                    var number = rs.getString("number");
                    var balanceInKopecks = rs.getInt("balance_in_kopecks");
                    System.out.println(id + " " + number + " " + balanceInKopecks);
                }
            }
        }
    }
}

