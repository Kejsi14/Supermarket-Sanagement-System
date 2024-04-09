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

public class AddProductScene
{
    Stage window;
    Scene scene;
    VBox layout;
    Label title;
    Button exitButton;

    TextField nameTxtField;
    Label fillNameError;
    ChoiceBox<String> categoryChBox;
    Label fillCategoryError;
    TextField supplierIDTxtField;
    Label fillSupplIDError;
    TextField priceTxtField;
    Label fillPriceError;
    TextField barcodeTxtField;
    Label fillBarcodeError;
    TextField quantityTxtField;
    Label fillQuantityError;
    TextField rackTxtField;
    Label fillRackError;
    TextField floorTxtField;
    Label fillFloorError;
    TextField warehouseTxtField;
    Label fillWarehouseError;

    Label yearLabel;
    ChoiceBox<String> yearChoiceBox;
    Label monthLabel;
    ChoiceBox<String> monthChoiceBox;
    Label dayLabel;
    ChoiceBox<String> dayChoiceBox;
    Label expiryDateErrLabel;

    VBox yearVBox;
    VBox monthVBok;
    VBox dayVBox;

    VBox nameHBox;
    VBox categoryHBox;
    VBox supplierIDHBox;
    VBox priceHBox;
    VBox barcodeHBox;
    VBox quantityHBox;
    HBox expiryDateHBox;
    VBox rackHBox;
    VBox floorHBox;
    VBox warehouseHBox;

    Button addButton;

    public void showScene()
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        setFields();

        monthChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> addDaysInDayChBox());
        yearChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> addDaysInDayChBox());

        addButton = new Button("Add product");
        addButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        addButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addButton.setOnAction(actionEvent -> addButtonPressed());

        VBox addButtonHBox = new VBox();
        addButtonHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addButtonHBox.setPadding(new Insets(0, 150, 0, 150));
        addButtonHBox.setAlignment(Pos.CENTER);
        addButtonHBox.getChildren().add(addButton);

        exitButton = new Button("Cancel");
        exitButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        exitButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        exitButton.setOnAction(actionEvent -> window.close());

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setPadding(new Insets(0, 0, 15, 0));
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().addAll(title, spacer, exitButton);

        layout = new VBox(16);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, nameHBox, categoryHBox, supplierIDHBox, priceHBox, barcodeHBox, quantityHBox,
                expiryDateHBox, rackHBox, floorHBox, warehouseHBox, addButtonHBox);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 480, 720);

        window.setScene(scene);
        window.showAndWait();
    }

    void addButtonPressed()
    {
        boolean isFieldCorrect = true;

        String name = "";
        String category = "";
        int supplierID = 0;
        double price = 0.0;
        String barcode = "";
        int quantity = 0;
        int rack = 0;
        int floor = 0;
        String wareHouse = "";
        int day = 0;
        int month = 0;
        int year = 0;

        if(nameTxtField.getText().isEmpty())
        {
            fillNameError.setText("Enter product name!");
            isFieldCorrect = false;
        }
        else
        {
            // Check if this product is already in the product list on the database
            if(isOnList(nameTxtField.getText()))
            {
                fillNameError.setText("This product already exists in the database!");
                isFieldCorrect = false;
            }
            else
            {
                name = nameTxtField.getText();
                fillNameError.setText("");
            }
        }

        if(categoryChBox.getValue().equals("----------"))
        {
            fillCategoryError.setText("Select product category!");
            isFieldCorrect = false;
        }
        else
        {
            category = categoryChBox.getValue();
            fillCategoryError.setText("");
        }

        if(supplierIDTxtField.getText().isEmpty() || !InputCheck.isInt(supplierIDTxtField.getText()))
        {
            fillSupplIDError.setText("Enter supplier ID!");
            isFieldCorrect = false;
        }
        else
        {
            supplierID = Integer.parseInt(supplierIDTxtField.getText());
            fillSupplIDError.setText("");
        }

        if(priceTxtField.getText().isEmpty() || !InputCheck.isDouble(priceTxtField.getText()))
        {
            fillPriceError.setText("Enter product price!");
            isFieldCorrect = false;
        }
        else
        {
            price = Double.parseDouble(priceTxtField.getText());
            fillPriceError.setText("");
        }

        if(barcodeTxtField.getText().isEmpty())
        {
            fillBarcodeError.setText("Enter product barcode!");
            isFieldCorrect = false;
        }
        else
        {
            barcode = barcodeTxtField.getText();
            fillBarcodeError.setText("");
        }

        if(quantityTxtField.getText().isEmpty() || !InputCheck.isInt(quantityTxtField.getText()))
        {
            fillQuantityError.setText("Enter product quantity!");
            isFieldCorrect = false;
        }
        else
        {
            quantity = Integer.parseInt(quantityTxtField.getText());
            fillQuantityError.setText("");
        }

        if(!yearChoiceBox.getValue().equals("----------") && !monthChoiceBox.getValue().equals("----------") && !dayChoiceBox.getValue().equals("----------"))
        {
            year = Integer.parseInt(yearChoiceBox.getValue());
            month = Integer.parseInt(monthChoiceBox.getValue());
            day = Integer.parseInt(dayChoiceBox.getValue());
            expiryDateErrLabel.setText("");
        }
        else
        {
            isFieldCorrect = false;
            expiryDateErrLabel.setText("Enter expiry date!");
        }

        if(rackTxtField.getText().isEmpty() || !InputCheck.isInt(rackTxtField.getText()))
        {
            fillRackError.setText("Enter the rack number on which the product belongs!");
            isFieldCorrect = false;
        }
        else
        {
            rack = Integer.parseInt(rackTxtField.getText());
            fillRackError.setText("");
        }

        if(floorTxtField.getText().isEmpty() || !InputCheck.isInt(floorTxtField.getText()))
        {
            fillFloorError.setText("Enter the floor number on which the product belongs!");
            isFieldCorrect = false;
        }
        else
        {
            floor = Integer.parseInt(floorTxtField.getText());
            fillFloorError.setText("");
        }

        if(warehouseTxtField.getText().isEmpty())
        {
            fillWarehouseError.setText("Enter the warehouse that the product was stored in!");
            isFieldCorrect = false;
        }
        else
        {
            wareHouse = warehouseTxtField.getText();
            fillWarehouseError.setText("");
        }

        if(isFieldCorrect)
        {
            DataBase.getProducts().add(new Product());
            int addedProdIndex = DataBase.getProducts().size() - 1;
            Product addedProduct = DataBase.getProducts().get(addedProdIndex);

            addedProduct.setName(name);
            addedProduct.setCategory(category);
            addedProduct.setSupplierID(supplierID);
            addedProduct.setPrice(price);
            addedProduct.setBarcode(barcode);
            addedProduct.setQuantity(quantity);
            addedProduct.setLocation(rack, floor, wareHouse);
            addedProduct.setExpiryDate(new Date(day, month, year));


            // Add product to products bought list on database
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

            window.close();
        }
    }

    void setFields()
    {
        title = new Label("Add a new Product");
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");

        nameTxtField = new TextField();
        nameTxtField.setPromptText("Name");
        nameTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        fillNameError = new Label();
        fillNameError.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        nameHBox = new VBox();
        nameHBox.getChildren().addAll(nameTxtField, fillNameError);

        categoryChBox = new ChoiceBox<>();
        categoryChBox.getItems().add("----------");
        categoryChBox.getItems().addAll(DataBase.getCategories());
        categoryChBox.setValue("----------");
        categoryChBox.setMaxWidth(Double.MAX_VALUE);
        fillCategoryError = new Label();
        fillCategoryError.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        categoryHBox = new VBox();
        categoryHBox.getChildren().addAll(categoryChBox, fillCategoryError);

        supplierIDTxtField = new TextField();
        supplierIDTxtField.setPromptText("Supplier ID");
        supplierIDTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        fillSupplIDError = new Label();
        fillSupplIDError.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        supplierIDHBox = new VBox();
        supplierIDHBox.getChildren().addAll(supplierIDTxtField, fillSupplIDError);

        priceTxtField = new TextField();
        priceTxtField.setPromptText("Price");
        priceTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        fillPriceError = new Label();
        fillPriceError.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        priceHBox = new VBox();
        priceHBox.getChildren().addAll(priceTxtField, fillPriceError);

        barcodeTxtField = new TextField();
        barcodeTxtField.setPromptText("Barcode");
        barcodeTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        fillBarcodeError = new Label();
        fillBarcodeError.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        barcodeHBox = new VBox();
        barcodeHBox.getChildren().addAll(barcodeTxtField, fillBarcodeError);

        quantityTxtField = new TextField();
        quantityTxtField.setPromptText("Quantity");
        quantityTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        fillQuantityError = new Label();
        fillQuantityError.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        quantityHBox = new VBox();
        quantityHBox.getChildren().addAll(quantityTxtField, fillQuantityError);

        yearLabel = new Label("Year");
        yearLabel.setMaxWidth(Double.MAX_VALUE);
        yearLabel.setAlignment(Pos.CENTER);
        yearLabel.setStyle("-fx-text-fill: #333333;");

        yearChoiceBox = new ChoiceBox<>();
        yearChoiceBox.getItems().add("----------");
        addYearsInYearChBox();
        yearChoiceBox.setValue("----------");
        yearChoiceBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        monthLabel = new Label("Month");
        monthLabel.setStyle("-fx-text-fill: #333333;");
        monthLabel.setMaxWidth(Double.MAX_VALUE);
        monthLabel.setAlignment(Pos.CENTER);

        monthChoiceBox = new ChoiceBox<>();
        monthChoiceBox.getItems().add("----------");
        addMonthsInMonthChBox();
        monthChoiceBox.setValue("----------");
        monthChoiceBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        dayLabel = new Label("Day");
        dayLabel.setStyle("-fx-text-fill: #333333;");
        dayLabel.setMaxWidth(Double.MAX_VALUE);
        dayLabel.setAlignment(Pos.CENTER);

        dayChoiceBox = new ChoiceBox<>();
        dayChoiceBox.getItems().add("----------");
        dayChoiceBox.setValue("----------");
        dayChoiceBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        expiryDateErrLabel = new Label("");
        expiryDateErrLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");

        yearVBox = new VBox(5);
        yearVBox.getChildren().addAll(yearChoiceBox, yearLabel);
        monthVBok = new VBox(5);
        monthVBok.getChildren().addAll(monthChoiceBox, monthLabel);
        dayVBox = new VBox(5);
        dayVBox.getChildren().addAll(dayChoiceBox, dayLabel);
        expiryDateHBox = new HBox(10);
        expiryDateHBox.getChildren().addAll(yearVBox, monthVBok, dayVBox, expiryDateErrLabel);

        rackTxtField = new TextField();
        rackTxtField.setPromptText("Rack number");
        rackTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        fillRackError = new Label();
        fillRackError.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        rackHBox = new VBox();
        rackHBox.getChildren().addAll(rackTxtField, fillRackError);

        floorTxtField = new TextField();
        floorTxtField.setPromptText("Floor");
        floorTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        fillFloorError = new Label();
        fillFloorError.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        floorHBox = new VBox();
        floorHBox.getChildren().addAll(floorTxtField, fillFloorError);

        warehouseTxtField = new TextField();
        warehouseTxtField.setPromptText("Warehouse");
        warehouseTxtField.setStyle("-fx-background-color: #eeeeee; -fx-text-fill: #333333; -fx-prompt-text-fill: #888888;");
        fillWarehouseError = new Label();
        fillWarehouseError.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #bb2020;");
        warehouseHBox = new VBox();
        warehouseHBox.getChildren().addAll(warehouseTxtField, fillWarehouseError);
    }

    private boolean isOnList(String text)
    {
        String name = text.toLowerCase();

        for(Product p : DataBase.getProducts())
        {
            String prodInListName = p.getName().toLowerCase();

            if(name.equals(prodInListName))
                return true;
        }

        return false;
    }

    void addDaysInDayChBox()
    {
        dayChoiceBox.getItems().clear();
        dayChoiceBox.getItems().add("----------");
        dayChoiceBox.setValue("----------");

        int month = 0;
        int year = 0;
        int max = 0;

        if(!monthChoiceBox.getValue().equals("----------")) month = Integer.parseInt(monthChoiceBox.getValue());
        if(!yearChoiceBox.getValue().equals("----------")) year = Integer.parseInt(yearChoiceBox.getValue());

        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) max = 31;
        if(month == 4 || month == 6 || month == 9 || month == 11) max = 30;

        if(month == 2)
        {
            max = 28;

            for(int i = 2020; i <= 2030; i += 4)
            {
                if(year == i)
                {
                    max = 29;
                    break;
                }

                if(i > year) break;
            }
        }

        if(year == 0) max = 0;

        for(int i = 1; i <= max; i++)
            dayChoiceBox.getItems().add(Integer.toString(i));
    }

    void addMonthsInMonthChBox()
    {
        for(int i = 1; i <= 12; i++)
            monthChoiceBox.getItems().add(Integer.toString(i));
    }

    void addYearsInYearChBox()
    {
        for(int i = 2030; i >= 2020; i--)
            yearChoiceBox.getItems().add(Integer.toString(i));
    }
}
