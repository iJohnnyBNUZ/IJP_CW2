package Controller;

import Model.Item;
import Model.User;
import Model.World;

public class BagController {

	public void removeFromBag (Item item) {
		User user = World.getWorld().getAllUsers().get(0);
		user.removeItem(item);
		user.updateBagView();
	}

	/*
	public void select(String item) {


	}

	public void unselect(String tmp_name) {
		// TODO Auto-generated method stub
		
	}

	public void itemPosition(double position_x, double position_y) {
		World.getWorld().getLocationByID(World.getWorld().getAllUsers().get(0).getCurrentLocation().getLocationID()).getItems();
	}
	*/

}
