package Controller;

import Model.Item;
import Model.Location;
import Model.User;
import Model.World;
import Utils.ReadJSON;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LoadGame {
    private String userSave;
    private String worldSave;


    public LoadGame(String userSave, String worldSave) {
        this.userSave = userSave;
        this.worldSave = worldSave;
    }


    public void Initialise(ViewController viewcontroller){
        LocationController locationcontroller = new LocationController();
        ItemsController itemscontroller = new ItemsController();
        BagController bagcontroller = new BagController();
        viewcontroller.setLocationController(locationcontroller);
        viewcontroller.setItemsController(itemscontroller);
        viewcontroller.setBagController(bagcontroller);
        this.loadWorld();
        this.loadUser();
    }

    public void loadWorld() {
        String locationSaveString = ReadJSON.getJSON(getClass().getResource("/" + worldSave).getFile());
        JsonArray locationArray = new JsonParser().parse(locationSaveString).getAsJsonArray();

        for(int i = 0; i < locationArray.size(); i++) {
            String locationID = locationArray.get(i).getAsJsonObject().get("id").getAsString();

            if(World.getWorld().getLocationByID(locationID) == null) {
                Location location = new Location();
                this.buildLocation(location, locationArray.get(i).getAsJsonObject());
                World.getWorld().addLocation(location);
            }
            else {
                this.buildLocation(World.getWorld().getLocationByID(locationID), locationArray.get(i).getAsJsonObject());
            }
        }
    }

    public void loadUser() {
        String userSaveString = ReadJSON.getJSON(getClass().getResource("/" + userSave).getFile());
        JsonArray userArray = new JsonParser().parse(userSaveString).getAsJsonArray();
        for(int i = 0; i < userArray.size(); i++) {
            User user = new User();
            JsonObject userData = userArray.get(i).getAsJsonObject().get("player_0").getAsJsonObject();
            user.setUserID(userData.get("id").getAsString());
            user.setCurrentLocation(World.getWorld().getLocationByID(userData.get("currentLocation").getAsString()));
            for (int j = 0; j < userData.getAsJsonArray("items").size(); j++) {
                Item item = new Item();
                buildItem(item, userData.getAsJsonArray("items").get(j).getAsJsonObject());
                user.addItem(item);
            }
            World.getWorld().addUser(user);

        }
        World.getWorld().getAllUsers().get(0).getCurrentLocation().initialLocation();
        World.getWorld().getAllUsers().get(0).getCurrentLocation().initialItems();
    }

    private void buildLocation(Location location, JsonObject locData) {
        location.setLocationID(locData.get("id").getAsString());
        location.setLocationName(locData.get("filePath").getAsString());
        for (int i = 0; i < locData.getAsJsonArray("items").size(); i++) {
            Item item = new Item();
            buildItem(item, locData.getAsJsonArray("items").get(i).getAsJsonObject());
            location.addItem(item);
        }
        JsonArray neighborArray = locData.getAsJsonArray("neighbors");
        for(int i = 0; i < neighborArray.size(); i++) {
            if(World.getWorld().getLocationByID(neighborArray.get(i).getAsJsonObject().get("id").getAsString()) != null) {
                location.addNeighbor(World.getWorld().getLocationByID(neighborArray.get(i).getAsJsonObject().get("id").getAsString()), neighborArray.get(i).getAsJsonObject().get("angle").getAsInt());
            }
            else {
                Location newNeighbor = new Location();
                newNeighbor.setLocationID(neighborArray.get(i).getAsJsonObject().get("id").getAsString());
                World.getWorld().addLocation(newNeighbor);
                location.addNeighbor(newNeighbor, neighborArray.get(i).getAsJsonObject().get("angle").getAsInt());
            }
        }
    }

    private void buildItem(Item item, JsonObject itemData) {
        item.setItemID(itemData.get("id").getAsString());
        item.setItemName(itemData.get("name").getAsString());
        double x = itemData.get("positionX").getAsDouble();
        double y = itemData.get("positionY").getAsDouble();
        item.setPosition(x, y);
    }
}
