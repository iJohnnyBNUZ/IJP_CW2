package Model;

public class Item {
    private String ItemID;
    private String ItemName;
    private double ItemPositionX;
    private double ItemPositionY;

    public void setPosition(String itemID, double x, double y){
        this.ItemID = itemID;
        this.ItemPositionX = x;
        this.ItemPositionY = y;
    }


    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        this.ItemName = itemName;
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
