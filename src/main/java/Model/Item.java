package Model;

public class Item {
    private String ItemID;
    private String ItemName;
    private Double ItemPositionX;
    private Double ItemPositionY;

    public Item() {
    }

    public void setPosition(Double x, Double y){
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

    public Double getItemPositionX() {
        return ItemPositionX;
    }

    public Double getItemPositionY() {
        return ItemPositionY;
    }
}
