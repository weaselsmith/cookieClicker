import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import model.Game;

import java.awt.*;

import static java.awt.SystemColor.text;

public class FileScreen {



    public static Scene create(Stage stage){
        Button backButton = new Button("Back to LOGIN");
        backButton.setOnAction(e -> {
            SceneManager.getInstance().navigateTo(SceneType.MAIN);
        });


        Text text1 = new Text("File 1");
        Text text2 = new Text("File 2");
        Text text3 = new Text("File 3");


        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getStylesheets().add(
                FileScreen.class.getResource("/style.css").toExternalForm()
        );

        HBox file1 = new HBox();
        file1.setAlignment(Pos.CENTER);
        file1.setPrefSize(100,100);
        file1.getStyleClass().add("hbox-bordered");
        file1.getChildren().add(text1);

        HBox file2 = new HBox();
        file2.setAlignment(Pos.CENTER);
        file2.setPrefSize(100,100);
        file2.getStyleClass().add("hbox-bordered");
        file2.getChildren().add(text2);


        HBox file3 = new HBox();
        file3.setAlignment(Pos.CENTER);
        file3.setPrefSize(100,100);
        file3.getStyleClass().add("hbox-bordered");
        file3.getChildren().add(text3);



        root.getChildren().addAll(backButton, file1,file2,file3);
        return new Scene(root, 640, 480);
    }

}
