package New.Components.Clock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountDownClock extends ClockController {
    private boolean hasSoundPlayed = false;

    public CountDownClock() {
        super(x -> x - 1000);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getElapsedTime() >= 1000) {
                    model.step();
                    view.setText(model.getClockText());
                    view.revalidate();
                    view.repaint();
                } else {
                    if (!hasSoundPlayed) {
                        // Play sound
                        hasSoundPlayed = true;
                    }
                }
            }
        });
        timer.start();
    }
}
