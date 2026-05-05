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



    public static Scene create(Stage stage, DatabaseManager db) {


        Text text1 = new Text("File 1");
        Text text2 = new Text("File 2");
        Text text3 = new Text("File 3");


        HBox file1 = new HBox();
        file1.setAlignment(Pos.CENTER);
        file1.setPrefSize(100,100);
        file1.getStyleClass().addAll("hbox-bordered", "hover-box");
        file1.getChildren().add(text1);
        file1.setOnMouseClicked(e -> {
            System.out.println("File 1 clicked!");
        });

        HBox file2 = new HBox();
        file2.setAlignment(Pos.CENTER);
        file2.setPrefSize(100,100);
        file2.getStyleClass().addAll("hbox-bordered", "hover-box");
        file2.getChildren().add(text2);
        file2.setOnMouseClicked(e -> {
            System.out.println("File 2 clicked!");
        });


        HBox file3 = new HBox();
        file3.setAlignment(Pos.CENTER);
        file3.setPrefSize(100,100);
        file3.getStyleClass().addAll("hbox-bordered", "hover-box");
        file3.getChildren().add(text3);
        file3.setOnMouseClicked(e -> {
            System.out.println("File 3 clicked!");
        });



        BorderPane root = new BorderPane();

        root.getStylesheets().add(
                FileScreen.class.getResource("/style.css").toExternalForm()
        );

        VBox filesBox = new VBox(10);
        filesBox.setAlignment(Pos.CENTER);

        filesBox.getChildren().addAll(file1, file2, file3);

        root.setCenter(filesBox);
        root.setBottom(NavBar.create(SceneType.FILE));

        return new Scene(root, 640, 480);
    }

}
