package New;

import Frontend.Tools.MoveFrame;
import New.Components.Clock.CountUpClock;
import New.Components.Croc.CrocController;
import New.Components.Settings.Interval.IntervalView;
import New.Components.IntController;
import New.Components.IntModel;
import New.Components.Settings.Volume.VolumeView;
import New.Components.Water.WaterView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

import static New.util.Disable;
import static New.util.Enable;

public class ContentView {
    public ContentView(int windowWidth, int windowHeight) {
        Font avocadoFont = FontMaker.makeFont(8, DirectoryManager.avocado);

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

// region Water
        IntController waterController = new IntController();
        IntModel waterModel = new IntModel(0, 8);
        WaterView waterView = new WaterView();

        waterController.addModel(waterModel);
        waterModel.addObserver(waterView);
        waterView.addController(waterController);

        waterView.setPosition(76, 160);
        mainLayer.add(waterView.show(), Integer.valueOf(1));
// endregion

// region settings-title
        JLabel settingsTitle = new JLabel("Settings");
        settingsTitle.setForeground(Color.white);

        settingsTitle.setFont(avocadoFont);
        settingsTitle.setHorizontalAlignment(SwingConstants.CENTER);
        settingsTitle.setBounds(75, 160, 46, 20);
        mainLayer.add(settingsTitle, Integer.valueOf(1));
        Disable(settingsTitle);
// endregion

// region settings-interval
        IntController intervalController = new IntController();
        IntModel intervalModel = new IntModel(1, 60);
        IntervalView intervalView = new IntervalView();

        intervalController.addModel(intervalModel);
        intervalModel.addObserver(intervalView);
        intervalView.addController(intervalController);

        intervalView.setPosition(59, 186);
        mainLayer.add(intervalView.show(), Integer.valueOf(1));
        Disable(intervalView.show());
// endregion

// region settings-volume
        IntController volumeController = new IntController();
        IntModel volumeModel = new IntModel(0, 100);
        VolumeView volumeView = new VolumeView(new Color(193, 153, 92));

        volumeController.addModel(volumeModel);
        volumeModel.addObserver(volumeView);
        volumeView.addController(volumeController);

        volumeView.setPosition(59, 210);
        mainLayer.add(volumeView.show(), Integer.valueOf(1));
        Disable(volumeView.show());
// endregion

        AtomicReference<Page> currentPage = new AtomicReference<>(Page.Water);
        JButton settingsNavigationButton = ComponentMaker.makeButton(
                () -> {
                    if (currentPage.get() == Page.Settings) return;
                    currentPage.set(Page.Settings);
                    Disable(waterView.show());
                    Enable(settingsTitle);
                    Enable(intervalView.show());
                    Enable(volumeView.show());
                }
        );
        settingsNavigationButton.setBounds(14, 159, 44, 38);
        settingsNavigationButton.setPreferredSize(new Dimension(44, 38));
        mainLayer.add(settingsNavigationButton, Integer.valueOf(1));

        JButton waterNavigationButton = ComponentMaker.makeButton(
                () -> {
                    if (currentPage.get() == Page.Water) return;
                    currentPage.set(Page.Water);
                    Enable(waterView.show());
                    Disable(settingsTitle);
                    Disable(intervalView.show());
                    Disable(volumeView.show());
                }
        );
        waterNavigationButton.setBounds(143, 159, 44, 38);
        waterNavigationButton.setPreferredSize(new Dimension(44, 38));
        mainLayer.add(waterNavigationButton, Integer.valueOf(1));

//        CountDownClock countDownClock = new CountDownClock();
//        countDownClock.getView().setBounds(0, 107, 200, 40);
//        mainLayer.add(countDownClock.getView(), Integer.valueOf(1));
//
//        Disable(countDownClock.getView());

        CountUpClock countUpClock = new CountUpClock();
        countUpClock.getView().setBounds(0, 107, 200, 40);
        mainLayer.add(countUpClock.getView(), Integer.valueOf(1));

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
