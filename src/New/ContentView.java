package New;

import Frontend.Tools.MoveFrame;
import New.Components.Croc.CrocController;
import New.Components.Settings.SettingsController;
import New.Components.Water.WaterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

public class ContentView {
    public ContentView(int windowWidth, int windowHeight) {
        setLookAndFeel();
        JFrame window = makeWindow(windowWidth, windowHeight);
        Container body = makeContainer(window);
        window.setVisible(true);

        JLayeredPane mainLayer = makeJLayeredPane(windowWidth, windowHeight);
        body.add(mainLayer);

        CrocController crocController = new CrocController(windowWidth, windowHeight);
        body.add(crocController.getView());

        JButton exitButton = makeExitButton(window);
        exitButton.setBounds(10, 110, 28, 33);
        mainLayer.add(exitButton, Integer.valueOf(1));

        WaterController waterController = new WaterController();
        waterController.getView().setBounds(75, 162, 48, 56);
        mainLayer.add(waterController.getView(), Integer.valueOf(1));

        JButton decWaterButton = ComponentMaker.makeIconButton(
                DirectoryManager.leftArrow,
                waterController::decreaseAmount
        );
        decWaterButton.setPreferredSize(new Dimension(5, 8));
        decWaterButton.setBounds(71, 218, 6, 9);
        JButton incWaterButton = ComponentMaker.makeIconButton(
                DirectoryManager.rightArrow,
                waterController::increaseAmount
        );
        incWaterButton.setPreferredSize(new Dimension(5, 8));
        incWaterButton.setBounds(120, 218, 6, 9);
        mainLayer.add(decWaterButton, Integer.valueOf(1));
        mainLayer.add(incWaterButton, Integer.valueOf(1));

        SettingsController settingsController = new SettingsController();
        settingsController.getView().setBounds(59, 166, 81, 60);
        mainLayer.add(settingsController.getView(), Integer.valueOf(1));

        Disable(settingsController.getView());

        AtomicReference<Page> currentPage = new AtomicReference<>(Page.Water);
        JButton settingsNavigationButton = ComponentMaker.makeButton(
                () -> {
                    if (currentPage.get() == Page.Settings) return;
                    currentPage.set(Page.Settings);
                    Disable(waterController.getView());
                    Disable(decWaterButton);
                    Disable(incWaterButton);
                    Enable(settingsController.getView());
                }
        );
        settingsNavigationButton.setBounds(24, 159, 44, 38);
        settingsNavigationButton.setPreferredSize(new Dimension(44, 38));
        mainLayer.add(settingsNavigationButton, Integer.valueOf(1));

        JButton waterNavigationButton = ComponentMaker.makeButton(
                () -> {
                    if (currentPage.get() == Page.Water) return;
                    currentPage.set(Page.Water);
                    Enable(waterController.getView());
                    Enable(decWaterButton);
                    Enable(incWaterButton);
                    Disable(settingsController.getView());
                }
        );
        waterNavigationButton.setBounds(133, 159, 44, 38);
        waterNavigationButton.setPreferredSize(new Dimension(44, 38));
        mainLayer.add(waterNavigationButton, Integer.valueOf(1));

        body.revalidate();
        body.repaint();
    }

    private void Enable(JComponent component) {
        component.setEnabled(true);
        component.setVisible(true);
    }

    private void Disable(JComponent component) {
        component.setEnabled(false);
        component.setVisible(false);
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

    private JButton makeExitButton(JFrame window) {
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
