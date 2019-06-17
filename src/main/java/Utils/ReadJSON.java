package Utils;

import Model.Location;
import Model.User;
import com.google.gson.Gson;

public class ReadJSON {

    public static Location readCurLocationJSON(String path){
        Gson gson = new Gson();
        Location location;
        String json = "";

        try {
            json = FileUtils.readFile(path);
        }catch (Exception e){
            e.printStackTrace();
        }

        location = gson.fromJson(json, Location.class);

        return location;
    }

    public static User readUserJSON(String path){
        Gson gson = new Gson();
        User user;
        String json = "";

        try {
            json = FileUtils.readFile(path);
        }catch (Exception e){
            e.printStackTrace();
        }

        user = gson.fromJson(json, User.class);

        return user;
    }
}
