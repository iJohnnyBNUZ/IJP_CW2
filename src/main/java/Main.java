import Controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

import View.LocationView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        /*
    	LocationController locationController = new LocationController();
        ItemsController itemController = new ItemsController();
        BagController bagController = new BagController();
        */

        /*
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 400, 400));
        LoadGame lg = new LoadGame("config/User.json", "config/Location.json");
        lg.Initialise();
        primaryStage.show();
        */

        /*Used for get the controller instance defined in the sample.fxml
         * actually the defined controller is LocationView instance*/
        URL location = getClass().getResource("view/sample.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        ViewController viewcontroller = (ViewController) fxmlLoader.getController();
        //bandView(controller,locationController,itemController,bagController);
        ViewController.setViewController(viewcontroller);
        LoadGame lg = new LoadGame("config/User.json", "config/Location.json");
        lg.Initialise(viewcontroller);
        primaryStage.show();
        System.out.println("Finished Loading");
    }


    /*
    private void bandView(LocationView view, LocationController locationController,
    		ItemsController itemController, BagController bagController) {
		// TODO Auto-generated method stub
		view.getBagViewController().setController(bagController);
		view.getItemViewController().setController(itemController);
    }
    */


	public static void main(String[] args) {
        launch(args);
        //LoadGame lg = new LoadGame("config/Location.json", "config/Location.json");
        //lg.loadWorld();
        //lg.loadUser();
    }
}
