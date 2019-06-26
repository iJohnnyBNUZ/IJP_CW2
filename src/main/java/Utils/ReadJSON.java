package Utils;

import Model.Location;
import Model.User;
import com.google.gson.Gson;

public class ReadJSON {

    public static String getJSON(String path){
        String json = "";
        System.out.println("Loading from " + path);
        try {
            json = FileUtils.readFile(path);
        } catch (Exception e){
            e.printStackTrace();
        }

        return json;
    }

    public static void writeJSON(String path, String data) {
        try {
            FileUtils.writeFile(path, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
