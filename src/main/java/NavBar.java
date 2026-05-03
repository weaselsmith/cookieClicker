import javafx.scene.control.*;
import java.util.*;

import static javafx.beans.binding.Bindings.select;

public class NavBar {
    private final List<Button> buttons;

    public NavBar (SceneManager sceneManager) {
        Button toMenu = new Button("menu");
        Button toCookie = new Button("game");
        Button toStore = new Button("store");
        Button toStats = new Button("stats");
        Button toLogin = new Button("log out");

        buttons = List.of(toMenu, toCookie, toStore, toStats, toLogin);

        toMenu.setOnAction(e -> {
            select(toMenu);
            sceneManager.navigateTo(SceneTypeNew.MENU);
        });

        toCookie.setOnAction(e -> {
            select(toCookie);
            sceneManager.navigateTo(SceneTypeNew.COOKIE);
        });

        toStore.setOnAction(e -> {
            select(toStore);
            sceneManager.navigateTo(SceneTypeNew.STORE);
        });

        toStats.setOnAction(e -> {
            select(toStats);
            sceneManager.navigateTo(SceneTypeNew.STATS);
        });
    }
}
