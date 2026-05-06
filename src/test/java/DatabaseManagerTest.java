import model.Game;
import model.PurchasedUpgrade;
import model.Upgrade;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

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
    void testSchema() throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:app.db")) {
            assertTrue(db.tableExists(conn, "games"));
            assertTrue(db.tableExists(conn, "users"));
            assertTrue(db.tableExists(conn, "upgrades"));
            assertTrue(db.tableExists(conn, "purchased_upgrades"));
        }
    }

    @Test
    void testUserTable() {
        db.addUser("Tom", "secret");

        List<User> users = db.getAllUsers();
        User user = users.get(0);

        assertEquals("Tom", user.getName());

        User updatedUser = new User(
                user.getId(),
                "Tom 2",
                9999,
                5,
                4,
                3,
                "secret"
        );

        db.updateUser(updatedUser);

        user = db.getAllUsers().get(0);

        assertEquals("Tom 2", user.getName());
        assertEquals(9999, user.getHighScore());
        assertEquals(5, user.getMaxGrandmas());
        assertEquals(4, user.getMaxFactories());
        assertEquals(3, user.getMaxWizards());

        db.deleteUser(user.getId());

        assertEquals(0, db.getAllUsers().size());
    }

    @Test
    void testGameTable() {
        db.addUser("Tom", "secret");
        db.addUser("Noah", "secret2");

        List<User> users = db.getAllUsers();
        int user1Id = users.get(0).getId();
        int user2Id = users.get(1).getId();

        db.addGame(user1Id, 1);
        db.addGame(user1Id, 2);
        db.addGame(user2Id, 1);

        List<Game> user1Games = db.getUserGames(user1Id);
        List<Game> user2Games = db.getUserGames(user2Id);
        List<Game> allGames = db.getAllGames();

        assertEquals(2, user1Games.size());
        assertEquals(1, user2Games.size());
        assertEquals(3, allGames.size());

        Game currGame = user1Games.get(0);
        int currGameId = currGame.getGameId();

        currGame.setCookies(currGame.getCookies() + 1);
        db.updateGame(currGame);

        currGame = db.getGameByID(currGameId);

        assertNotNull(currGame);
        assertEquals(1, currGame.getCookies());

        db.deleteGame(currGameId);

        assertEquals(2, db.getAllGames().size());
    }

    @Test
    void testPurchasedUpgradesTable() {
        db.addUser("Tom", "secret");

        int userId = db.getAllUsers().get(0).getId();
        db.addGame(userId, 1);

        int gameId = db.getAllGames().get(0).getGameId();

        List<Upgrade> upgrades = db.getAllUpgrades();
        Upgrade firstUpgrade = upgrades.get(0);

        db.addPurchase(gameId, firstUpgrade.getUpgradeId(), firstUpgrade.getName());

        List<PurchasedUpgrade> purchasedUpgrades =
                db.getAllPurchasedUpgradesByGameID(gameId);

        PurchasedUpgrade purchase = purchasedUpgrades.get(0);

        assertEquals(firstUpgrade.getName(), purchase.getName());
        assertEquals(firstUpgrade.getUpgradeId(), purchase.getUpgradeId());

        PurchasedUpgrade updatedPurchase = new PurchasedUpgrade(
                purchase.getPurchaseId(),
                purchase.getGameId(),
                purchase.getUpgradeId(),
                "changed name"
        );

        db.updatePurchasedUpgrade(updatedPurchase);

        PurchasedUpgrade modifiedPurchase =
                db.getAllPurchasedUpgradesByGameID(gameId).get(0);

        assertEquals("changed name", modifiedPurchase.getName());

        db.deletePurchasedUpgrade(modifiedPurchase.getPurchaseId());

        assertEquals(0, db.getAllPurchasedUpgrades().size());
    }

    @Test
    void testUpgradeTable() {
        int originalSize = db.getAllUpgrades().size();

        db.addUpgrade("Test Upgrade", 100, "grandma");

        List<Upgrade> upgrades = db.getAllUpgrades();
        Upgrade newUpgrade = upgrades.get(upgrades.size() - 1);

        assertEquals(originalSize + 1, upgrades.size());
        assertEquals("Test Upgrade", newUpgrade.getName());
        assertEquals(100, newUpgrade.getCost());
        assertEquals("grandma", newUpgrade.getCategory());

        db.updateUpgradePrice(newUpgrade.getUpgradeId(), 200);

        Upgrade modifiedUpgrade = db.getUpgradeByID(newUpgrade.getUpgradeId());

        assertNotNull(modifiedUpgrade);
        assertEquals(200, modifiedUpgrade.getCost());

        db.deleteUpgrade(modifiedUpgrade.getUpgradeId());

        assertEquals(originalSize, db.getAllUpgrades().size());
    }

    @Test
    void addUpgrade() {
        db.addUpgrade("Test Upgrade", 100, "grandma");

        List<Upgrade> upgrades = db.getAllUpgrades();
        Upgrade lastUpgrade = upgrades.get(upgrades.size() - 1);

        assertEquals("Test Upgrade", lastUpgrade.getName());
    }

    @Test
    void addGame() {
        db.addUser("Tom", "secret");

        User user = db.getAllUsers().get(0);

        db.addGame(user.getId(), 1);

        List<Game> games = db.getAllGames();

        assertEquals(1, games.size());
        assertEquals(0, games.get(0).getCookies());
    }
}