package New.Components.Settings.Volume;

import New.Components.BoundsManager;
import New.Components.ComponentView;
import New.Components.IntController;
import New.DirectoryManager;
import New.FontMaker;
import New.SaveSingleton;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VolumeView implements ComponentView<Integer, JPanel> {
    private final JPanel body;

    private final JSlider slider;
    private final ArrayList<IntController> controllers;
    private final BoundsManager boundsManager;

    public VolumeView(Color paletteBGColor) {
        controllers = new ArrayList<>();
        boundsManager = new BoundsManager();

        Font avocadoFont = FontMaker.makeFont(8, DirectoryManager.avocado);

        body = new JPanel();
        body.setBackground(paletteBGColor);
        body.setLayout(new GridLayout(1, 2));
        JLabel title = new JLabel("Volume");
        title.setForeground(Color.BLACK);
        title.setFont(avocadoFont);
        boundsManager.setBounds(
                title,
                new Rectangle(0, 0, 38, 12)
        );
        body.add(title);

        slider = new JSlider();
        slider.setOpaque(false);
        slider.setForeground(new Color(252, 241, 131));
        slider.setBackground(new Color(184, 147, 89));
        slider.setOrientation(javax.swing.JSlider.HORIZONTAL);
        boundsManager.setBounds(
                slider,
                new Rectangle(40, 0, 38, 12)
        );
        slider.setUI(new JSliderUI(slider));
        slider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                SaveSingleton.instance().writeVolume(slider.getValue());
            }
        });
        body.add(slider);
    }

    public void addController(IntController ctrl) {
        controllers.add(ctrl);
    }

    @Override
    public void setPosition(int x, int y) {
        boundsManager.setBoundsForMaster(body, x, y);
    }

    @Override
    public JPanel show() {
        return body;
    }

    @Override
    public void update(Integer data) {
        slider.setValue(data);
    }

    @Override
    public void reset() {
        controllers.forEach(c -> c.updateModels(0));
    }

    @Override
    public String getName() {
        return "volume view";
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
