package Scenes;

import com.project.Economist;
import com.project.Product;
import com.project.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EconomistScene
{
    Economist economist;
    Scene scene;
    Label title;
    Button logoutButton;
    Button addProductButton;
    Button addQuantityButton;
    Button addCategoryButton;
    Button showCashierStatsButton;
    Button showProductsSoldButton;
    Button showProductsBoughtButton;
    Button showSuppliersButton;
    VBox statsVBox;
    VBox addVBox;
    HBox titleHBox;
    HBox allButtons;

    VBox layout;
    ObservableList<Product> lowQuantityProds;

    public void showScene(Economist economist, Stage window) throws FileNotFoundException
    {
        this.economist = economist;

        Image image = new Image(new FileInputStream("resources/Images/economist.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(38);
        imageView.setPreserveRatio(true);

        title = new Label(economist.getName());
        title.setGraphic(imageView);
        setLayout(window);

        layout = new VBox(35);
        layout.setPadding(new Insets(10, 30, 10, 30));
        layout.getChildren().addAll(titleHBox, allButtons);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 360, 250);
        window.setScene(scene);

        lowQuantityProds = FXCollections.observableArrayList();
        checkProductQuantity();
    }

    void checkProductQuantity()
    {
        for(Product p : DataBase.getProducts())
            if(p.getQuantity() < 5)
                lowQuantityProds.add(p);

        if(lowQuantityProds.size() > 0)
            new LowQuantityAlertScene().showScene(lowQuantityProds);
    }

    void setLayout(Stage window)
    {
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");

        // Add Product button
        addProductButton = new Button("Add a new product");
        addProductButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addProductButton.setPrefWidth(128);
        addProductButton.setAlignment(Pos.CENTER);
        addProductButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        addProductButton.setOnAction(actionEvent -> new AddProductScene().showScene());

        // Add quantity button
        addQuantityButton = new Button("Add Quantity");
        addQuantityButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addQuantityButton.setPrefWidth(128);
        addQuantityButton.setAlignment(Pos.CENTER);
        addQuantityButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        addQuantityButton.setOnAction(actionEvent ->  new IncreaseQuantityScene().showScene());

        // Add Category button
        addCategoryButton = new Button("Add Category");
        addCategoryButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addCategoryButton.setPrefWidth(128);
        addCategoryButton.setAlignment(Pos.CENTER);
        addCategoryButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        addCategoryButton.setOnAction(actionEvent -> new AddCategoryScene().showScene());

        // Show Cashier stats button
        showCashierStatsButton = new Button("Cashier stats");
        showCashierStatsButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        showCashierStatsButton.setPrefWidth(128);
        showCashierStatsButton.setAlignment(Pos.CENTER);
        showCashierStatsButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        showCashierStatsButton.setOnAction(actionEvent -> new CashierStatsScene().showScene());

        // Show sold products button
        showProductsSoldButton = new Button("Products sold");
        showProductsSoldButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        showProductsSoldButton.setPrefWidth(128);
        showProductsSoldButton.setAlignment(Pos.CENTER);
        showProductsSoldButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        showProductsSoldButton.setOnAction(actionEvent -> new ProductsSoldScene().showScene());

        // Show bought products button
        showProductsBoughtButton = new Button("Products bought");
        showProductsBoughtButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        showProductsBoughtButton.setPrefWidth(128);
        showProductsBoughtButton.setAlignment(Pos.CENTER);
        showProductsBoughtButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        showProductsBoughtButton.setOnAction(actionEvent -> new ProductsBoughtScene().showScene());

        // Show Suppliers button
        showSuppliersButton = new Button("Suppliers");
        showSuppliersButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        showSuppliersButton.setPrefWidth(128);
        showSuppliersButton.setAlignment(Pos.CENTER);
        showSuppliersButton.setStyle("-fx-background-color: #2050bb; -fx-text-fill: #eeeeee;");
        showSuppliersButton.setOnAction(actionEvent -> new SuppliersListScene().showScene());

        // Logout button
        logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #bb2020; -fx-text-fill: #eeeeee;");
        logoutButton.setOnAction(actionEvent -> {
            try { MainMenu.showScene(window, "Employee"); }
            catch (FileNotFoundException e) { e.printStackTrace(); }
        });

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        titleHBox = new HBox();
        titleHBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setPadding(new Insets(5, 0, 5, 0));
        titleHBox.getChildren().addAll(title, spacer, logoutButton);

        statsVBox = new VBox(10);
        statsVBox.getChildren().addAll(showCashierStatsButton, showProductsSoldButton, showProductsBoughtButton, showSuppliersButton);

        addVBox = new VBox(10);
        addVBox.getChildren().addAll(addProductButton, addQuantityButton, addCategoryButton);

        Pane spacer2 = new Pane();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        allButtons = new HBox();
        allButtons.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        allButtons.setAlignment(Pos.CENTER);
        allButtons.getChildren().addAll(statsVBox, spacer2, addVBox);
    }
}
