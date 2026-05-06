import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Game;
import model.User;

public class StatsScene {

    public static Scene create(DatabaseManager db, Context context) {
        Label title = new Label("Stats");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        User user = context.getUser();
        Game game = context.getGame();
        GameDriver driver = context.getDriver();

        Label userLabel = new Label("User: " + user.getName());
        userLabel.setStyle("-fx-font-size: 14px;");

        HBox grandmaRow = createStatRow(
                "/images/Grandma.png",
                "You have " + game.getNumGrandmas() + " grandmas"
        );

        HBox factoryRow = createStatRow(
                "/images/Factory.png",
                "You have " + game.getNumFactories() + " factories"
        );

        HBox wizardRow = createStatRow(
                "/images/Wizard.jpeg",
                "You have " + game.getNumWizards() + " wizards"
        );

        int cps = (int) driver.calculateCps();

        Label cpsLabel = new Label("Your Cookie Power is " + cps);
        cpsLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label titleLabel = new Label("You are a Cookie " + getTitle(game.getCookies()));
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        VBox bottomBox = new VBox(12, cpsLabel, titleLabel);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(16, 0, 0, 0));

        VBox root = new VBox(
                8,
                title,
                userLabel,
                grandmaRow,
                factoryRow,
                wizardRow,
                bottomBox
        );

        root.setPadding(new Insets(10));
        root.setAlignment(Pos.TOP_CENTER);

        return new Scene(root, 640, 480);
    }

    private static HBox createStatRow(String imagePath, String text) {
        ImageView imageView = new ImageView(
                new Image(StatsScene.class.getResource(imagePath).toExternalForm())
        );

        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        imageView.setPreserveRatio(true);

        VBox textBox = new VBox();
        textBox.setPadding(new Insets(6));
        textBox.setPrefHeight(70);
        textBox.setMaxHeight(70);
        textBox.setAlignment(Pos.CENTER_LEFT);
        textBox.setStyle(
                "-fx-background-color: #eaf4ff;" +
                        "-fx-border-color: #7ec8ff;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;"
        );

        Label statLabel = new Label(text);
        statLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        textBox.getChildren().add(statLabel);

        HBox row = new HBox(12, imageView, textBox);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(2));
        row.setPrefWidth(540);

        textBox.prefWidthProperty().bind(row.widthProperty().subtract(82));

        return row;
    }

    private static String getTitle(long cookies) {
        if (cookies >= 1000) return "God";
        if (cookies >= 750) return "Devil";
        if (cookies >= 500) return "Baker";
        if (cookies >= 400) return "Master";
        if (cookies >= 300) return "Apprentice";
        if (cookies >= 200) return "Journeyman";
        if (cookies >= 100) return "Rookie";
        return "Cadet";
    }
}