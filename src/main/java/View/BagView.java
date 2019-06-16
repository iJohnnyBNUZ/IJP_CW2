package View;

import java.util.List;

public class BagView {
    private static volatile BagView bagView = null;

    private BagView(){}

    public static BagView getBagView(){
        synchronized (BagView.class){
            if(bagView == null){
                bagView = new BagView();
            }
        }

        return bagView;
    }

    public void updateBag(List<String> bagItems) {

    }
}
