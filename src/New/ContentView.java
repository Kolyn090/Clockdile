package New;

import Frontend.Tools.MoveFrame;
import New.Components.Croc.CrocController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentView {
    private final JFrame window;

    public ContentView(int windowWidth, int windowHeight) {
        setLookAndFeel();
        window = makeWindow(windowWidth, windowHeight);
        Container body = makeContainer(window);
        window.setVisible(true);

        JLayeredPane mainLayer = makeJLayeredPane(windowWidth, windowHeight);
        body.add(mainLayer);

        CrocController crocController = new CrocController(windowWidth, windowHeight);
        body.add(crocController.getView());

        JButton exitButton = makeExitButton();
        exitButton.setBounds(10, 110, 28, 33);
        mainLayer.add(exitButton, Integer.valueOf(1));

        body.revalidate();
        body.repaint();
    }

    private JFrame makeWindow(int windowWidth, int windowHeight) {
        JFrame window = new MoveFrame();
        window.setSize(windowWidth, windowHeight);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(new BorderLayout());
        window.setResizable(false);
        Color transparentColor = new Color(0f, 0f, 0f, 0f);
        window.setBackground(transparentColor);

        return window;
    }

    private Container makeContainer(JFrame window) {
        Container container = window.getContentPane();
        container.setPreferredSize(new Dimension(window.getWidth(), window.getHeight()));

        return container;
    }

    private JLayeredPane makeJLayeredPane(int width, int height) {
        JLayeredPane pane = new JLayeredPane();
        pane.setBackground(Color.black);
        pane.setBounds(0, 0, width, height);
        return pane;
    }

    private JButton makeExitButton() {
        JButton button = new JButton("");
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
            }
        });

        return button;
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
