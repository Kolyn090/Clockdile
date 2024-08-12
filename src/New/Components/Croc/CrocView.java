package New.Components.Croc;

import New.DirectoryManager;

import javax.swing.*;
import java.awt.*;

public class CrocView extends JLayeredPane {
    public CrocView(int width, int height) {
        this.setBounds(0, 0, width, height);
        ImageIcon body = new ImageIcon(DirectoryManager.crocImageDirectory);
        JLabel bodyLabel = new JLabel();
        bodyLabel.setBounds(0, 0, width, height);
        bodyLabel.setIcon(body);

        JButton leftEyeButton = new CrocEyeButton(
                DirectoryManager.leftEye.get("Open"),
                DirectoryManager.leftEye.get("Closed"),
                new Dimension(28, 28),
                new Rectangle(56, 66, 29, 29),
                () -> {System.out.println("Resume");});
        JButton rightEyeButton = new CrocEyeButton(
                DirectoryManager.rightEye.get("Open"),
                DirectoryManager.rightEye.get("Closed"),
                new Dimension(28, 28),
                new Rectangle(114, 66, 29, 29),
                () -> {System.out.println("Reset");});

        this.add(bodyLabel, Integer.valueOf(0));
        this.add(leftEyeButton, Integer.valueOf(1));
        this.add(rightEyeButton, Integer.valueOf(1));
        this.revalidate();
        this.repaint();
    }
}
