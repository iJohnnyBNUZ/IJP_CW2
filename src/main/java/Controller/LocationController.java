package Controller;

import Model.User;
import Model.World;

public class LocationController {

    public void moveToDirection(Integer arrowAngle){
        // Temporary workaround until we implement some way to get users by String
        User user = World.getWorld().getAllUsers().get(0);
        user.moveInDirection(arrowAngle);
    }
}
