package New.Components.Water;

import New.ComponentMaker;
import New.Components.BoundsManager;
import New.Components.ComponentView;
import New.Components.IntController;
import New.DirectoryManager;
import New.SaveSingleton;
import New.util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WaterView implements ComponentView<Integer, JLayeredPane> {
    private final ImageIcon[] icons;
    private final JLayeredPane body;
    private final JLabel waterIconLabel;
    private final ArrayList<IntController> controllers;
    private final BoundsManager boundsManager;

    public WaterView() {
        controllers = new ArrayList<>();
        boundsManager = new BoundsManager();

        icons = util.map(DirectoryManager.waterImageDirectories,
                ImageIcon::new, new ImageIcon[DirectoryManager.waterImageDirectories.length]);
        body = new JLayeredPane();

        waterIconLabel = new JLabel();
        waterIconLabel.setIcon(icons[0]);
        boundsManager.setBounds(
                waterIconLabel,
                new Rectangle(0, 0, 48, 56)
        );

        JButton decWaterButton = ComponentMaker.makeIconButton(
                DirectoryManager.leftArrow,
                () -> controllers.forEach(IntController::decrement)
        );
        decWaterButton.setPreferredSize(new Dimension(5, 8));
        boundsManager.setBounds(
                decWaterButton,
                new Rectangle(0, 60, 6, 9)
        );
        JButton incWaterButton = ComponentMaker.makeIconButton(
                DirectoryManager.rightArrow,
                () -> controllers.forEach(IntController::increment)
        );
        incWaterButton.setPreferredSize(new Dimension(5, 8));
        boundsManager.setBounds(
                incWaterButton,
                new Rectangle(42, 60, 6, 9)
        );

        body.add(waterIconLabel, Integer.valueOf(1));
        body.add(decWaterButton, Integer.valueOf(1));
        body.add(incWaterButton, Integer.valueOf(1));
    }

    public void addController(IntController ctrl) {
        controllers.add(ctrl);
    }

    @Override
    public void update(Integer data) {
        waterIconLabel.setIcon(icons[data]);
        SaveSingleton.instance().writeWaterAmount(data);
    }

    @Override
    public void reset() {
        controllers.forEach(c -> c.updateModels(0));
    }

    @Override
    public String getName() {
        return "water view";
    }

    public void setPosition(int x, int y) {
        boundsManager.setBoundsForMaster(body, x, y);
    }

    @Override
    public JLayeredPane show() {
        return body;
    }
}
