import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import model.Upgrade;

import java.util.List;

public class SceneFactory {
    public static Scene create(SceneType type, Stage stage, DatabaseManager db) {
        return switch (type) {
            case MAIN -> buildMainScene(stage, db);
            case DASHBOARD -> buildDashboardScene(stage, db);
            case ADD_ITEM -> buildAddItemScene(stage, db);
            case DB_TEST -> buildDBTestScene(stage, db);
        };
    }

    private static Scene buildMainScene(Stage stage, DatabaseManager db) {
        Button loginBtn = new Button("Log In");
        loginBtn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.DASHBOARD)
        );
        return new Scene(new StackPane(loginBtn), 640, 480);
    }

    private static Scene buildDashboardScene(Stage stage, DatabaseManager db) {
        Button btn = new Button("Dashboard");

        btn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.ADD_ITEM)
        );

        return new Scene(new StackPane(btn), 640, 480);
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
}