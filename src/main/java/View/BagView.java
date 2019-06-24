package View;

import java.net.URL;
import java.util.*;

import Controller.BagController;
import Controller.ViewController;
import Model.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * An view for the virtual world application which uses JavaFx
 * and allows the users to look up items in their bag. 
 * Users also can bring out the item by clicking the item and confirm the action.
 * 
 * @author  Danyang Yu;
 * @version 1.0;
 */
public class BagView {

	private static volatile BagView bagview = null;
	private ViewController viewcontroller = null;
	private BagController bagcontroller = null;
	private GridPane inBag;
	private Button confirm;
	private Button close;
	private int row=3;
	private int column=3;
	private double image_h = 50.0;
	private double image_w =50.0;
	private TitledPane bagView;
	private AnchorPane itemspage;
	private ImageView imageView;

	public BagView(ViewController viewcontroller){
          this.viewcontroller = viewcontroller;
          this.bagcontroller = viewcontroller.getBagController();
          this.inBag = viewcontroller.getInBag();
		  this.bagView = viewcontroller.getBagView();
		  this.confirm = viewcontroller.getConfirm();
		  this.close = viewcontroller.getClose();
		  this.itemspage = viewcontroller.getItemsPage();
		  this.imageView = viewcontroller.getImageView();
		  close.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bagView.setVisible(false);
			}
		  });  
		  confirm.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				bagcontroller.showChoice();
				bagView.setVisible(false);
			}
			  
		  });
	}

	public void setViewController(ViewController controller){this.viewcontroller = controller;}
	public ViewController getViewController(){return viewcontroller;}


	public void setBagController(BagController controller){this.bagcontroller = controller;}
	public BagController getLocationController(){return bagcontroller;}

	public static BagView getBagView(){
		synchronized (BagView.class){
			if(bagview == null){
				bagview = new BagView(ViewController.getViewController());
			}
		}

		return bagview;
	}

	public void updateBag(final List<Item> bag) {
		System.out.println(bag.size());
		inBag.getChildren().clear();
		int r = 0;
		int c = 0;
		List<String> bagItems = new LinkedList<String>();
		for (Item item: bag){
			bagItems.add(item.getItemName());
		}

		if (bagItems != null) {
			//convert the list to set.
			Set<String> uniqueSet = new HashSet<String>(bagItems);
			for (final String tmp_name : uniqueSet) {

				//Item item_bag = null;
				if (r > row - 1) {
					System.out.println("ERROR");
					break;
				}


				//create border pane for each item
				final BorderPane item = new BorderPane();

				//create label to show the number of item
				Label item_num = new Label("" + Collections.frequency(bagItems, tmp_name));

				item.setBottom(item_num);

				//create ImageView to each of the items
				final ImageView item_img = new ImageView();
				URL url = this.getClass().getResource("/images/" + tmp_name + ".png");
				final Image image = new Image(url.toString(), image_h, image_w, false, false);
				//final String style = "-fx-background-color:  #ffffff";
				final GaussianBlur effect = new GaussianBlur();
				
				//set image on-click effect
				item_img.setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent arg0) {

						// TODO Auto-generated method stub
						/*
						if (item.getStyle() == style) {
							item.setStyle("");
							bagcontroller.unselect(tmp_name);
						} else {
							item.setStyle(style);
						}
						*/
						if(item_img.getEffect() == effect){
							item_img.setEffect(null);
							bagcontroller.unselect();
						}
						else{
							item_img.setEffect(effect);
							for(int i=0; i<bag.size();i++){
								if(bag.get(i).getItemName()==tmp_name){
									bagcontroller.select(bag.get(i));
									break;
								}
							}
						}
					}

				});
				

				//create item's image.
				item_img.setImage(image);

				item.setCenter(item_img);
				//set the item's position.
				inBag.add(item, c, r);
				if (c < column - 1) {
					c++;
				} else {
					c = 0;
					r++;
				}

			}

		}
	}

	/*
	
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

	*/


    
    /**for test the update bag method
     * after the controller is finished, this method can be removed
     * */

    /*
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

	*/
}
