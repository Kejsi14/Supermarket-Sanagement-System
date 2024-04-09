package Scenes;

import com.project.Cashier;
import com.project.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CashierStatsScene
{
    Stage window;
    Scene scene;
    Label title;
    Button closeButton;
    VBox layout;

    TableView<Cashier> cashierStatsTable;
    ObservableList<Cashier> cashiers;

    public void showScene()
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        title = new Label("All cashiers");
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");

        cashiers = FXCollections.observableArrayList();
        cashiers.addAll(DataBase.getCashiers());
        setTable();

        closeButton = new Button("Close");
        closeButton.setPrefWidth(68);
        closeButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        closeButton.setOnAction(actionEvent -> window.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(title, spacer, closeButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, cashierStatsTable);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 642, 700);
        window.setScene(scene);
        window.showAndWait();
    }

    void setTable()
    {
        TableColumn<Cashier, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Cashier, Integer> nrOfBillsColumn = new TableColumn<>("Bills");
        nrOfBillsColumn.setMinWidth(150);
        nrOfBillsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfBills"));

        TableColumn<Cashier, Integer> prodSoldColumn = new TableColumn<>("Products Sold");
        prodSoldColumn.setMinWidth(150);
        prodSoldColumn.setCellValueFactory(new PropertyValueFactory<>("numOfProdsSold"));

        TableColumn<Cashier, Double> moneyGenColumn = new TableColumn<>("Money Generated");
        moneyGenColumn.setMinWidth(150);
        moneyGenColumn.setCellValueFactory(new PropertyValueFactory<>("moneyGenerated"));

        cashierStatsTable = new TableView<>();
        cashierStatsTable.setItems(cashiers);
        cashierStatsTable.getColumns().addAll(nameColumn, nrOfBillsColumn, prodSoldColumn, moneyGenColumn);
    }
}
