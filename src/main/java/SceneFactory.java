import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SceneFactory {
    public static Scene create(SceneType type, Stage stage, DatabaseManager db) {
        return switch (type) {
            case MAIN -> buildMainScene(stage, db);
            case DASHBOARD -> buildDashboardScene(stage, db);
            case ADD_ITEM -> buildAddItemScene(stage, db);
            case DB_TEST -> buildDBTestScene(stage, db);
        };
    }
    private static Scene buildMainScene(Stage stage, DatabaseManager db) {
        Button loginBtn = new Button("Log In");
        loginBtn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.DASHBOARD)
        );
        return new Scene(new StackPane(loginBtn), 640, 480);
    }

    private static Scene buildDashboardScene(Stage stage, DatabaseManager db) {
        Button btn = new Button("Dashboard");

        btn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.ADD_ITEM)
        );

        return new Scene(new StackPane(btn), 640, 480);
    }

    private static Scene buildAddItemScene(Stage stage, DatabaseManager db) {


        Button saveBtn = new Button("Save");

        saveBtn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.DASHBOARD)
        );

        return new Scene(new StackPane(saveBtn), 640, 480);
    }

    private static Scene buildDBTestScene(Stage stage, DatabaseManager db) {
        Button testBtn = new Button("Test Database");

        testBtn.setOnAction(e -> {
            db.addUpgrade("Minty Cookie", 50);
            db.addUpgrade("Mega Grandma", 1000);

            SceneManager.getInstance().navigateTo(SceneType.DASHBOARD);
        });

        return new Scene(new StackPane(testBtn), 640, 480);
    }
}