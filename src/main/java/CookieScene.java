import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CookieScene {

    private static int count = 0;

    public static Scene create(Stage stage) {
        Text counterText = new Text("Score: 0");
        Button plusOne = new Button("+1");
        Button plusTen = new Button("+10");
        Button timesTwo = new Button("X2");

        plusOne.setOnAction(e -> {
            count++;
            counterText.setText("Score: " + count);
        });

        plusTen.setOnAction(e -> {
            count+=10;
            counterText.setText("Score: " + count);
        });

        timesTwo.setOnAction(e -> {
            count*=2;
            counterText.setText("Score: " + count);
        });

        VBox root = new VBox(10);
        HBox hBox = new HBox(10);
        root.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(plusOne, plusTen, timesTwo);
        root.getChildren().addAll(counterText, hBox);

        return new Scene(root, 640, 480);
    }

}

