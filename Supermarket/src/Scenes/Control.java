package Scenes;

import com.project.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Control extends Application
{
    static Stage window;

    @Override
    public void start(Stage stage) throws Exception
    {
        window = stage;

        Image icon = new Image(new FileInputStream("resources/Images/icon.png"));
        window.getIcons().add(icon);

        window.setTitle("Supermarket");

        window.setOnCloseRequest(e -> DataBase.save());

        PreLoginScene.showScene(window);
        window.show();

        help();
    }

    static void help()
    {
        System.out.println("Users:");
        for (Cashier c : DataBase.getCashiers())
            System.out.println("Login as " + c.getName() + ":   username: " + c.getUsername() + "   password" + c.getPassword());
        for (Economist e : DataBase.getEconomists())
            System.out.println("Login as " + e.getName() + ":   username: " + e.getUsername() + "   password: " + e.getPassword());
        System.out.println();

        System.out.println("Available Products:");
        for(Product p : DataBase.getProducts())
            System.out.println(p.getName());
        System.out.println();

        System.out.println("Login as Administrator:   username: admin   password: admin123\n");
    }
}
