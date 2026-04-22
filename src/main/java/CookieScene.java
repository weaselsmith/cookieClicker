import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CookieScene {
    public static Scene create(Stage stage) {
        VBox root = new VBox();
        Button btn = new Button("New Cookie Scene!");

        btn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.MAIN)
        );

        root.getChildren().add(btn);
        return new Scene(new StackPane(btn), 640, 480);
    }

}

