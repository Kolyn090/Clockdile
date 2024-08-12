package New.Components.Water;

public class WaterModel {
    private int amount = 0;
    private final int max;

    public WaterModel(int max) {
        this.max = max;
    }

    public void increaseAmount() {
        amount++;
        if (amount > max) {
            amount = max;
        }
    }

    public void decreaseAmount() {
        amount--;
        if (amount < 0) {
            amount = 0;
        }
    }

    public int getAmount() {
        return amount;
    }
}
