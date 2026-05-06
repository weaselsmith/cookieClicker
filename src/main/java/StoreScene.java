import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Game;
import model.Upgrade;

import java.util.List;

public class StoreScene {

    public static Scene create(DatabaseManager db, Context context) {
        Game game = context.getGame();

        Label title = new Label("Store");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        Label cookiesLabel = new Label("Cookies: " + game.getCookies());
        cookiesLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        List<Upgrade> upgrades = db.getAllUpgrades();

        HBox grandmaRow = createUpgradeRow(
                db,
                game,
                cookiesLabel,
                "grandma",
                "/images/Grandma.png",
                upgrades.stream()
                        .filter(u -> u.getCategory().equalsIgnoreCase("grandma"))
                        .toList()
        );

        HBox factoryRow = createUpgradeRow(
                db,
                game,
                cookiesLabel,
                "factory",
                "/images/Factory.png",
                upgrades.stream()
                        .filter(u -> u.getCategory().equalsIgnoreCase("factory"))
                        .toList()
        );

        HBox wizardRow = createUpgradeRow(
                db,
                game,
                cookiesLabel,
                "wizard",
                "/images/Wizard.jpeg",
                upgrades.stream()
                        .filter(u -> u.getCategory().equalsIgnoreCase("wizard"))
                        .toList()
        );

        VBox content = new VBox(10, title, cookiesLabel, grandmaRow, factoryRow, wizardRow);
        content.setPadding(new Insets(12));
        content.setAlignment(Pos.TOP_CENTER);

        BorderPane root = new BorderPane();
        root.getStylesheets().add(
                StoreScene.class.getResource("/style.css").toExternalForm()
        );

        content.setMaxHeight(390);
        root.setCenter(content);
        root.setBottom(NavBar.create(SceneType.STORE));

        return new Scene(root, 640, 700);
    }

    private static HBox createUpgradeRow(
            DatabaseManager db,
            Game game,
            Label cookiesLabel,
            String category,
            String imagePath,
            List<Upgrade> upgrades
    ) {
        ImageView imageView = new ImageView(
                new Image(StoreScene.class.getResource(imagePath).toExternalForm())
        );

        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);

        Label countLabel = new Label(getCategoryCountText(game, category));
        countLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Button buyButton = new Button(getBuyButtonText(category));
        buyButton.setCursor(Cursor.HAND);

        buyButton.setOnAction(e -> {
            increaseCategoryCount(game, category);
            countLabel.setText(getCategoryCountText(game, category));
            cookiesLabel.setText("Cookies: " + game.getCookies());
        });

        VBox portraitBox = new VBox(4, imageView, countLabel, buyButton);
        portraitBox.setAlignment(Pos.CENTER);

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
            HBox upgradeRow = createUpgradeItemRow(db, game, cookiesLabel, upgrade);
            tableBox.getChildren().add(upgradeRow);
        }

        HBox row = new HBox(18, portraitBox, tableBox);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(4));

        return row;
    }

    private static HBox createUpgradeItemRow(
            DatabaseManager db,
            Game game,
            Label cookiesLabel,
            Upgrade upgrade
    ) {
        HBox upgradeRow = new HBox();
        upgradeRow.setPadding(new Insets(3, 8, 3, 8));
        upgradeRow.setAlignment(Pos.CENTER_LEFT);

        Label nameLabel = new Label(upgrade.getName());
        nameLabel.setPrefWidth(230);
        nameLabel.setStyle("-fx-font-size: 12px;");

        Label costLabel = new Label("FREE");
        costLabel.setPrefWidth(80);
        costLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");

        upgradeRow.getChildren().addAll(nameLabel, costLabel);

        refreshUpgradeRowStyle(upgradeRow);

        upgradeRow.setOnMouseClicked(e -> {
            String category = upgrade.getCategory().toLowerCase();

            if (category.equals("grandma")) {
                game.setGrandmaLvl(game.getGrandmaLvl() + 1);
                db.updateGame(game);
            } else if (category.equals("factory")) {
                game.setFactoryLvl(game.getFactoryLvl() + 1);
                db.updateGame(game);
            } else if (category.equals("wizard")) {
                game.setWizardsLvl(game.getWizardsLvl() + 1);
                db.updateGame(game);
            }

            db.addPurchase(
                    1,
                    upgrade.getUpgradeId(),
                    upgrade.getName()
            );

            cookiesLabel.setText("Cookies: " + game.getCookies());
            refreshUpgradeRowStyle(upgradeRow);

            System.out.println("Purchased free upgrade: " + upgrade.getName());
        });

        return upgradeRow;
    }

    private static void increaseCategoryCount(Game game, String category) {
        switch (category.toLowerCase()) {
            case "grandma":
                game.setNumGrandmas(game.getNumGrandmas() + 1);
                break;
            case "factory":
                game.setNumFactories(game.getNumFactories() + 1);
                break;
            case "wizard":
                game.setNumWizards(game.getNumWizards() + 1);
                break;
        }
    }

    private static String getCategoryCountText(Game game, String category) {
        switch (category.toLowerCase()) {
            case "grandma":
                return "Owned: " + game.getNumGrandmas();
            case "factory":
                return "Owned: " + game.getNumFactories();
            case "wizard":
                return "Owned: " + game.getNumWizards();
            default:
                return "Owned: 0";
        }
    }

    private static String getBuyButtonText(String category) {
        switch (category.toLowerCase()) {
            case "grandma":
                return "Buy Grandma";
            case "factory":
                return "Buy Factory";
            case "wizard":
                return "Buy Wizard";
            default:
                return "Buy";
        }
    }

    private static void refreshUpgradeRowStyle(HBox row) {
        row.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-opacity: 1.0;"
        );
        row.setCursor(Cursor.HAND);
    }
}