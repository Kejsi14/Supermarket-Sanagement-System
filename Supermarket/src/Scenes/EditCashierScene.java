package Scenes;

import com.project.Cashier;
import com.project.DataBase;
import com.project.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditCashierScene
{
    Stage window;
    Label titleLabel;
    Button cancelButton;
    Scene scene;
    VBox layout;

    Button chUsernameButton;
    Button chPasswordButton;
    Button chPhoneNrButton;
    Button chEmailButton;
    Button chSalaryButton;
    VBox buttonsVBox;

    String username;
    Cashier cashier;

    public void showScene(User user)
    {
        username = user.getUsername();
        getUser();

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        titleLabel = new Label("Edit Cashier " + cashier.getName());
        titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");

        chUsernameButton = new Button("Change username");
        chUsernameButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chUsernameButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        chUsernameButton.setOnAction(actionEvent -> {
            String newUsername = new GetInputScene().showScene("username");
            changeUsername(newUsername);
        });

        chPasswordButton = new Button("Change password");
        chPasswordButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chPasswordButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        chPasswordButton.setOnAction(actionEvent -> {
            String newPassword = new ChangePassScene().showScene(cashier.getPassword());
            changePassword(newPassword);
        });

        chPhoneNrButton = new Button("Change phone number");
        chPhoneNrButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chPhoneNrButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        chPhoneNrButton.setOnAction(actionEvent -> {
            String newPhoneNr = new GetInputScene().showScene("phone number");
            changePhoneNr(newPhoneNr);
        });

        chEmailButton = new Button("Change email");
        chEmailButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chEmailButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        chEmailButton.setOnAction(actionEvent -> {
            String newEmail = new GetInputScene().showScene("email");
            changeEmail(newEmail);
        });

        chSalaryButton = new Button("Change salary");
        chSalaryButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        chSalaryButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        chSalaryButton.setOnAction(actionEvent -> {
            String newSalary = new GetInputScene().showScene("salary");
            changeSalary(newSalary);
        });

        buttonsVBox = new VBox(10);
        buttonsVBox.getChildren().addAll(chUsernameButton, chPasswordButton, chPhoneNrButton, chEmailButton, chSalaryButton);

        cancelButton = new Button("Exit");
        cancelButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        cancelButton.setOnAction(actionEvent -> window.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(titleLabel, spacer, cancelButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, buttonsVBox);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 380, 280);
        window.setScene(scene);
        window.showAndWait();
    }

    void changeUsername(String newUsername)
    {
        if(!newUsername.equals(""))
            cashier.setUsername(newUsername);
    }

    void changePassword(String newPassword)
    {
        if(!newPassword.equals(""))
            cashier.setPassword(newPassword);
    }

    void changePhoneNr(String newPhoneNr)
    {
        if(!newPhoneNr.equals(""))
            cashier.setPhoneNumber(newPhoneNr);
    }

    void changeEmail(String newEmail)
    {
        if(!newEmail.equals(""))
            cashier.setEmail(newEmail);
    }

    void changeSalary(String newSalary)
    {
        if(!newSalary.equals(""))
            cashier.setSalary(Double.parseDouble(newSalary));
    }

    void getUser()
    {
        cashier = new Cashier();

        for(Cashier c : DataBase.getCashiers())
            if(username.equals(c.getUsername()))
            {
                cashier = c;
                return;
            }
    }
}
