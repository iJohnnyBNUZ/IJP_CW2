package View;

import java.net.URL;
import java.util.List;

import Model.Item;
import Controller.ItemsController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * An view for the virtual world application which uses JavaFx
 * and allows the user to pick up items by clicking the item.
 * 
 * @author  Danyang Yu;
 * @version 1.0;
 */
public class ItemView {
	private AnchorPane page; 
	
    private static volatile ItemView itemView = null;
    private ItemsController controller =null;
    
    private LocationView locationView;


    public ItemView(LocationView locationView) {
    	this.page = locationView.getPage();
    }

	//band ItemsController with ItemView.
    public ItemView(ItemsController controller){
    	this.controller=controller;
    	this.page = locationView.getPage();
    }

    public ItemsController getController() {
		return controller;
	}

	public void setController(ItemsController controller) {
		this.controller = controller;
	}
    
    public static ItemView getItemView(){
        synchronized (ItemView.class){
            if(itemView == null){
                itemView = new ItemView(new ItemsController());
            }
        }

        return itemView;
    }

    /**
	 * Add an item to the interface which the user can click.
	 *
	 * @param items a list contains all of the item which should be displayed in the interface
	 */
    public void updateItems(List<Item> items) {
    	 if(items!=null) {
    		 for(int i=0;i<items.size();i++) {	 
    			final Item tmp = items.get(i);
    			 
    			 //create ImageView to each of the items
        		 ImageView item_v = new ImageView();
        		 item_v.setOnMouseClicked(new EventHandler<MouseEvent>(){

					public void handle(MouseEvent arg0) {
						// TODO Auto-generated method stub
						controller.pickUp(tmp);
					}
        			 
        		 });
        		 
        		 //create item's image.
        		 URL url = this.getClass().getResource("/images/"+tmp.getItemName()+".png");
             	 Image image = new Image(url.toString(),65.0,65.0,false,false); 
        	     item_v.setImage(image);
        	     
        	     //set the item's position.
        	     item_v.setLayoutX(tmp.getItemPositionX());
        		 item_v.setLayoutY(tmp.getItemPositionY());
        		 
        		 //add each ImageView to the interface
        	     page.getChildren().add(item_v);
        	     
        	 }
    	 }
    }
}
