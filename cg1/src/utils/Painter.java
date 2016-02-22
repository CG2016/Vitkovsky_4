package utils;

import javax.swing.*;
import java.awt.*;

public class Painter extends JPanel {
    //private JPanel panel;
    private Color color;

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRect (0, 0, this.getWidth(), this.getHeight());
    }

    public Painter() {
        super();
        this.color = new Color(0);
    }

    public void paint(double r, double g, double b) {
        color = new Color((float) r, (float)g, (float)b);
        this.repaint();
    }


}
