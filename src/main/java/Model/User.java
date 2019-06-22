package Model;

import View.BagView;
import View.ItemView;
import View.LocationView;

import java.util.*;

public class User {
    private String userID;
    private List<Item> bag;
    private Location currentLocation;

    public User(String userID, List<Item> bag, Location currentLocation) {
        this.userID = userID;
        this.bag = bag;
        this.currentLocation = currentLocation;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<Item> getBag() {
        return bag;
    }

    public void setBag(List<Item> bag) {
        this.bag = bag;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public List<String> listBagItems(){
        List<String> bagItems = new ArrayList<>();
        for(Item item: bag){
            bagItems.add(item.getItemName());
        }
        return bagItems;
    }

    public void addItem(Item item){
        currentLocation.addItem(item);
    }

    public void removeItem(Item item){
        currentLocation.removeItem(item);
    }

    public void moveInDirection(int angle){
        currentLocation = currentLocation.getLocationAtAngle(angle);
    }

    public void updateView(){

//        get all keys from hashMap
        List<Integer> arrowAngles = new LinkedList<>();
        Set<Map.Entry<Integer, Location>> eSet = currentLocation.getNeighbors().entrySet();
        Iterator<Map.Entry<Integer, Location>> it = eSet.iterator();
        while (it.hasNext()){
            arrowAngles.add(it.next().getKey());
        }
//        Update location view
        LocationView.getLocationView().updateLocation(currentLocation.getLocationName(), arrowAngles);
//        Update item view
        ItemView.getItemView().updateItems(currentLocation.getItems());
//        Update bag view
        List<String> bagItems = new LinkedList<>();
        for (Item item: bag){
            bagItems.add(item.getItemName());
        }
        BagView.getBagView().updateBag(bagItems);
    }
}
