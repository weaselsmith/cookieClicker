import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class SignUpScreenController {

    //private final DatabaseManager db = DatabaseManager.getInstance();
    //TODO commented this cause I was writing it before it's implemented

    public Scene buildScene(){
        Label title = new Label("Sign Up Page!");
        TextField newName = new TextField();
        PasswordField newPassword = new PasswordField();
        Button signUpButton = new Button("Sign Up!");

        VBox root = new VBox(10, title, newName, newPassword, signUpButton);
        root.setPadding(new Insets(16));

        //TODO signUpButton.setOnAction(e -> SceneManager.getInstance().navigateTo(SceneType.{FIX THIS SPOT HERE}));)

        //refresh();
        //TODO wasn't sure if this is correct here or not

        return new Scene(root, 640, 480);

    }


}
