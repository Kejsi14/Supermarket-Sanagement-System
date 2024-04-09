package Scenes;

import com.project.DataBase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PreLoginScene
{
    static Scene scene;
    static Label titleLabel;
    static Button adminButton;
    static Button employeeButton;
    static Button exitButton;

    public static void showScene(Stage window) throws FileNotFoundException
    {
        MainMenu.window = window;
        Image image = new Image(new FileInputStream("resources/Images/logo.png"));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(240);
        imageView.setPreserveRatio(true);

        titleLabel = new Label("Login as");
        titleLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(5));
        titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");

        adminButton = new Button("Administrator");
        adminButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        adminButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");

        adminButton.setOnAction(actionEvent -> {
            try { MainMenu.showScene(window, "Administrator"); }
            catch (FileNotFoundException e) { e.printStackTrace(); }
        });

        employeeButton = new Button("Employee");
        employeeButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        employeeButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");

        employeeButton.setOnAction(actionEvent -> {
            try { MainMenu.showScene(window, "Employee"); }
            catch (FileNotFoundException e) { e.printStackTrace(); }
        });

        exitButton = new Button("Exit");
        exitButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        exitButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        exitButton.setOnAction(actionEvent -> {
            DataBase.save();
            window.close();
        });

        VBox buttonsVBox = new VBox(20);
        buttonsVBox.getChildren().addAll(titleLabel, adminButton, employeeButton, exitButton);
        buttonsVBox.setPadding(new Insets(50, 50, 0 ,50));

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30, 40, 10, 40));
        layout.getChildren().addAll(imageView , buttonsVBox);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 320, 420);
        MainMenu.window.setScene(scene);
    }
}
