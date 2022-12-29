package dev.phonis.horseinfomod.render.grid;

import dev.phonis.horseinfomod.render.RGBAColor;
import dev.phonis.horseinfomod.render.RenderUtils;
import net.minecraft.client.util.math.MatrixStack;

public
class PercentBarRenderGridCell implements RenderGridCell
{

    private final RGBAColor color1;
    private final RGBAColor color2;
    private final float     width;
    private final float     height;
    private final double    percent;

    public
    PercentBarRenderGridCell(RGBAColor color1, RGBAColor color2, float width, float height, double percent)
    {
        this.color1  = color1;
        this.color2  = color2;
        this.width   = width;
        this.height  = height;
        this.percent = percent;
    }

    @Override
    public
    void render(MatrixStack matrixStack)
    {
        RenderUtils.renderPercentBar(matrixStack, this.color2, this.color1, this.percent, this.width, this.height);
    }

    @Override
    public
    double getWidth()
    {
        return this.width;
    }

    @Override
    public
    double getHeight()
    {
        return this.height;
    }

}
