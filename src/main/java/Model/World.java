package Model;

import java.util.List;

public class World {

    private static volatile World world = null;

    private World(){}

    public static World getWorld(){
        if (world == null){
            synchronized (World.class){
                if (world == null){
                    world = new World();
                }
            }
        }
        
        return world;
    }

    private List<Location> allLocations;
    private List<User> allUsers;

    public List<Location> getAllLocations() {
        return allLocations;
    }

    public void setAllLocations(List<Location> allLocations) {
        this.allLocations = allLocations;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    public void addUser(User newUser) {allUsers.add(newUser);}

    public void addLocation(Location newLocation) {allLocations.add(newLocation);}
}
