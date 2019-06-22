package Controller;

import Model.Location;
import Model.User;
import Model.World;

import java.util.List;

public class SaveGame {
    private String userSave;
    private String worldSave;

    private SaveGame(String userSave, String worldSave) {
        this.userSave = userSave;
        this.worldSave = worldSave;
    }

    public void saveUser(String userID) {
        User user = World.getWorld().getAllUsers().get(0);
    }

    public void saveWorld() {
        List<Location> locations = World.getWorld().getAllLocations();
        for(Location location : locations) {

        }
    }

    public void saveToFile() {

    }
}
