package Controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class SaveGameTest {

    @Test
    public void testSaveUser() {
        LoadGame lg = new LoadGame("config/User.json", "config/Location.json");

    }

    @Test
    public void testSaveWorld() {
    }
}