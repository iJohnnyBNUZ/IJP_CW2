package Model;

import java.util.List;

public class CurrentLocation {
    private String locationName;
    private List<Item> itemList;
    private List<NextLocation> nextLocations;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<NextLocation> getNextLocations() {
        return nextLocations;
    }

    public void setNextLocations(List<NextLocation> nextLocations) {
        this.nextLocations = nextLocations;
    }
}
