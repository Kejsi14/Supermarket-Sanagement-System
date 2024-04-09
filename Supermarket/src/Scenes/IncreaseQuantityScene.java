package Scenes;

import com.project.BoughtProduct;
import com.project.DataBase;
import com.project.InputCheck;
import com.project.Product;
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

public class IncreaseQuantityScene
{
    Stage window;
    Scene scene;
    Label title;
    Button addButton;
    TextField quantityTextField;
    Label selItemErrorLabel;
    Label notIntErrorLabel;
    VBox tableVBox;
    Button closeButton;
    HBox quantityHBox;
    VBox layout;

    ObservableList<Product> productsList;
    TableView<Product> productTable;

    public void showScene()
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        title = new Label("Add Product Quantity");
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");

        productsList = FXCollections.observableArrayList();
        productsList.addAll(DataBase.getProducts());
        setTable();

        selItemErrorLabel = new Label("");
        selItemErrorLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");

        tableVBox = new VBox(5);
        tableVBox.getChildren().addAll(productTable, selItemErrorLabel);

        quantityTextField = new TextField();
        quantityTextField.setPromptText("Quantity");
        quantityTextField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");

        notIntErrorLabel = new Label("");
        notIntErrorLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");

        addButton = new Button("Add");
        addButton.setPrefWidth(76);
        addButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        addButton.setOnAction(actionEvent -> addProductQuantity());

        quantityHBox = new HBox(10);
        quantityHBox.setMaxWidth(Double.MAX_VALUE);
        quantityHBox.setAlignment(Pos.CENTER_RIGHT);
        quantityHBox.getChildren().addAll(notIntErrorLabel, quantityTextField, addButton);

        closeButton = new Button("Close");
        closeButton.setPrefWidth(76);
        closeButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        closeButton.setOnAction(actionEvent -> window.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setPadding(new Insets(0, 0, 10, 0));
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(title, spacer, closeButton);

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, tableVBox, quantityHBox);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 376, 568);
        window.setScene(scene);
        window.showAndWait();
    }

    void addProductQuantity()
    {
        String name = "";
        int quantity = 0;
        String input = quantityTextField.getText();
        boolean isFieldCorrect = true;

        if(!productTable.getSelectionModel().isEmpty())
        {
            name = productTable.getSelectionModel().getSelectedItem().getName();
            selItemErrorLabel.setText("");
        }
        else
        {
            selItemErrorLabel.setText("Select a product!");
            isFieldCorrect = false;
        }

        if(!input.isEmpty() && InputCheck.isInt(input))
        {
            quantity = Integer.parseInt(input);
            notIntErrorLabel.setText("");
        }
        else
        {
            notIntErrorLabel.setText("Enter a number!");
            isFieldCorrect = false;
        }

        if(isFieldCorrect)
        {
            for(Product addedProduct : DataBase.getProducts())
                if(name.equals(addedProduct.getName()))
                {
                    addedProduct.setQuantity(addedProduct.getQuantity() + quantity);

                    DataBase.getBoughtProducts().add(new BoughtProduct());
                    int addedBoughtProdIndex = DataBase.getBoughtProducts().size() - 1;
                    BoughtProduct addedBoughtProd = DataBase.getBoughtProducts().get(addedBoughtProdIndex);

                    addedBoughtProd.setName(addedProduct.getName());
                    addedBoughtProd.setID(addedProduct.getID());
                    addedBoughtProd.setCategory(addedProduct.getCategory());
                    addedBoughtProd.setSupplierID(addedProduct.getSupplierID());
                    addedBoughtProd.setPrice(addedProduct.getPrice());
                    addedBoughtProd.setBarcode(addedProduct.getBarcode());
                    addedBoughtProd.setQuantity(addedProduct.getQuantity());
                    addedBoughtProd.setExpiryDate(addedProduct.getExpiryDate());

                    break;
                }

            setTable();
            tableVBox.getChildren().set(0, productTable);
        }

        quantityTextField.clear();
    }

    void setTable()
    {
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(70);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        productTable = new TableView<>();
        productTable.setItems(productsList);
        productTable.getColumns().addAll(nameColumn, quantityColumn);
    }
}
