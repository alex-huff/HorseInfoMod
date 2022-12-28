package dev.phonis.horseinfomod.render;

import me.shedaniel.math.Color;

public
class RGBAColor
{

    public int r;
    public int g;
    public int b;
    public int a;

    public
    RGBAColor(int r, int g, int b, int a)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public
    RGBAColor(int color)
    {
        this.a = ((color >>> 24) & 0xFF);
        this.r = ((color >>> 16) & 0xFF);
        this.g = ((color >>> 8) & 0xFF);
        this.b = (color & 0xFF);
    }

    public
    int toInt()
    {
        int red   = this.r & 0xFF;
        int green = this.g & 0xFF;
        int blue  = this.b & 0xFF;
        int alpha = this.a & 0xFF;

        return (alpha << 24) + (red << 16) + (green << 8) + (blue);
    }

    public
    Color toSheDanielColor()
    {
        return Color.ofRGB(this.r, this.g, this.b);
    }

    public
    void updateRGB(Color sheDaniel)
    {
        this.r = sheDaniel.getRed();
        this.g = sheDaniel.getGreen();
        this.b = sheDaniel.getBlue();
    }

    public
    void updateA(int a)
    {
        this.a = a;
    }

}