import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Game;

public class FileScreen {

    public static Scene create(Stage stage, DatabaseManager db, Context context) {

        Text text1 = new Text("File 1");
        text1.getStyleClass().add("bold-text");
        Text text2 = new Text("File 2");
        text2.getStyleClass().add("bold-text");
        Text text3 = new Text("File 3");
        text3.getStyleClass().add("bold-text");

        HBox file1 = createFileBox("File 1", 1, db, context);
        HBox file2 = createFileBox("File 2", 2, db, context);
        HBox file3 = createFileBox("File 3", 3, db, context);

        VBox filesBox = new VBox(10, file1, file2, file3);
        filesBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane(filesBox);
        root.setBottom(NavBar.create(SceneType.FILE));
        root.getStylesheets().add(
                FileScreen.class.getResource("/style.css").toExternalForm()
        );

        return new Scene(root, 640, 480);
    }

    private static HBox createFileBox(String label, int slot, DatabaseManager db, Context context) {
        Text text = new Text(label);
        text.getStyleClass().add("bold-text");

        HBox fileBox = new HBox(text);
        fileBox.setAlignment(Pos.CENTER);
        fileBox.setPrefSize(100, 100);
        fileBox.getStyleClass().addAll("hbox-bordered", "hover-box");

        fileBox.setOnMouseClicked(e -> {
            System.out.println(label + " clicked!");

            Game game = db.getGameByUserAndSlot(context.getUser().getId(), slot);

            if (game == null) {
                db.addGame(context.getUser().getId(), slot);
                game = db.getLastGame();
                System.out.println("Added game for user_id: " + context.getUser().getId() + " in slot " + slot);
            }

            if (game.getGameId() <= 0) {
                System.err.println("Invalid game id: " + game.getGameId() + " for slot " + slot);
                return;
            }

            System.out.println("Loaded or created game id: " + game.getGameId() + " (slot " + slot + ")");

            if (context.getDriver() != null) {
                context.getDriver().stopAutoCookies();
            }

            GameDriver driver = new GameDriver(game);
            context.setGame(game);
            context.setDriver(driver);

            SceneManager.getInstance().navigateTo(SceneType.COOKIE);
        });

        return fileBox;
    }
}
