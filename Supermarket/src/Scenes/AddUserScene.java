package Scenes;

import com.project.*;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddUserScene
{
    Stage window;
    Label titleLabel;
    Button cancelButton;
    Scene scene;
    VBox layout;

    TextField usernameTxtField;
    Label usernameErrLabel;
    PasswordField passPassField;
    Label passErrLabel;
    PasswordField repeatPassField;
    Label repeatPassErrLabel;
    TextField nameTxtField;
    Label nameErrLabel;
    ChoiceBox<String> roleChoiceBox;
    Label roleErrLabel;
    TextField phoneNrTxtField;
    Label phoneNrErrLabel;

    Label yearLabel;
    ChoiceBox<String> yearChoiceBox;
    Label monthLabel;
    ChoiceBox<String> monthChoiceBox;
    Label dayLabel;
    ChoiceBox<String> dayChoiceBox;
    Label bDayErrLabel;
    VBox yearVBox;
    VBox monthVBok;
    VBox dayVBox;

    TextField emailTxtField;
    Label emailErrLabel;
    TextField salaryTxtField;
    Label salaryErrLabel;
    TextField idCardNrTxtField;
    Label idCardNrErrLabel;

    VBox usernameHBox;
    VBox passwordHBox;
    VBox repeatPassHBox;
    VBox nameHBox;
    VBox roleHBox;
    VBox phoneNumberHBox;
    HBox birthdayHBox;
    VBox emailHBox;
    VBox salaryHBox;
    VBox idCardNrHBox;

    Button addButton;

    public void showScene()
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        titleLabel = new Label("Add User");
        titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");
        setFields();

        monthChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox, monthChoiceBox, yearChoiceBox, 1950, 2000));
        yearChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                        DateChoiceBox.addDaysInDayChBox(dayChoiceBox, monthChoiceBox, yearChoiceBox, 1950, 2000));

        addButton = new Button("Add");
        addButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        addButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addButton.setOnAction(actionEvent -> addUser());

        cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        cancelButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cancelButton.setOnAction(actionEvent -> window.close());

        VBox addButtonHBox = new VBox();
        addButtonHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addButtonHBox.setPadding(new Insets(0, 150, 0, 150));
        addButtonHBox.setAlignment(Pos.CENTER);
        addButtonHBox.getChildren().add(addButton);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setPadding(new Insets(0, 0, 15, 0));
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(titleLabel, spacer, cancelButton);

        layout = new VBox(16);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, usernameHBox, passwordHBox, repeatPassHBox, nameHBox, roleHBox,
                phoneNumberHBox, birthdayHBox, emailHBox, salaryHBox, idCardNrHBox, addButtonHBox);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 480, 720);
        window.setScene(scene);
        window.showAndWait();
    }

    void addUser()
    {
        String username = "";
        String password = "";
        String name = "";
        String role = "";
        String phoneNumber = "";
        int year = 0;
        int month = 0;
        int day = 0;
        String email = "";
        double salary = 0.0;
        String idCardNr = "";

        boolean areFieldsCorrect = true;

        // check username input
        if(!usernameTxtField.getText().isEmpty() && InputCheck.isUsernameValid(usernameTxtField.getText()))
        {
            if(InputCheck.doesUsernameExists(usernameTxtField.getText()))
            {
                areFieldsCorrect = false;
                usernameErrLabel.setText("This username already exists.");
            }
            else
            {
                username = usernameTxtField.getText();
                usernameErrLabel.setText("");
            }
        }
        else
        {
            areFieldsCorrect = false;
            usernameErrLabel.setText("The only values that are allowed are: letters, digits and - _ .");
        }

        // check password input
        if(!passPassField.getText().isEmpty() && passPassField.getText().length() >= 8)
        {
            password = passPassField.getText();
            passErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            passErrLabel.setText("Enter a password!");
        }

        // check repeat password input
        if(!repeatPassField.getText().equals(password))
        {
            areFieldsCorrect = false;
            repeatPassErrLabel.setText("Password doesn't match.");
        }
        else
            repeatPassErrLabel.setText("");

        // check name input
        if(!nameTxtField.getText().isEmpty() && InputCheck.isNameValid(nameTxtField.getText()))
        {
            name = nameTxtField.getText();
            nameErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            nameErrLabel.setText("Enter name!");
        }

        // check role input
        if(!roleChoiceBox.getValue().equals("----------"))
        {
            role = roleChoiceBox.getValue();
            roleErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            roleErrLabel.setText("Select a role!");
        }

        // check phoneNumberHBox input
        if(!phoneNrTxtField.getText().isEmpty() && InputCheck.isPhoneNrValid(phoneNrTxtField.getText()))
        {
            phoneNumber = phoneNrTxtField.getText();
            phoneNrErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            phoneNrErrLabel.setText("Enter 10 digits only!");
        }

        // check date
        if(!yearChoiceBox.getValue().equals("----------") && !monthChoiceBox.getValue().equals("----------") && !dayChoiceBox.getValue().equals("----------"))
        {
            year = Integer.parseInt(yearChoiceBox.getValue());
            month = Integer.parseInt(monthChoiceBox.getValue());
            day = Integer.parseInt(dayChoiceBox.getValue());
            bDayErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            bDayErrLabel.setText("Select Date of Birth!");
        }

        // check email input
        if(!emailTxtField.getText().isEmpty())
        {
            email = emailTxtField.getText();
            emailErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            emailErrLabel.setText("Enter email!");
        }

        // check salary input
        if(!salaryTxtField.getText().isEmpty() && InputCheck.isDouble(salaryTxtField.getText()))
        {
            salary = Double.parseDouble(salaryTxtField.getText());
            salaryErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            salaryErrLabel.setText("Enter salary!");
        }

        // check idCardNr input
        if(!idCardNrTxtField.getText().isEmpty() && idCardNrTxtField.getText().length() == 10)
        {
            idCardNr = idCardNrTxtField.getText();
            idCardNrErrLabel.setText("");
        }
        else
        {
            areFieldsCorrect = false;
            idCardNrErrLabel.setText("Enter ID Card Number!");
        }

        if(areFieldsCorrect)
        {
            if(role.equals("Cashier"))
            {
                DataBase.getCashiers().add(new Cashier());
                int addedCashierIndex = DataBase.getCashiers().size() - 1;
                Cashier addedUser = DataBase.getCashiers().get(addedCashierIndex);

                addedUser.setUsername(username);
                addedUser.setPassword(password);
                addedUser.setName(name);
                addedUser.setRole(role);
                addedUser.setPhoneNumber(phoneNumber);
                addedUser.setEmail(email);
                addedUser.setSalary(salary);
                addedUser.setIdCardNumber(idCardNr);
                addedUser.setBirthday(new Date(day, month, year));
            }

            if(role.equals("Economist"))
            {
                DataBase.getEconomists().add(new Economist());
                int addedCashierIndex = DataBase.getEconomists().size() - 1;
                Economist addedUser = DataBase.getEconomists().get(addedCashierIndex);

                addedUser.setUsername(username);
                addedUser.setPassword(password);
                addedUser.setName(name);
                addedUser.setRole(role);
                addedUser.setPhoneNumber(phoneNumber);
                addedUser.setEmail(email);
                addedUser.setSalary(salary);
                addedUser.setIdCardNumber(idCardNr);
                addedUser.setBirthday(new Date(day, month, year));
            }

            window.close();
        }
    }

    void setFields()
    {
        usernameTxtField = new TextField();
        usernameTxtField.setPromptText("Username");
        usernameTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        usernameErrLabel = new Label("");
        usernameErrLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        usernameHBox = new VBox();
        usernameHBox.getChildren().addAll(usernameTxtField, usernameErrLabel);

        passPassField = new PasswordField();
        passPassField.setPromptText("Password");
        passPassField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        passErrLabel = new Label("");
        passErrLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        passwordHBox = new VBox();
        passwordHBox.getChildren().addAll(passPassField, passErrLabel);

        repeatPassField = new PasswordField();
        repeatPassField.setPromptText("Confirm password");
        repeatPassField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        repeatPassErrLabel = new Label("");
        repeatPassErrLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        repeatPassHBox = new VBox();
        repeatPassHBox.getChildren().addAll(repeatPassField, repeatPassErrLabel);

        nameTxtField = new TextField();
        nameTxtField.setPromptText("Name");
        nameTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        nameErrLabel = new Label("");
        nameErrLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        nameHBox = new VBox();
        nameHBox.getChildren().addAll(nameTxtField, nameErrLabel);

        roleChoiceBox = new ChoiceBox<>();
        roleChoiceBox.getItems().addAll("----------", "Cashier", "Economist");
        roleChoiceBox.setValue("----------");
        roleChoiceBox.setMaxWidth(Double.MAX_VALUE);
        roleErrLabel = new Label("");
        roleErrLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        roleHBox = new VBox();
        roleHBox.getChildren().addAll(roleChoiceBox, roleErrLabel);

        phoneNrTxtField = new TextField();
        phoneNrTxtField.setPromptText("Phone number");
        phoneNrTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        phoneNrErrLabel = new Label("");
        phoneNrErrLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        phoneNumberHBox = new VBox();
        phoneNumberHBox.getChildren().addAll(phoneNrTxtField, phoneNrErrLabel);

        //----------------------------------------------------------------------
        yearLabel = new Label("Year");
        yearLabel.setMaxWidth(Double.MAX_VALUE);
        yearLabel.setAlignment(Pos.CENTER);
        yearLabel.setStyle("-fx-text-fill: #333333;");

        yearChoiceBox = new ChoiceBox<>();
        yearChoiceBox.getItems().add("----------");
        DateChoiceBox.addYearsInYearChBox(yearChoiceBox, 1950, 2002);
        yearChoiceBox.setValue("----------");
        yearChoiceBox.setMaxWidth(Double.MAX_VALUE);

        monthLabel = new Label("Month");
        monthLabel.setStyle("-fx-text-fill: #333333;");
        monthLabel.setMaxWidth(Double.MAX_VALUE);
        monthLabel.setAlignment(Pos.CENTER);

        monthChoiceBox = new ChoiceBox<>();
        monthChoiceBox.getItems().add("----------");
        DateChoiceBox.addMonthsInMonthChBox(monthChoiceBox);
        monthChoiceBox.setValue("----------");
        monthChoiceBox.setMaxWidth(Double.MAX_VALUE);

        dayLabel = new Label("Day");
        dayLabel.setStyle("-fx-text-fill: #333333;");
        dayLabel.setMaxWidth(Double.MAX_VALUE);
        dayLabel.setAlignment(Pos.CENTER);

        dayChoiceBox = new ChoiceBox<>();
        dayChoiceBox.getItems().add("----------");
        dayChoiceBox.setValue("----------");
        dayChoiceBox.setMaxWidth(Double.MAX_VALUE);

        bDayErrLabel = new Label("");
        bDayErrLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");

        yearVBox = new VBox(5);
        yearVBox.getChildren().addAll(yearChoiceBox, yearLabel);
        monthVBok = new VBox(5);
        monthVBok.getChildren().addAll(monthChoiceBox, monthLabel);
        dayVBox = new VBox(5);
        dayVBox.getChildren().addAll(dayChoiceBox, dayLabel);

        birthdayHBox = new HBox(5);
        birthdayHBox.setMaxWidth(Double.MAX_VALUE);
        birthdayHBox.getChildren().addAll(yearVBox, monthVBok, dayVBox, bDayErrLabel);
        //------------------------------------------------------------------------------

        emailTxtField = new TextField();
        emailTxtField.setPromptText("E-mail");
        emailTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        emailErrLabel = new Label("");
        emailErrLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        emailHBox = new VBox();
        emailHBox.getChildren().addAll(emailTxtField, emailErrLabel);

        salaryTxtField = new TextField();
        salaryTxtField.setPromptText("Salary");
        salaryTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        salaryErrLabel = new Label("");
        salaryErrLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        salaryHBox = new VBox();
        salaryHBox.getChildren().addAll(salaryTxtField, salaryErrLabel);

        idCardNrTxtField = new TextField();
        idCardNrTxtField.setPromptText("ID card number");
        idCardNrTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        idCardNrErrLabel = new Label("");
        idCardNrErrLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        idCardNrHBox = new VBox();
        idCardNrHBox.getChildren().addAll(idCardNrTxtField, idCardNrErrLabel);
    }
}
