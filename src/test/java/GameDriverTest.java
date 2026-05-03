import makers.Grandmas;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Game;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GameDriverTest {
    GameDriver driver_new;
    GameDriver driver_loaded;

    @BeforeEach
    void setup() {
        Game tuple = new Game(0, 1000, 10, 1, 0,
                3, 2, 1, LocalDate.now());
        driver_new = new GameDriver();
        driver_loaded = new GameDriver(tuple);
    }

    @Test
    void addCookie() {
        assertEquals(0, driver_new.getCookies());
        driver_new.addCookie();
        assertEquals(1, driver_new.getCookies());
        assertEquals(1000, driver_loaded.getCookies());
        driver_loaded.addCookie();
        assertEquals(1001, driver_loaded.getCookies());
    }

    @Test
    void cookiesPerSecond() {
        assertEquals(50, driver_loaded.calculateCps());
    }

    @Test
    void grandmas() {
        Grandmas grandmas = driver_loaded.getGrandmas();
        assertEquals(256, grandmas.getPriceForNext());
        assertTrue(driver_loaded.buyUnit(MakerType.GRANDMA));
        assertEquals(744, driver_loaded.getCookies());
        assertEquals(11, grandmas.getNum());
        assertTrue(driver_loaded.upgradeUnit(MakerType.GRANDMA, 700));
        assertEquals(4, grandmas.getLvl());
        assertEquals(44, driver_loaded.getCookies());
        assertFalse(driver_loaded.buyUnit(MakerType.GRANDMA));
    }

    @Test
    void wizards() {

    }

    @Test
    void factories() {
    }

}