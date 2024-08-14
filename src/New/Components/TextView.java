package New.Components;

import New.DirectoryManager;
import New.FontMaker;

import javax.swing.*;
import java.awt.*;

public class TextView implements ComponentView<String, JLabel> {
    private final JLabel body;
    private final int width = 100;
    private final int height = 30;

    public TextView(Font font) {
        body = new JLabel();
        body.setBounds(0, 0, width, height);
        body.setFont(font);
        body.setHorizontalAlignment(SwingConstants.CENTER);
        body.setForeground(Color.black);
    }

    @Override
    public void setPosition(int x, int y) {
        body.setBounds(x, y, width, height);
    }

    @Override
    public JLabel show() {
        return body;
    }

    @Override
    public void update(String data) {
        body.setText(data);
    }

    @Override
    public void reset() {
        body.setText("");
    }

    @Override
    public String getName() {
        return "text view";
    }
}
