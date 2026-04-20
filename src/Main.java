public class Main {

    @Override
    public void start(Stage stage) {
        SceneManager.init(stage);
        stage.setTitle("Todo App");
        SceneManager.getInstance().navigateTo(SceneType.MAIN);
        stage.show();
    }
    @Override
    public void stop() {
        DatabaseManager.getInstance().close();
    }
}
