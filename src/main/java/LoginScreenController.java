import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class LoginScreenController {

    //private final DatabaseManager db = DatabaseManager.getInstance();
    //TODO commented this cause I was writing it before it's implemented

    public Scene buildScene(){
        Label title = new Label("Login Page");
        TextField usernameField = new TextField();

        String inputtedUsername;

        PasswordField passwordField = new PasswordField();

        String inputtedPassword;

        Button loginButton = new Button("Login!");
        TextArea newUser = new TextArea("New user? Sign up below!");
        Button signUp = new Button("Sign up!");

        VBox root = new VBox(10, title, usernameField, passwordField, loginButton, newUser, signUp);
        root.setPadding(new Insets(16));

        //TODO loginButton.setOnAction(e -> SceneManager.getInstance().navigateTo(SceneType.{FIX THIS SPOT HERE}));)
        //TODO signUpButton.setOnAction(e -> SceneManager.getInstance().navigateTo(SceneType.{FIX THIS SPOT HERE}));)

        //refresh();
        //TODO wasn't sure if this is correct here or not

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
