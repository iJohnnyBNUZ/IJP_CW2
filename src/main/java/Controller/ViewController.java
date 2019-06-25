package Controller;

import Model.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;



public class ViewController {

    /*
    @FXML
    private AnchorPane page;
    */

    @FXML
    private ImageView imageView;

    @FXML
    private HBox buttonBox;

    @FXML
    private MenuItem menu_bag;
    

   

	@FXML
    private MenuItem menu_position;

    @FXML
    private AnchorPane itemspage;

    @FXML
    private GridPane inBag;

    @FXML
    private TitledPane bagView;

    @FXML
    private Button putDown;

    @FXML
    private Button close;


    private static volatile ViewController viewcontroller;

    private LocationController locationcontroller = null;
    private ItemsController itemscontroller = null;
    private BagController bagcontroller = null;
    private SaveGame saveGame= null;


    public static void setViewController(ViewController controller){
        viewcontroller = controller;
    }

    public static ViewController getViewController(){
        return viewcontroller;
    }


    public AnchorPane getItemsPage(){
        return itemspage;
    }

    public ImageView getImageView(){
        return imageView;
    }

    public HBox getHBox(){
        return buttonBox;
    }

    public MenuItem getMenuBag(){
        return menu_bag;
    }

    
    public MenuItem getMenuPosition(){
        return menu_position;
    }

    public GridPane getInBag(){ return inBag; }

    public TitledPane getBagView(){ return bagView; }

    public Button getPutDown(){ return putDown; }

    public Button getClose(){ return close; }

    public void setLocationController(LocationController controller){
        this.locationcontroller = controller;
    }

    public LocationController getLocationController(){
        return locationcontroller;
    }

    public void setItemsController(ItemsController controller){
        this.itemscontroller = controller;
    }

    public ItemsController getItemsController(){
        return itemscontroller;
    }

    public void setBagController(BagController controller){
        this.bagcontroller = controller;
    }

    public BagController getBagController(){
        return bagcontroller;
    }

    

    public SaveGame getSaveGame() {
		return saveGame;
	}

	public void setSaveGame(SaveGame saveGame) {
		this.saveGame = saveGame;
	}

	public void save() {
		saveGame.saveUser(World.getWorld().getAllUsers().get(0).getUserID());
		saveGame.saveWorld();
    	System.out.println("Save Game");
    }


}
