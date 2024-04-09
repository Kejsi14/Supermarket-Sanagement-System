package Scenes;

import com.project.*;
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

public class ProductsSoldScene
{
    Stage window;
    Scene scene;
    Label title;
    Button closeButton;
    VBox layout;

    ObservableList<SoldProduct> soldProductsList;
    TableView<SoldProduct> soldProductTable;

    public void showScene()
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        title = new Label("Products Sold");
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");

        setSoldProdsList();
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
        layout.getChildren().addAll(titleHBox, soldProductTable);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 512, 700);
        window.setScene(scene);
        window.showAndWait();
    }

    void setSoldProdsList()
    {
        soldProductsList = FXCollections.observableArrayList();

        for(Cashier cashier : DataBase.getCashiers())
            soldProductsList.addAll(cashier.getSoldProducts());
    }

    void setTable()
    {
        TableColumn<SoldProduct, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<SoldProduct, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));

        TableColumn<SoldProduct, Integer> billColumn = new TableColumn<>("Bill no");
        billColumn.setMinWidth(80);
        billColumn.setCellValueFactory(new PropertyValueFactory<>("billNumber"));

        TableColumn<SoldProduct, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(50);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<SoldProduct, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(70);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        soldProductTable = new TableView<>();
        soldProductTable.setItems(soldProductsList);
        soldProductTable.getColumns().addAll(nameColumn, idColumn, billColumn, quantityColumn, priceColumn);
    }
}
