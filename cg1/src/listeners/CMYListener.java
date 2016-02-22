package listeners;

import utils.Converter;
import utils.Main;
import utils.Painter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CMYListener implements ChangeListener{
    private Painter painter;
    private Converter converter;
    private Main main;

    public CMYListener(Converter conv, Painter pntr, Main m){
        converter = conv;
        painter = pntr;
        main = m;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (main.isCmy()) {
            main.getErrorLabel().setVisible(false);

            double c = (double) main.getSliderCyan().getValue() / main.MAX_CMY;
            double m = (double) main.getSliderMagenta().getValue() / main.MAX_CMY;
            double y = (double) main.getSliderYellow().getValue() / main.MAX_CMY;

            converter.setCMY(c, m, y);
            converter.CMYtoRGB();
            converter.RGBtoHSV();
            converter.RGBtoLUV();

            main.setRgb(false);
            setRGB();
            main.setRgb(true);
            main.setHsv(false);
            setHSV();
            main.setHsv(true);
            main.setLuv(false);
            setLUV();
            main.setLuv(true);

            painter.paint(converter.getR(), converter.getG(), converter.getB());
        }
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

    private void setLUV() {
        //TODO whole method
        main.getSliderL().setValue((int)converter.getL_());
        main.getSliderU().setValue((int)converter.getU_());
        main.getSliderV().setValue((int)converter.getV_());
    }
}
