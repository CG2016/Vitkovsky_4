package listeners;

import utils.Converter;
import utils.Main;
import utils.Painter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HSVListener implements ChangeListener {
    private Painter painter;
    private Converter converter;
    private Main main;

    public HSVListener(Converter conv, Painter pntr, Main m){
        converter = conv;
        painter = pntr;
        main = m;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (main.isHsv()) {
            main.getErrorLabel().setVisible(false);

            double h = (double) main.getSliderHue().getValue() / main.MAX_HUE;
            double s = (double) main.getSliderSaturation().getValue() / main.MAX_SV;
            double v = (double) main.getSliderValue().getValue() / main.MAX_SV;

            converter.setHSV(h, s, v);
            converter.HSVtoRGB();
            converter.RGBtoCMY();
            converter.RGBtoLUV();

            main.setRgb(false);
            setRGB();
            main.setRgb(true);
            main.setCmy(false);
            setCMY();
            main.setCmy(true);
            main.setLuv(false);
            setLUV();
            main.setLuv(true);

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

    private void setLUV() {
        //TODO whole method
        main.getSliderL().setValue((int)converter.getL_());
        main.getSliderU().setValue((int)converter.getU_());
        main.getSliderV().setValue((int)converter.getV_());
    }
}
