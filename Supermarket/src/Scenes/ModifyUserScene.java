package Scenes;

import com.project.DataBase;
import com.project.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModifyUserScene
{
    Stage window;
    Label titleLabel;
    Label errorLabel;
    Button editButton;
    Button cancelButton;
    Scene scene;
    VBox layout;
    ObservableList<User> users;
    TableView<User> usersTable;

    public void showScene()
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        titleLabel = new Label("Users");
        titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");

        users = FXCollections.observableArrayList();
        users.addAll(DataBase.getCashiers());
        users.addAll(DataBase.getEconomists());
        setTable();

        errorLabel = new Label("");
        errorLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");

        editButton = new Button("Edit");
        editButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        editButton.setPrefWidth(64);
        editButton.setOnAction(actionEvent -> editUser());

        Pane spacer2 = new Pane();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        HBox addHBox = new HBox(15);
        addHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addHBox.setAlignment(Pos.CENTER);
        addHBox.getChildren().addAll(spacer2, errorLabel, editButton);

        cancelButton = new Button("Exit");
        cancelButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        cancelButton.setPrefWidth(64);
        cancelButton.setOnAction(actionEvent -> window.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(titleLabel, spacer, cancelButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, usersTable, addHBox);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 380, 520);
        window.setScene(scene);
        window.showAndWait();
    }

    private void editUser()
    {
        if(!usersTable.getSelectionModel().isEmpty())
        {
            errorLabel.setText("");
            User user = usersTable.getSelectionModel().getSelectedItem();

            if(user.getRoleName().equals("Cashier"))
                new EditCashierScene().showScene(user);

            if(user.getRoleName().equals("Economist"))
                new EditEconomistScene().showScene(user);
        }
        else
        {
            errorLabel.setText("Select a user!");
        }
    }

    void setTable()
    {
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(100);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setMinWidth(100);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("roleName"));

        usersTable = new TableView<>();
        usersTable.setItems(users);
        usersTable.getColumns().addAll(idColumn, usernameColumn, roleColumn);
    }
}
