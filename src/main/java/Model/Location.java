package Model;

import View.ItemView;
import View.LocationView;

import java.util.*;

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

    public List<String> getIemsName(){
        List<String> itemnames = new ArrayList<>();

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
        ItemView.getItemView().updateItems(getItems());
    }

    public void removeItem(Item item){
        this.items.remove(item);
        ItemView.getItemView().updateItems(getItems());
    }

    public Location getLocationAtAngle(int angle){
        int i = angle/60;
        if(neighbors.containsKey(i)){
            return neighbors.get(i);
        }else{
            return null;
        }
    }

    public void initialLocation(){
        List<Integer> arrowAngles = new LinkedList<>();
        Set<Map.Entry<Integer, Location>> eSet = getNeighbors().entrySet();
        Iterator<Map.Entry<Integer, Location>> it = eSet.iterator();
        while (it.hasNext()){
            arrowAngles.add(it.next().getKey());
        }

        LocationView.getLocationView().updateLocation(getLocationName(),arrowAngles );
        ItemView.getItemView().updateItems(this.items);
    }
}
