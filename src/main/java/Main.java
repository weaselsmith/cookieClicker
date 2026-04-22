import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager.init(stage);
        stage.setTitle("Cookie Clicker");
        SceneManager.getInstance().navigateTo(SceneType.MAIN);
        stage.show();
    }
    @Override
    public void stop() {
        //DatabaseManager.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}