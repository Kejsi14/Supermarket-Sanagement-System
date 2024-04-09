package Scenes;

import com.project.Product;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LowQuantityAlertScene
{
    Stage window;
    Scene scene;
    Label titleLabel;
    Button closeButton;
    ObservableList<Product> products;
    TableView<Product> productsTable;

    public void showScene(ObservableList<Product> products)
    {
        this.products = products;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        titleLabel = new Label("The following products have a less than 5 quantity");
        titleLabel.setMaxWidth(Double.MAX_VALUE);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");
        setTable();

        closeButton = new Button("Close");
        closeButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        closeButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        closeButton.setOnAction(actionEvent -> window.close());

        VBox buttonVBox = new VBox(10);
        buttonVBox.setPadding(new Insets(10, 80, 5, 80));
        buttonVBox.getChildren().addAll(closeButton);

        VBox layout = new VBox(25);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, productsTable, buttonVBox);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 362, 546);
        window.setScene(scene);
        window.showAndWait();
    }

    void setTable()
    {
        TableColumn<Product, String> nameColumn = new TableColumn<>("Product");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(120);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        productsTable = new TableView<>();
        productsTable.setItems(products);
        productsTable.getColumns().addAll(nameColumn, quantityColumn);
    }
}
