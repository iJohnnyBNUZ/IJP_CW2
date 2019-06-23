package Controller;

import Model.Item;
import Model.User;
import Model.World;

public class ItemsController {
	public void pickUp(Item item) {
		// Temporary workaround until we implement some way to get users by String
		User user = World.getWorld().getAllUsers().get(0);
		user.addItem(item);
		item.setPosition(-1.0,-1.0);
		user.updateBagView();
	}

	public void putDown(Item item) {
		// Temporary workaround until we implement some way to get users by String
		User user = World.getWorld().getAllUsers().get(0);
		user.removeItem(item);
		user.getCurrentLocation().addItem(item);
	}
}
