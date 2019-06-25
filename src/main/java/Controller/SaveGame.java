package Controller;

import Model.Item;
import Model.Location;
import Model.User;
import Model.World;

import java.util.List;
import java.util.Map;

public class SaveGame {
    private String userSave;
    private String worldSave;

    public SaveGame(String userSave, String worldSave) {
        this.userSave = userSave;
        this.worldSave = worldSave;
    }

    public void saveUser(String userID) {
        String saveData = "[";
        List<User> users = World.getWorld().getAllUsers();
        for(User user : users) {
            saveData += "\t{ \"player_0\":\n";
            saveData += "\t\t{\n";
            saveData += "\t\t\t\"id\": \"" + user.getUserID() + "\",\n";
            saveData += "\t\t\t\"name\": \"" + user.getUserID() + "\",\n";
            saveData += "\t\t\t\"currentLocation\": \"" + user.getCurrentLocation() + "\",\n";
            saveData += "\t\t\t\"items\": [\n";
            if(user.getBag().size() != 0) {
                for(Item item : user.getBag()) {
                    saveData += this.saveItem(item);
                }
            }
            saveData += "\t\t\t]";
        }
    }

    public void saveWorld() {
        String saveData = "[";
        List<Location> locations = World.getWorld().getAllLocations();
        for(Location location : locations) {
            saveData += "\n\t{\n\t\t\"id\": \"" + location.getLocationID() + "\",\n\t\t";
            saveData += "\"name\": \"" + location.getLocationName() + "\",\n\t\t";
            saveData += "\"filePath\": \"" + location.getLocationName() + "\",\n\t\t";
            saveData += "\"neighbors\": [\n";
            for (Map.Entry<Integer, Location> neighbor: location.getNeighbors().entrySet()) {
                saveData += "\t\t\t{\n\t\t\t\t";
                saveData += "\"id\": \"" + neighbor.getValue().getLocationID() + "\",\n\t\t\t\t";
                saveData += "\"angle\": " + neighbor.getKey() + "\n\t\t\t},";
            }
            saveData += "\t\t],\n\t\t";
            saveData += "\"items\": [\n";
            for (Item item : location.getItems()) {
                saveData += this.saveItem(item);
            }
            saveData += "\t\t]\t},";
        }
        this.saveToFile(saveData);
    }

    private String saveItem(Item item) {
        String saveData = "";
        saveData += "\t\t\t{";
        saveData += "\t\t\t\t\"id\": \"" + item.getItemID() + "\",\n";
        saveData += "\t\t\t\t\"name\": \"" + item.getItemName() + "\",\n";
        saveData += "\t\t\t\t\"filePath\": \"" + item.getItemName() + "\",\n";
        saveData += "\t\t\t\t\"positionX\": \"" + item.getItemPositionX() + "\",\n";
        saveData += "\t\t\t\t\"positionY\": \"" + item.getItemPositionY() + "\",\n";
        saveData += "\t\t\t},\n";
        return saveData;
    }

    private void saveToFile(String saveData) {

    }
}
