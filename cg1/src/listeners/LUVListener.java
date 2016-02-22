package listeners;

import utils.Converter;
import utils.Main;
import utils.Painter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LUVListener implements ChangeListener {
    private Painter painter;
    private Converter converter;
    private Main main;

    public LUVListener(Converter conv, Painter pntr, Main m){
        converter = conv;
        painter = pntr;
        main = m;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (main.isLuv()) {
            double l = (double) main.getSliderL().getValue();
            double u = (double) main.getSliderU().getValue();
            double v = (double) main.getSliderV().getValue();

            converter.setLUV(l, u, v);
            converter.LUVtoRGB();
            if (converter.isError()) {
                converter.setError(false);
                main.getErrorLabel().setVisible(true);
            }
            else
                main.getErrorLabel().setVisible(false);
            converter.RGBtoCMY();
            converter.RGBtoHSV();

            main.setRgb(false);
            setRGB();
            main.setRgb(true);
            main.setCmy(false);
            setCMY();
            main.setCmy(true);
            main.setHsv(false);
            setHSV();
            main.setHsv(true);

            painter.paint(converter.getR(), converter.getG(), converter.getB());
        }
    }

    private void setCMY() {
        main.getSliderCyan().setValue((int)(main.MAX_CMY * converter.getC()));
        main.getSliderMagenta().setValue((int)(main.MAX_CMY * converter.getM()));
        main.getSliderYellow().setValue((int)(main.MAX_CMY * converter.getY()));
    }

    private void setRGB() {
        main.getSliderRed().setValue((int)(main.MAX_RGB * converter.getR()));
        main.getSliderGreen().setValue((int)(main.MAX_RGB * converter.getG()));
        main.getSliderBlue().setValue((int)(main.MAX_RGB * converter.getB()));
    }

    private void setHSV() {
        main.getSliderHue().setValue((int)(main.MAX_HUE * converter.getH()));
        main.getSliderSaturation().setValue((int)(main.MAX_SV * converter.getS()));
        main.getSliderValue().setValue((int)(main.MAX_SV * converter.getV()));
    }
}
