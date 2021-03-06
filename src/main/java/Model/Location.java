package Model;

import View.ItemView;
import View.LocationView;

import java.util.*;

public class Location {
    private String locationName;
    private HashMap<Integer,Location> neighbors = new HashMap<Integer, Location>();
    private List<Item> items = new ArrayList<Item>();
    private String locationID;

    public Location() {
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationID() { return this.locationID; }

    public void setLocationID(String id) { this.locationID = id; }

    public HashMap<Integer, Location> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(HashMap<Integer, Location> neighbors) {
        this.neighbors = neighbors;
    }

    public void addNeighbor(Location location, Integer angle) { neighbors.put(angle, location); }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<String> getIemsName(){
        List<String> itemnames = new ArrayList<String>();

        for(int i = 0; i < items.size();i++){
            itemnames.add(items.get(i).getItemName());
        }
        return itemnames;
    }

    public List<Item> getItems(){
        return items;
    }

    public void addItem(Item item){
        this.items.add(item);
//        ItemView.getItemView().updateItems(getItems());
    }

    public void removeItem(Item item){
        this.items.remove(item);
        //ItemView.getItemView().updateItems(getItems());
    }

    public Location getLocationAtAngle(int angle){
        if(neighbors.containsKey(angle)){
            return neighbors.get(angle);
        }else{
            return null;
        }
    }

    public void initialLocation(){
        List<Integer> arrowAngles = new LinkedList<Integer>();
        Set<Map.Entry<Integer, Location>> eSet = getNeighbors().entrySet();
        Iterator<Map.Entry<Integer, Location>> it = eSet.iterator();
        while (it.hasNext()){
            arrowAngles.add(it.next().getKey());
        }

        Collections.sort(arrowAngles);
        World world = World.getWorld();
        LocationView.getLocationView().updateLocation(getLocationName(),arrowAngles );

    }

    public void initialItems(){
        ItemView.getItemView().updateItems(this.items);
    }
}
