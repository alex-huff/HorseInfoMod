package dev.phonis.horseinfomod.render;

import dev.phonis.horseinfomod.config.HIMConfig;
import dev.phonis.horseinfomod.render.grid.*;
import dev.phonis.horseinfomod.util.HorsieUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.AbstractHorseEntity;

import java.math.BigDecimal;
import java.math.MathContext;

public
class HorseOverlayRenderer
{

    public static
    void renderHorseOverlay(MatrixStack matrixStack, float tickDelta, AbstractHorseEntity horsie)
    {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        float           scale           = HIMConfig.INSTANCE.renderScale / 100F;
        float           barWidth        = 60F;
        float           barHeight       = minecraftClient.textRenderer.fontHeight;
        RenderGrid renderGridStats = RenderGridImpl.of(HIMConfig.INSTANCE.margin, new RenderGridCell[][]{
            {
                new TextRenderGridCell("Speed:", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(HorseOverlayRenderer.formatDouble(HorsieUtils.getMovementSpeed(horsie)),
                    HIMConfig.INSTANCE.textColor), new TextRenderGridCell("/", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(HorseOverlayRenderer.formatDouble(HorsieUtils.getMaxMovementSpeed()),
                    HIMConfig.INSTANCE.textColor),
                new PercentBarRenderGridCell(HIMConfig.INSTANCE.percentColor1, HIMConfig.INSTANCE.percentColor2,
                    barWidth, barHeight, HorsieUtils.getMovementSpeedPercent(horsie))
            }, {
                new TextRenderGridCell("Jump:", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(HorseOverlayRenderer.formatDouble(HorsieUtils.getJumpStrength(horsie)),
                    HIMConfig.INSTANCE.textColor), new TextRenderGridCell("/", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(HorseOverlayRenderer.formatDouble(HorsieUtils.getMaxJumpStrength()),
                    HIMConfig.INSTANCE.textColor),
                new PercentBarRenderGridCell(HIMConfig.INSTANCE.percentColor1, HIMConfig.INSTANCE.percentColor2,
                    barWidth, barHeight, HorsieUtils.getJumpStrengthPercent(horsie))
            }, {
                new TextRenderGridCell("Health:", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(HorseOverlayRenderer.formatDouble(HorsieUtils.getHealth(horsie)),
                    HIMConfig.INSTANCE.textColor), new TextRenderGridCell("/", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(HorseOverlayRenderer.formatDouble(HorsieUtils.getMaxHealth()),
                    HIMConfig.INSTANCE.textColor),
                new PercentBarRenderGridCell(HIMConfig.INSTANCE.percentColor1, HIMConfig.INSTANCE.percentColor2,
                    barWidth, barHeight, HorsieUtils.getHealthPercent(horsie))
            }, {
                new TextRenderGridCell("Score:", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(HorseOverlayRenderer.formatDouble(HorsieUtils.getScore(horsie)),
                    HIMConfig.INSTANCE.textColor), new TextRenderGridCell("/", HIMConfig.INSTANCE.textColor),
                new TextRenderGridCell(HorseOverlayRenderer.formatDouble(HorsieUtils.getMaxScore()),
                    HIMConfig.INSTANCE.textColor),
                new PercentBarRenderGridCell(HIMConfig.INSTANCE.percentColor1, HIMConfig.INSTANCE.percentColor2,
                    barWidth, barHeight, HorsieUtils.getScore(horsie))
            },
            });
        RenderGrid visualTraits = RenderGridImpl.of(HIMConfig.INSTANCE.margin, new RenderGridCell[][]{
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
            { new HorsePreviewRenderGridCell(horsie, tickDelta, 35), visualTraits }
        });
        RenderGrid renderGridFull = RenderGridImpl.of(HIMConfig.INSTANCE.margin, new RenderGridCell[][]{
            { renderGridStats }, { renderGridPreview }
        });
        double width  = HIMConfig.INSTANCE.margin + renderGridFull.getWidth() + HIMConfig.INSTANCE.margin;
        double height = HIMConfig.INSTANCE.margin + renderGridFull.getHeight() + HIMConfig.INSTANCE.margin;
        matrixStack.push();
        matrixStack.translate(minecraftClient.getWindow().getScaledWidth() / 2.0,
            minecraftClient.getWindow().getScaledHeight() / 2.0, 0);
        matrixStack.scale(scale, scale, scale);
        matrixStack.translate(-width / 2, -height / 2, 0);
        RenderUtils.renderBox(matrixStack, HIMConfig.INSTANCE.overlayBackgroundColor, (float) width, (float) height);
        matrixStack.translate(HIMConfig.INSTANCE.margin, HIMConfig.INSTANCE.margin, 0);
        renderGridFull.render(matrixStack);
        matrixStack.pop();
    }

    private static
    String formatDouble(double number)
    {
        if (HIMConfig.INSTANCE.roundNumbers)
        {
            return new BigDecimal(number).round(new MathContext(HIMConfig.INSTANCE.roundingPrecision))
                .stripTrailingZeros().toPlainString();
        }
        return Double.toString(number);
    }

}
