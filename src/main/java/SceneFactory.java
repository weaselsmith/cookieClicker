import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import model.Upgrade;

import java.util.List;

public class SceneFactory {

    public static Scene create(SceneType type, Stage stage, DatabaseManager db) {
        return switch (type) {
            case LOGIN -> buildLoginScene(stage, db);
            case MENU -> buildMenuScene(stage, db);
            case COOKIE -> buildCookieScene(stage, db);
            case STORE -> buildStoreScene(stage, db);
            case STATS -> buildStatsScene(stage, db);
            case CREDITS -> buildCreditsScene(stage, db);
        };
    }

    private static Scene buildLoginScene(Stage stage, DatabaseManager db) {
        // LoginScreenController either needs buildScene to be static, or it needs a constructor
        // Static Call:
        // return LoginScreenController.buildScene();
        // replaces line below
        return new Scene(new VBox(), 640, 480);  // this is a dummy return val
    }

    private static Scene buildMenuScene(Stage stage, DatabaseManager db) {
        BorderPane root = new BorderPane();  // doesn't have to stay borderPane
        // other stuff can go here if needed
        root.setBottom(NavBar.create(SceneType.MENU));
        return new Scene(root, 640, 480);
    }

    private static Scene buildCookieScene(Stage stage, DatabaseManager db) {
        return CookieScene.create(stage);
    }

    private static Scene buildStoreScene(Stage stage, DatabaseManager db) {
        BorderPane root = new BorderPane();  // doesn't have to stay borderPane
        // other stuff can go here if needed
        root.setBottom(NavBar.create(SceneType.MENU));
        return new Scene(root, 640, 480);
    }

    private static Scene buildStatsScene(Stage stage, DatabaseManager db) {
        BorderPane root = new BorderPane();  // doesn't have to stay borderPane
        // other stuff can go here if needed
        root.setBottom(NavBar.create(SceneType.MENU));
        return new Scene(root, 640, 480);
    }

    private static Scene buildCreditsScene(Stage stage, DatabaseManager db) {
        BorderPane root = new BorderPane();  // doesn't have to stay borderPane
        // other stuff can go here if needed
        root.setBottom(NavBar.create(SceneType.MENU));
        return new Scene(root, 640, 480);
    }

    /*
    private static Scene buildMainScene(Stage stage, DatabaseManager db) {
        Button loginBtn = new Button("Log In");
        loginBtn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.DASHBOARD)
        );

        return new Scene(new StackPane(loginBtn), 640, 480);
    }

    private static Scene buildDashboardScene(Stage stage, DatabaseManager db) {
        Button btn = new Button("Dashboard");
        Button cookiekBtn = new Button("Navigate to Cookie Scene");
        Button fileBtn = new Button("Navigate to File Screen");

        btn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.ADD_ITEM)
        );

        cookiekBtn.setOnAction(e ->
                stage.setScene(CookieScene.create(stage))
        );

        fileBtn.setOnAction(e ->
                stage.setScene(FileScreen.create(stage))
        );

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(btn, fileBtn, cookiekBtn);

        return new Scene(vBox, 640, 480);
    }

    private static Scene buildAddItemScene(Stage stage, DatabaseManager db) {

        Button saveBtn = new Button("Save");

        saveBtn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.DASHBOARD)
        );

        return new Scene(new StackPane(saveBtn), 640, 480);
    }

    private static Scene buildDBTestScene(Stage stage, DatabaseManager db) {
        // create
        Button addBtn = new Button("Add Upgrades Test");
        addBtn.setOnAction(e -> {
            db.addUpgrade("Minty Cookie", 50);
            db.addUpgrade("Mega Grandma", 1000);
        });

        // read
        Button showUpgradesBtn = new Button("Show All Upgrades Test");

        TextArea outputArea = new TextArea();
        outputArea.setPrefHeight(100);

        showUpgradesBtn.setOnAction(e -> {
            List<Upgrade> upgrades = db.getAllUpgrades();

            StringBuilder message = new StringBuilder();

            for (Upgrade upgrade : upgrades) {
                message.append("ID: ").append(upgrade.getUpgradeId())
                        .append(", Name: ").append(upgrade.getName())
                        .append(", Cost: ").append(upgrade.getCost())
                        .append("\n");
            }

            outputArea.setText(message.toString());
        });

        //update
        Button editBtn = new Button("Edit Upgrade");

        TextField idField = new TextField();
        idField.setPromptText("Upgrade ID");

        TextField costField = new TextField();
        costField.setPromptText("New Cost");

        editBtn.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            int newCost = Integer.parseInt(costField.getText());

            db.updateUpgradePrice(id, newCost);

        });

        HBox editLayout = new HBox(editBtn, idField, costField);

        //delete
        Button deleteBtn = new Button("Delete Upgrade");

        TextField deleteIdField = new TextField();
        deleteIdField.setPromptText("Upgrade ID");

        deleteBtn.setOnAction(e -> {
            int id = Integer.parseInt(deleteIdField.getText());
            db.deleteUpgrade(id);
        });

        HBox deleteLayout = new HBox(deleteBtn, deleteIdField);

        //view
        VBox layout = new VBox(addBtn, showUpgradesBtn, outputArea, editLayout, deleteLayout);
        layout.setAlignment(Pos.CENTER);

        return new Scene(layout, 640, 480);
    }

*/
}