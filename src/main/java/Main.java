import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private DatabaseManager db;
    private Context context;

    @Override
    public void start(Stage stage) {
        db = new DatabaseManager();
        context = new Context();


        SaveManager.init(db);
        SceneManager.init(stage, db, context);
        stage.setTitle("Cookie Clicker");
        SceneManager.getInstance().navigateTo(SceneType.LOGIN);
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