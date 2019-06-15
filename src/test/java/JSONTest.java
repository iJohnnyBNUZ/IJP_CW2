import Model.Location;
import org.junit.Test;
import Utils.ReadJSON;

public class JSONTest {

    @Test
    public void testReadJSON(){
        Location c = ReadJSON.readCurLocationJSON(getClass().getResource("config/Location.json").getFile());
        System.out.println();
    }
}
