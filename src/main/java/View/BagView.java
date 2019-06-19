package View;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Controller.BagController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;

/**
 * An view for the virtual world application which uses JavaFx
 * and allows the users to look up items in their bag. 
 * Users also can bring out the item by clicking the item and confirm the action.
 * 
 * @author  Danyang Yu;
 * @version 1.0;
 */
public class BagView {
	
	private LocationView locationView;
	
	@FXML
	private GridPane InBag; 
	
	@FXML
	private TitledPane bagView; 
	
	@FXML 
	private Button confirm;
	
	@FXML
	private Button close;
	
    private static volatile BagView bagView_instance = null;
    private BagController controller = null;
    private double image_h = 50.0;
    private double image_w =50.0;
    private int row=3;
    private int column=3;
    

    
    public void injectMainController(LocationView locationView) {
		// TODO Auto-generated method stub
    	this.locationView = locationView;
		
	}
    
    /**for test the update bag method
     * after the controller is finished, this method can be removed
     * */
    public void initialize() {
    	
    	List<String> tmp = new ArrayList<String>();
    	tmp.add("apple");
    	tmp.add("apple");
    	tmp.add("lemon");
    	tmp.add("orange");
    	updateBag(tmp);
    	bagView.setVisible(false);
    }

    public static BagView getBagView(){
        synchronized (BagView.class){
            if(bagView_instance == null){
            	bagView_instance = new BagView();
            }
        }

        return bagView_instance;
    }

    public BagController getController() {
		return controller;
	}

	public void setController(BagController controller) {
		this.controller = controller;
	}

	public void updateBag(List<String> bagItems) {
		InBag.getChildren().clear();
		int r=0;
		int c=0;
		
		if(bagItems!=null) {
			//convert the list to set.
			Set<String> uniqueSet = new HashSet<String>(bagItems);
			for (final String tmp_name : uniqueSet) {
				
				if(r>row-1) {
					System.out.println("ERROR");
					break;
				}
				
				
				//create border pane for each item
				final BorderPane item = new BorderPane();
				
				//create label to show the number of item
				Label item_num = new Label(""+Collections.frequency(bagItems, tmp_name));
				
				item.setBottom(item_num);
				
				//create ImageView to each of the items
	       		ImageView item_img = new ImageView();
	       		final String style = "-fx-background-color:  #ffffff";
	       		item_img.setOnMouseClicked(new EventHandler<MouseEvent>(){

					public void handle(MouseEvent arg0) {
						// TODO Auto-generated method stub
						if(item.getStyle()==style) {
							item.setStyle("");
							controller.unselect(tmp_name);
						}else {
							item.setStyle(style);
						}
							
						
						controller.select(tmp_name);
					}
					
       			 });
	       		 
	       		 //create item's image.
	       		 URL url = this.getClass().getResource("/images/"+tmp_name+".png");
	             Image image = new Image(url.toString(),image_h,image_w,false,false); 
	             item_img.setImage(image);
	       	     
	       	     item.setCenter(item_img);
	       	     //set the item's position.
	       	     InBag.add(item, c, r);
	       	     if(c<column-1) {
	       	    	 c++;
	       	     }else {
	       	    	 c=0;
	       	    	 r++;
	       	     }
				
			}
			
   		 //show the bag on the interface
   	     bagView.setVisible(true);
   	     
   	 }
		
    }
    
    public void putDown(ActionEvent event) {
    	controller.removeFromBag();
		bagView.setVisible(false);
	}
    
	public void disappear(ActionEvent event) {
		bagView.setVisible(false);
		
	}

	public void showBag() {
		// TODO Auto-generated method stub
		bagView.setVisible(true);
		
		
	}

	
}
