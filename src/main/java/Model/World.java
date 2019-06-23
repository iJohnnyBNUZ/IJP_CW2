package Model;

import java.util.ArrayList;
import java.util.List;

public class World {

    private static volatile World world = null;

    private World(){}

    public static World getWorld(){
    	System.out.println("have");
        if (world == null){
            synchronized (World.class){
                if (world == null){
                    world = new World();
                }
            }
        }
        
        
        return world;
    }

    private List<Location> allLocations = new ArrayList<Location>();
    private List<User> allUsers = new ArrayList<User>();

    public List<Location> getAllLocations() {
        return allLocations;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void addUser(User newUser) {allUsers.add(newUser);}

    public void addLocation(Location newLocation) {allLocations.add(newLocation);}

    public Location getLocationByID(String id) throws NullPointerException {
        if(allLocations.size() != 0) {
            for (Location allLocation : allLocations) {
                if (allLocation.getLocationID().equals(id)) {
                    return allLocation;
                }
            }
        }
        return null;
    }
}
