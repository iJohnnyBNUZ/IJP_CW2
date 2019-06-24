package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Item;
import Model.User;
import Model.World;
import View.ItemView;
import View.LocationView;

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
	
	public void showChoice() {
		if(this.selected==null) {
			System.out.println("User selected nothing");
		}else {
			LocationView.getLocationView().showChoice();
			System.out.println("Show choice interface.");
			
			
		}
		
	}
	
	public void addToLocation(List<Double> position) {
		LocationView.getLocationView().removeChoice();
		this.selected.setPosition(position.get(0), position.get(1));
		System.out.println("Get position.");
		ItemView.getItemView().addNewItems(this.selected);
		System.out.println("add new items on the interface.");
		removeFromBag(this.selected);
	}

	
}
