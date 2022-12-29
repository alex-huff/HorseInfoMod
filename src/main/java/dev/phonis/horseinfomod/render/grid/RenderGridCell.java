package dev.phonis.horseinfomod.render.grid;

import net.minecraft.client.util.math.MatrixStack;

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

    void render(MatrixStack matrixStack);

    double getWidth();

    double getHeight();

    default RenderGridCell.Anchor getAnchor()
    {
        return RenderGridCell.Anchor.MIDDLE;
    }

}
