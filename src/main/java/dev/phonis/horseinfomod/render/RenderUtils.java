package dev.phonis.horseinfomod.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
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

}
