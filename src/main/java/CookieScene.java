import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import model.Game;

import java.awt.*;

public class CookieScene {


    public static Scene create(Stage stage, DatabaseManager db, Game game1) {


        Text counterText = new Text("Score: 0");
        counterText.getStyleClass().add("bold-text");

        Image cookie = new Image(CookieScene.class.getResource("/images/cookie_1.jfif").toExternalForm());
        ImageView cookieView = new ImageView(cookie);
        cookieView.setFitHeight(100);
        cookieView.setFitWidth(100);

        Button cookieButton = new Button();
        cookieButton.setGraphic(cookieView);
        cookieButton.setStyle("-fx-background-color: transparent;");
        cookieButton.setOnAction(e -> {
            //db.updateGame();
            game1.setCookies(game1.getCookies() + 1);
            counterText.setText("Score: " + game1.getCookies());
        });


        BorderPane root = new BorderPane();
        root.getStylesheets().add(
                FileScreen.class.getResource("/style.css").toExternalForm()
        );

        root.setBottom(NavBar.create(SceneType.COOKIE));

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(cookieButton,counterText);
        vBox.setAlignment(Pos.CENTER);

        root.setCenter(vBox);

        return new Scene(root, 640, 480);
    }

}

