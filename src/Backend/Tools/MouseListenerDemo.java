package Backend.Tools;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerDemo implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setIcon(new ImageIcon("lib/Images/" + button.getName() + "-Closed.png"));
        button.revalidate();
        button.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setIcon(new ImageIcon("lib/Images/" + button.getName() + "-Open.png"));
        button.revalidate();
        button.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

