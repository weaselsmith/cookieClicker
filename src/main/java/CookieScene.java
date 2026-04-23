import javafx.geometry.Pos; //?
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CookieScene {

    private static int count = 0; // belongs to this scene

    public static Scene create(Stage stage) {
        Text counterText = new Text("Score: 0");
        Button button = new Button("+1");

        button.setOnAction(e -> {
            count++;
            counterText.setText("Score: " + count);
        });

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(counterText, button);

        return new Scene(root, 640, 480);
    }

}

