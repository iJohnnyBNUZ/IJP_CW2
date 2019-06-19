import org.junit.Test;
import Utils.ReadJSON;

public class JSONTest {

    @Test
    public void testReadJSON(){
        String c = ReadJSON.getJSON(getClass().getResource("config/Location.json").getFile());
        System.out.println(c);
    }
}
