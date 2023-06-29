package dev.phonis.horseinfomod.render;

import dev.phonis.horseinfomod.config.HIMConfig;
import dev.phonis.horseinfomod.render.grid.*;
import dev.phonis.horseinfomod.util.HorsieUtils;
import dev.phonis.horseinfomod.util.StringUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.passive.AbstractHorseEntity;

public
class HorseOverlayRenderer
{

    public static
    void renderHorseOverlay(DrawContext drawContext, float tickDelta, AbstractHorseEntity horsie)
    {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        float           scale           = HIMConfig.INSTANCE.renderScale / 100F;
        float           barWidth        = 60F;
        float           barHeight       = minecraftClient.textRenderer.fontHeight;
        RenderGrid renderGridStats = RenderGridImpl.of(HIMConfig.INSTANCE.margin, new RenderGridCell[][]{
            {
                new TextRenderGridCell("Speed:", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(StringUtils.formatDouble(HorsieUtils.getMovementSpeed(horsie)),
                    HIMConfig.INSTANCE.textColor), new TextRenderGridCell("/", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(StringUtils.formatDouble(HorsieUtils.getMaxMovementSpeed()),
                    HIMConfig.INSTANCE.textColor),
                new PercentBarRenderGridCell(HIMConfig.INSTANCE.percentColor1, HIMConfig.INSTANCE.percentColor2,
                    barWidth, barHeight, HorsieUtils.getMovementSpeedPercent(horsie))
            }, {
                new TextRenderGridCell("Jump:", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(StringUtils.formatDouble(HorsieUtils.getJumpStrength(horsie)),
                    HIMConfig.INSTANCE.textColor), new TextRenderGridCell("/", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(StringUtils.formatDouble(HorsieUtils.getMaxJumpStrength()),
                    HIMConfig.INSTANCE.textColor),
                new PercentBarRenderGridCell(HIMConfig.INSTANCE.percentColor1, HIMConfig.INSTANCE.percentColor2,
                    barWidth, barHeight, HorsieUtils.getJumpStrengthPercent(horsie))
            }, {
                new TextRenderGridCell("Health:", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(StringUtils.formatDouble(HorsieUtils.getHealth(horsie)),
                    HIMConfig.INSTANCE.textColor), new TextRenderGridCell("/", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(StringUtils.formatDouble(HorsieUtils.getMaxHealth()),
                    HIMConfig.INSTANCE.textColor),
                new PercentBarRenderGridCell(HIMConfig.INSTANCE.percentColor1, HIMConfig.INSTANCE.percentColor2,
                    barWidth, barHeight, HorsieUtils.getHealthPercent(horsie))
            }, {
                new TextRenderGridCell("Score:", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(StringUtils.formatDouble(HorsieUtils.getScore(horsie)),
                    HIMConfig.INSTANCE.textColor), new TextRenderGridCell("/", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(StringUtils.formatDouble(HorsieUtils.getMaxScore()),
                    HIMConfig.INSTANCE.textColor),
                new PercentBarRenderGridCell(HIMConfig.INSTANCE.percentColor1, HIMConfig.INSTANCE.percentColor2,
                    barWidth, barHeight, HorsieUtils.getScore(horsie))
            },
            });
        RenderGrid renderGridVisualTraits = RenderGridImpl.of(HIMConfig.INSTANCE.margin, new RenderGridCell[][]{
            {
                new TextRenderGridCell("Color:", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(HorsieUtils.getColorString(horsie), HIMConfig.INSTANCE.textColor)
            }, {
                new TextRenderGridCell("Marking:", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(HorsieUtils.getMarkingString(horsie), HIMConfig.INSTANCE.textColor)
            }, {
                new TextRenderGridCell("Name:", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(HorsieUtils.getCustomNameString(horsie), HIMConfig.INSTANCE.textColor)
            }
        });
        RenderGrid renderGridPreview = RenderGridImpl.of(HIMConfig.INSTANCE.margin, new RenderGridCell[][]{
            { new HorsePreviewRenderGridCell(horsie, tickDelta, 35), renderGridVisualTraits }
        });
        RenderGrid renderGridFull = RenderGridImpl.of(HIMConfig.INSTANCE.margin, new RenderGridCell[][]{
            { renderGridStats }, { renderGridPreview }
        });
        double width  = HIMConfig.INSTANCE.margin + renderGridFull.getWidth() + HIMConfig.INSTANCE.margin;
        double height = HIMConfig.INSTANCE.margin + renderGridFull.getHeight() + HIMConfig.INSTANCE.margin;
        drawContext.getMatrices().push();
        drawContext.getMatrices().translate(minecraftClient.getWindow().getScaledWidth() / 2.0,
            minecraftClient.getWindow().getScaledHeight() / 2.0, 0);
        drawContext.getMatrices().scale(scale, scale, scale);
        drawContext.getMatrices().translate(-width / 2, -height / 2, 0);
        RenderUtils.renderBox(drawContext.getMatrices(), HIMConfig.INSTANCE.overlayBackgroundColor, (float) width, (float) height);
        drawContext.getMatrices().translate(HIMConfig.INSTANCE.margin, HIMConfig.INSTANCE.margin, 0);
        renderGridFull.render(drawContext);
        drawContext.getMatrices().pop();
    }

}
