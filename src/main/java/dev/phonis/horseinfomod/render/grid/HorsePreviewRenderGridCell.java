package dev.phonis.horseinfomod.render.grid;

import dev.phonis.horseinfomod.render.RenderUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.passive.AbstractHorseEntity;

public
class HorsePreviewRenderGridCell implements RenderGridCell
{

    private static final float previewYaw = 35F;
    private static final float baseWidth  = 2F; // approximate width of horsie with 35 yaw
    private static final float baseHeight = 2.5F; // approximate height of horsie with 35 yaw

    private final AbstractHorseEntity horsie;
    private final float               tickDelta;
    private final float               scale;

    public
    HorsePreviewRenderGridCell(AbstractHorseEntity horsie, float tickDelta, float scale)
    {
        this.horsie    = horsie;
        this.tickDelta = tickDelta;
        this.scale     = scale;
    }

    @Override
    public
    void render(DrawContext drawContext)
    {
        drawContext.getMatrices().push();
        drawContext.getMatrices().scale(this.scale, -this.scale, this.scale);
        drawContext.getMatrices()
            .translate(HorsePreviewRenderGridCell.baseWidth / 2, -HorsePreviewRenderGridCell.baseHeight,
                HorsePreviewRenderGridCell.baseHeight);
        RenderUtils.renderHorsePreview(drawContext.getMatrices(), this.horsie, this.tickDelta,
            HorsePreviewRenderGridCell.previewYaw);
        drawContext.getMatrices().pop();
    }

    @Override
    public
    double getWidth()
    {
        return HorsePreviewRenderGridCell.baseWidth * this.scale;
    }

    @Override
    public
    double getHeight()
    {
        return HorsePreviewRenderGridCell.baseHeight * this.scale;
    }

}
