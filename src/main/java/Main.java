import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private DatabaseManager db;

    @Override
    public void start(Stage stage) {
        db = new DatabaseManager();


        SaveManager.init(db);
        SceneManager.init(stage, db);
        stage.setTitle("Cookie Clicker");
        SceneManager.getInstance().navigateTo(SceneType.MENU); // changed from SceneType.LOGIN
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