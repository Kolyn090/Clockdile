package Clockdile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComponentMaker {
    public static JButton makeButton(Runnable callback) {
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

    public static JButton makeIconButton(String imageDirectory,
                                         Runnable callback) {
        JButton button = ComponentMaker.makeButton(callback);

        ImageIcon icon = new ImageIcon(imageDirectory);
        button.setIcon(icon);

        return button;
    }
}
