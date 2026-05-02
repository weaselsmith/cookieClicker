import model.Game;
import makers.*;

import java.math.BigInteger;
import java.util.*;

public class GameDriver {
    private Grandmas grandmas;
    private Factories factories;
    private Wizards wizards;
    private Game tuple;
    private long cookies;
    private long cps;

    /**
     * Constructor for new game
     * initializes fresh unit classes
     * sets cookies to 0
     * calculates cps, which will be 0
     * creates new model class for game tuple
     */
    GameDriver() {
        this.grandmas = new Grandmas();
        this.factories = new Factories();
        this.wizards = new Wizards();
        this.cookies = 0;
        this.cps = calculateCps();
        this.tuple = new Game();
    }

    /**
     * Constructor to load a game from save
     * Uses db Game model to get data
     *
     * @param tuple
     */
    GameDriver(Game tuple) {
        this.tuple = tuple;
        this.grandmas = new Grandmas(tuple);
        this.factories = new Factories(tuple);
        this.wizards = new Wizards(tuple);
        this.cookies = tuple.getCookies();
        this.cps = calculateCps();
    }

    /**
     * calculates player's total cookies per second (cps)
     * @return sum of each unit type's cps
     */
    public long calculateCps() {
        return grandmas.getCps() + factories.getCps() + wizards.getCps();
    }

    public void addCookie() {
        this.cookies++;
    }

    public long getCookies() {
        return cookies;
    }
}
