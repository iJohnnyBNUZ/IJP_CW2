package View;

import java.util.List;

public class ItemView {
    private static volatile ItemView itemView = null;

    private ItemView(){}

    public static ItemView getItemView(){
        synchronized (ItemView.class){
            if(itemView == null){
                itemView = new ItemView();
            }
        }

        return itemView;
    }

    public void updateItems(List<String> items) {

    }
}
