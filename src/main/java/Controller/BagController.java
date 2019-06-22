package Controller;

import Model.World;

public class BagController {
	public void removeFromBag () {
		
	}
	
	public void select(String item) {

	}

	public void unselect(String tmp_name) {
		// TODO Auto-generated method stub
		
	}

	public void itemPosition(double position_x, double position_y) {
		World.getWorld().getLocationByID(World.getWorld().getAllUsers().get(0).getCurrentLocation().getLocationID()).getItems();
	}

}
