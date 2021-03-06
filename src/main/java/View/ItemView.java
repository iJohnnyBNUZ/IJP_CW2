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
	private double image_h = 60.0;
	private double image_w =60.0;

    public ItemView(ViewController viewcontroller){
    	this.viewcontroller = viewcontroller;
    	this.itemscontroller = viewcontroller.getItemsController();
    	this.page = viewcontroller.getItemsPage();
	}

	public void setViewController(ViewController controller){this.viewcontroller = controller;}
	public ViewController getViewController(){return viewcontroller;}

	public void setItemController(ItemsController controller){this.itemscontroller = controller;}
	public ItemsController getItemController(){return itemscontroller;}


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
    		 for(int i=0;i<items.size();i++) {
    			final Item tmp = items.get(i);
    			 
    			 //create ImageView to each of the items
        		 final ImageView item_v = new ImageView();
        		 item_v.setOnMouseClicked(new EventHandler<MouseEvent>(){
					public void handle(MouseEvent arg0) {
						// TODO Auto-generated method stub
						itemscontroller.pickUp(tmp);
						page.getChildren().remove(item_v);
					}
        		 });
        		 
        		 //create item's image.
        		 URL url = this.getClass().getResource("/images/"+tmp.getItemName()+".png");
             	 Image image = new Image(url.toString(),image_h,image_w,false,false); 
        	     item_v.setImage(image);
        	     
        	     //set the item's position.
        	     item_v.setLayoutX(tmp.getItemPositionX());
        		 item_v.setLayoutY(tmp.getItemPositionY());
        		 
        		 //add each ImageView to the interface
        	     page.getChildren().add(item_v);
        	     
        	 }
    	 }
    }
    
    public void addNewItems(Item item) {
    	if(item!=null) {
    		final Item tmp = item;
    		final ImageView item_v = new ImageView();
    		item_v.setOnMouseClicked(new EventHandler<MouseEvent>() {

				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					itemscontroller.pickUp(tmp);
					page.getChildren().remove(item_v);
				}
    			
    		});
    		
    		//create item's image.
    		URL url = this.getClass().getResource("/images/"+tmp.getItemName()+".png");
       	    Image image = new Image(url.toString(),image_h,image_w,false,false); 
  	        item_v.setImage(image);
  	     
  	        //set the item's position.
  	        item_v.setLayoutX(tmp.getItemPositionX()-image_h/2);
  		    item_v.setLayoutY(tmp.getItemPositionY()-image_w/2);
  		 
	  		//add each ImageView to the interface
	  	    page.getChildren().add(item_v);
    	}
    }
   
}
