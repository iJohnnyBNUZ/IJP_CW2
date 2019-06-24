package View;

import Controller.BagController;
import Controller.LoadGame;
import Controller.LocationController;
import Controller.ViewController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocationView {
    private static volatile LocationView locationView = null;
    private ViewController viewcontroller = null;
    private LocationController locationcontroller = null;
    private BagController bagcontroller = null;
    private double position_x, position_y;

    private ImageView imageView;
    private HBox buttonBox;
    private MenuItem menu_bag;
    private MenuItem menu_position;
    private AnchorPane itemspage;
    private TitledPane bagView;

    public LocationView(ViewController viewcontroller){
        this.viewcontroller = viewcontroller;
        this.locationcontroller = viewcontroller.getLocationController();
        this.bagcontroller = viewcontroller.getBagController();
        this.imageView = viewcontroller.getImageView();
        this.buttonBox = viewcontroller.getHBox();
        this.menu_bag = viewcontroller.getMenuBag();
        this.menu_position = viewcontroller.getMenuPosition();
        this.itemspage = viewcontroller.getItemsPage();
        this.bagView = viewcontroller.getBagView();
        menu_bag.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                bagView.setVisible(true);
            }
        });
        /*
        menu_position.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        */
    }

    public void setViewController(ViewController controller){this.viewcontroller = controller;}
    public ViewController getViewController(){return viewcontroller;}


    public void setLocationController(LocationController controller){this.locationcontroller = controller;}
    public LocationController getLocationController(){return locationcontroller;}


    public static LocationView getLocationView(){
        synchronized (LocationView.class){
            if(locationView == null){
                locationView = new LocationView(ViewController.getViewController());
            }
        }

        return locationView;
    }


    public void updateLocation(String locationName, List<Integer> arrowAngles) {
         File file = new File(getClass().getResource("/images/"+locationName+".JPG").getFile());
         Image locationimage = new Image(file.toURI().toString());
         imageView.setImage(locationimage);
         bagView.setVisible(false);
         int buttonbox_width = 60 * arrowAngles.size();
         buttonBox.setPrefWidth(buttonbox_width);
         buttonBox.getChildren().clear();
         itemspage.getChildren().clear();
         for(int i=0; i<arrowAngles.size(); i++){
             addArrows(arrowAngles.get(i));
         }
    }


    public void addArrows(final Integer arrowAngle){
        Button button = new Button("←");
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
    

	public void showChoice() {
		final List<Double> position = new ArrayList<Double>();
		final GaussianBlur effect = new GaussianBlur();
		
		
		// TODO Auto-generated method stub
		imageView.setEffect(effect);
		
		disableItems();
		
		itemspage.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent m) {
				// TODO Auto-generated method stub
				position_x = m.getX();
				position_y = m.getY();
				position.add(position_x);
		    	position.add(position_y);
		    	bagcontroller.addToLocation(position);
		    	ableItems();
			}

		});
	}

	private void disableItems() {
		// TODO Auto-generated method stub
		int size =itemspage.getChildren().size();
		
		for(int i=0; i<size; i++){
			itemspage.getChildren().get(i).setMouseTransparent(true);
			
		}
	}

	private void ableItems() {
		// TODO Auto-generated method stub
		int size =itemspage.getChildren().size();
		
		for(int i=0; i<size; i++){
			itemspage.getChildren().get(i).setMouseTransparent(false);
			
		}
	}
	public void removeChoice() {
		// TODO Auto-generated method stub
		imageView.setEffect(null);
		itemspage.setOnMouseClicked(null);
	}
    
    


    

}
