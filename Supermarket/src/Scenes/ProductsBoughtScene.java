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

public class ProductsBoughtScene
{
    Stage window;
    Scene scene;
    Label title;
    Button closeButton;
    VBox layout;

    ObservableList<BoughtProduct> productsList;
    TableView<BoughtProduct> productTable;

    public void showScene()
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        title = new Label("Products Bought");
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");

        productsList = FXCollections.observableArrayList();
        productsList.addAll(DataBase.getBoughtProducts());
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
        layout.getChildren().addAll(titleHBox, productTable);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 654, 700);
        window.setScene(scene);
        window.showAndWait();
    }

    void setTable()
    {
        TableColumn<BoughtProduct, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<BoughtProduct, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(40);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<BoughtProduct, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setMinWidth(100);
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<BoughtProduct, Integer> supplierIDColumn = new TableColumn<>("Supplier ID");
        supplierIDColumn.setMinWidth(40);
        supplierIDColumn.setCellValueFactory(new PropertyValueFactory<>("supplierID"));

        TableColumn<BoughtProduct, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(80);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<BoughtProduct, String> barcodeColumn = new TableColumn<>("Barcode");
        barcodeColumn.setMinWidth(50);
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("barcode"));

        TableColumn<BoughtProduct, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(40);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        productTable = new TableView<>();
        productTable.setItems(productsList);
        productTable.getColumns().addAll(nameColumn, idColumn, categoryColumn, supplierIDColumn, priceColumn, barcodeColumn, quantityColumn);
    }
}
