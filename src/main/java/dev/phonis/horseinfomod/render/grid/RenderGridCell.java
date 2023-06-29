package dev.phonis.horseinfomod.render.grid;

import net.minecraft.client.gui.DrawContext;

public
interface RenderGridCell
{

    enum Anchor
    {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        NORTHEAST,
        NORTHWEST,
        SOUTHEAST,
        SOUTHWEST,
        MIDDLE
    }

    void render(DrawContext drawContext);

    double getWidth();

    double getHeight();

    default RenderGridCell.Anchor getAnchor()
    {
        return RenderGridCell.Anchor.MIDDLE;
    }

}
