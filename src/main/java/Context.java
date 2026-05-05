import model.Game;
import model.User;

public class Context {

    User user;
    Game game;
    GameDriver driver;

    public Context(){
        driver = new GameDriver();
        game = driver.getGame();
        // driver.startAutoCookies();
    }

    public Context(User user, Game game){
        this.user = user;
        this.game = game;
        driver = new GameDriver(game);
        // driver.startAutoCookies();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        driver.stopAutoCookies();
        driver = new GameDriver(game);
        // driver.startAutoCookies();
    }

    public GameDriver getDriver() {
        return driver;
    }
}