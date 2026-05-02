import model.Game;
import makers.*;

import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class GameDriver {
    private Grandmas grandmas;
    private Factories factories;
    private Wizards wizards;
    private Game tuple;
    private long cookies;
    private long cps;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

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
        calculateCps();
        this.tuple = new Game();
    }

    /**
     * Constructor to load a game from save
     * Uses db Game model to get data
     * Units are initialized using load game constructor
     * cookies are derived from db data, then increased based on time away
     * @param tuple a row of data from the Game table
     */
    GameDriver(Game tuple) {
        this.tuple = tuple;
        this.grandmas = new Grandmas(tuple);
        this.factories = new Factories(tuple);
        this.wizards = new Wizards(tuple);
        this.cookies = tuple.getCookies();
        calculateCps();
    }

    /**
     * calculates player's total cookies per second (cps)
     * @return sum of each unit type's cps
     */
    public void calculateCps() {
        this.cps = grandmas.getCps() + factories.getCps() + wizards.getCps();
    }

    

    public void addCookie() {
        this.cookies++;
    }

    public long getCookies() {
        return cookies;
    }

    public Grandmas getGrandmas() {
        return grandmas;
    }

    public Wizards getWizards() {
        return wizards;
    }

    public Factories getFactories() {
        return factories;
    }
}
