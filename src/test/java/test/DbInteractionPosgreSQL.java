package test;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbInteractionPosgreSQL {
    public DbInteractionPosgreSQL() throws SQLException {
    }
    @BeforeEach
    @SneakyThrows
    void setUp() {
        var faker = new Faker();
        var dataSQL = "INSERT INTO users(login, password) VALUES (?, ?);";

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:postgres://185.119.57.164:5432/app", "app", "pass"
                );
                var dataStmt = conn.prepareStatement(dataSQL);
        ) {
            dataStmt.setString(1, faker.name().username());
            dataStmt.setString(2, "password");
            dataStmt.executeUpdate();
            dataStmt.setString(1, faker.name().username());
            dataStmt.setString(2, "password");
            dataStmt.executeUpdate();
        }
    }

    @Test
    @SneakyThrows
    void stubTest() {
        var countSQL = "SELECT COUNT(*) FROM users;";
        var cardsSQL = "SELECT id, number, balance_in_kopecks FROM cards WHERE user_id = ?;";

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:postgres://185.119.57.164:5432/app", "app", "pass"
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
