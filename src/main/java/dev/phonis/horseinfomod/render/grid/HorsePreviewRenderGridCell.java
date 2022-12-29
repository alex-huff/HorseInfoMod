package dev.phonis.horseinfomod.render.grid;

import dev.phonis.horseinfomod.render.RenderUtils;
import net.minecraft.client.util.math.MatrixStack;
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
    void render(MatrixStack matrixStack)
    {
        matrixStack.push();
        matrixStack.scale(scale, -scale, scale);
        matrixStack.translate(HorsePreviewRenderGridCell.baseWidth / 2, -HorsePreviewRenderGridCell.baseHeight,
            HorsePreviewRenderGridCell.baseHeight);
        RenderUtils.renderHorsePreview(matrixStack, horsie, tickDelta, HorsePreviewRenderGridCell.previewYaw);
        matrixStack.pop();
    }

    @Override
    public
    double getWidth()
    {
        return HorsePreviewRenderGridCell.baseWidth * scale;
    }

    @Override
    public
    double getHeight()
    {
        return HorsePreviewRenderGridCell.baseHeight * scale;
    }

}
