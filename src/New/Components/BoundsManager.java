package New.Components;

import javax.swing.*;
import java.awt.*;

public class BoundsManager {
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public void setBounds(JComponent component, Rectangle rectangle) {
        setBounds(component, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void setBounds(JComponent component, int x, int y, int width, int height) {
        component.setBounds(x, y, width, height);
        if (x + width > this.maxX) {
            this.maxX = x + width;
        }
        if (y + height > this.maxY) {
            this.maxY = y + height;
        }
        if (x < this.minX) {
            this.minX = x;
        }
        if (y < this.minY) {
            this.minY = y;
        }
    }

    public void setBoundsForMaster(JComponent master, int x, int y) {
        master.setBounds(x+minX, y+minY, maxX + Math.abs(minX), maxY + Math.abs(minY));
    }
}
