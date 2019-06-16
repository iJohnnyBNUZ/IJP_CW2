package Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Location {
    private String locationName;
    private HashMap<Integer,Location> neighbors;
    private List<Item> items;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public HashMap<Integer, Location> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(HashMap<Integer, Location> neighbors) {
        this.neighbors = neighbors;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<String> getItems(){

        List<String> itemnames = Arrays. asList("one", "two", "three");
        return itemnames;
    }

    public void addItem(Item item){

    }

    public void removeItem(Item item){

    }

    public Location getLocationAtAngle(int angle){
        return this;
    }
}
