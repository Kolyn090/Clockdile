package Clockdile.Components;

import javax.swing.*;

public interface ComponentView<T, J extends JComponent> extends View<T> {
    void setPosition(int x, int y);
    J show();
}
