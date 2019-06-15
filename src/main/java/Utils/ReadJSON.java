package Utils;

import Model.CurrentLocation;
import com.google.gson.Gson;

public class ReadJSON {

    public static CurrentLocation readCurLocationJSON(String path){
        Gson gson = new Gson();
        CurrentLocation currentLocation = new CurrentLocation();
        String json = "";

        try {
            json = FileUtils.readFile(path);
        }catch (Exception e){
            e.printStackTrace();
        }

        currentLocation = gson.fromJson(json, CurrentLocation.class);

        return  currentLocation;
    }
}
