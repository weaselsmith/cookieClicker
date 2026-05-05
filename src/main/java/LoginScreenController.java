import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class LoginScreenController {

    public static Scene buildScene(DatabaseManager db, Context context){
        Label title = new Label("Login Page");
        TextField usernameField = new TextField();
        Label usernameLabel = new Label("Username:");
        //String inputtedUsername;

        PasswordField passwordField = new PasswordField();
        Label passwordLabel = new Label("Password:");
        //String inputtedPassword;

        HBox usernameRow = new HBox(10, usernameLabel, usernameField);
        HBox passwordRow = new HBox(10, passwordLabel, passwordField);

        Button loginButton = new Button("Login!");
        TextArea newUser = new TextArea("New user? Sign up below!");
        Button signUpButton = new Button("Sign up!");

        usernameField.setPromptText("Type username here");
        passwordField.setPromptText("******");

        VBox root = new VBox(10, title, usernameRow, passwordRow, loginButton, newUser, signUpButton);
        root.setPadding(new Insets(16));

        loginButton.setOnAction(e -> {
            String inputtedUsername = usernameField.getText();
            String inputtedPassword = passwordField.getText();
            if (isValidInput(inputtedUsername, inputtedPassword)) {
                //TODO check if login info is successful
                //if (login info is successful) {
                //     pass in user info
                //     SceneManager.getInstance().navigateTo(SceneType.COOKIE);
                //}
                //else {
                //      title.setText("Invalid login information, try again");
                //}
            }
            else{
                title.setText("Please make sure you input a valid username and password.");
            }
        });

        //signUpButton.setOnAction(e -> SceneManager.getInstance().navigateTo(SceneType.SIGNUP));

        return new Scene(root, 640, 480);

    }

    public static boolean isValidInput(String username, String password) {
        if (username == null || username.trim().isEmpty()){
            return false;
        }
        if (password == null || password.length() < 6){
            return false;
        }
        return true;
    }

}
