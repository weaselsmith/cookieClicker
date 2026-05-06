import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneFactory {
    public static Scene create(SceneType type, Stage stage, DatabaseManager db, Context context) {
        return switch (type) {
            case LOGIN -> LoginScreenController.buildScene(db, context);
            case SIGNUP -> SignUpScreenController.buildScene(db, context);
            case FILE  -> FileScreen.create(stage, db, context);
            case COOKIE -> CookieScene.create(stage, db, context);
            case STORE -> StoreScene.create(db, context);
            case STATS -> StatsScene.create(db, context);
            case CREDITS -> buildCreditsScene(stage, db, context);
        };
    }

    private static Scene buildCreditsScene(Stage stage, DatabaseManager db, Context context) {
        BorderPane root = new BorderPane();  // doesn't have to stay borderPane
        // other stuff can go here if needed
        return new Scene(root, 640, 480);
    }

    /*

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