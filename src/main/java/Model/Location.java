package Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Location {
    private String locationName;
    private HashMap<Integer,Location> neighbors;
    private List<Item> items;

    public List<String> getItems(){

        List<String> itemnames = Arrays. asList("one", "two", "three");
        return itemnames;
    }

    public void addItem(Item item){

    }

    public void removeItem(Item item){

    }

    public Location getLocationAtAngle(){
        return this;
    }
}
