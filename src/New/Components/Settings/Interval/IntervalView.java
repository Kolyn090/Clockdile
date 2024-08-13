package New.Components.Settings.Interval;

import New.ComponentMaker;
import New.Components.BoundsManager;
import New.Components.ComponentView;
import New.Components.IntController;
import New.DirectoryManager;
import New.FontMaker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class IntervalView implements ComponentView<Integer, JLayeredPane> {
    private final static int DEFAULT_INTERVAL = 1;
    private final JLayeredPane body;
    private final JLabel numberText;
    private final ArrayList<IntController> controllers;
    private final BoundsManager boundsManager;

    public IntervalView() {
        controllers = new ArrayList<>();
        boundsManager = new BoundsManager();

        Font avocadoFont = FontMaker.makeFont(8, DirectoryManager.avocado);

        body = new JLayeredPane();
        body.setBackground(new Color(0, 0, 0, 0));
        JLabel title = new JLabel("Interval");
        title.setForeground(Color.BLACK);
        title.setFont(avocadoFont);
        title.setPreferredSize(new Dimension(44, 8));
        boundsManager.setBounds(
                title,
                new Rectangle(0, 0, 44,  12)
        );
        body.add(title, Integer.valueOf(1));

        JButton decIntervalButton = ComponentMaker.makeIconButton(
                DirectoryManager.leftArrow,
                () -> controllers.forEach(IntController::decrement)
        );
        Color paletteBGColor = new Color(193, 153, 92);
        decIntervalButton.setPreferredSize(new Dimension(5, 8));
        boundsManager.setBounds(
                decIntervalButton,
                new Rectangle(44, 0, 6, 12)
        );
        decIntervalButton.setOpaque(true);
        decIntervalButton.setBackground(paletteBGColor);
        numberText = new JLabel(getPrettyIntervalNumberText(DEFAULT_INTERVAL));
        numberText.setForeground(Color.BLACK);
        numberText.setFont(avocadoFont);
        numberText.setPreferredSize(new Dimension(14, 12));
        boundsManager.setBounds(
                numberText,
                new Rectangle(52, 0, 16, 12)
        );
        numberText.setOpaque(true);
        numberText.setBackground(paletteBGColor);
        JButton incIntervalButton = ComponentMaker.makeIconButton(
                DirectoryManager.rightArrow,
                () -> controllers.forEach(IntController::increment)
        );
        incIntervalButton.setPreferredSize(new Dimension(5, 8));
        boundsManager.setBounds(
                incIntervalButton,
                new Rectangle(68, 0, 6, 12)
        );
        incIntervalButton.setOpaque(true);
        incIntervalButton.setBackground(paletteBGColor);

        body.add(decIntervalButton, Integer.valueOf(1));
        body.add(numberText, Integer.valueOf(1));
        body.add(incIntervalButton, Integer.valueOf(1));
    }

    private String getPrettyIntervalNumberText(int amount) {
        return (amount < 10 ? "   ": " ") + amount;
    }

    public void addController(IntController ctrl) {
        controllers.add(ctrl);
    }

    @Override
    public void update(Integer data) {
        numberText.setText(getPrettyIntervalNumberText(data));
    }

    @Override
    public void reset() {
        controllers.forEach(c -> c.updateModels(DEFAULT_INTERVAL));
    }

    @Override
    public String getName() {
        return "interval view";
    }

    @Override
    public void setPosition(int x, int y) {
        boundsManager.setBoundsForMaster(body, x, y);
    }

    @Override
    public JLayeredPane show() {
        return body;
    }
}
