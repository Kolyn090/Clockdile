package Clockdile.Components.Clock;

import Clockdile.Components.IntController;
import Clockdile.Components.Model;
import Clockdile.IClock;
import Clockdile.SaveSingleton;
import Clockdile.SoundSingleton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountDownClock extends IntController implements IClock {
    private final Timer timer;
    private Model<Integer> model;
    private boolean hasSoundPlayed = false;
    private boolean started = false;
    private int interval = 1;
    private final int min;
    private final int max;

    public CountDownClock(int defaultInterval, int min, int max) {
        interval = defaultInterval;
        this.min = min;
        this.max = max;

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getData() >= 1000) {
                    model.update(model.getData()-1000);
                } else {
                    if (!hasSoundPlayed) {
                        // Play sound
                        SoundSingleton.instance().play(SaveSingleton.instance().getVolume());
                        hasSoundPlayed = true;
                    }
                }
            }
        });
        timer.stop();
    }

    @Override
    public boolean hasStarted() {
        return started;
    }

    public void start() {
        timer.start();
        started = true;
    }

    public void stop() {
        timer.stop();
        started = false;
    }

    @Override
    public void reset() {
        model.update(interval * 60000);
        hasSoundPlayed = false;
    }

    @Override
    public void addModel(Model<Integer> model) {
        this.model = model;
    }

    @Override
    public void updateModels(Integer data) {
        model.update(data * 60000);
        hasSoundPlayed = false;
    }

    @Override
    public void increment() {
        interval++;
        if (interval > max) {
            interval = max;
        }
        updateModels(interval);
    }

    @Override
    public void decrement() {
        interval--;
        if (interval < min) {
            interval = min;
        }
        updateModels(interval);
    }
}
