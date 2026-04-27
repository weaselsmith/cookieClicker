import java.sql.*;
import java.util.*;

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

    public void addUser(String name) {
        String sql = "INSERT INTO users (name) VALUES (?)";

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

    public boolean tableExists(Connection conn, String tableName) {
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tableName);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("table "+tableName+" does not exist");
        }
        return false;
    }
}