import java.sql.*;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import model.Upgrade;
=======
import java.util.*;
>>>>>>> df48b5d (insertion methods for each table)

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

    private void createTables() {
        String createGamesTable = """
                CREATE TABLE IF NOT EXISTS games (
                    game_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    cookies INTEGER NOT NULL DEFAULT 0,
                    num_grandmas INTEGER NOT NULL DEFAULT 0,
                    num_factories INTEGER NOT NULL DEFAULT 0,
                    num_wizards INTEGER NOT NULL DEFAULT 0,
                    grandma_lvl INTEGER NOT NULL DEFAULT 1,
                    factory_lvl INTEGER NOT NULL DEFAULT 1,
                    wizards_lvl INTEGER NOT NULL DEFAULT 1,
                    last_login TEXT
                )
                """;

        String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    cur_game_id INTEGER,
                    high_score INTEGER NOT NULL DEFAULT 0,
                    max_grandmas INTEGER NOT NULL DEFAULT 0,
                    max_factories INTEGER NOT NULL DEFAULT 0,
                    max_wizards INTEGER NOT NULL DEFAULT 0,
                    FOREIGN KEY (cur_game_id) REFERENCES games(game_id)
                )
                """;

        String createUpgradesTable = """
                CREATE TABLE IF NOT EXISTS upgrades (
                    upgrade_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL UNIQUE,
                    cost INTEGER NOT NULL
                )
                """;

        String createPurchasedUpgradesTable = """
                CREATE TABLE IF NOT EXISTS purchased_upgrades (
                        purchase_id INTEGER PRIMARY KEY AUTOINCREMENT,
                        game_id INTEGER NOT NULL,
                        upgrade_id INTEGER NOT NULL,
                        FOREIGN KEY (game_id) REFERENCES games(game_id),
                        FOREIGN KEY (upgrade_id) REFERENCES upgrades(upgrade_id)
                    )
                """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createGamesTable);
            stmt.execute(createUsersTable);
            stmt.execute(createUpgradesTable);
            stmt.execute(createPurchasedUpgradesTable);
            System.out.println("createTables success");
        } catch (SQLException e) {
            System.err.println("createTables failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addUpgrade(String name, int cost) {
        String sql = "INSERT INTO upgrades (name, cost) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, cost);
            pstmt.executeUpdate();

            System.out.println("Added upgrade, name: " + name + ", cost: " + cost);
        } catch (SQLException e) {
            System.err.println("addUpgrade failed: " + e.getMessage());
        }
    }

<<<<<<< HEAD
    public List<Upgrade> getAllUpgrades() {
        List<Upgrade> upgrades = new ArrayList<>();
        String sql = "SELECT * FROM upgrades ORDER BY upgrade_id ASC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Upgrade upgrade = new Upgrade(
                        rs.getInt("upgrade_id"),
                        rs.getString("name"),
                        rs.getInt("cost")
                );
                upgrades.add(upgrade);
            }
        } catch (SQLException e) {
            System.err.println("getAllUpgrades failed: " + e.getMessage());
        }

        return upgrades;
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

    public void insertItem(String name) {
        String sql = "INSERT INTO items (name) VALUES (?)";
=======
    public void addUser(String name) {
        String sql = "INSERT INTO users (name) VALUES (?)";
>>>>>>> df48b5d (insertion methods for each table)

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("addUser failed: " + e.getMessage());
        }
    }

    public void addGame() {
        String sql = "INSERT INTO games DEFAULT VALUES";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("addGame failed: " + e.getMessage());
        }
    }

    public void addPurchase(int game_id, int upgrade_id) {
        String sql = "INSERT INTO purchased_upgrades (game_id, upgrade_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, game_id);
            pstmt.setInt(2, upgrade_id);
            pstmt.executeUpdate();

            System.out.println("Added purchase of upgrade "+upgrade_id+" to game "+game_id);
        } catch (SQLException e) {
            System.err.println("addPurchase failed: " + e.getMessage());
        }
    }

    public List<String> getAllItems() {
        List<String> items = new ArrayList<>();
        String sql = "SELECT name FROM users ORDER BY created DESC";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                items.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            System.err.println("getAllItems failed: " + e.getMessage());
        }

        return items;
    }

    public void markDone(int id) {
        String sql = "UPDATE items SET done = 1 WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("markDone failed: " + e.getMessage());
        }
    }

    public void deleteItem(int id) {
        String sql = "DELETE FROM items WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deleteItem failed: " + e.getMessage());
        }
    }
}