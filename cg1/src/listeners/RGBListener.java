package listeners;

import utils.*;
import utils.Painter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RGBListener implements ChangeListener {
    private Painter painter;
    private Converter converter;
    private Main main;

    public RGBListener(Converter conv, Painter pntr, Main m){
        converter = conv;
        painter = pntr;
        main = m;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (main.isRgb()) {
            main.getErrorLabel().setVisible(false);

            double r = (double) main.getSliderRed().getValue() / main.MAX_RGB;
            double g = (double) main.getSliderGreen().getValue() / main.MAX_RGB;
            double b = (double) main.getSliderBlue().getValue() / main.MAX_RGB;

            converter.setRGB(r, g, b);
            converter.RGBtoCMY();
            converter.RGBtoHSV();
            converter.RGBtoLUV();

            main.setCmy(false);
            setCMY();
            main.setCmy(true);
            main.setHsv(false);
            setHSV();
            main.setHsv(true);
            main.setLuv(false);
            setLUV();
            main.setLuv(true);

            painter.paint(r, g, b);
        }
    }

    private void setCMY() {
        main.getSliderCyan().setValue((int)(main.MAX_CMY * converter.getC()));
        main.getSliderMagenta().setValue((int)(main.MAX_CMY * converter.getM()));
        main.getSliderYellow().setValue((int)(main.MAX_CMY * converter.getY()));
    }

    private void setHSV() {
        main.getSliderHue().setValue((int)(main.MAX_HUE * converter.getH()));
        main.getSliderSaturation().setValue((int)(main.MAX_SV * converter.getS()));
        main.getSliderValue().setValue((int)(main.MAX_SV * converter.getV()));
    }

    private void setLUV() {
        //TODO whole method
        main.getSliderL().setValue((int)converter.getL_());
        main.getSliderU().setValue((int)converter.getU_());
        main.getSliderV().setValue((int)converter.getV_());
    }

}
