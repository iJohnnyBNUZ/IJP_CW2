import Model.CurrentLocation;
import Model.Item;
import Model.NextLocation;
import com.google.gson.Gson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JSONTest {

    @Test
    public void test(){
        List<Item> items = new ArrayList<Item>();
        Item item1 = new Item();
        item1.setItemName("apple");
        item1.setItemPath("/item/blaaaa");
        items.add(item1);

        Item item2 = new Item();
        item2.setItemName("beef");
        item2.setItemPath("/item/bla");
        items.add(item2);

        List<NextLocation> nextLocations = new ArrayList<NextLocation>();
        NextLocation nextLocation1 = new NextLocation();
        nextLocation1.setAngle(20);
        nextLocation1.setImgPath("/bla/bla");
        nextLocations.add(nextLocation1);

        NextLocation nextLocation2 = new NextLocation();
        nextLocation2.setAngle(45);
        nextLocation2.setImgPath("/bla/bla/bla");
        nextLocations.add(nextLocation2);

        CurrentLocation currentLocation = new CurrentLocation();
        currentLocation.setLocationName("reception");
        currentLocation.setItemList(items);
        currentLocation.setNextLocations(nextLocations);

        Gson gson = new Gson();
        System.out.println(gson.toJson(currentLocation));

    }
}
