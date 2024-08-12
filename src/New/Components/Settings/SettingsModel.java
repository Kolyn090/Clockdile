package New.Components.Settings;

public class SettingsModel {
    private int intervalAmount = 1;
    private final int maxInterval;

    public SettingsModel(int maxInterval) {
        this.maxInterval = maxInterval;
    }

    public void incInterval() {
        setInterval(intervalAmount + 1);
    }

    public void decInterval() {
        setInterval(intervalAmount - 1);
    }

    private void setInterval(int newAmount) {
        if (newAmount > maxInterval) {
            intervalAmount = 1;
        } else if (newAmount < 1) {
            intervalAmount = maxInterval;
        } else {
            intervalAmount = newAmount;
        }
    }

    public int getInterval() {
        return intervalAmount;
    }
}
