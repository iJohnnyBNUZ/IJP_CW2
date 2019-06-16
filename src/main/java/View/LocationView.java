package View;

import Controller.LocationController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;

public class LocationView {
    private static volatile LocationView locationView = null;

    private LocationController locationcontroller = null;

    @FXML
    private ImageView imageView;
    private HBox buttonBox;

    private LocationView(LocationController locationcontroller){
        this.locationcontroller = locationcontroller;
    }

    public void setLocationController(LocationController controller){this.locationcontroller = controller;}
    public LocationController getLocationController(){return locationcontroller;}

    public static LocationView getLocationView(){
        synchronized (LocationView.class){
            if(locationView == null){
                locationView = new LocationView(new LocationController());
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
            @Override
            public void handle(ActionEvent event) {
                if(locationcontroller != null){
                    locationcontroller.moveToDirection(arrowAngle);
                }
            }
        });


    }
}
