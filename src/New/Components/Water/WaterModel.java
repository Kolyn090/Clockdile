package New.Components.Water;

public class WaterModel {
    private int amount = 0;

    public void increaseAmount() {
        amount++;
    }

    public void decreaseAmount() {
        amount--;
    }

    public int getAmount() {
        return amount;
    }
}
