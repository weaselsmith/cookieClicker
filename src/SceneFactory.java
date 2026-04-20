

public class SceneFactory {
    public static Scene create(SceneType type, Stage stage) {
        return switch (type) {
            case MAIN -> buildMainScene(stage);
            case DASHBOARD -> buildDashboardScene(stage);
            case ADD_ITEM -> buildAddItemScene(stage);
        };
    }

    private static Scene buildMainScene(Stage stage) {
        Button loginBtn = new Button("Log In");
        loginBtn.setOnAction(e ->
                SceneManager.getInstance().navigateTo(SceneType.DASHBOARD)
        );
        return new Scene(new StackPane(loginBtn), 640, 480);
    }

    private static Scene buildAddItemScene(Stage stage) {
// ... layout only ...
        saveBtn.setOnAction(e -> {
            db.insertItem(/* ... */);
            SceneManager.getInstance().navigateTo(SceneType.DASHBOARD);
        });
        return new Scene(root, 640, 480);
    }
}