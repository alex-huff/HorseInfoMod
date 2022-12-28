package dev.phonis.horseinfomod.render;

import dev.phonis.horseinfomod.config.HIMConfig;
import dev.phonis.horseinfomod.util.HorsieUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.AbstractHorseEntity;

import java.util.stream.Stream;

public
class HorseOverlayRenderer
{

    public static
    void renderHorseOverlay(MatrixStack matrixStack, float tickDelta, AbstractHorseEntity horsie)
    {
        float           scale             = HIMConfig.INSTANCE.renderScale / 100F;
        float           margin            = 10F;
        float           barWidth          = 60F;
        RGBAColor       backgroundColor   = new RGBAColor(20, 20, 20, 200);
        RGBAColor       percentForeground = new RGBAColor(60, 200, 50, 255);
        RGBAColor       percentBackground = new RGBAColor(0, 0, 0, 255);
        MinecraftClient minecraftClient   = MinecraftClient.getInstance();
        TextRenderer    textRenderer      = minecraftClient.textRenderer;
        String speedLabel = "Speed:     " + HorsieUtils.getMovementSpeed(horsie) + " / " +
                            HorsieUtils.getMaxMovementSpeed();
        String jumpLabel = "Jump:      " + HorsieUtils.getJumpStrength(horsie) + " / " +
                           HorsieUtils.getMaxJumpStrength();
        String healthLabel = "Health:    " + HorsieUtils.getHealth(horsie) + " / " + HorsieUtils.getMaxHealth();
        String cockSizeLabel = "Cock Size: " + HorsieUtils.getCockSize(horsie) + " / " + HorsieUtils.getMaxCockSize() +
                               " inches";
        float maxLabelWidth = Stream.of(speedLabel, jumpLabel, healthLabel, cockSizeLabel)
            .mapToInt(textRenderer::getWidth).max().getAsInt();
        float width = margin + maxLabelWidth + margin + barWidth + margin;
        float height = margin + textRenderer.fontHeight + margin + textRenderer.fontHeight + margin +
                       textRenderer.fontHeight + margin + textRenderer.fontHeight + margin;
        matrixStack.push();
        matrixStack.translate(minecraftClient.getWindow().getScaledWidth() / 2.0,
            minecraftClient.getWindow().getScaledHeight() / 2.0, 0);
        matrixStack.scale(scale, scale, 0);
        matrixStack.translate(-width / 2, -height / 2, 0);
        RenderUtils.renderBox(matrixStack, backgroundColor, width, height);
        matrixStack.translate(margin, margin, 0);
        textRenderer.drawWithShadow(matrixStack, speedLabel, 0, 0, 0xFFFFFFFF);
        matrixStack.translate(maxLabelWidth + margin, 0, 0);
        RenderUtils.renderPercentBar(matrixStack, percentBackground, percentForeground, HorsieUtils.getMovementSpeedPercent(horsie),
            barWidth,
            textRenderer.fontHeight);
        matrixStack.translate(-(maxLabelWidth + margin), textRenderer.fontHeight + margin, 0);
        textRenderer.drawWithShadow(matrixStack, jumpLabel, 0, 0, 0xFFFFFFFF);
        matrixStack.translate(maxLabelWidth + margin, 0, 0);
        RenderUtils.renderPercentBar(matrixStack, percentBackground, percentForeground, HorsieUtils.getJumpStrengthPercent(horsie),
            barWidth,
            textRenderer.fontHeight);
        matrixStack.translate(-(maxLabelWidth + margin), textRenderer.fontHeight + margin, 0);
        textRenderer.drawWithShadow(matrixStack, healthLabel, 0, 0, 0xFFFFFFFF);
        matrixStack.translate(maxLabelWidth + margin, 0, 0);
        RenderUtils.renderPercentBar(matrixStack, percentBackground, percentForeground, HorsieUtils.getHealthPercent(horsie),
            barWidth,
            textRenderer.fontHeight);
        matrixStack.translate(-(maxLabelWidth + margin), textRenderer.fontHeight + margin, 0);
        textRenderer.drawWithShadow(matrixStack, cockSizeLabel, 0, 0, 0xFFFFFFFF);
        matrixStack.translate(maxLabelWidth + margin, 0, 0);
        RenderUtils.renderPercentBar(matrixStack, percentBackground, percentForeground, HorsieUtils.getCockSizePercent(horsie),
            barWidth,
            textRenderer.fontHeight);
        matrixStack.pop();
    }

}
