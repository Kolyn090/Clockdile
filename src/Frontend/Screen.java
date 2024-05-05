package Frontend;

import Backend.Tools.NumberSpinner;
import Frontend.Tools.JsliderCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Screen {

    private final UI ui;

    public Screen(UI ui) {
        this.ui = ui;
    }

    public void assignTheClockdileImage() {
        ImageIcon imageIcon = new ImageIcon("lib/Images/TheClockdile.png");
        JLabel label1= new JLabel();
        label1.setBackground(ui.getTransparentColor());
        label1.setBounds(0, 0,
                imageIcon.getIconWidth(),imageIcon.getIconHeight());
        label1.setIcon(imageIcon);
        ui.getMainLayer().add(label1, Integer.valueOf(0));
        ui.getMainLayer().setBackground(ui.getTransparentColor());
    }

    public void assignTheClockdileTitle() {
        JLabel label = ui.assignJLabel("Clockdile @Kolyn Lin", 10);
        label.setFont(ui.assignAvocadoFont(10));
        label.setForeground(Color.black);
        label.setBounds(44, 187, 200, 13);
        ui.getMainLayer().add(label, Integer.valueOf(0));
    }

    public void assignWaterReminderPalette() {
        assignWaterGlassPalette();
        assignReduceWaterButton();
        assignAddWaterButton();
    }

    private void assignWaterGlassPalette() {
        ImageIcon imageIcon = new ImageIcon("lib/Images/Water/"+
                ui.getAppTracker().getWaterReminder().getWaterTracker()+".png");
        ui.getWaterLabel().setName("WaterLabel");
        ui.getWaterLabel().setBounds(75, 108, 48, 56);
        ui.getWaterLabel().setIcon(imageIcon);
        ui.getMainLayer().add(ui.getWaterLabel(), Integer.valueOf(1));
    }

    private void assignReduceWaterButton() {
        ImageIcon imageIcon = new ImageIcon("lib/Images/LeftButton.png");
        JButton button = ui.assignJButton("", "ReduceWater");
        button.setName("ReduceWater");
        button.setIcon(imageIcon);
        button.setPreferredSize(new Dimension(5, 8));
        button.setBounds(71, 163, 6, 9);
        button.addActionListener(ui.getChoiceController().getChoiceHandler());
        ui.getMainLayer().add(button, Integer.valueOf(1));
    }

    private void assignAddWaterButton() {
        ImageIcon imageIcon = new ImageIcon("lib/Images/RightButton.png");
        JButton button = ui.assignJButton("", "AddWater");
        button.setName("AddWater");
        button.setIcon(imageIcon);
        button.setPreferredSize(new Dimension(5, 8));
        button.setBounds(120, 163, 6, 9);
        button.addActionListener(ui.getChoiceController().getChoiceHandler());
        ui.getMainLayer().add(button, Integer.valueOf(1));
    }

    public void removeWaterGlassPalette() {
        String[] thingsToBeRemoved = new String[] {
                "WaterLabel",
                "AddWater",
                "ReduceWater"
        };
        Component[] components = ui.getMainLayer().getComponentsInLayer(1);
        for (String thing :thingsToBeRemoved) {
            for (Component component : components) {
                if (component.getName() != null && component.getName().equals(thing)) {
                    ui.getMainLayer().remove(component);
                }
            }
        }

        ui.getContainer().revalidate();
        ui.getContainer().repaint();
    }

    public void assignModeSettingPalette() {
        assignModeTitle();
        assignIntervalBar();
        assignVolumeBar();
    }

    private void assignModeTitle() {
        JLabel titleLabel = ui.assignJLabel("Settings", 7);
        titleLabel.setName("SettingsTitle");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(ui.assignAvocadoFont(7));
        titleLabel.setBounds(69, 102, 61, 12);
        ui.getMainLayer().add(titleLabel, Integer.valueOf(2));
    }

    private void assignIntervalBar() {
        JLabel label = ui.assignJLabel("Interval", 7);
        label.setName("IntervalText");
        label.setForeground(Color.black);
        label.setFont(ui.assignAvocadoFont(7));
        label.setBounds(69, 123, 38, 12);
        ui.getMainLayer().add(label, Integer.valueOf(2));
        assignIntervalSpinner();
    }

    private void assignIntervalSpinner() {
        JLabel numberDisplayLabel = ui.assignJLabel(""+ui.getAppTracker().getModeSetting().getInterval(), 7);
        numberDisplayLabel.setName("NumberDisplay");
        numberDisplayLabel.setFont(ui.assignAvocadoFont(7));
        numberDisplayLabel.setForeground(Color.black);
        numberDisplayLabel.setPreferredSize(new Dimension(14, 8));
        numberDisplayLabel.setBounds(114, 125, 16, 8);
        ui.getMainLayer().add(numberDisplayLabel, Integer.valueOf(2));

        ImageIcon leftImageIcon = new ImageIcon("lib/Images/LeftButton.png");
        JButton leftButton = ui.assignJButton("", "-");
        leftButton.setName("LeftButton");
        leftButton.setBounds(106, 125, 6, 9);
        leftButton.setIcon(leftImageIcon);
        ui.getMainLayer().add(leftButton, Integer.valueOf(2));
        leftButton.addActionListener(new NumberSpinner(ui,  numberDisplayLabel));

        ImageIcon rightImageIcon = new ImageIcon("lib/Images/RightButton.png");
        JButton rightButton = ui.assignJButton("", "+");
        rightButton.setName("RightButton");
        rightButton.setBounds(125, 125, 6, 9);
        rightButton.setIcon(rightImageIcon);
        ui.getMainLayer().add(rightButton, Integer.valueOf(2));
        rightButton.addActionListener(new NumberSpinner(ui, numberDisplayLabel));
    }

    private void assignVolumeBar() {
        JLabel label = ui.assignJLabel("Volume", 7);
        label.setName("VolumeText");
        label.setForeground(Color.black);
        label.setFont(ui.assignAvocadoFont(7));
        label.setBounds(69, 143, 38, 12);
        ui.getMainLayer().add(label, Integer.valueOf(2));
        assignVolumeSlider();
    }

    private void assignVolumeSlider() {
        JsliderCustom slider = new JsliderCustom();
        slider.setName("VolumeSlider");
        slider.setValue(ui.getAppTracker().getModeSetting().getVolume());
        slider.setForeground(new java.awt.Color(252, 241, 131));
        slider.setBackground(new java.awt.Color(184, 147, 89));
        slider.setOrientation(javax.swing.JSlider.HORIZONTAL);
        slider.setBounds(100, 143, 38, 12);
        slider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                ui.getAppTracker().getModeSetting().setVolume(slider.getValue());
                ui.getAppTracker().rewriteVolumeSetting(slider.getValue()+"");
            }
        });
        ui.getMainLayer().add(slider, Integer.valueOf(2));
    }

    public void removeModeSettingPalette() {
        String[] thingsToBeRemoved = new String[] {
                "SettingsTitle",
                "Interval",
                "Volume",
                "NumberDisplay",
                "LeftButton",
                "RightButton",
                "VolumeSlider",
                "IntervalText",
                "VolumeText"
        };
        Component[] components = ui.getMainLayer().getComponentsInLayer(2);
        for (String thing :thingsToBeRemoved) {
            for (Component component : components) {
                if (component.getName() != null && component.getName().equals(thing)) {
                    ui.getMainLayer().remove(component);
                }
            }
        }

        ui.getContainer().revalidate();
        ui.getContainer().repaint();
    }

    public void assignChangeModeButton() {
        ui.getModeButton().setBounds(1, 145, 50, 40);
        ui.getMainLayer().add(ui.getModeButton(), Integer.valueOf(1));
    }

    public void assignGoToModeSettingButton(ActionListener listener) {
        JButton button = ui.assignJButton("", "MoveLeft");
        button.setBounds(24, 99, 44, 38);
        button.setName("MoveLeft");
        button.setPreferredSize(new Dimension(44, 38));
        ui.getMainLayer().add(button, Integer.valueOf(1));
        button.addActionListener(listener);
    }

    public void assignGoToWaterReminderButton(ActionListener listener) {
        JButton button = ui.assignJButton("", "MoveRight");
        button.setBounds(133, 99, 44, 38);
        button.setName("MoveRight");
        button.setPreferredSize(new Dimension(44, 38));
        ui.getMainLayer().add(button, Integer.valueOf(1));
        button.addActionListener(listener);
    }

    public void assignChangeToRealTimeButton(ActionListener listener) {
        JButton button = ui.assignJButton("", "ChangeToRealTime");
        button.setBounds(145, 145, 50, 40);
        button.setName("ChangeToRealTime");
        button.setPreferredSize(new Dimension(50, 40));
        ui.getMainLayer().add(button, Integer.valueOf(1));
        button.addActionListener(listener);
    }

    public void exitRealTime() {
        // Remove day and date from layer 3
        // assign previous clock and water/setting
        removeDayDateLabels();
        int previous = ui.getChoiceController().getPreviousClock();
        if (previous == 0) { // stop watch
            ui.assignStopWatchScreen();
            ui.getAppTracker().rewriteCurrenClock(0+"");
        } else if (previous == 1) { // count down
            ui.assignCountDownScreen();
            ui.getAppTracker().rewriteCurrenClock(1+"");
        }

        int palette = ui.getAppTracker().getCurrentPalette();
        if (palette == 0) { // water
            ui.getScreen().assignWaterReminderPalette();
        } else if (palette == 1) { // setting
            ui.getScreen().assignModeSettingPalette();
        }
    }

    private void removeDayDateLabels() {
        String[] thingsToBeRemoved = new String[] {
                "Day",
                "Date",
        };
        Component[] components = ui.getMainLayer().getComponentsInLayer(3);
        for (String thing :thingsToBeRemoved) {
            for (Component component : components) {
                if (component.getName() != null && component.getName().equals(thing)) {
                    ui.getMainLayer().remove(component);
                }
            }
        }

        ui.getContainer().revalidate();
        ui.getContainer().repaint();
    }

    public void assignExitButton() {
        JButton button = ui.assignJButton("", "");
        button.setBounds(10, 57, 28, 33);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ui.disposeWindow();
            }
        });
        ui.getMainLayer().add(button, Integer.valueOf(1));
    }
}
