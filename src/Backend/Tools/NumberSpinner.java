package Backend.Tools;

import Frontend.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberSpinner implements ActionListener {

    private final JLabel numberLabel;
    private final UI ui;

    public NumberSpinner(UI ui, JLabel numberLabel) {
        this.ui = ui;
        this.numberLabel = numberLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "-":
                int changeValue;
                if (ui.getAppTracker().getModeSetting().getInterval() > 1) {
                    changeValue = ui.getAppTracker().getModeSetting().getInterval()-1;
                } else {
                    changeValue = 60;
                }
                ui.getAppTracker().getModeSetting().setInterval(changeValue);
                ui.getAppTracker().rewriteIntervalSetting(changeValue+"");
                break;
            case "+":
                if (ui.getAppTracker().getModeSetting().getInterval() < 60) {
                    changeValue = ui.getAppTracker().getModeSetting().getInterval()+1;
                } else {
                    changeValue = 1;
                }
                ui.getAppTracker().getModeSetting().setInterval(changeValue);
                ui.getAppTracker().rewriteIntervalSetting(changeValue+"");
                break;
            default:
                System.out.println("Number Spinner Error.");
                break;
        }
        numberLabel.setText(""+ui.getAppTracker().getModeSetting().getInterval());
        numberLabel.revalidate();
        numberLabel.repaint();
    }
}
