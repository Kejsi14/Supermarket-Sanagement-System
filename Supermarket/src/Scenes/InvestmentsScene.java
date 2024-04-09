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
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InvestmentsScene extends ProductsBoughtScene
{
    Label productsLabel;
    VBox productsVBox;

    Label totalInvLabel;
    Label amountLabel;
    HBox investmentsHBox;

    Label usersLabel;
    HBox usersHBox;
    VBox usersVBox;

    Label usersSalaryLabel;
    Label usersAmountLabel;
    HBox usersSalaryHBox;

    ObservableList<Cashier> cashiers;
    ObservableList<Economist> economists;
    TableView<Cashier> cashierTable;
    TableView<Economist> economistTable;

    @Override
    public void showScene()
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        title = new Label("Investments");
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");

        productsList = FXCollections.observableArrayList();
        productsList.addAll(DataBase.getBoughtProducts());

        cashiers = FXCollections.observableArrayList();
        cashiers.addAll(DataBase.getCashiers());
        economists = FXCollections.observableArrayList();
        economists.addAll(DataBase.getEconomists());
        setTable();

        productsLabel = new Label("Products");
        productsVBox = new VBox(5);
        productsVBox.getChildren().addAll(productsLabel, productTable);

        totalInvLabel = new Label("Product investments:");
        totalInvLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");
        amountLabel = new Label("0.0");
        amountLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");
        investmentsHBox = new HBox(5);
        investmentsHBox.getChildren().addAll(totalInvLabel, amountLabel);

        usersLabel = new Label("Users");
        setCashierTable();
        setEconomistTable();

        usersHBox = new HBox(30);
        usersHBox.getChildren().addAll(cashierTable, economistTable);

        usersVBox = new VBox(5);
        usersVBox.getChildren().addAll(usersLabel, usersHBox);

        usersSalaryLabel = new Label("Total users salary:");
        usersSalaryLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");
        usersAmountLabel = new Label("0.0");
        usersAmountLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");
        usersSalaryHBox = new HBox(5);
        usersSalaryHBox.getChildren().addAll(usersSalaryLabel, usersAmountLabel);

        setLabelsText();

        closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        closeButton.setOnAction(actionEvent -> window.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(title, spacer, closeButton);

        layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, productsVBox, investmentsHBox, usersVBox, usersSalaryHBox);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 474, 720);
        window.setScene(scene);
        window.showAndWait();
    }

    void setLabelsText()
    {
        double productsSum = 0;
        for(BoughtProduct bp : productsList)
            productsSum += bp.getTotalPrice();

        double cashiersTotal = 0;
        for(Cashier c : cashiers)
            cashiersTotal += c.getSalary();

        double economistsTotal = 0;
        for(Economist e : economists)
            economistsTotal += e.getSalary();

        double usersTotal = cashiersTotal + economistsTotal;

        amountLabel.setText(Double.toString(productsSum));
        usersAmountLabel.setText(Double.toString(usersTotal));
    }

    @Override
    void setTable()
    {
        TableColumn<BoughtProduct, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<BoughtProduct, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(80);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<BoughtProduct, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(40);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<BoughtProduct, Double> totalPriceColumn = new TableColumn<>("Total price");
        totalPriceColumn.setMinWidth(80);
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        productTable = new TableView<>();
        productTable.setItems(productsList);
        productTable.getColumns().addAll(nameColumn, priceColumn, quantityColumn, totalPriceColumn);
    }

    void setCashierTable()
    {
        TableColumn<Cashier, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Cashier, Double> salaryPerMonthColumn = new TableColumn<>("Salary/month");
        salaryPerMonthColumn.setMinWidth(100);
        salaryPerMonthColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        cashierTable = new TableView<>();
        cashierTable.setItems(cashiers);
        cashierTable.getColumns().addAll(nameColumn, salaryPerMonthColumn);
    }

    void setEconomistTable()
    {
        TableColumn<Economist, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Economist, Double> salaryPerMonthColumn = new TableColumn<>("Salary/month");
        salaryPerMonthColumn.setMinWidth(100);
        salaryPerMonthColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        economistTable = new TableView<>();
        economistTable.setItems(economists);
        economistTable.getColumns().addAll(nameColumn, salaryPerMonthColumn);
    }
}
