import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;

public class CookieScene {

    private static int count = 0;

    public static Scene create(Stage stage) {
        Text counterText = new Text("Score: 0");
//        Button plusOne = new Button("+1");
//        Button plusTen = new Button("+10");
//        Button timesTwo = new Button("X2");

        Image cookie = new Image(CookieScene.class.getResource("/images/cookie_1.jfif").toExternalForm());
        ImageView cookieView = new ImageView(cookie);
        cookieView.setFitHeight(100);
        cookieView.setFitWidth(100);

        Button cookieButton = new Button();
        cookieButton.setGraphic(cookieView);
        cookieButton.setStyle("-fx-background-color: transparent;");
        cookieButton.setOnAction(e -> {
            counterText.setText("Score: " + ++count);
        });


//        plusOne.setOnAction(e -> {
//            count++;
//            counterText.setText("Score: " + count);
//        });
//
//        plusTen.setOnAction(e -> {
//            count+=10;
//            counterText.setText("Score: " + count);
//        });
//
//        timesTwo.setOnAction(e -> {
//            count*=2;
//            counterText.setText("Score: " + count);
//        });

        VBox root = new VBox(10);
        HBox hBox = new HBox(10);
        root.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);

//        hBox.getChildren().addAll(plusOne, plusTen, timesTwo);
        root.getChildren().addAll(cookieButton, counterText, hBox);

        return new Scene(root, 640, 480);
    }

}

