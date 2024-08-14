package Clockdile;

import Clockdile.Components.Clock.ClockView;
import Clockdile.Components.Clock.CountDownClock;
import Clockdile.Components.Clock.CountUpClock;
import Clockdile.Components.Croc.CrocEyeButton;
import Clockdile.Components.Settings.Interval.IntervalView;
import Clockdile.Components.IntController;
import Clockdile.Components.IntModel;
import Clockdile.Components.Settings.Volume.VolumeView;
import Clockdile.Components.TextView;
import Clockdile.Components.Water.WaterView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicReference;

import static Clockdile.util.Disable;
import static Clockdile.util.Enable;

public class ContentView {
    public ContentView(int windowWidth, int windowHeight) {
        Font avocadoFont = FontMaker.makeFont(8, DirectoryManager.avocado);
        Font avocadoFont12 = FontMaker.makeFont(12, DirectoryManager.avocado);
        Font digitalFont = FontMaker.makeFont(15, DirectoryManager.digitalClock);
        ClockModeManager clockModeManager = new ClockModeManager(SaveSingleton.instance().getCurrentClockMode());

        setLookAndFeel();
        JFrame window = new MoveFrame();
        window.setSize(windowWidth, windowHeight);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(new BorderLayout());
        window.setResizable(false);
        Color transparentColor = new Color(0f, 0f, 0f, 0f);
        window.setBackground(transparentColor);

        Container body = window.getContentPane();
        body.setPreferredSize(new Dimension(window.getWidth(), window.getHeight()));
        window.setVisible(true);

        JLayeredPane mainLayer = new JLayeredPane();
        mainLayer.setBackground(Color.black);
        mainLayer.setBounds(0, 0, windowWidth, windowHeight);
        body.add(mainLayer);

        JButton exitButton = makeExitButton(window::dispose);
        exitButton.setBounds(10, 110, 28, 33);
        mainLayer.add(exitButton, Integer.valueOf(1));

        ArrayList<JComponent> waterPage = new ArrayList<>();
        ArrayList<JComponent> settingsPage = new ArrayList<>();
        ArrayList<JComponent> realTimePage = new ArrayList<>();

// region Water
        IntController waterController = new IntController();
        IntModel waterModel = new IntModel(0, 8);
        WaterView waterView = new WaterView();

        waterController.addModel(waterModel);
        waterModel.addObserver(waterView);
        waterView.addController(waterController);

        waterView.setPosition(76, 160);
        mainLayer.add(waterView.show(), Integer.valueOf(1));
        waterPage.add(waterView.show());
// endregion

// region Settings-title
        JLabel settingsTitle = new JLabel("Settings");
        settingsTitle.setForeground(Color.white);

        settingsTitle.setFont(avocadoFont);
        settingsTitle.setHorizontalAlignment(SwingConstants.CENTER);
        settingsTitle.setBounds(75, 160, 46, 20);
        mainLayer.add(settingsTitle, Integer.valueOf(1));
        settingsPage.add(settingsTitle);
// endregion

// region Settings-interval
        IntController intervalController = new IntController();
        IntModel intervalModel = new IntModel(1, 60);
        IntervalView intervalView = new IntervalView();

        intervalController.addModel(intervalModel);
        intervalModel.addObserver(intervalView);
        intervalView.addController(intervalController);

        intervalView.setPosition(59, 186);
        mainLayer.add(intervalView.show(), Integer.valueOf(1));
        settingsPage.add(intervalView.show());
// endregion

// region Settings-volume
        IntController volumeController = new IntController();
        IntModel volumeModel = new IntModel(0, 100);
        VolumeView volumeView = new VolumeView(new Color(193, 153, 92));

        volumeController.addModel(volumeModel);
        volumeModel.addObserver(volumeView);
        volumeView.addController(volumeController);

        volumeView.setPosition(59, 210);
        mainLayer.add(volumeView.show(), Integer.valueOf(1));
        settingsPage.add(volumeView.show());
// endregion

// region Clock-countdown
        // min=1, max=60 according to interval
        CountDownClock countDownClock = new CountDownClock(SaveSingleton.instance().getInterval(), 1, 60);
        // infinity time (in theory), and time cannot be negative
        IntModel countDownModel = new IntModel(0, Integer.MAX_VALUE);
        ClockView countDownView = new ClockView();

        intervalView.addController(countDownClock);

        intervalController.addModel(countDownModel);
        countDownClock.addModel(countDownModel);
        countDownModel.addObserver(countDownView);
        countDownView.addController(countDownClock);

        countDownView.setPosition(50, 112);
        mainLayer.add(countDownView.show(), Integer.valueOf(1));
// endregion

// region Clock-countup
        CountUpClock countUpClock = new CountUpClock(SaveSingleton.instance().getInterval(), 1, 60);
        IntModel countUpModel = new IntModel(0, Integer.MAX_VALUE);
        ClockView countUpView = new ClockView();

        intervalView.addController(countUpClock);

        countUpClock.addModel(countUpModel);
        countUpModel.addObserver(countUpView);
        countUpView.addController(countUpClock);

        countUpView.setPosition(50, 112);
        mainLayer.add(countUpView.show(), Integer.valueOf(1));
// endregion

// region Clock-realtime
        SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm:ss");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");

        final String[] time = {timeFormat.format(Calendar.getInstance().getTime())};
        final String[] day = {dayFormat.format(Calendar.getInstance().getTime())};
        final String[] date = {dateFormat.format(Calendar.getInstance().getTime())};

        TextView timeText = new TextView(digitalFont);
        timeText.setPosition(50, 112);
        TextView dayText = new TextView(avocadoFont12);
        dayText.setPosition(50, 170);
        TextView dateText = new TextView(avocadoFont12);
        dateText.setPosition(50, 190);

        Timer realTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time[0] = timeFormat.format(Calendar.getInstance().getTime());
                day[0] = dayFormat.format(Calendar.getInstance().getTime());
                date[0] = dateFormat.format(Calendar.getInstance().getTime());
                timeText.update(time[0]);
                dayText.update(day[0]);
                dateText.update(date[0]);
            }
        });
        realTimer.start();

        mainLayer.add(timeText.show(), Integer.valueOf(1));
        mainLayer.add(dayText.show(), Integer.valueOf(1));
        mainLayer.add(dateText.show(), Integer.valueOf(1));
        realTimePage.add(timeText.show());
        realTimePage.add(dayText.show());
        realTimePage.add(dateText.show());
// endregion

//region Croc
        JLabel crocBody = new JLabel();
        crocBody.setBounds(0, 0, windowWidth, windowHeight);
        crocBody.setIcon(new ImageIcon(DirectoryManager.crocImageDirectory));
        mainLayer.add(crocBody, Integer.valueOf(0));

        JButton leftEyeButton = new CrocEyeButton(
                DirectoryManager.leftEye.get("Open"),
                DirectoryManager.leftEye.get("Closed"),
                new Dimension(28, 28),
                new Rectangle(56, 66, 29, 29),
                () -> {
                    if (clockModeManager.getMode() == ClockMode.CountDown) {
                        pressLeftEye(countDownClock);
                    } else if (clockModeManager.getMode() == ClockMode.StopWatch){
                        pressLeftEye(countUpClock);
                    }
                    stopSound();
                });
        JButton rightEyeButton = new CrocEyeButton(
                DirectoryManager.rightEye.get("Open"),
                DirectoryManager.rightEye.get("Closed"),
                new Dimension(28, 28),
                new Rectangle(114, 66, 29, 29),
                () -> {
                    if (clockModeManager.getMode() == ClockMode.CountDown) {
                        countDownClock.reset();
                        countDownClock.stop();
                    } else if (clockModeManager.getMode() == ClockMode.StopWatch){
                        countUpClock.reset();
                        countUpClock.stop();
                    }
                    stopSound();
                });
        mainLayer.add(leftEyeButton, Integer.valueOf(1));
        mainLayer.add(rightEyeButton, Integer.valueOf(1));
// endregion

// region Navigation
        AtomicReference<Page> currentPage = new AtomicReference<>(Page.Water);
        JButton settingsNavigationButton = ComponentMaker.makeButton(
                () -> {
                    if (clockModeManager.getMode() == ClockMode.RealTime) return;

                    if (currentPage.get() == Page.Settings) return;
                    currentPage.set(Page.Settings);
                    Disable(waterView.show());
                    Enable(settingsTitle);
                    Enable(intervalView.show());
                    Enable(volumeView.show());
                    stopSound();
                }
        );
        settingsNavigationButton.setBounds(14, 159, 44, 38);
        settingsNavigationButton.setPreferredSize(new Dimension(44, 38));
        mainLayer.add(settingsNavigationButton, Integer.valueOf(1));

        JButton waterNavigationButton = ComponentMaker.makeButton(
                () -> {
                    if (clockModeManager.getMode() == ClockMode.RealTime) return;

                    if (currentPage.get() == Page.Water) return;
                    currentPage.set(Page.Water);
                    Enable(waterView.show());
                    Disable(settingsTitle);
                    Disable(intervalView.show());
                    Disable(volumeView.show());
                    stopSound();
                }
        );
        waterNavigationButton.setBounds(143, 159, 44, 38);
        waterNavigationButton.setPreferredSize(new Dimension(44, 38));
        mainLayer.add(waterNavigationButton, Integer.valueOf(1));
// endregion

// region Feet
        JButton leftFootButton = ComponentMaker.makeButton(
                () -> {
                    if (clockModeManager.getMode() == ClockMode.RealTime) return;

                    clockModeManager.toggle();
                    if (clockModeManager.getMode() == ClockMode.CountDown) {
                        countDownClock.reset();
                        countDownClock.stop();
                        Enable(countDownView.show());
                        Disable(countUpView.show());
                    } else if (clockModeManager.getMode() == ClockMode.StopWatch) {
                        countUpClock.reset();
                        countUpClock.stop();
                        Disable(countDownView.show());
                        Enable(countUpView.show());
                    }
                    stopSound();
                }
        );
        leftFootButton.setBounds(0, 200, 58, 40);
        leftFootButton.setPreferredSize(new Dimension(58, 40));
        mainLayer.add(leftFootButton, Integer.valueOf(1));

        JButton rightFootButton = ComponentMaker.makeButton(
                () -> {
                    clockModeManager.toggleRealTime();
                    SaveSingleton.instance().writeClock(clockModeManager.getMode());
                    if (clockModeManager.getMode() == ClockMode.RealTime) {
                        countDownClock.stop();
                        countUpClock.stop();
                        waterPage.forEach(util::Disable);
                        settingsPage.forEach(util::Disable);
                        realTimePage.forEach(util::Enable);
                        Disable(countDownView.show());
                        Disable(countUpView.show());
                    } else {
                        if (currentPage.get() == Page.Water) {
                            waterPage.forEach(util::Enable);
                            settingsPage.forEach(util::Disable);
                            realTimePage.forEach(util::Disable);
                        } else {
                            waterPage.forEach(util::Disable);
                            settingsPage.forEach(util::Enable);
                            realTimePage.forEach(util::Disable);
                        }
                        if (clockModeManager.getMode() == ClockMode.CountDown) {
                            Enable(countDownView.show());
                        } else {
                            Enable(countUpView.show());
                        }
                    }
                    stopSound();
                }
        );
        rightFootButton.setBounds(144, 200, 58, 40);
        rightFootButton.setPreferredSize(new Dimension(58, 40));
        mainLayer.add(rightFootButton, Integer.valueOf(1));
// endregion
        waterController.updateModels(SaveSingleton.instance().getWaterAmount());
        volumeController.updateModels(SaveSingleton.instance().getVolume());
        intervalController.updateModels(SaveSingleton.instance().getInterval());
        countDownClock.updateModels(SaveSingleton.instance().getInterval());
        countUpClock.updateModels(SaveSingleton.instance().getInterval());

        if (clockModeManager.getMode() != ClockMode.RealTime) {
            realTimePage.forEach(util::Disable);
            if (clockModeManager.getMode() == ClockMode.StopWatch) {
                Disable(countDownView.show());
            } else {
                Disable(countUpView.show());
            }
        } else {
            waterPage.forEach(util::Disable);
        }
        settingsPage.forEach(util::Disable);

        body.revalidate();
        body.repaint();
    }

    private void pressLeftEye(IClock clock) {
        if (clock.hasStarted()) {
            clock.stop();
        } else {
            clock.start();
        }
    }

    private void stopSound() {
        SoundSingleton.instance().reset();
    }

    private JButton makeExitButton(Runnable callback) {
        JButton button = new JButton("");
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callback.run();
            }
        });

        return button;
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ClockModeManager {
        private ClockMode clockMode;
        private ClockMode lastMode = ClockMode.StopWatch;

        public ClockModeManager(ClockMode defaultMode) {
            clockMode = defaultMode;
        }

        public void toggle() {
            if (clockMode == ClockMode.StopWatch) {
                clockMode = ClockMode.CountDown;
            } else if (clockMode == ClockMode.CountDown) {
                clockMode = ClockMode.StopWatch;
            }
        }

        public void toggleRealTime() {
            if (clockMode == ClockMode.RealTime) {
                clockMode = lastMode;
            } else {
                lastMode = clockMode;
                clockMode = ClockMode.RealTime;
            }
        }

        public ClockMode getMode() {
            return clockMode;
        }
    }
}
