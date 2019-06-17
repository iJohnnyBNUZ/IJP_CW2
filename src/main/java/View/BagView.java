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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
	@FXML
	private MenuItem menu_bag;
	
	@FXML
	private GridPane InBag; 
	
	@FXML
	private TitledPane bag; 
	
	@FXML 
	private Button confirm;
	
	@FXML
	private Button close;
	
    private static volatile BagView bagView = null;
    private BagController controller = null;
    private double image_h = 65.0;
    private double image_w =65.0;
    private int row=3;
    private int column=3;

    public BagView(BagController controller){
    	this.controller = controller;
    	initialise();
    }
    
    public void initialise() {
    	menu_bag.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				bag.setVisible(true);
			}
    		
    	});
    	
    	confirm.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controller.removeFromBag();
				bag.setVisible(false);
			}
    		
    	});
    	
    	close.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				bag.setVisible(false);
			}
    		
    	});
    }
    

    public static BagView getBagView(){
        synchronized (BagView.class){
            if(bagView == null){
                bagView = new BagView(new BagController());
            }
        }

        return bagView;
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
				BorderPane item = new BorderPane();
				
				//create label to show the number of item
				Label item_num = new Label(""+Collections.frequency(bagItems, tmp_name));
				
				item.setBottom(item_num);
				
				//create ImageView to each of the items
	       		ImageView item_img = new ImageView();
	       		item_img.setOnMouseClicked(new EventHandler<MouseEvent>(){

					public void handle(MouseEvent arg0) {
						// TODO Auto-generated method stub
						controller.select(tmp_name);
					}
					
       			 });
	       		 
	       		 //create item's image.
	       		 URL url = this.getClass().getResource("/images/"+tmp_name+".png");
	             Image image = new Image(url.toString(),image_h,image_w,false,false); 
	             item_img.setImage(image);
	       	     
	       	     item.setCenter(item_img);
	       	     //set the item's position.
	       	     InBag.add(item, r, c);
	       	     
	       	     if(c<column-1) {
	       	    	 c++;
	       	     }else {
	       	    	 c=0;
	       	    	 r++;
	       	     }
				
			}
			
   		 //show the bag on the interface
   	     bag.setVisible(true);
   	     
   	 }
		
    }
	public void disappear() {
		bag.setVisible(false);
	}
}
