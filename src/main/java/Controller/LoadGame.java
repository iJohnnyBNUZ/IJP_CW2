package Controller;

import Model.Location;
import Model.User;
import Model.World;
import Utils.ReadJSON;

import java.io.IOException;

public class LoadGame {
    private String userSave;
    private String worldSave;

    public LoadGame(String userSave, String worldSave) {
        this.userSave = userSave;
        this.worldSave = worldSave;
    }

    public void loadWorld() {
        Location location = ReadJSON.readCurLocationJSON(worldSave);
        World.getWorld().addLocation(location);
    }

    public void loadUser() {
        User user = ReadJSON.readUserJSON(userSave);
        World.getWorld().addUser(user);
    }
}
