package Scenes;

import com.project.Cashier;
import com.project.Economist;
import com.project.DataBase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenu
{
    static Stage window;
    static Scene scene;
    static String userType;

    static Label titleLabel;
    static TextField usernameField;
    static PasswordField passwordField;
    static Label incorrectMessage;

    static Button loginButton;
    static Button exitButton;

    public static void showScene(Stage window, String userType) throws FileNotFoundException
    {
        MainMenu.window = window;
        MainMenu.userType = userType;

        Image image = new Image(new FileInputStream("resources/Images/logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(240);
        imageView.setPreserveRatio(true);

        titleLabel = new Label("Login as " + userType);
        titleLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(5));
        titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");

        usernameField = new TextField();
        usernameField.setPromptText("username");
        usernameField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");

        passwordField = new PasswordField();
        passwordField.setPromptText("password");
        passwordField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");

        incorrectMessage = new Label("");
        incorrectMessage.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        incorrectMessage.setAlignment(Pos.CENTER);
        incorrectMessage.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");

        loginButton = new Button("Login");
        loginButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        loginButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");

        loginButton.setOnAction(actionEvent -> {
            try { login(usernameField.getText(), passwordField.getText()); }
            catch (FileNotFoundException e) { e.printStackTrace(); }
        });

        exitButton = new Button("Back");
        exitButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        exitButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");

        exitButton.setOnAction(actionEvent -> {
            try { PreLoginScene.showScene(window); }
            catch (FileNotFoundException e) { e.printStackTrace(); }
        });

        VBox inputVBox = new VBox(10);
        inputVBox.setPadding(new Insets(10, 0, 0, 0));
        inputVBox.getChildren().addAll(titleLabel, usernameField, passwordField, incorrectMessage);

        VBox buttonsVBox = new VBox(10);
        buttonsVBox.getChildren().addAll(loginButton, exitButton);
        buttonsVBox.setPadding(new Insets(0, 50, 0 ,50));

        VBox layout = new VBox(35);
        layout.setPadding(new Insets(30, 40, 10, 40));
        layout.getChildren().addAll(imageView, inputVBox , buttonsVBox);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 320, 460);
        MainMenu.window.setScene(scene);
    }

    static void login(String username, String password) throws FileNotFoundException
    {
        if(userType.equals("Administrator"))
        {
            if(username.equals("admin"))
                if(password.equals("admin123"))
                {
                    new AdminScene().showScene(window);
                    return;
                }
        }
        else
        {
            for(Cashier c : DataBase.getCashiers())
                if(username.equals(c.getUsername()))
                    if(password.equals(c.getPassword()))
                    {
                        new CashierScene().showScene(c, window);
                        return;
                    }

            for(Economist e : DataBase.getEconomists())
                if(username.equals(e.getUsername()))
                    if(password.equals(e.getPassword()))
                    {
                        new EconomistScene().showScene(e, window);
                        return;
                    }
        }

        incorrectMessage.setText("Incorrect username or password.");
    }
}
