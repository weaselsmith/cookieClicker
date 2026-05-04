import model.Game;
import makers.*;

import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * GameDriver class
 * handles game logic and interaction between game elements and application
 * a new instance is created when a game is started or loaded
 * there is only need of one instance at a time
 */
public class GameDriver {
    private Grandmas grandmas;
    private Factories factories;
    private Wizards wizards;
    private Game tuple;
    private long cookies;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    /**
     * Constructor for new game
     * initializes fresh unit classes
     * sets cookies to 0
     * creates new model class for game tuple
     */
    GameDriver() {
        this.grandmas = new Grandmas();
        this.factories = new Factories();
        this.wizards = new Wizards();
        this.cookies = 0;
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
    }

    /**
     * calculates player's total cookies per second (cps)
     * @return sum of each unit type's cps
     */
    public long calculateCps() {
        return grandmas.getCps() + factories.getCps() + wizards.getCps();
    }

    /**
     * schedules an addition of cookies made by units once every second
     */
    public void startAutoCookies() {
        scheduler.scheduleAtFixedRate(this::countAutoCookies, 0, 1, TimeUnit.SECONDS);
    }

    private void countAutoCookies() {
        cookies += calculateCps();
        tuple.setCookies(this.cookies);
    }

    public void stopAutoCookies() {
        scheduler.shutdown();
    }

    public void addCookie() {
        this.cookies++;
        tuple.setCookies(this.cookies);
    }

    /**
     * public method for unit purchases
     * intended to be used by store scene
     * @param type type of unit being purchased
     * @return true if purchase was successful
     */
    public boolean buyUnit(MakerType type) {
        long price;
        switch (type) {
            case GRANDMA -> {
                price = grandmas.getPriceForNext();
                if (price > cookies) {
                    return false;
                }
                cookies -= price;
                grandmas.add();
                tuple.setNumGrandmas(grandmas.getNum());
            }
            case FACTORY -> {
                price = factories.getPriceForNext();
                if (price > cookies) {
                    return false;
                }
                cookies -= price;
                factories.add();
                tuple.setNumFactories(factories.getNum());
            }
            case WIZARD -> {
                price = wizards.getPriceForNext();
                if (price > cookies) {
                    return false;
                }
                cookies -= price;
                wizards.add();
                tuple.setNumWizards(wizards.getNum());
            }
        }
        tuple.setCookies(this.cookies);
        return true;
    }

    /**
     * public method for maker unit upgrades
     * intended for use by store scene
     * @param type the type of unit being upgraded
     * @param price cost of the upgrade
     * @return false if not enough cookies, true if successfully upgraded
     */
    public boolean upgradeUnit(MakerType type, long price) {
        if (price > cookies) {
            return false;
        }
        cookies -= price;
        switch (type) {
            case GRANDMA -> {
                grandmas.levelUp();
                tuple.setGrandmaLvl(grandmas.getLvl());
            }
            case FACTORY -> {
                factories.levelUp();
                tuple.setFactoryLvl(factories.getLvl());
            }
            case WIZARD -> {
                wizards.levelUp();
                tuple.setWizardsLvl(wizards.getLvl());
            }
        }
        tuple.setCookies(this.cookies);
        return true;
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
