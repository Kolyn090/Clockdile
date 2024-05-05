package Master;


import Backend.Tools.AppTracker;
import Frontend.UI;

public class Clockdile {

    public static void main(String[] args) {
        AppTracker appTracker = new AppTracker();
        UI ui = new UI(appTracker);
        if (appTracker.getCurrentClock() == 0) {
            ui.assignStopWatchScreen();
        } else if (appTracker.getCurrentClock() == 1) {
            ui.assignCountDownScreen();
        }
    }
}
