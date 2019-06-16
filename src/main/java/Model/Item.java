package Model;

public class Item {
    private String itemName;
    private int ItemPositionX;
    private int ItemPositionY;

    public void setPosition(int x, int y){
        this.ItemPositionX = x;
        this.ItemPositionY = y;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPositionX() {
        return ItemPositionX;
    }

    public void setItemPositionX(int itemPositionX) {
        ItemPositionX = itemPositionX;
    }

    public int getItemPositionY() {
        return ItemPositionY;
    }

    public void setItemPositionY(int itemPositionY) {
        ItemPositionY = itemPositionY;
    }
}
