package Clockdile.Components.Clock;

import Clockdile.Components.BoundsManager;
import Clockdile.Components.ComponentView;
import Clockdile.Components.Controller;
import Clockdile.DirectoryManager;
import Clockdile.FontMaker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClockView implements ComponentView<Integer, JLabel> {
    private final JLabel body;
    private final ArrayList<Controller<Integer>> controllers;
    private final BoundsManager boundsManager;

    public ClockView() {
        controllers = new ArrayList<>();
        boundsManager = new BoundsManager();

        Font digitalFont = FontMaker.makeFont(15, DirectoryManager.digitalClock);

        body = new JLabel();
        body.setFont(digitalFont);
        body.setHorizontalAlignment(SwingConstants.CENTER);
        body.setForeground(Color.black);
        boundsManager.setBounds(
                body,
                new Rectangle(0, 0, 100, 30)
        );

        update(0);
    }

    public void addController(Controller<Integer> ctrl) {
        controllers.add(ctrl);
    }

    @Override
    public void setPosition(int x, int y) {
        boundsManager.setBoundsForMaster(body, x, y);
    }

    @Override
    public JLabel show() {
        return body;
    }

    @Override
    public void update(Integer data) {
        body.setText(convertToTimeText(data));
    }

    private String convertToTimeText(Integer data) {
        return getClockText(
                data / 3600000,
                (data / 60000) % 60,
                (data / 1000) % 60
        );
    }

    private String getClockText(int hours, int minutes, int seconds) {
        return toTwoDigits(hours) + ":" +
                toTwoDigits(minutes) + ":" +
                toTwoDigits(seconds);
    }

    private String toTwoDigits(int number) {
        return String.format("%02d", number);
    }

    @Override
    public void reset() {
        controllers.forEach(c -> c.updateModels(0));
    }

    @Override
    public String getName() {
        return "clock view";
    }
}
