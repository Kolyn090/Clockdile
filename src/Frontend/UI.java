package Frontend;

import Backend.*;
import Backend.Tools.AppTracker;
import Backend.Tools.MouseListenerDemo;

import javax.swing.*;
import java.awt.*;


public class UI extends UIObject{

    private final AppTracker appTracker;
    private JFrame window;
    private Container container;
    private final JLayeredPane mainLayer = assignJLayeredPane(getWindowHeight(), getWindowWidth());
    private final JLabel waterLabel = assignJLabel("", 10);
    private final JButton modeButton = assignJButton("", "ChangeMode");
    private final MouseListenerDemo mouseListenerDemo = new MouseListenerDemo();

    private final ChoiceController choiceController = new ChoiceController(this);
    private final Screen screen = new Screen(this);

    public UI(AppTracker appTracker) {
        this.appTracker = appTracker;
        appTracker.getTimeTracker().assignTimeLabel(this);
        assignBaseScreen();
    }

    private void assignBaseScreen() {
        setLookAndFeel();
        window = assignWindow();
        container = assignContainer(window);
        window.setVisible(true);

        assignMainLayer();
        initializeButtons();
    }

    public void assignStopWatchScreen() {
        getTimeTracker().reset(0);
        getContainer().revalidate();
        getContainer().repaint();
    }

    public void assignCountDownScreen() {
        getTimeTracker().resetCountDown(getAppTracker().getModeSetting().getInterval()*60000,
                getAppTracker().getModeSetting().getInterval());
        getContainer().revalidate();
        getContainer().repaint();
    }

    private void assignMainLayer() {
        container.add(mainLayer);
        screen.assignTheClockdileImage();
        screen.assignTheClockdileTitle();
        appTracker.getTimeTracker().getTimeLabel().setBounds(72, 62, 57, 15);
        mainLayer.add(appTracker.getTimeTracker().getTimeLabel(), Integer.valueOf(1));
        screen.assignWaterReminderPalette();
    }

    private void initializeButtons() {
        JButton resumeButton = assignJButton("", "Resume");
        JButton resetButton = assignJButton("", "Reset");

        ImageIcon imageIcon = new ImageIcon("lib/Images/ResumeButton-Open.png");
        resumeButton.setIcon(imageIcon);
        resumeButton.setName("ResumeButton");
        resumeButton.setPreferredSize(new Dimension(28, 28));
        resumeButton.setBounds(56, 9, 29, 29);
        resumeButton.addMouseListener(mouseListenerDemo);
        resumeButton.addActionListener(getChoiceController().getChoiceHandler());
        mainLayer.add(resumeButton, Integer.valueOf(1));

        ImageIcon imageIcon2 = new ImageIcon("lib/Images/ResetButton-Open.png");
        resetButton.setIcon(imageIcon2);
        resetButton.setName("ResetButton");
        resetButton.setPreferredSize(new Dimension(28, 28));
        resetButton.setBounds(114, 9, 29, 29);
        resetButton.addMouseListener(mouseListenerDemo);
        resetButton.addActionListener(getChoiceController().getChoiceHandler());
        mainLayer.add(resetButton, Integer.valueOf(1));

        screen.assignExitButton();
        screen.assignChangeModeButton(); // still needs listener, is added already but not here

        screen.assignGoToModeSettingButton(choiceController.getChoiceHandler());
        screen.assignGoToWaterReminderButton(choiceController.getChoiceHandler());
        screen.assignChangeToRealTimeButton(choiceController.getChoiceHandler());

        modeButton.addActionListener(choiceController.getChoiceHandler());
    }

    public void disposeWindow() {
        if (choiceController.getRealTimer() != null) {
            choiceController.getRealTimer().stop();
        }
        window.dispose();
    }

    public AppTracker getAppTracker() {
        return appTracker;
    }
    public Container getContainer() {
        return container;
    }
    public JLayeredPane getMainLayer() {
        return mainLayer;
    }
    public JButton getModeButton() {
        return modeButton;
    }
    public TimeTracker getTimeTracker() {
        return appTracker.getTimeTracker();
    }
    public ChoiceController getChoiceController() {
        return choiceController;
    }
    public JLabel getWaterLabel() {
        return waterLabel;
    }
    public Screen getScreen() {
        return screen;
    }
}
