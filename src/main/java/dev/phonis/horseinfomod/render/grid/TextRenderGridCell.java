package dev.phonis.horseinfomod.render.grid;

import dev.phonis.horseinfomod.render.RGBAColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public
class TextRenderGridCell implements RenderGridCell
{

    private final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
    private final String       text;
    private final RGBAColor    color;

    public
    TextRenderGridCell(String text, RGBAColor color)
    {
        this.text  = text;
        this.color = color;
    }

    @Override
    public
    void render(DrawContext drawContext)
    {
        drawContext.drawTextWithShadow(this.textRenderer, this.text, 0, 0, this.color.toInt());
    }

    @Override
    public
    double getWidth()
    {
        return this.textRenderer.getWidth(this.text);
    }

    @Override
    public
    double getHeight()
    {
        return this.textRenderer.fontHeight;
    }

    @Override
    public RenderGridCell.Anchor getAnchor()
    {
        return Anchor.WEST;
    }

}
