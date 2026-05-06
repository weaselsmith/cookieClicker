import model.Game;
import model.User;

public class Context {

    User user;
    Game game;
    GameDriver driver;

    public Context(){
        driver = null;
        game = null;
        driver = null;
    }

    public Context(User user, Game game){
        this.user = user;
        this.game = game;
        driver = new GameDriver(game);
        driver.startAutoCookies();
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

//    public void setGame(Game game) {
//        this.game = game;
//        driver.stopAutoCookies();
//        driver = new GameDriver(game);
//        driver.startAutoCookies();
//    }
public void setGame(Game game) {
    this.game = game;
}


    public GameDriver getDriver() {
        return driver;
    }

    public void setDriver(GameDriver newDriver) {
        if (this.driver != null) {
            this.driver.stopAutoCookies();
        }
        this.driver = newDriver;

        if (this.driver != null) {
            this.driver.startAutoCookies();
        }
    }





}