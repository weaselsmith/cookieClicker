import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class LoginScreenController {

    //private final DatabaseManager db = DatabaseManager.getInstance();
    //TODO commented this cause I was writing it before it's implemented

    public Scene buildScene(){
        Label title = new Label("Login Page");
        TextField usernameField = new TextField();
        Label usernameLabel = new Label("Username:");
        String inputtedUsername;

        PasswordField passwordField = new PasswordField();
        Label passwordLabel = new Label("Password:");
        String inputtedPassword;

        HBox usernameRow = new HBox(10, usernameLabel, usernameField);
        HBox passwordRow = new HBox(10, passwordLabel, passwordField);

        Button loginButton = new Button("Login!");
        TextArea newUser = new TextArea("New user? Sign up below!");
        Button signUp = new Button("Sign up!");

        usernameField.setPromptText("Type username here");
        passwordField.setPromptText("******");

        VBox root = new VBox(10, title, usernameRow, passwordRow, loginButton, newUser, signUp);
        root.setPadding(new Insets(16));

        //TODO loginButton.setOnAction(e -> SceneManager.getInstance().navigateTo(SceneType.{FIX THIS SPOT HERE}));)
        //TODO signUpButton.setOnAction(e -> SceneManager.getInstance().navigateTo(SceneType.{FIX THIS SPOT HERE}));)

        //refresh();

        return new Scene(root, 640, 480);

    }

    public boolean isValidInput(String username, String password) {
        if (username == null || username.trim().isEmpty()){
            return false;
        }
        if (password == null || password.length() < 6){
            return false;
        }
        return true;
    }

}
