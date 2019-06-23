package View;

import java.net.URL;
import java.util.List;

import Controller.ViewController;
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
	
    private static volatile ItemView itemView = null;
    private ViewController viewcontroller = null;
    private ItemsController itemscontroller = null;
    private AnchorPane page;

    public ItemView(ViewController viewcontroller){
    	this.viewcontroller = viewcontroller;
    	this.itemscontroller = viewcontroller.getItemsController();
    	this.page = viewcontroller.getItemsPage();
	}

    
    public static ItemView getItemView(){
        synchronized (ItemView.class){
            if(itemView == null){
                itemView = new ItemView(ViewController.getViewController());
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
             //page.getChildren().clear();
    		 for(int i=0;i<items.size();i++) {	 
    			final Item tmp = items.get(i);
    			 
    			 //create ImageView to each of the items
        		 ImageView item_v = new ImageView();
        		 item_v.setOnMouseClicked(new EventHandler<MouseEvent>(){
					public void handle(MouseEvent arg0) {
						// TODO Auto-generated method stub
						itemscontroller.pickUp(tmp);
						page.getChildren().remove(item_v);
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
