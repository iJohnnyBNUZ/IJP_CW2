package View;

import java.util.List;

public class LocationView {
    private static volatile LocationView locationView = null;

    private LocationView(){}

    public static LocationView getLocationView(){
        synchronized (LocationView.class){
            if(locationView == null){
                locationView = new LocationView();
            }
        }

        return locationView;
    }

    public void updateLocation(String locationName, List<Integer> arrowAngles) {

    }
}
