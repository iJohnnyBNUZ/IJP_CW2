import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Controller.LoadGame;
import Controller.LocationController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 400, 400));   
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        LoadGame lg = new LoadGame("config/Location.json", "config/Location.json");
        lg.loadWorld();
        lg.loadUser();
    }
}
