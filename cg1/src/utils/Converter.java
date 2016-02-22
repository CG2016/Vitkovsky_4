package utils;

public class Converter {
    private double r;
    private double g;
    private double b;

    public void setRGB(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    private double c;
    private double m;
    private double y;

    public void setCMY(double c, double m, double y) {
        this.c = c;
        this.m = m;
        this.y = y;
    }

    private double h;
    private double s;
    private double v;

    public void setHSV(double h, double s, double v) {
        this.h = h;
        this.s = s;
        this.v = v;
    }

    private double l_;
    private double u_;
    private double v_;

    public void setLUV(double l, double u, double v) {
        this.l_ = l;
        this.u_ = u;
        this.v_ = v;
    }

    private double Xxyz;
    private double Yxyz;
    private double Zxyz;

    private boolean error;

    public Converter() {
        r = 0;
        g = 0;
        b = 0;
        RGBtoCMY();
        RGBtoHSV();
    }

    public void LUVtoRGB() {
        LUVtoXYZ();
        XYZtoRGB();

        error = false;
        if (r > 1.) {
            r = 1.;
            error = true;
        }
        if (g > 1.){
            g = 1.;
            error = true;
        }
        if (b > 1.) {
            b = 1.;
            error = true;
        }

        if (r < 0.) {
            r = 0.;
            error = true;
        }
        if (g < 0.) {
            g = 0.;
            error = true;
        }
        if (b < 0.) {
            b = 0.;
            error = true;
        }
    }

    private void LUVtoXYZ() {
        double var_Y = (l_ + 16.) / 116.;
        if (Math.pow(var_Y, 3) > 0.008856)
            var_Y = Math.pow(var_Y, 3);
        else
            var_Y = (var_Y - 16. / 116.) / 7.787;

        double ref_X =  95.047;     //Observer= 2°, Illuminant= D65
        double ref_Y = 100.000;
        double ref_Z = 108.883;

        double ref_U = ( 4 * ref_X ) / ( ref_X + ( 15 * ref_Y ) + ( 3 * ref_Z ) );
        double ref_V = ( 9 * ref_Y ) / ( ref_X + ( 15 * ref_Y ) + ( 3 * ref_Z ) );

        double var_U = u_ / ( 13. * l_ ) + ref_U;
        double var_V = v_ / ( 13. * l_ ) + ref_V;

        Yxyz = var_Y;
        Xxyz =  - ( 9. * Yxyz * var_U ) / ( ( var_U - 4. ) * var_V  - var_U * var_V );
        Zxyz = ( 9. * Yxyz - ( 15. * var_V * Yxyz ) - ( var_V * Xxyz ) ) / ( 3. * var_V );
    }

    private void XYZtoRGB() {
        double Xt = Xxyz;
        double Yt = Yxyz;
        double Zt = Zxyz;

        r = Xt *  3.2406 + Yt * -1.5372 + Zt * -0.4986;
        g = Xt * -0.9689 + Yt *  1.8758 + Zt *  0.0415;
        b = Xt *  0.0557 + Yt * -0.2040 + Zt *  1.0570;

        if (r > 0.0031308)
            r = 1.055 * Math.pow(r, (1 / 2.4)) - 0.055;
        else
            r *= 12.92;

        if (g > 0.0031308)
            g = 1.055 * Math.pow(g, (1 / 2.4)) - 0.055;
        else
            g *= 12.92;

        if ( b > 0.0031308 )
            b = 1.055 * Math.pow(b, (1 / 2.4)) - 0.055;
        else
            b *= 12.92;
    }

    public void RGBtoLUV() {
        RGBtoXYZ();
        XYZtoLUV();
    }

    private void RGBtoXYZ() {
        double rt = r;
        double gt = g;
        double bt = b;

        if ( rt > 0.04045 )
            rt = Math.pow((rt + 0.055) / 1.055, 2.4);
        else
            rt /= 12.92;
        if ( gt > 0.04045 )
            gt = Math.pow((gt + 0.055) / 1.055, 2.4);
        else
            gt /= 12.92;
        if ( bt > 0.04045 )
            bt = Math.pow((bt + 0.055) / 1.055, 2.4);
        else
            bt /= 12.92;

        Xxyz = rt * 0.4124 + gt * 0.3576 + bt * 0.1805;
        Yxyz = rt * 0.2126 + gt * 0.7152 + bt * 0.0722;
        Zxyz = rt * 0.0193 + gt * 0.1192 + bt * 0.9505;
    }

    private void XYZtoLUV() {
        double ut = (4 * Xxyz) / (Xxyz + (15 * Yxyz) + (3 * Zxyz));
        double vt = (9 * Yxyz) / (Xxyz + (15 * Yxyz) + (3 * Zxyz));

        double ref_X =  95.047;        //Observer= 2°, Illuminant= D65
        double ref_Y = 100.000;
        double ref_Z = 108.883;

        double yt = Yxyz;
        if (yt > 0.008856)
            yt = Math.pow(yt, 1./3);
        else
            yt = ( 7.787 * yt ) + ( 16. / 116. );

        double ref_U = ( 4 * ref_X ) / ( ref_X + ( 15 * ref_Y ) + ( 3 * ref_Z ) );
        double ref_V = ( 9 * ref_Y ) / ( ref_X + ( 15 * ref_Y ) + ( 3 * ref_Z ) );

        l_ = (116 * yt) - 16;
        u_ = 13 * l_ * (ut - ref_U);
        v_ = 13 * l_ * (vt - ref_V);
    }

    public void RGBtoCMY() {
        c = 1 - r;
        m = 1 - g;
        y = 1 - b;
    }

    public void RGBtoHSV() {
        double cmax = Math.max(r, Math.max(g, b));
        double cmin = Math.min(r, Math.min(g, b));
        double delt = cmax - cmin;

        v = cmax;

        if (delt == 0) {
            h = 0;
            s = 0;
        }
        else {
            s = delt / cmax;

            double deltR = (((cmax - r) / 6) + (delt / 2)) / delt;
            double deltG = (((cmax - g) / 6) + (delt / 2)) / delt;
            double deltB = (((cmax - b) / 6) + (delt / 2)) / delt;

            if (cmax == r)
                h = deltB - deltG;
            else if (cmax == g)
                h = 1. / 3. + deltR - deltB;
            else if (cmax == b)
                h = 2. / 3. + deltG - deltR;

            if (h < 0)
                h += 1;
            if (h > 1)
                h -= 1;
        }
    }

    public void HSVtoRGB() {
        if (s == 0) {
            r = v;
            g = v;
            b = v;
        }
        else {
            double th = h * 6;
            if ( th == 6 )
                th = 0;
            int i = (int)th;
            double v1 = v * (1 - s);
            double v2 = v * (1 - s * (th - i));
            double v3 = v * (1 - s * (1 - (th - i)));

            if (i == 0) {
                r = v;
                g = v3;
                b = v1;
            }
            else if (i == 1) {
                r = v2;
                g = v;
                b = v1;
            }
            else if (i == 2) {
                r = v1;
                g = v;
                b = v3;
            }
            else if (i == 3) {
                r = v1;
                g = v2;
                b = v;
            }
            else if (i == 4) {
                r = v3;
                g = v1;
                b = v;
            }
            else {
                r = v;
                g = v1;
                b = v2;
            }
        }
    }

    public void CMYtoRGB() {
        r = 1 - c;
        g = 1 - m;
        b = 1 - y;
    }

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getM() {
        return m;
    }

    public double getY() {
        return y;
    }

    public double getH() {
        return h;
    }

    public double getS() {
        return s;
    }

    public double getV() {
        return v;
    }

    public double getL_() {
        return l_;
    }

    public double getU_() {
        return u_;
    }

    public double getV_() {
        return v_;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
