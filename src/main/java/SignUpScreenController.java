import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class SignUpScreenController {

    public static Scene buildScene(DatabaseManager db){
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

        signUpButton.setOnAction(e -> {
            String inputtedUsername = newName.getText();
            String inputtedPassword = newPassword.getText();
            String inputtedConfirm = confirmPassword.getText();

            if (isValidInput(inputtedUsername, inputtedPassword)) {
                if (inputtedPassword.equals(inputtedConfirm) /* && inputtedUsername not in db already*/){
                    db.addUser(inputtedUsername);
                    SceneManager.getInstance().navigateTo(SceneType.LOGIN);
                    //and pass in user id
                }

            }
        });
        //refresh();

        return new Scene(root, 640, 480);

    }

    public static boolean isValidInput(String username, String password) {
        if (username == null || username.trim().isEmpty() || username.length() < 3){
            return false;
        }
        if (password == null || password.length() < 6){
            return false;
        }
        return true;
    }


}
