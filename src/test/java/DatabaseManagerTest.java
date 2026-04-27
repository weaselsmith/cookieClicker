import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Upgrade;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest {

    private DatabaseManager db;

    @BeforeEach
    void setUp() {
        db = new DatabaseManager();
        db.clearUpgrades();
    }

    @Test
    void testAddUpgrade() {
        db.addUpgrade("Test Upgrade", 100);
        List<Upgrade> upgrades = db.getAllUpgrades();

        assertEquals(1, upgrades.size());
        assertEquals("Test Upgrade", upgrades.get(0).getName());
    }

    @Test
    void testDeleteUpgrade() {
        db.addUpgrade("Delete Test", 75);
        Upgrade upgrade = db.getAllUpgrades().get(0);
        db.deleteUpgrade(upgrade.getUpgradeId());
        assertTrue(db.getAllUpgrades().isEmpty());
    }
}