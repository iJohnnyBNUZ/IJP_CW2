package Utils;

import Model.Location;
import com.google.gson.Gson;

public class ReadJSON {

    public static Location readCurLocationJSON(String path){
        Gson gson = new Gson();
        Location location = new Location();
        String json = "";

        try {
            json = FileUtils.readFile(path);
        }catch (Exception e){
            e.printStackTrace();
        }

        location = gson.fromJson(json, Location.class);

        return location;
    }
}
