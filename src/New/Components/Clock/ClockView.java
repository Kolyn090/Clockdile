package New.Components.Clock;

import New.DirectoryManager;
import New.FontMaker;

import javax.swing.*;
import java.awt.*;

public class ClockView extends JLabel {

    public ClockView() {
        Font digitalFont = FontMaker.makeFont(15, DirectoryManager.digitalClock);
        this.setFont(digitalFont);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setForeground(Color.black);
    }
}
