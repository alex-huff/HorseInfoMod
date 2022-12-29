package dev.phonis.horseinfomod.render.grid;

import net.minecraft.client.util.math.MatrixStack;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;

public
class RenderGridImpl implements RenderGrid
{

    public static
    RenderGridImpl of(float margin, RenderGridCell[][] grid)
    {
        return new RenderGridImpl(margin, grid);
    }

    private       double             margin;
    private final RenderGridCell[][] grid;
    private       double             width;
    private       double             height;
    private final int                rows;
    private final int                columns;
    private       double[]           rowHeights;
    private       double[]           columnWidths;

    private
    RenderGridImpl(double margin, RenderGridCell[][] grid)
    {
        this.margin = margin;
        this.grid   = grid;
        this.rows   = grid.length;
        if (this.rows == 0)
        {
            throw new IllegalArgumentException("0 width RenderGrid");
        }
        this.columns = grid[0].length;
        if (this.columns == 0)
        {
            throw new IllegalArgumentException("0 height RenderGrid");
        }
        if (!Arrays.stream(this.grid).mapToInt(Array::getLength).allMatch(c -> c == this.columns))
        {
            throw new IllegalArgumentException("Non-rectangular array");
        }
        this.calculateGridAndCellDimensions();
    }

    public
    void calculateGridAndCellDimensions()
    {
        this.calculateCellDimensions();
        this.calculateGridDimensions();
    }

    private
    void calculateGridDimensions()
    {
        this.width  = Arrays.stream(this.columnWidths).sum() + (this.columns - 1) * this.margin;
        this.height = Arrays.stream(this.rowHeights).sum() + (this.rows - 1) * this.margin;
    }

    private
    void calculateCellDimensions()
    {
        this.rowHeights   = IntStream.range(0, this.rows)
            .mapToDouble(r -> Arrays.stream(this.grid[r]).mapToDouble(RenderGridCell::getHeight).max().getAsDouble())
            .toArray();
        this.columnWidths = IntStream.range(0, this.columns).mapToDouble(
                c -> IntStream.range(0, this.rows).mapToDouble(r -> this.grid[r][c].getWidth()).max().getAsDouble())
            .toArray();
    }

    private
    double extraWidth(int r, int c)
    {
        return this.columnWidths[c] - this.grid[r][c].getWidth();
    }

    private
    double extraHeight(int r, int c)
    {
        return this.rowHeights[r] - this.grid[r][c].getHeight();
    }

    @Override
    public
    void render(MatrixStack matrixStack)
    {
        matrixStack.push();
        for (int r = 0; r < this.grid.length; r++)
        {
            matrixStack.push();
            for (int c = 0; c < this.grid[r].length; c++)
            {
                matrixStack.push();
                switch (this.grid[r][c].getAnchor())
                {
                    case NORTH:
                        matrixStack.translate(this.extraWidth(r, c) / 2, 0, 0);
                        break;
                    case SOUTH:
                        matrixStack.translate(this.extraWidth(r, c) / 2, this.extraHeight(r, c), 0);
                        break;
                    case EAST:
                        matrixStack.translate(this.extraWidth(r, c), this.extraHeight(r, c) / 2, 0);
                        break;
                    case WEST:
                        matrixStack.translate(0, this.extraHeight(r, c) / 2, 0);
                        break;
                    case NORTHEAST:
                        matrixStack.translate(this.extraWidth(r, c), 0, 0);
                        break;
                    case NORTHWEST:
                        break;
                    case SOUTHEAST:
                        matrixStack.translate(this.extraWidth(r, c), this.extraHeight(r, c), 0);
                        break;
                    case SOUTHWEST:
                        matrixStack.translate(0, this.extraHeight(r, c), 0);
                        break;
                    case MIDDLE:
                        matrixStack.translate(this.extraWidth(r, c) / 2, this.extraHeight(r, c) / 2, 0);
                        break;
                }
                this.grid[r][c].render(matrixStack);
                matrixStack.pop();
                if (c < this.grid[r].length - 1)
                {
                    matrixStack.translate(this.columnWidths[c] + margin, 0, 0);
                }
            }
            matrixStack.pop();
            if (r < this.grid.length - 1)
            {
                matrixStack.translate(0, this.rowHeights[r] + margin, 0);
            }
        }
        matrixStack.pop();
    }

    @Override
    public
    double getWidth()
    {
        return this.width;
    }

    @Override
    public
    double getHeight()
    {
        return this.height;
    }

    @Override
    public
    RenderGrid setInsideMargins(double margin)
    {
        this.margin = margin;
        this.calculateGridDimensions();
        return this;
    }

    @Override
    public
    double getInsideMargins()
    {
        return this.margin;
    }

}
