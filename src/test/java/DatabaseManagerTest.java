import model.Game;
import model.PurchasedUpgrade;
import model.Upgrade;
import model.User;
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
    void testUserTable() {
        // Create
        db.addUser("Tom", "secret");

        // Read
        List<User> users = db.getAllUsers();
        User user = users.getFirst();

        assertEquals("Tom", user.getName());

        // Update
        User updatedUser = new User(
                user.getId(),
                "Tom 2",
                9999,
                5,
                4,
                3
        );

        db.updateUser(updatedUser);

        user = db.getAllUsers().getFirst();

        assertEquals("Tom 2", user.getName());
        assertEquals(9999, user.getHighScore());
        assertEquals(5, user.getMaxGrandmas());
        assertEquals(4, user.getMaxFactories());
        assertEquals(3, user.getMaxWizards());

        // Delete
        db.deleteUser(user.getId());

        assertEquals(0, db.getAllUsers().size());
    }

    @Test
    void testGameTable() {
        // Create
        db.addUser("Tom", "secret");
        db.addUser("Noah", "secret2");

        db.addGame(1);
        db.addGame(1);
        db.addGame(2);

        // Read
        List<Game> user1Games = db.getUserGames(1);
        List<Game> user2Games = db.getUserGames(2);
        List<Game> allGames = db.getAllGames();

        assertEquals(2, user1Games.size());
        assertEquals(1, user2Games.size());
        assertEquals(3, allGames.size());

        // Update
        Game currGame = user1Games.getFirst();
        int currGameID = currGame.getGameId();
        currGame.setCookies(currGame.getCookies() + 1);
        db.updateGame(currGame);

        currGame = db.getGameByID(currGameID);

        assertEquals(1, currGame.getCookies());

        // Delete

        db.deleteGame(1);
        assertEquals(2, db.getAllGames().size());
    }

    @Test
    void testUpgradeTable() {
        // Create
        db.addUser("Tom", "secret");
        db.addGame(1);
        int gameId = db.getAllGames().getFirst().getGameId();

        List<Upgrade> upgrades = db.getAllUpgrades();
        Upgrade firstUpgrade = upgrades.getFirst();

        db.addPurchase(gameId, firstUpgrade.getUpgradeId(), firstUpgrade.getName());

        // Read
        List<PurchasedUpgrade> purchasedUpgrades = db.getAllPurchasedUpgradesByGameID(gameId);
        PurchasedUpgrade purchase = purchasedUpgrades.getFirst();
        assertEquals(firstUpgrade.getName(), purchase.getName());

        // Update
        PurchasedUpgrade newPurchase = new PurchasedUpgrade(purchase.getPurchaseId(), purchase.getGameId(), purchase.getGameId(), "changed name");
        db.updatePurchasedUpgrade(newPurchase);

        PurchasedUpgrade modifiedPurchase = db.getAllPurchasedUpgradesByGameID(gameId).getFirst();

        assertEquals("changed name", modifiedPurchase.getName());

        // Delete
        db.deletePurchasedUpgrade(modifiedPurchase.getPurchaseId());

        assertEquals(0, db.getAllPurchasedUpgrades().size());
    }

    @Test
    void testPurchasedUpgradesTable() {
        // Create
        int size = db.getAllUpgrades().size();
        db.addUpgrade("Test Upgrade", 100, "grandma");
        List<Upgrade> upgrades = db.getAllUpgrades();
        Upgrade newUpgrade = upgrades.getLast();
        assertEquals("Test Upgrade", newUpgrade.getName());

        // Read
        assertEquals(10, size + 1);

        // Update
        db.updateUpgradePrice(newUpgrade.getUpgradeId(), 100);

        Upgrade modifiedUpgrade = db.getUpgradeByID(newUpgrade.getUpgradeId());

        assertEquals(100, modifiedUpgrade.getCost());

        // Delete
        db.deleteUpgrade(modifiedUpgrade.getUpgradeId());

        assertEquals(size, db.getAllUpgrades().size());
    }

    @Test
    void addUpgrade() {
        db.addUpgrade("Test Upgrade", 100, "grandma");

        List<Upgrade> upgrades = db.getAllUpgrades();

        assertEquals("Test Upgrade", upgrades.getLast().getName());
    }

    @Test
    void addGame() {
        db.addUser("Tom", "secret");
        List<User> users = db.getAllUsers();
        db.addGame(users.getFirst().getId());
        List<Game> games = db.getAllGames();

        assertEquals(1, games.size());
        assertEquals(0, games.getFirst().getCookies());
    }
}