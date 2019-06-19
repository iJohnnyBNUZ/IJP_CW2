package Utils;

import Model.Location;
import Model.User;
import com.google.gson.Gson;

public class ReadJSON {

    public static String getJSON(String path){
        String json = "";
        try {
            json = FileUtils.readFile(path);
        }catch (Exception e){
            e.printStackTrace();
        }

        return json;
    }
}
