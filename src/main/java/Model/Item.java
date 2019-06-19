package Model;

public class Item {
    private String itemName;
    private double ItemPositionX;
    private double ItemPositionY;

    public void setPosition(double x, double y){
        this.ItemPositionX = x;
        this.ItemPositionY = y;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPositionX() {
        return ItemPositionX;
    }

    public void setItemPositionX(double itemPositionX) {
        ItemPositionX = itemPositionX;
    }

    public double getItemPositionY() {
        return ItemPositionY;
    }

    public void setItemPositionY(double itemPositionY) {
        ItemPositionY = itemPositionY;
    }
}
