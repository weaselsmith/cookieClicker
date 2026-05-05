import model.Game;
import model.User;

public class Context {

    User user;
    Game game;

    public Context(){

    }

    public Context(User user, Game game){
        this.user = user;
        this.game = game;
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
    }
}
