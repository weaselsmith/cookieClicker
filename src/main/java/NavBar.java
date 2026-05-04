import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.util.*;

import static javafx.beans.binding.Bindings.select;

public class NavBar {

    private NavBar() {

    }

    public static Node create (SceneType current, SceneManager sceneManager) {
        HBox root = new HBox();
        Button toMenu = new Button("menu");
        Button toCookie = new Button("game");
        Button toStore = new Button("store");
        Button toStats = new Button("stats");
        Button toLogin = new Button("log out");

        
        toMenu.setOnAction(e -> {
            sceneManager.navigateTo(SceneType.MENU);
        });

        toCookie.setOnAction(e -> {
            select(toCookie);
            sceneManager.navigateTo(SceneType.COOKIE);
        });

        toStore.setOnAction(e -> {
            select(toStore);
            sceneManager.navigateTo(SceneType.STORE);
        });

        toStats.setOnAction(e -> {
            select(toStats);
            sceneManager.navigateTo(SceneType.STATS);
        });

        toLogin.setOnAction(e -> {
            select(toStats);
            sceneManager.navigateTo(SceneType.LOGIN);
        });

        return root;
    }
}
