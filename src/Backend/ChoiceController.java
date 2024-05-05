package Backend;

import Frontend.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ChoiceController {

    private final UI ui;
    private final ChoiceHandler choiceHandler = new ChoiceHandler();
    private Timer realTimer;
    private int previousClock;

    public ChoiceController(UI ui) {
        this.ui = ui;
    }

    public class ChoiceHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Resume":
                    if (ui.getAppTracker().getCurrentClock() == 2) {
                        break;
                    }
                    if (ui.getAppTracker().getWaterReminder().isWatchStarted()) {
                        if (ui.getAppTracker().getCurrentClock() == 0) {
                            ui.getTimeTracker().stop();
                        } else {
                            ui.getTimeTracker().stopCountDown();
                        }
                    } else {
                        if (ui.getAppTracker().getCurrentClock() == 0) {
                            ui.getTimeTracker().start();
                        } else {
                            ui.getTimeTracker().startCountDown();
                        }
                    }
                    ui.getAppTracker().getWaterReminder().pressWatch();
                    break;
                case "Reset":
                    if (ui.getAppTracker().getCurrentClock() == 2) {
                        break;
                    }
                    ui.getAppTracker().getWaterReminder().watchStopped();
                    if (ui.getAppTracker().getCurrentClock() == 0) {
                        ui.getTimeTracker().reset(0);
                    } else {
                        ui.getTimeTracker().resetCountDown(ui.getAppTracker().getModeSetting().getInterval()*60000,
                                ui.getAppTracker().getModeSetting().getInterval());
                    }

                    break;
                case "ChangeMode":
                    if (ui.getAppTracker().getCurrentClock() == 2) {
                        break;
                    }

                    if (ui.getAppTracker().getCurrentClock() == 0) {
                        ui.assignCountDownScreen();
                        ui.getAppTracker().setCurrentClock(1);
                        ui.getAppTracker().rewriteCurrenClock(1 + "");
                    } else {
                        ui.assignStopWatchScreen();
                        ui.getAppTracker().setCurrentClock(0);
                        ui.getAppTracker().rewriteCurrenClock(0 + "");
                    }
                    break;
                case "AddWater":
                    ui.getAppTracker().getWaterReminder().addWater();
                    ui.getWaterLabel().setIcon(new ImageIcon("lib/Images/Water/" + ui.getAppTracker().getWaterReminder().getWaterTracker() + ".png"));
                    ui.getWaterLabel().revalidate();
                    ui.getWaterLabel().repaint();
                    ui.getAppTracker().rewriteWaterAmount(ui.getAppTracker().getWaterReminder().getWaterTracker()+"");
                    break;
                case "ReduceWater":
                    ui.getAppTracker().getWaterReminder().removeWater();
                    ui.getWaterLabel().setIcon(new ImageIcon("lib/Images/Water/" + ui.getAppTracker().getWaterReminder().getWaterTracker() + ".png"));
                    ui.getWaterLabel().revalidate();
                    ui.getWaterLabel().repaint();
                    ui.getAppTracker().rewriteWaterAmount(ui.getAppTracker().getWaterReminder().getWaterTracker()+"");
                    break;
                case "MoveLeft":
                    if (ui.getAppTracker().getCurrentClock() == 2) {
                        break;
                    }
                    if (ui.getAppTracker().getCurrentPalette() == 1) {
                        break;
                    }
                    ui.getScreen().removeWaterGlassPalette();
                    ui.getScreen().assignModeSettingPalette();
                    ui.getAppTracker().setCurrentPalette(1);
                    break;
                case "MoveRight":
                    if (ui.getAppTracker().getCurrentClock() == 2) {
                        break;
                    }
                    if (ui.getAppTracker().getCurrentPalette() == 0) {
                        break;
                    }
                    ui.getScreen().removeModeSettingPalette();
                    ui.getScreen().assignWaterReminderPalette();
                    ui.getAppTracker().setCurrentPalette(0);
                    break;
                case "ChangeToRealTime":
                    // exit and water/setting
                    // add date label
                    // set clock to 2
                    if (ui.getAppTracker().getCurrentClock() != 2) {
                        previousClock = ui.getAppTracker().getCurrentClock();
                    }
                    if (realTimer != null && ui.getAppTracker().getCurrentClock() == 2) {
                        if (previousClock == 2) {
                            previousClock = 0;
                        }
                        realTimer.stop();
                        ui.getScreen().exitRealTime();
                        ui.getAppTracker().setCurrentClock(previousClock);
                        ui.getAppTracker().rewriteCurrenClock(previousClock+"");
                        break;
                    }
                    ui.getScreen().removeWaterGlassPalette();
                    ui.getScreen().removeModeSettingPalette();

                    ui.getTimeTracker().assignDayLabel(ui);
                    ui.getTimeTracker().assignDateLabel(ui);
                    ui.getAppTracker().getTimeTracker().stop();
                    ui.getAppTracker().getTimeTracker().stopCountDown();

                    setToRealClock();

                    ui.getAppTracker().setCurrentClock(2);
                    ui.getAppTracker().rewriteCurrenClock(2+"");
                    break;
                default:
                    System.out.println("General Handler error.");
                    break;
            }
        }
    }

    public void setToRealClock() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm:ss");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");

        final String[] time = {timeFormat.format(Calendar.getInstance().getTime())};
        final String[] day = {dayFormat.format(Calendar.getInstance().getTime())};
        final String[] date = {dateFormat.format(Calendar.getInstance().getTime())};

        realTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time[0] = timeFormat.format(Calendar.getInstance().getTime());
                day[0] = dayFormat.format(Calendar.getInstance().getTime());
                date[0] = dateFormat.format(Calendar.getInstance().getTime());
                ui.getTimeTracker().getTimeLabel().setText(time[0]);
                ui.getTimeTracker().getDayLabel().setText(day[0]);
                ui.getTimeTracker().getDateLabel().setText(date[0]);
            }
        });

        realTimer.start();
    }

    public ChoiceHandler getChoiceHandler() {
        return choiceHandler;
    }

    public int getPreviousClock() {
        return previousClock;
    }

    public Timer getRealTimer() {
        return realTimer;
    }
}
