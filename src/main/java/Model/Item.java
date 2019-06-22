package Model;

public class Item {
    private String ItemID;
    private String ItemName;
    private double ItemPositionX;
    private double ItemPositionY;

    public Item() {
    }

    public void setPosition(double x, double y){
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

    public double getItemPositionY() {
        return ItemPositionY;
    }
}
