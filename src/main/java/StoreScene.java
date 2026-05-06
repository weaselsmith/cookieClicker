import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Upgrade;

import java.util.List;

public class StoreScene {

    public static Scene create(DatabaseManager db, Context context) {
        Label title = new Label("Store");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        List<Upgrade> upgrades = db.getAllUpgrades();

        HBox grandmaRow = createUpgradeRow(
                "/images/Grandma.png",
                upgrades.stream()
                        .filter(u -> u.getCategory().equalsIgnoreCase("grandma"))
                        .toList()
        );

        HBox factoryRow = createUpgradeRow(
                "/images/Factory.png",
                upgrades.stream()
                        .filter(u -> u.getCategory().equalsIgnoreCase("factory"))
                        .toList()
        );

        HBox wizardRow = createUpgradeRow(
                "/images/Wizard.jpeg",
                upgrades.stream()
                        .filter(u -> u.getCategory().equalsIgnoreCase("wizard"))
                        .toList()
        );

        VBox root = new VBox(10, title, grandmaRow, factoryRow, wizardRow);
        root.setPadding(new Insets(12));
        root.setAlignment(Pos.TOP_CENTER);

        return new Scene(root, 640, 480);
    }

    private static HBox createUpgradeRow(String imagePath, List<Upgrade> upgrades) {
        ImageView imageView = new ImageView(
                new Image(StoreScene.class.getResource(imagePath).toExternalForm())
        );

        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);

        VBox tableBox = new VBox(3);
        tableBox.setPadding(new Insets(8));
        tableBox.setPrefWidth(360);
        tableBox.setMaxHeight(110);
        tableBox.setStyle(
                "-fx-background-color: #eaf4ff;" +
                        "-fx-border-color: #7ec8ff;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;"
        );

        HBox headerRow = new HBox();
        headerRow.setPadding(new Insets(2, 8, 2, 8));

        Label nameHeader = new Label("Name");
        nameHeader.setPrefWidth(230);
        nameHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Label costHeader = new Label("Cost");
        costHeader.setPrefWidth(80);
        costHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        headerRow.getChildren().addAll(nameHeader, costHeader);
        tableBox.getChildren().add(headerRow);

        for (Upgrade upgrade : upgrades) {
            HBox upgradeRow = new HBox();
            upgradeRow.setPadding(new Insets(3, 8, 3, 8));
            upgradeRow.setAlignment(Pos.CENTER_LEFT);
            upgradeRow.setStyle("-fx-background-color: transparent;");

            Label nameLabel = new Label(upgrade.getName());
            nameLabel.setPrefWidth(230);
            nameLabel.setStyle("-fx-font-size: 12px;");

            Label costLabel = new Label(String.valueOf(upgrade.getCost()));
            costLabel.setPrefWidth(80);
            costLabel.setStyle("-fx-font-size: 12px;");

            upgradeRow.getChildren().addAll(nameLabel, costLabel);

            upgradeRow.setOnMouseEntered(e -> upgradeRow.setStyle(
                    "-fx-background-color: #9fd3ff;" +
                            "-fx-background-radius: 6;"
            ));

            upgradeRow.setOnMouseExited(e -> upgradeRow.setStyle(
                    "-fx-background-color: transparent;"
            ));

            tableBox.getChildren().add(upgradeRow);
        }

        HBox row = new HBox(18, imageView, tableBox);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(4));

        return row;
    }
}