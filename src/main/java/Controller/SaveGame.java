package Controller;

import Model.Item;
import Model.Location;
import Model.User;
import Model.World;
import Utils.ReadJSON;

import java.util.List;
import java.util.Map;

public class SaveGame {
    private String userSave;
    private String worldSave;

    public SaveGame(String userSave, String worldSave) {
        this.userSave = userSave;
        this.worldSave = worldSave;
    }

    void saveUser(String userID) {
        StringBuilder saveData = new StringBuilder("[\n");
        List<User> users = World.getWorld().getAllUsers();
        for(User user : users) {
            saveData.append("\t{ \"player_0\":\n");
            saveData.append("\t\t{\n");
            saveData.append("\t\t\t\"id\": \"").append(user.getUserID()).append("\",\n");
            saveData.append("\t\t\t\"name\": \"").append(user.getUserID()).append("\",\n");
            saveData.append("\t\t\t\"currentLocation\": \"").append(user.getCurrentLocation().getLocationID()).append("\",\n");
            saveData.append("\t\t\t\"items\": [\n");
            if(user.getBag().size() != 0) {
                for(Item item : user.getBag()) {
                    saveData.append(this.saveItem(item));
                }
            }
	        if(saveData.charAt(saveData.length() - 2) == ',')
		        saveData = new StringBuilder(saveData.substring(0, saveData.length() - 2));
            saveData.append("\n\t\t\t]\n");
            saveData.append("\t\t}\n");
            saveData.append("\t}\n");
        }
        saveData.append("]");
        ReadJSON.writeJSON("User.json", saveData.toString());
    }

    void saveWorld() {
        StringBuilder saveData = new StringBuilder("[\n");
        List<Location> locations = World.getWorld().getAllLocations();
        for(Location location : locations) {
            saveData.append("\t{\n\t\t\"id\": \"").append(location.getLocationID()).append("\",\n");
            saveData.append("\t\t\"name\": \"").append(location.getLocationName()).append("\",\n");
            saveData.append("\t\t\"filePath\": \"").append(location.getLocationName()).append("\",\n");
            saveData.append("\t\t\"neighbors\": [\n");
            for (Map.Entry<Integer, Location> neighbor: location.getNeighbors().entrySet()) {
                saveData.append("\t\t\t{\n");
                saveData.append("\t\t\t\t\"id\": \"").append(neighbor.getValue().getLocationID()).append("\",\n");
                saveData.append("\t\t\t\t\"angle\": ").append(neighbor.getKey()).append("\n\t\t\t},\n");
            }
	        saveData = new StringBuilder(saveData.substring(0, saveData.length() - 2));
            saveData.append("\n\t\t],\n");
            saveData.append("\t\t\"items\": [\n");
            for (Item item : location.getItems()) {
                saveData.append(this.saveItem(item));
            }
            if(saveData.charAt(saveData.length() - 2) == ',')
	            saveData = new StringBuilder(saveData.substring(0, saveData.length() - 2));
            saveData.append("\n\t\t]\n\t},\n");
        }
	    saveData = new StringBuilder(saveData.substring(0, saveData.length() - 2));
        saveData.append("\n]");
        ReadJSON.writeJSON("Location.json", saveData.toString());
    }

    private String saveItem(Item item) {
        String saveData = "";
        saveData += "\t\t\t{\n";
        saveData += "\t\t\t\t\"id\": \"" + item.getItemID() + "\",\n";
        saveData += "\t\t\t\t\"name\": \"" + item.getItemName() + "\",\n";
        saveData += "\t\t\t\t\"filePath\": \"" + item.getItemName() + "\",\n";
        saveData += "\t\t\t\t\"positionX\": " + item.getItemPositionX() + ",\n";
        saveData += "\t\t\t\t\"positionY\": " + item.getItemPositionY() + "\n";
        saveData += "\t\t\t},\n";
        return saveData;
    }
}
