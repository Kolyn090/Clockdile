package New.Components.Croc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CrocEyeButton extends JButton {
    private final ImageIcon openIcon;
    private final ImageIcon closedIcon;

    public CrocEyeButton(String open,
                         String closed,
                         Dimension dim,
                         Rectangle bounds,
                         Runnable callback) {
        openIcon = new ImageIcon(open);
        closedIcon = new ImageIcon(closed);

        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callback.run();
            }
        });

        EyeMouseListener mouseListener = new EyeMouseListener();
        this.addMouseListener(mouseListener);

        this.setIcon(openIcon);
        this.setPreferredSize(dim);
        this.setBounds(bounds);
    }

    public void renderOpen() {
        this.setIcon(openIcon);
        this.revalidate();
        this.repaint();
    }

    public void renderClosed() {
        this.setIcon(closedIcon);
        this.revalidate();
        this.repaint();
    }

    private static class EyeMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            CrocEyeButton button = (CrocEyeButton) e.getSource();
            button.renderClosed();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            CrocEyeButton button = (CrocEyeButton) e.getSource();
            button.renderOpen();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
