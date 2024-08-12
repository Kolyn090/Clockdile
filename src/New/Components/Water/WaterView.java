package New.Components.Water;

import New.DirectoryManager;
import New.util;

import javax.swing.*;

public class WaterView extends JLabel {
    private final ImageIcon[] icons = util.map(DirectoryManager.waterImageDirectories,
            ImageIcon::new, new ImageIcon[DirectoryManager.waterImageDirectories.length]);

    public WaterView() {
        ImageIcon icon = new ImageIcon(DirectoryManager.waterImageDirectories[0]);
        this.setIcon(icon);
    }

    public void changeAmount(int amount) {
        this.setIcon(icons[amount]);
    }
}
