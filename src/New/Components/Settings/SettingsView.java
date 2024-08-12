package New.Components.Settings;

import New.ComponentMaker;
import New.DirectoryManager;
import New.FontMaker;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingsView extends JPanel {
    private final JLabel intervalNumberText;

    public SettingsView(Runnable decInterval, Runnable incInterval) {
        Font avocadoFont = FontMaker.makeFont(8, DirectoryManager.avocado);

        this.setLayout(new GridLayout(3, 1));
        this.setBackground(new Color(0, 0, 0, 0));

        JLabel titleLabel = makeTitleLabel();
        titleLabel.setFont(avocadoFont);
        this.add(titleLabel);

// region Interval
        JLayeredPane intervalStack = new JLayeredPane();
        intervalStack.setBackground(new Color(0, 0, 0, 0));
        JLabel intervalTitle = makeText("Interval");
        intervalTitle.setFont(avocadoFont);
        intervalTitle.setPreferredSize(new Dimension(44, 8));
        intervalTitle.setBounds(0, 0, 44,  12);
        intervalStack.add(intervalTitle, Integer.valueOf(1));

        JButton decIntervalButton = ComponentMaker.makeIconButton(
                DirectoryManager.leftArrow,
                decInterval
        );
        Color paletteBGColor = new Color(193, 153, 92);
        decIntervalButton.setPreferredSize(new Dimension(5, 8));
        decIntervalButton.setBounds(44, 0, 6, 12);
        decIntervalButton.setOpaque(true);
        decIntervalButton.setBackground(paletteBGColor);
        intervalNumberText = makeText(getPrettyIntervalNumberText(1));
        intervalNumberText.setFont(avocadoFont);
        intervalNumberText.setPreferredSize(new Dimension(14, 12));
        intervalNumberText.setBounds(52, 0, 16, 12);
        intervalNumberText.setOpaque(true);
        intervalNumberText.setBackground(paletteBGColor);
        JButton incIntervalButton = ComponentMaker.makeIconButton(
                DirectoryManager.rightArrow,
                incInterval
        );
        incIntervalButton.setPreferredSize(new Dimension(5, 8));
        incIntervalButton.setBounds(68, 0, 6, 12);
        incIntervalButton.setOpaque(true);
        incIntervalButton.setBackground(paletteBGColor);

        intervalStack.add(decIntervalButton, Integer.valueOf(1));
        intervalStack.add(intervalNumberText, Integer.valueOf(1));
        intervalStack.add(incIntervalButton, Integer.valueOf(1));
// endregion

// region Volume
        JPanel volumeStack = new JPanel();
        volumeStack.setBackground(paletteBGColor);
        volumeStack.setLayout(new GridLayout(1, 2));
        JLabel volumeTitle = makeText("Volume");
        volumeTitle.setFont(avocadoFont);
        volumeStack.add(volumeTitle);

        JSlider volumeSlider = new JSlider();
        volumeSlider.setOpaque(false);
        volumeSlider.setForeground(new Color(252, 241, 131));
        volumeSlider.setBackground(new Color(184, 147, 89));
        volumeSlider.setOrientation(javax.swing.JSlider.HORIZONTAL);
        volumeSlider.setBounds(0, 0, 38, 12);
        volumeSlider.setUI(new JSliderUI(volumeSlider));
        volumeSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }
        });
        volumeStack.add(volumeSlider);
// endregion

        this.add(intervalStack);
        this.add(volumeStack);

        this.revalidate();
        this.repaint();
    }

    public void setIntervalNumberText(int amount) {
        intervalNumberText.setText(getPrettyIntervalNumberText(amount));
    }

    private String getPrettyIntervalNumberText(int amount) {
        return (amount < 10 ? "   ": " ") + amount;
    }

    private JLabel makeTitleLabel() {
        JLabel label = new JLabel("Settings");
        label.setForeground(Color.white);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JLabel makeText(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.black);
        return label;
    }

    private static class JSliderUI extends BasicSliderUI {

        public JSliderUI(JSlider slider) {
            super(slider);
        }

        @Override
        public void paintFocus(Graphics grphcs) {

        }

        @Override
        protected Dimension getThumbSize() {
            return new Dimension(14, 14);
        }

        @Override
        public void paintThumb(Graphics grphcs) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(slider.getForeground());
            g2.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
        }

        @Override
        public void paintTrack(Graphics grphcs) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(slider.getBackground());
            if (slider.getOrientation() == JSlider.VERTICAL) {
                g2.fillRoundRect(slider.getWidth() / 2 - 2, 2, 4, slider.getHeight(), 1, 1);
            } else {
                g2.fillRoundRect(2, slider.getHeight() / 2 - 2, slider.getWidth() - 5, 4, 1, 1);
            }
        }
    }
}
