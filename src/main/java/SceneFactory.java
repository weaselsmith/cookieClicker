import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SceneFactory {
    public static Scene create(SceneType type, Stage stage) {
        return switch (type) {
            case MAIN -> buildMainScene(stage);
            case DASHBOARD -> buildDashboardScene(stage);
            case ADD_ITEM -> buildAddItemScene(stage);
            case COOKIE -> CookieScene.create(stage);
        };
    }
    private static Scene buildMainScene(Stage stage) {
        Button loginBtn = new Button("Switch to Cookie Scene");
        loginBtn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.COOKIE)
        );
        return new Scene(new StackPane(loginBtn), 640, 480);
    }

    private static Scene buildCookieScene(Stage stage) {
        Button btn = new  Button("Currently on Cookie battle scene");
        System.out.println("Hello!");

        btn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.MAIN)
        );

        return new Scene(new StackPane(btn), 640, 480);
    }

    private static Scene buildDashboardScene(Stage stage) {
        Button btn = new Button("Dashboard");

        btn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.ADD_ITEM)
        );

        return new Scene(new StackPane(btn), 640, 480);
    }

    private static Scene buildAddItemScene(Stage stage) {

        Button saveBtn = new Button("Save");

        saveBtn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.DASHBOARD)
        );

        return new Scene(new StackPane(saveBtn), 640, 480);
    }
}