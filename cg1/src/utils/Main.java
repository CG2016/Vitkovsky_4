package utils;

import listeners.CMYListener;
import listeners.HSVListener;
import listeners.LUVListener;
import listeners.RGBListener;

import javax.swing.*;

/**
 * Created by artvi on 17/02/2016.
 */
public class Main {
    public final int MIN_RGB = 0;
    public final int MAX_RGB = 255;
    public final int INIT_RGB = 0;

    public final int MIN_CMY = 0;
    public final int MAX_CMY = 255;

    public final int MIN_HUE = 0;
    public final int MAX_HUE = 360;
    public final int MIN_SV = 0;
    public final int MAX_SV = 100;

    public final int MIN_L = 0;
    public final int MAX_L = 100;
    public final int MIN_UV = -200;
    public final int MAX_UV =  200;

    private JPanel mainPanel;
    private JPanel view;
    private JPanel controllers;

    private JSlider sliderRed;
    private JSlider sliderGreen;
    private JSlider sliderBlue;
    private JSlider sliderHue;
    private JSlider sliderSaturation;
    private JSlider sliderValue;
    private JSlider sliderCyan;
    private JSlider sliderMagenta;
    private JSlider sliderYellow;
    private JSlider sliderL;
    private JSlider sliderU;
    private JSlider sliderV;

    private JLabel labelRed;
    private JLabel labelGreen;
    private JLabel labelBlue;
    private JLabel labelHue;
    private JLabel labelSat;
    private JLabel labelVal;
    private JLabel labelCyan;
    private JLabel labelMagenta;
    private JLabel labelYellow;
    private JLabel labelL;
    private JLabel labelU;
    private JLabel labelV;
    private JLabel errorLabel;

    private boolean rgb = true;
    private boolean cmy = true;
    private boolean hsv = true;
    private boolean luv = true;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        Main main = new Main();
        frame.setContentPane(main.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        main.initialize();
        frame.setVisible(true);
    }

    public void initialize() {
        Converter converter = new Converter();

        RGBListener rgbl = new RGBListener(converter, (Painter)view, this);
        sliderRed.addChangeListener(rgbl);
        sliderGreen.addChangeListener(rgbl);
        sliderBlue.addChangeListener(rgbl);

        CMYListener cmyl = new CMYListener(converter, (Painter)view, this);
        sliderCyan.addChangeListener(cmyl);
        sliderMagenta.addChangeListener(cmyl);
        sliderYellow.addChangeListener(cmyl);

        HSVListener hsvl = new HSVListener(converter, (Painter)view, this);
        sliderHue.addChangeListener(hsvl);
        sliderSaturation.addChangeListener(hsvl);
        sliderValue.addChangeListener(hsvl);

        LUVListener luvl = new LUVListener(converter, (Painter)view, this);
        sliderL.addChangeListener(luvl);
        sliderU.addChangeListener(luvl);
        sliderV.addChangeListener(luvl);
    }

    private void createUIComponents() {
        sliderRed = new JSlider(MIN_RGB, MAX_RGB, INIT_RGB);
        sliderRed.setMajorTickSpacing(MAX_RGB);
        sliderRed.setPaintLabels(true);

        sliderGreen = new JSlider(MIN_RGB, MAX_RGB, INIT_RGB);
        sliderGreen.setMajorTickSpacing(MAX_RGB);
        sliderGreen.setPaintLabels(true);

        sliderBlue = new JSlider(MIN_RGB, MAX_RGB, INIT_RGB);
        sliderBlue.setMajorTickSpacing(MAX_RGB);
        sliderBlue.setPaintLabels(true);


        sliderCyan = new JSlider(MIN_CMY, MAX_CMY, INIT_RGB);
        sliderCyan.setMajorTickSpacing(MAX_CMY);
        sliderCyan.setPaintLabels(true);

        sliderMagenta = new JSlider(MIN_CMY, MAX_CMY, INIT_RGB);
        sliderMagenta.setMajorTickSpacing(MAX_CMY);
        sliderMagenta.setPaintLabels(true);

        sliderYellow = new JSlider(MIN_CMY, MAX_CMY, INIT_RGB);
        sliderYellow.setMajorTickSpacing(MAX_CMY);
        sliderYellow.setPaintLabels(true);


        sliderHue = new JSlider(MIN_HUE, MAX_HUE, 0);
        sliderHue.setMajorTickSpacing(MAX_HUE);
        sliderHue.setPaintLabels(true);

        sliderSaturation = new JSlider(MIN_SV, MAX_SV, 0);
        sliderSaturation.setMajorTickSpacing(MAX_SV);
        sliderSaturation.setPaintLabels(true);

        sliderValue = new JSlider(MIN_SV, MAX_SV, 0);
        sliderValue.setMajorTickSpacing(MAX_SV);
        sliderValue.setPaintLabels(true);


        sliderL = new JSlider(MIN_L, MAX_L, 0);
        sliderL.setMajorTickSpacing(MAX_L);
        sliderL.setPaintLabels(true);

        sliderU = new JSlider(MIN_UV, MAX_UV, 0);
        sliderU.setMajorTickSpacing(MAX_UV - MIN_UV);
        sliderU.setPaintLabels(true);

        sliderV = new JSlider(MIN_UV, MAX_UV, 0);
        sliderV.setMajorTickSpacing(MAX_UV - MIN_UV);
        sliderV.setPaintLabels(true);


        view = new Painter();
    }

    public JSlider getSliderRed() {
        return sliderRed;
    }

    public JSlider getSliderGreen() {
        return sliderGreen;
    }

    public JSlider getSliderBlue() {
        return sliderBlue;
    }

    public JSlider getSliderHue() {
        return sliderHue;
    }

    public JSlider getSliderSaturation() {
        return sliderSaturation;
    }

    public JSlider getSliderValue() {
        return sliderValue;
    }

    public JSlider getSliderCyan() {
        return sliderCyan;
    }

    public JSlider getSliderMagenta() {
        return sliderMagenta;
    }

    public JSlider getSliderYellow() {
        return sliderYellow;
    }

    public JSlider getSliderL() {
        return sliderL;
    }

    public JSlider getSliderU() {
        return sliderU;
    }

    public JSlider getSliderV() {
        return sliderV;
    }

    public boolean isRgb() {
        return rgb;
    }

    public void setRgb(boolean rgb) {
        this.rgb = rgb;
    }

    public boolean isCmy() {
        return cmy;
    }

    public void setCmy(boolean cmy) {
        this.cmy = cmy;
    }

    public boolean isHsv() {
        return hsv;
    }

    public void setHsv(boolean hsv) {
        this.hsv = hsv;
    }

    public boolean isLuv() {
        return luv;
    }

    public void setLuv(boolean luv) {
        this.luv = luv;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }
}
