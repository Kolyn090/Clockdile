package New.Components.Clock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountUpClock extends ClockController {
    public CountUpClock() {
        super(x -> x + 1000);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ref = 60000;

                if (model.getElapsedTime()/ref > 0 &&
                model.getElapsedTime() % ref == 0) {
                    // Play sound
                }
                model.step();
                view.setText(model.getClockText());
                view.revalidate();
                view.repaint();
            }
        });

        timer.start();
    }
}
