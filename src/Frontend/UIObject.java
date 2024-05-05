package Frontend;

import Frontend.Tools.MoveFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class UIObject {
    private final int windowWidth = 200;
    private final int windowHeight = 200;
    private final Font font = assignDigitalClockFont(35);

    public UIObject() {

    }

    public JPanel assignJPanel(int height) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setPreferredSize(new Dimension(windowWidth, height));
        panel.setLayout(new BorderLayout());
        return panel;
    }

    public JLayeredPane assignJLayeredPane(int height, int width) {
        JLayeredPane pane = new JLayeredPane();
        pane.setBackground(Color.black);
        pane.setBounds(0, 0, width, height);
        return pane;
    }

    public JLabel assignJLabel(String content, int fontSize) {
        Font tempFont = font.deriveFont(Font.PLAIN, fontSize);
        JLabel jLabel = new JLabel(content);
        jLabel.setForeground(Color.white);
        jLabel.setFont(tempFont);

        return jLabel;
    }

    public JButton assignJButton(String text, String command) {
        JButton jButton = new JButton(text);
        jButton.setBackground(Color.black);
        jButton.setForeground(Color.white);
        jButton.setFont(font);
        jButton.setOpaque(true);
        jButton.setBorderPainted(false);
        //jButton.addActionListener(ch); add this when needed
        jButton.setActionCommand(command);
        jButton.setFocusPainted(false);
        jButton.setContentAreaFilled(false);

        return jButton;
    }

    public JFrame assignWindow() {
        JFrame window = new MoveFrame();
        window.setSize(windowWidth, windowHeight);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(new BorderLayout());
        window.setResizable(false);
        window.setBackground(getTransparentColor());

        return window;
    }

    public Container assignContainer(JFrame window) {
        Container container = window.getContentPane();
        container.setPreferredSize(new Dimension(windowWidth, windowHeight));

        return container;
    }

    public Font assignDigitalClockFont(int fontSize) {
        Font temp = null;
        try {
            temp = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Frontend/Fonts/digital-7 (mono).ttf"));
            temp = temp.deriveFont(Font.PLAIN, fontSize);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public Font assignAvocadoFont(int fontSize) {
        Font temp = null;
        try {
            temp = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Frontend/Fonts/avocado.ttf"));
            temp = temp.deriveFont(Font.PLAIN, fontSize);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Color getTransparentColor() {
        return new Color(0f, 0f, 0f, 0f);
    }
    public int getWindowWidth() {
        return windowWidth;
    }
    public int getWindowHeight() {
        return windowHeight;
    }
}
