package View;

import Controller.LocationController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;

public class LocationView {
    private static volatile LocationView locationView = null;

    private LocationController locationcontroller = null;

    @FXML
    private ImageView imageView;
    private HBox buttonBox;
    
    @FXML
	private MenuItem menu_bag;
	
	@FXML
	private GridPane InBag; 
	
	@FXML
	private TitledPane bag; 

    @FXML
    private void initialize() {
    	System.out.println("init");
    	menu_bag.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				bag.setVisible(true);
			}
    		
    	});
    }

    public void setLocationController(LocationController controller){this.locationcontroller = controller;}
    public LocationController getLocationController(){return locationcontroller;}

    public static LocationView getLocationView(){
        synchronized (LocationView.class){
            if(locationView == null){
                locationView = new LocationView();
            }
        }

        return locationView;
    }


    public void updateLocation(String locationName, List<Integer> arrowAngles) {
         URL locationviewurl = getClass().getResource("images/"+locationName+".jpg");
         Image locationimage = new Image(locationviewurl.toString());
         imageView.setImage(locationimage);
         int buttonbox_width = 60 * arrowAngles.size();
         buttonBox.setPrefWidth(buttonbox_width);
         for(int i=0; i<arrowAngles.size(); i++){
             addArrows(arrowAngles.get(i));
         }
    }


    public void addArrows(Integer arrowAngle){
        Button button = new Button("↑");
        button.setPrefWidth(60);
        button.setRotate(arrowAngle);
        buttonBox.getChildren().add(button);
        button.setOnAction(new EventHandler<ActionEvent>() {
             
            public void handle(ActionEvent event) {
            	// TODO Auto-generated method stub
                if(locationcontroller != null){
                    locationcontroller.moveToDirection(arrowAngle);
                }
            }
        });


    }
}
