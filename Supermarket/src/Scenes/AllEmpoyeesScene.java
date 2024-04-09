package Scenes;

import com.project.*;
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

public class AllEmpoyeesScene
{
    Stage window;
    Label titleLabel;
    Label cashiersLabel;
    Label economistsLabel;
    Button cancelButton;
    VBox cashiersVBox;
    VBox economistVBox;
    Scene scene;
    VBox layout;
    ObservableList<Cashier> cashiers;
    TableView<Cashier> cashiersTable;
    ObservableList<Economist> economists;
    TableView<Economist> economistsTable;

    public void showScene()
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        titleLabel = new Label("All Users");
        titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");

        setCashiersTableTable();
        setEconomistsTable();

        cancelButton = new Button("Exit");
        cancelButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        cancelButton.setOnAction(actionEvent -> window.close());

        cashiersLabel = new Label("Cashiers");
        cashiersLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");
        cashiersVBox = new VBox(10);
        cashiersVBox.getChildren().addAll(cashiersLabel, cashiersTable);

        economistsLabel = new Label("Economists");
        economistsLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");
        economistVBox = new VBox(10);
        economistVBox.getChildren().addAll(economistsLabel, economistsTable);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 5, 10, 5));
        titleHBox.getChildren().addAll(titleLabel, spacer, cancelButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, cashiersVBox, economistVBox);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 1024, 720);
        window.setScene(scene);
        window.showAndWait();
    }

    void setCashiersTableTable()
    {
        TableColumn<Cashier, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Cashier, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(90);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Cashier, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setMinWidth(100);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("roleName"));

        TableColumn<Cashier, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(70);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Cashier, String> phNrColumn = new TableColumn<>("Phone Number");
        phNrColumn.setMinWidth(100);
        phNrColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        // Birthday Column

        TableColumn<Cashier, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(150);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Cashier, Double> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setMinWidth(70);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Cashier, String> idCardNrColumn = new TableColumn<>("ID Card Number");
        idCardNrColumn.setMinWidth(100);
        idCardNrColumn.setCellValueFactory(new PropertyValueFactory<>("idCardNumber"));

        TableColumn<Cashier, Integer> nrOfBillsColumn = new TableColumn<>("Bills");
        nrOfBillsColumn.setMinWidth(50);
        nrOfBillsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfBills"));

        TableColumn<Cashier, Integer> nrOfProdSoldColumn = new TableColumn<>("Products Sold");
        nrOfProdSoldColumn.setMinWidth(100);
        nrOfProdSoldColumn.setCellValueFactory(new PropertyValueFactory<>("numOfProdsSold"));

        TableColumn<Cashier, Double> moneyGenColumn = new TableColumn<>("Money Generated");
        moneyGenColumn.setMinWidth(100);
        moneyGenColumn.setCellValueFactory(new PropertyValueFactory<>("moneyGenerated"));

        cashiers = FXCollections.observableArrayList();
        cashiers.addAll(DataBase.getCashiers());

        cashiersTable = new TableView<>();
        cashiersTable.setItems(cashiers);
        cashiersTable.getColumns().addAll(idColumn, usernameColumn, roleColumn, nameColumn, phNrColumn, emailColumn,
                salaryColumn, idCardNrColumn, nrOfBillsColumn, nrOfProdSoldColumn, moneyGenColumn);
    }

    void setEconomistsTable()
    {
        TableColumn<Economist, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Economist, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(90);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Economist, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setMinWidth(100);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("roleName"));

        TableColumn<Economist, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(70);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Economist, String> phNrColumn = new TableColumn<>("Phone Number");
        phNrColumn.setMinWidth(100);
        phNrColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        // Birthday Column

        TableColumn<Economist, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(150);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Economist, Double> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setMinWidth(70);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Economist, String> idCardNrColumn = new TableColumn<>("ID Card Number");
        idCardNrColumn.setMinWidth(100);
        idCardNrColumn.setCellValueFactory(new PropertyValueFactory<>("idCardNumber"));

        economists = FXCollections.observableArrayList();
        economists.addAll(DataBase.getEconomists());

        economistsTable = new TableView<>();
        economistsTable.setItems(economists);
        economistsTable.getColumns().addAll(idColumn, usernameColumn, roleColumn, nameColumn, phNrColumn, emailColumn,
                salaryColumn, idCardNrColumn);
    }
}
