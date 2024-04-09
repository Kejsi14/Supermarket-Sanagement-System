package Scenes;

import com.project.BoughtProduct;
import com.project.SoldProduct;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IncomesScene extends ProductsSoldScene
{
    Label totalIncLabel;
    Label amountLabel;
    HBox investmentsHBox;

    @Override
    public void showScene()
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        title = new Label("Products Sold");
        title.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");

        setSoldProdsList();
        setTable();

        totalIncLabel = new Label("Total income:");
        totalIncLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");
        amountLabel = new Label("0.0");
        amountLabel.setStyle("-fx-font: normal bold 12px 'arial'; -fx-text-fill: #333333;");
        investmentsHBox = new HBox(5);
        investmentsHBox.getChildren().addAll(totalIncLabel, amountLabel);
        setAmountLabel();

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

        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleHBox, soldProductTable, investmentsHBox);
        layout.setStyle("-fx-background-color: #cccccc;");

        scene = new Scene(layout, 586, 720);
        window.setScene(scene);
        window.showAndWait();
    }

    void setAmountLabel()
    {
        double total = 0;
        for(SoldProduct sp : soldProductsList)
            total += sp.getTotalPrice();
        amountLabel.setText(Double.toString(total));
    }

    @Override
    void setTable()
    {
        super.setTable();

        TableColumn<SoldProduct, Double> totalPriceColumn = new TableColumn<>("Total Price");
        totalPriceColumn.setMinWidth(80);
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        soldProductTable.getColumns().add(totalPriceColumn);
    }
}
