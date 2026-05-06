import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Upgrade;
import model.Game;
import model.User;
import model.PurchasedUpgrade;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:app.db";
    private Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(DB_URL);

            try (Statement stmt = connection.createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON;");
            }

            System.out.println("Database connected.");
            createTables();

        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Close failed: " + e.getMessage());
        }
    }

    private Game createGameFromResultSet(ResultSet rs) throws SQLException {
        return new Game(
                rs.getInt("game_id"),
                rs.getInt("cookies"),
                rs.getInt("num_grandmas"),
                rs.getInt("num_factories"),
                rs.getInt("num_wizards"),
                rs.getInt("grandma_lvl"),
                rs.getInt("factory_lvl"),
                rs.getInt("wizards_lvl"),
                LocalDate.now()
        );
    }

    private void createTables() {
        String createGamesTable = """
                CREATE TABLE IF NOT EXISTS games (
                    game_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER,
                    slot INTEGER NOT NULL,
                    cookies INTEGER NOT NULL DEFAULT 0,
                    num_grandmas INTEGER NOT NULL DEFAULT 0,
                    num_factories INTEGER NOT NULL DEFAULT 0,
                    num_wizards INTEGER NOT NULL DEFAULT 0,
                    grandma_lvl INTEGER NOT NULL DEFAULT 1,
                    factory_lvl INTEGER NOT NULL DEFAULT 1,
                    wizards_lvl INTEGER NOT NULL DEFAULT 1,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                )
                """;

        String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    high_score INTEGER NOT NULL DEFAULT 0,
                    max_grandmas INTEGER NOT NULL DEFAULT 0,
                    max_factories INTEGER NOT NULL DEFAULT 0,
                    max_wizards INTEGER NOT NULL DEFAULT 0,
                    password TEXT NOT NULL
                )
                """;

        String createUpgradesTable = """
                CREATE TABLE IF NOT EXISTS upgrades (
                    upgrade_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL UNIQUE,
                    cost INTEGER NOT NULL,
                    category TEXT NOT NULL
                )
                """;

        String createPurchasedUpgradesTable = """
                CREATE TABLE IF NOT EXISTS purchased_upgrades (
                    purchase_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    game_id INTEGER NOT NULL,
                    upgrade_id INTEGER NOT NULL,
                    name TEXT NOT NULL,
                    FOREIGN KEY (game_id) REFERENCES games(game_id),
                    FOREIGN KEY (upgrade_id) REFERENCES upgrades(upgrade_id)
                )
                """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createGamesTable);
            stmt.execute(createUpgradesTable);
            stmt.execute(createPurchasedUpgradesTable);
            System.out.println("createTables success");
        } catch (SQLException e) {
            System.err.println("createTables failed: " + e.getMessage());
            e.printStackTrace();
        }

        String upgradesSeedSQL = "INSERT OR IGNORE INTO upgrades (name, cost, category) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(upgradesSeedSQL)) {
            pstmt.setString(1, "Lovin'");
            pstmt.setInt(2, 10);
            pstmt.setString(3, "grandma");
            pstmt.executeUpdate();

            pstmt.setString(1, "Ramblin'");
            pstmt.setInt(2, 30);
            pstmt.setString(3, "grandma");
            pstmt.executeUpdate();

            pstmt.setString(1, "Gamblin'");
            pstmt.setInt(2, 40);
            pstmt.setString(3, "grandma");
            pstmt.executeUpdate();

            pstmt.setString(1, "Wise");
            pstmt.setInt(2, 50);
            pstmt.setString(3, "wizard");
            pstmt.executeUpdate();

            pstmt.setString(1, "Old");
            pstmt.setInt(2, 100);
            pstmt.setString(3, "wizard");
            pstmt.executeUpdate();

            pstmt.setString(1, "Funky");
            pstmt.setInt(2, 250);
            pstmt.setString(3, "wizard");
            pstmt.executeUpdate();

            pstmt.setString(1, "Socialist");
            pstmt.setInt(2, 50);
            pstmt.setString(3, "factory");
            pstmt.executeUpdate();

            pstmt.setString(1, "Capitalist");
            pstmt.setInt(2, 500);
            pstmt.setString(3, "factory");
            pstmt.executeUpdate();

            pstmt.setString(1, "OSHA Approved");
            pstmt.setInt(2, 1000);
            pstmt.setString(3, "factory");
            pstmt.executeUpdate();

            System.out.println("Default upgrades seeded.");
        } catch (SQLException e) {
            System.err.println("seedDefaultUpgrades failed: " + e.getMessage());
        }
    }

    public Game getGameByUserAndSlot(int userId, int slot) {
        String sql = "SELECT * FROM games WHERE user_id = ? AND slot = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, slot);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return createGameFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("getGameByUserAndSlot failed: " + e.getMessage());
        }
        return null;
    }


    public void addUpgrade(String name, int cost, String category) {
        String sql = "INSERT INTO upgrades (name, cost, category) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, cost);
            pstmt.setString(3, category);
            pstmt.executeUpdate();

            System.out.println("Added upgrade, name: " + name + ", cost: " + cost);
        } catch (SQLException e) {
            System.err.println("addUpgrade failed: " + e.getMessage());
        }
    }

    public List<Upgrade> getAllUpgrades() {
        List<Upgrade> upgrades = new ArrayList<>();
        String sql = "SELECT * FROM upgrades ORDER BY upgrade_id ASC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Upgrade upgrade = new Upgrade(
                        rs.getInt("upgrade_id"),
                        rs.getString("name"),
                        rs.getInt("cost"),
                        rs.getString("category")
                );
                upgrades.add(upgrade);
            }
        } catch (SQLException e) {
            System.err.println("getAllUpgrades failed: " + e.getMessage());
        }

        return upgrades;
    }

    public Upgrade getUpgradeByID(int upgradeId) {
        String sql = "SELECT * FROM upgrades WHERE upgrade_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, upgradeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Upgrade(
                            rs.getInt("upgrade_id"),
                            rs.getString("name"),
                            rs.getInt("cost"),
                            rs.getString("category")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("getUpgradeByID failed: " + e.getMessage());
        }

        return null;
    }

    public void updateUpgradePrice(int upgradeId, int newCost) {
        String sql = "UPDATE upgrades SET cost = ? WHERE upgrade_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, newCost);
            pstmt.setInt(2, upgradeId);
            pstmt.executeUpdate();

            System.out.println("Updated upgrade " + upgradeId + " to new cost: " + newCost);
        } catch (SQLException e) {
            System.err.println("updatePrice failed: " + e.getMessage());
        }
    }

    public void deleteUpgrade(int id) {
        String sql = "DELETE FROM upgrades WHERE upgrade_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Deleted upgrade with id: " + id);
        } catch (SQLException e) {
            System.err.println("deleteUpgrade failed: " + e.getMessage());
        }
    }

//    public void addGame(int userId) {
//        String sql = "INSERT INTO games (user_id) VALUES (?)";
//
//        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//            pstmt.setInt(1, userId);
//            pstmt.executeUpdate();
//            System.out.println("Added game for user_id: " + userId);
//        } catch (SQLException e) {
//            System.err.println("addGame failed: " + e.getMessage());
//        }
//    }

    public void addGame(int userId, int slot) {
        String sql = "INSERT INTO games (user_id, slot) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, slot);
            pstmt.executeUpdate();
            System.out.println("Added game for user_id " + userId + " in slot " + slot);
        } catch (SQLException e) {
            System.err.println("addGame failed: " + e.getMessage());
        }
    }


    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games ORDER BY game_id ASC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                games.add(createGameFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("getAllGames failed: " + e.getMessage());
        }

        return games;
    }

    public List<Game> getUserGames(int userId) {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games WHERE user_id = ? ORDER BY game_id ASC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    games.add(createGameFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("getUserGames failed: " + e.getMessage());
        }

        return games;
    }

    public Game getLastGame() {
        String sql = "SELECT * FROM games ORDER BY game_id DESC LIMIT 1";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return createGameFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("getLastGame failed: " + e.getMessage());
        }

        return null;
    }

    public Game getGameByID(int gameId) {
        String sql = "SELECT * FROM games WHERE game_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, gameId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return createGameFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("getGameByID failed: " + e.getMessage());
        }

        return null;
    }

    public void updateGame(Game game) {
        String sql = "UPDATE games SET cookies = ?, num_grandmas = ?, num_factories = ?, " +
                "num_wizards = ?, grandma_lvl = ?, factory_lvl = ?, wizards_lvl = ? " +
                "WHERE game_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, game.getCookies());
            pstmt.setInt(2, game.getNumGrandmas());
            pstmt.setInt(3, game.getNumFactories());
            pstmt.setInt(4, game.getNumWizards());
            pstmt.setInt(5, game.getGrandmaLvl());
            pstmt.setInt(6, game.getFactoryLvl());
            pstmt.setInt(7, game.getWizardsLvl());
            pstmt.setInt(8, game.getGameId());

            pstmt.executeUpdate();

            System.out.println("Updated game with id: " + game.getGameId());
        } catch (SQLException e) {
            System.err.println("updateGame failed: " + e.getMessage());
        }
    }

    public void deleteGame(int id) {
        String sql = "DELETE FROM games WHERE game_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Deleted game with id: " + id);
        } catch (SQLException e) {
            System.err.println("deleteGame failed: " + e.getMessage());
        }
    }

    public void addUser(String name, String password) {
        String sql = "INSERT INTO users (name, password) VALUES (?, ?)";
        System.out.println("Storing values: " + name + " and  " + password);
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("addUser failed: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id ASC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("high_score"),
                        rs.getInt("max_grandmas"),
                        rs.getInt("max_factories"),
                        rs.getInt("max_wizards"),
                        rs.getString("password")
                );

                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("getAllUsers failed: " + e.getMessage());
        }

        return users;
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, high_score = ?, max_grandmas = ?, " +
                "max_factories = ?, max_wizards = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, (int) user.getHighScore());
            pstmt.setInt(3, user.getMaxGrandmas());
            pstmt.setInt(4, user.getMaxFactories());
            pstmt.setInt(5, user.getMaxWizards());
            pstmt.setInt(6, user.getId());

            pstmt.executeUpdate();

            System.out.println("Updated user with id: " + user.getId());
        } catch (SQLException e) {
            System.err.println("updateUser failed: " + e.getMessage());
        }
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Deleted user with id: " + id);
        } catch (SQLException e) {
            System.err.println("deleteUser failed: " + e.getMessage());
        }
    }

    public void addPurchase(int gameId, int upgradeId, String name) {
        String sql = "INSERT INTO purchased_upgrades (game_id, upgrade_id, name) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, gameId);
            pstmt.setInt(2, upgradeId);
            pstmt.setString(3, name);
            pstmt.executeUpdate();

            System.out.println("Added purchase of upgrade " + upgradeId + " in game " + gameId);
        } catch (SQLException e) {
            System.err.println("addPurchase failed: " + e.getMessage());
        }
    }

    public List<PurchasedUpgrade> getAllPurchasedUpgrades() {
        List<PurchasedUpgrade> purchases = new ArrayList<>();
        String sql = "SELECT * FROM purchased_upgrades ORDER BY purchase_id ASC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PurchasedUpgrade purchase = new PurchasedUpgrade(
                        rs.getInt("purchase_id"),
                        rs.getInt("game_id"),
                        rs.getInt("upgrade_id"),
                        rs.getString("name")
                );

                purchases.add(purchase);
            }
        } catch (SQLException e) {
            System.err.println("getAllPurchasedUpgrades failed: " + e.getMessage());
        }

        return purchases;
    }

    public List<PurchasedUpgrade> getAllPurchasedUpgradesByGameID(int gameId) {
        List<PurchasedUpgrade> purchases = new ArrayList<>();
        String sql = "SELECT * FROM purchased_upgrades WHERE game_id = ? ORDER BY purchase_id ASC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, gameId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PurchasedUpgrade purchase = new PurchasedUpgrade(
                            rs.getInt("purchase_id"),
                            rs.getInt("game_id"),
                            rs.getInt("upgrade_id"),
                            rs.getString("name")
                    );

                    purchases.add(purchase);
                }
            }
        } catch (SQLException e) {
            System.err.println("getAllPurchasedUpgradesByGameID failed: " + e.getMessage());
        }

        return purchases;
    }

    public void updatePurchasedUpgrade(PurchasedUpgrade purchase) {
        String sql = "UPDATE purchased_upgrades SET game_id = ?, upgrade_id = ?, name = ? WHERE purchase_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, purchase.getGameId());
            pstmt.setInt(2, purchase.getUpgradeId());
            pstmt.setString(3, purchase.getName());
            pstmt.setInt(4, purchase.getPurchaseId());

            pstmt.executeUpdate();

            System.out.println("Updated purchased upgrade with id: " + purchase.getPurchaseId());
        } catch (SQLException e) {
            System.err.println("updatePurchasedUpgrade failed: " + e.getMessage());
        }
    }

    public void deletePurchasedUpgrade(int purchaseId) {
        String sql = "DELETE FROM purchased_upgrades WHERE purchase_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, purchaseId);
            pstmt.executeUpdate();

            System.out.println("Deleted purchased upgrade with id: " + purchaseId);
        } catch (SQLException e) {
            System.err.println("deletePurchasedUpgrade failed: " + e.getMessage());
        }
    }

    public boolean tableExists(Connection conn, String tableName) {
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tableName);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("table " + tableName + " does not exist");
        }

        return false;
    }
}