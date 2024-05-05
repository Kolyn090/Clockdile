package Backend.Tools;

public class WaterReminder {

    private int waterTracker = 0;
    private boolean watchStarted = false;

    public WaterReminder() {

    }



    public void addWater() {
        if (waterTracker < 8) {
            waterTracker++;
        }
    }

    public void removeWater() {
        if (waterTracker >= 1) {
            waterTracker--;
        }
    }

    public int getWaterTracker() {
        return waterTracker;
    }
    public void setWaterTracker(int waterTracker) {
        this.waterTracker = waterTracker;
    }
    public boolean isWatchStarted() {
        return watchStarted;
    }
    public void pressWatch() {
        watchStarted = !watchStarted;
    }
    public void watchStopped() {
        watchStarted = false;
    }
}
