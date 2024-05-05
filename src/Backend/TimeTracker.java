package Backend;

import Backend.Audio.Sound;
import Backend.Tools.ModeSetting;
import Frontend.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeTracker {

    private JLabel timeLabel = new JLabel();
    private JLabel dayLabel = new JLabel();
    private JLabel dateLabel = new JLabel();
    private int elapsedTime = 0;
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;
    private String seconds_string = String.format("%02d", seconds);
    private String minutes_string = String.format("%02d", minutes);
    private String hours_string = String.format("%02d", hours);

    private final Timer timer;
    private final Timer countDownTimer;
    private boolean countDownSoundPlayed = false;
    private final Sound sound;

    public TimeTracker(ModeSetting modeSetting, Sound sound) {
        this.sound = sound;
        timer =  new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int ref = modeSetting.getInterval()*60000;
                if (elapsedTime/ref > 0 && elapsedTime % ref == 0) {
                    sound.play(modeSetting.getVolume());
                }
                elapsedTime = elapsedTime + 1000;
                hours = (elapsedTime / 3600000);
                minutes = (elapsedTime / 60000) % 60;
                seconds = (elapsedTime / 1000) % 60;
                seconds_string = String.format("%02d", seconds);
                minutes_string = String.format("%02d", minutes);
                hours_string = String.format("%02d", hours);
                timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
            }
        });

        countDownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (elapsedTime >= 1000) {
                    elapsedTime = elapsedTime - 1000;
                    hours = (elapsedTime / 3600000);
                    minutes = (elapsedTime / 60000) % 60;
                    seconds = (elapsedTime / 1000) % 60;
                    seconds_string = String.format("%02d", seconds);
                    minutes_string = String.format("%02d", minutes);
                    hours_string = String.format("%02d", hours);
                    timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
                } else {
                    if (!countDownSoundPlayed) {
                        sound.play(modeSetting.getVolume());
                        countDownSoundPlayed = true;
                    }
                }
            }
        });
    }

    public void assignTimeLabel(UI ui) {
        timeLabel = ui.assignJLabel(hours_string + ":" + minutes_string + ":" + seconds_string, 35);
        timeLabel.setName("TimeLabel");
        timeLabel.setFont(ui.assignDigitalClockFont(15));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setForeground(Color.black);
    }

    public void assignDayLabel(UI ui) {
        dayLabel = ui.assignJLabel("", 10);
        dayLabel.setFont(ui.assignAvocadoFont(10));
        dayLabel.setName("Day");
        dayLabel.setForeground(Color.black);
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dayLabel.setPreferredSize(new Dimension(67, 19));
        dayLabel.setBounds(66,113,67,19);
        ui.getMainLayer().add(dayLabel, Integer.valueOf(3));
    }

    public void assignDateLabel(UI ui) {
        dateLabel = ui.assignJLabel("", 10);
        dateLabel.setFont(ui.assignAvocadoFont(10));
        dateLabel.setName("Date");
        dateLabel.setForeground(Color.black);
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setPreferredSize(new Dimension(75, 19));
        dateLabel.setBounds(63, 142, 75, 19);
        ui.getMainLayer().add(dateLabel, Integer.valueOf(3));
    }

    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
    }

    void startCountDown() {
        countDownTimer.start();
    }

    void stopCountDown() {
        countDownTimer.stop();
    }

    public void reset(int timeToElapse) {
        sound.getClip().stop();
        countDownTimer.stop();
        timer.stop();
        elapsedTime = timeToElapse;
        seconds = 0;
        minutes = 0;
        hours = 0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }

    public void resetCountDown(int timeToElapse, int interval) {
        sound.getClip().stop();
        timer.stop();
        countDownTimer.stop();
        elapsedTime = timeToElapse;
        seconds = 0;
        minutes = interval;
        hours = 0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        countDownSoundPlayed = false;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public JLabel getDayLabel() {
        return dayLabel;
    }

    public JLabel getDateLabel() {
        return dateLabel;
    }
}
