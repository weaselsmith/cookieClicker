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

    private static int count = 0;

    public static Scene create(Stage stage, DatabaseManager db) {

        Text counterText = new Text("Score: 0");

        Image cookie = new Image(CookieScene.class.getResource("/images/cookie_1.jfif").toExternalForm());
        ImageView cookieView = new ImageView(cookie);
        cookieView.setFitHeight(100);
        cookieView.setFitWidth(100);

        Button cookieButton = new Button();
        cookieButton.setGraphic(cookieView);
        cookieButton.setStyle("-fx-background-color: transparent;");
        cookieButton.setOnAction(e -> {
            //db.updateGame();
            counterText.setText("Score: " + ++count);
        });


        BorderPane root = new BorderPane();

        root.setBottom(NavBar.create(SceneType.COOKIE));

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(counterText, cookieButton);
        hBox.setAlignment(Pos.CENTER);

        root.setCenter(hBox);

        return new Scene(root, 640, 480);
    }

}

