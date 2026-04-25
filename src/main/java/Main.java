import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private DatabaseManager db;

    @Override
    public void start(Stage stage) {
        db = new DatabaseManager();

        SceneManager.init(stage, db);
        stage.setTitle("Cookie Clicker");
        SceneManager.getInstance().navigateTo(SceneType.MAIN);
        stage.show();
    }

    @Override
    public void stop() {
        //DatabaseManager.getInstance().close();
        if (db != null) db.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}