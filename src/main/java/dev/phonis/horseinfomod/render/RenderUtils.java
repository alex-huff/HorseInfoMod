package dev.phonis.horseinfomod.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;

public
class RenderUtils
{

    public static
    void setupRender()
    {
        RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }

    public static
    void endRender()
    {
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }

    public static
    void renderBox(MatrixStack matrixStack, RGBAColor color, float width, float height)
    {
        Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
        RenderUtils.setupRender();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(positionMatrix, 0, 0, 0).color(color.r, color.g, color.b, color.a).next();
        bufferBuilder.vertex(positionMatrix, width, 0, 0).color(color.r, color.g, color.b, color.a).next();
        bufferBuilder.vertex(positionMatrix, width, height, 0).color(color.r, color.g, color.b, color.a).next();
        bufferBuilder.vertex(positionMatrix, 0, height, 0).color(color.r, color.g, color.b, color.a).next();
        Tessellator.getInstance().draw();
        RenderUtils.endRender();
    }

    public static
    void renderPercentBar(MatrixStack matrixStack, RGBAColor backgroundColor, RGBAColor foregroundColor,
                          double percent, float width, float height)
    {
        RenderUtils.renderBox(matrixStack, foregroundColor, (float) (percent * width), height);
        matrixStack.translate(percent * width, 0, 0);
        RenderUtils.renderBox(matrixStack, backgroundColor, (float) ((1 - percent) * width), height);
        matrixStack.translate(-percent * width, 0, 0);
    }

    public static
    void renderHorsePreview(MatrixStack matrixStack, AbstractHorseEntity horsie, float tickDelta, float horsePreviewYaw)
    {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        float oldPrevBodyYaw  = horsie.prevBodyYaw;
        float oldBodyYaw      = horsie.getBodyYaw();
        float oldPrevHeadYaw  = horsie.prevYaw;
        float oldHeadYaw      = horsie.getYaw();
        horsie.prevBodyYaw = horsePreviewYaw;
        horsie.setBodyYaw(horsePreviewYaw);
        horsie.prevHeadYaw = horsePreviewYaw;
        horsie.setHeadYaw(horsePreviewYaw);
        /*
        I should not have to do this. I should just be able to control whether the name
        tag is rendered from within the entity, but EntityRenderer seems to ignore
        the relevant setting even though the code appears to take it into account? I think
        there might be a conflict with another mod.
         */
        EntityRenderController.withNameTagsOff(() ->
        {
            minecraftClient.getEntityRenderDispatcher().getRenderer(horsie)
                .render(horsie, MathHelper.lerp(tickDelta, horsie.prevYaw, horsie.getYaw()), tickDelta, matrixStack,
                    minecraftClient.getBufferBuilders().getEntityVertexConsumers(),
                    minecraftClient.getEntityRenderDispatcher().getLight(horsie, tickDelta));
            minecraftClient.getBufferBuilders().getEntityVertexConsumers().draw();
        });
        horsie.prevBodyYaw = oldPrevBodyYaw;
        horsie.setBodyYaw(oldBodyYaw);
        horsie.prevHeadYaw = oldPrevHeadYaw;
        horsie.setHeadYaw(oldHeadYaw);
    }

}
