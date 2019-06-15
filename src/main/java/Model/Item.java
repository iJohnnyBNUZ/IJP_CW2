package Model;

public class Item {
    private String itemName;
    private int ItemPositionX;
    private int ItemPositionY;

    public void setPosition(int x, int y){
        this.ItemPositionX = x;
        this.ItemPositionY = y;
    }

    public int getItemPositionX() {
        return ItemPositionX;
    }

    public int getItemPositionY() {
        return ItemPositionY;
    }
}
