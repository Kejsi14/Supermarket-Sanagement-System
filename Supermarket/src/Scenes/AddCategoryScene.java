package Scenes;

import com.project.DataBase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddCategoryScene
{
    Stage window;
    Label instructionLabel;
    TextField categoryTxtField;
    Label errorLabel;
    Button addButton;
    Button exitButton;
    HBox buttonsHBox;
    VBox layout;

    public void showScene()
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        instructionLabel = new Label("Enter category name!");
        instructionLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");
        instructionLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        instructionLabel.setAlignment(Pos.CENTER);

        categoryTxtField = new TextField();
        categoryTxtField.setPromptText("Category");
        categoryTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");

        errorLabel = new Label("");
        errorLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");

        VBox inputVBox = new VBox(5);
        inputVBox.getChildren().addAll(categoryTxtField, errorLabel);

        addButton = new Button("Add");
        addButton.setPrefWidth(96);
        addButton.setStyle("-fx-background-color: #108010; -fx-text-fill: #eeeeee;");
        addButton.setOnAction(actionEvent -> add());

        exitButton = new Button("Cancel");
        exitButton.setPrefWidth(96);
        exitButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        exitButton.setOnAction(actionEvent -> window.close());

        buttonsHBox = new HBox(30);
        buttonsHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.getChildren().addAll(exitButton, addButton);

        layout = new VBox(30);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(instructionLabel, inputVBox, buttonsHBox);
        layout.setStyle("-fx-background-color: #cccccc;");

        Scene scene = new Scene(layout, 480, 200);
        window.setScene(scene);
        window.showAndWait();
    }

    void add()
    {
        if(categoryTxtField.getText().isEmpty())
            errorLabel.setText("The text field is empty.");
        else
        {
            errorLabel.setText("");
            DataBase.getCategories().add(categoryTxtField.getText());
            window.close();
        }
    }
}
