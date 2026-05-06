import model.Game;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CookieSceneTest {

    @BeforeAll
    static void setupSaveManager() {
        SaveManager.init(new FakeDatabaseManager());
    }

    @Test
    void addCookie_updatesCookieCount_andGameObject() {
        Game game = new Game(1, 0, 0, 0, 0, 1, 1, 1, java.time.LocalDate.now());
        GameDriver driver = new GameDriver(game);
        FakeDatabaseManager db = new FakeDatabaseManager();

        long before = driver.getCookies();
        driver.addCookie();
        db.updateGame(game);

        long after = driver.getCookies();
        assertTrue(after > before);
        assertEquals(after, game.getCookies());
        assertTrue(db.wasUpdated);
    }

    static class FakeDatabaseManager extends DatabaseManager {
        boolean wasUpdated = false;
        @Override
        public void updateGame(Game game) { wasUpdated = true; }
    }
}
