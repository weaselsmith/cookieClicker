import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class SignUpScreenController {

    //private final DatabaseManager db = DatabaseManager.getInstance();
    //TODO commented this cause I was writing it before it's implemented

    public Scene buildScene(){
        Button goBackButton = new Button("Back to Login?");
        Label title = new Label("Sign Up Page!");

        TextField newName = new TextField();
        newName.setPromptText("Type username here");
        Label nameLabel = new Label("Enter new Username:");
        HBox nameBox = new HBox(nameLabel, newName);

        PasswordField newPassword = new PasswordField();
        newPassword.setPromptText("******");
        Label newPasswordLabel = new Label("Enter new password:");
        HBox passwordBox = new HBox(newPasswordLabel, newPassword);

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("******");
        Label confirmPasswordLabel = new Label("Confirm new password:");
        HBox confirmBox = new HBox(confirmPasswordLabel, confirmPassword);

        Button signUpButton = new Button("Sign Up!");

        VBox root = new VBox(10, goBackButton, title, nameBox, passwordBox, confirmBox, signUpButton);
        root.setPadding(new Insets(16));

        //TODO signUpButton.setOnAction(e -> SceneManager.getInstance().navigateTo(SceneType.{FIX THIS SPOT HERE}));)

        //refresh();
        //TODO wasn't sure if this is correct here or not

        return new Scene(root, 640, 480);

    }


}
