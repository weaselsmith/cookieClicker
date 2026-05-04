import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.util.*;

import static javafx.beans.binding.Bindings.select;

public class NavBar {
    private static final SceneManager sceneManager = SceneManager.getInstance();
    private static final String NORMAL_STYLE =
            "-fx-background-color: #0b2a4a;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-color: white;" +
                    "-fx-border-width: 0 1px 0 0;" +
                    "-fx-background-radius: 0;";

    private static final String SELECTED_STYLE =
            "-fx-background-color: white;" +
                    "-fx-text-fill: #0b2a4a;" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-color: #0b2a4a;" +
                    "-fx-border-width: 0 1px 0 0;" +
                    "-fx-background-radius: 0;";

    private NavBar() {}

    /**
     * Button Factory
     * Creates a navbar, returning it as an HBox component
     * The NavBar has 5 buttons, 4 of which are functional
     * The button to the current scene does nothing
     * It is a different color to mark which scene you're on
     * @param current: the current scene
     * @return Node root, an HBox with a navbar
     */
    public static Node create (SceneType current) {
        HBox root = new HBox();
        ArrayList<Button> buttons = new ArrayList<>();
        for (NavButtonType type : NavButtonType.values()) {
            if (type.getTarget().equals(current)) {
                buttons.add(createSelectedButton(type.getLabel()));
            } else {
                buttons.add(createNavButton(type.getLabel(), type.getTarget()));
            }
        }
        root.getChildren().addAll(buttons);
        return root;
    }

    // Standard button
    private static Button createNavButton(String label, SceneType navTo) {
        Button button = new Button(label);
        button.setPrefWidth(128);
        button.setPrefHeight(48);
        button.setStyle(NORMAL_STYLE);
        button.setOnAction(e -> {
            sceneManager.navigateTo(navTo);
        });
        return button;
    }

    // style for the button of the selected scene
    // does nothing since you're already there
    private static Button createSelectedButton(String label) {
        Button button = new Button(label);
        button.setPrefWidth(128);
        button.setPrefHeight(48);
        button.setStyle(SELECTED_STYLE);
        return button;
    }
}
