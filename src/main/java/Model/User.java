package Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Item> bag;
    private Location currentLocation;

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

    }

    public void removeItem(Item item){

    }

    public void moveInDirection(int angle){

    }

    public void updateView(){

    }
}
