package Controller;

import Model.Item;
import Model.User;
import Model.World;
import View.ItemView;

public class BagController {
	private Item selected;

	public void removeFromBag (Item item) {
		User user = World.getWorld().getAllUsers().get(0);
		user.removeItem(item);
		user.updateBagView();
	}

	
	public void select(Item item) {
		this.selected =item;

	}

	public void unselect() {
		// TODO Auto-generated method stub
		this.selected = null;
	}
	
	/*
	public void itemPosition(double position_x, double position_y) {
		World.getWorld().getLocationByID(World.getWorld().getAllUsers().get(0).getCurrentLocation().getLocationID()).getItems();
	}
	*/
	
	public void addToLocation() {
		if(this.selected==null) {
			System.out.println("User selected nothing");
		}else {
			ItemView.getItemView().addNewItems(this.selected);
			removeFromBag(this.selected);
		}
		
	}
	

}
