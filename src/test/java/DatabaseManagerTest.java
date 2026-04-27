import model.Game;
import model.Upgrade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.IOException;
import java.nio.file.*;
import java.sql.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseManagerTest {
    DatabaseManager db;

    @BeforeEach
    void setUp() throws IOException {
        Files.deleteIfExists(Path.of("app.db"));
        db = new DatabaseManager();
    }

    @AfterEach
    void tearDown() throws IOException {
        db.close();
        Files.deleteIfExists(Path.of("app.db"));
    }

    @Test
    void testSchema() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:app.db")) {
            assertTrue(db.tableExists(conn, "games"));
            assertTrue(db.tableExists(conn, "users"));
            assertTrue(db.tableExists(conn, "upgrades"));
            assertTrue(db.tableExists(conn, "purchased_upgrades"));
        } catch (Exception e) {
            System.err.println("connection failed");
        }
    }

    @Test
    void addUpgrade() {
        db.addUpgrade("Test Upgrade", 100);

        List<Upgrade> upgrades = db.getAllUpgrades();

        assertEquals(1, upgrades.size());
        assertEquals("Test Upgrade", upgrades.get(0).getName());
    }

    @Test
    void addUser() {
    }

    @Test
    void addGame() {
        //WIP: bug with localdate on last_login here
        db.addGame();

        List<Game> games = db.getAllGames();

        assertEquals(1, games.size());
        assertEquals(0, games.getFirst().getCookies());
    }

    @Test
    void addPurchase() {
    }
}