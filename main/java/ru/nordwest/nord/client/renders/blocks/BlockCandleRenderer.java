package ru.nordwest.nord.client.renders.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import ru.nordwest.nord.Nord;

import java.awt.*;
import java.util.Random;

public class BlockCandleRenderer extends BlockRenderer
        implements
        ISimpleBlockRenderingHandler {

        @Override
        public void renderInventoryBlock(final Block block, final int metadata,
                                         final int modelID, final RenderBlocks renderer) {
                final Color color = new Color(Nord.colors[15 - metadata]);
                final float r = color.getRed() / 255.0F;
                final float g = color.getGreen() / 255.0F;
                final float b = color.getBlue() / 255.0F;
                GL11.glColor3f(r, g, b);
                block.setBlockBounds(BlockRenderer.W6, 0.0F, BlockRenderer.W6,
                        BlockRenderer.W10, 0.5F, BlockRenderer.W10);
                renderer.setRenderBoundsFromBlock(block);
                this.drawFaces(renderer, block, 67, true);
                GL11.glColor3f(1.0F, 1.0F, 1.0F);

                block.setBlockBounds(0.475F, 0.5F, 0.475F, 0.525F, BlockRenderer.W10,
                        0.525F);
                renderer.setRenderBoundsFromBlock(block);
                this.drawFaces(renderer, block, 66, true);
        }

        @Override
        public boolean renderWorldBlock(final IBlockAccess world, final int x,
                                        final int y, final int z, final Block block, final int modelId,
                                        final RenderBlocks renderer) {
                final int type = 0;

                block.setBlockBounds(BlockRenderer.W6, 0.0F, BlockRenderer.W6,
                        BlockRenderer.W10, 0.5F, BlockRenderer.W10);
                renderer.setRenderBoundsFromBlock(block);
                renderer.renderStandardBlock(block, x, y, z);

                final Random rr = new Random(x + (y * z));
                final int q = 1 + rr.nextInt(5);
                for (int a = 0; a < q; a++) {
                        final boolean side = rr.nextBoolean();
                        final int loc = 2 + rr.nextInt(2);
                        if ((a % 2) == 0) {
                                block.setBlockBounds(BlockRenderer.W5
                                                + (BlockRenderer.W1 * loc),
                                        0.0F - (BlockRenderer.W1 * rr.nextInt(2)), side
                                                ? BlockRenderer.W5
                                                : BlockRenderer.W10, BlockRenderer.W6
                                                + (BlockRenderer.W1 * loc), BlockRenderer.W1
                                                * (1 + rr.nextInt(3)), side
                                                ? BlockRenderer.W6
                                                : BlockRenderer.W11);
                                renderer.setRenderBoundsFromBlock(block);
                                renderer.renderStandardBlock(block, x, y, z);
                        } else {
                                block.setBlockBounds(side
                                                ? BlockRenderer.W5
                                                : BlockRenderer.W10, 0.0F - (BlockRenderer.W1 * rr
                                                .nextInt(2)), BlockRenderer.W5
                                                + (BlockRenderer.W1 * loc), side
                                                ? BlockRenderer.W6
                                                : BlockRenderer.W11,
                                        BlockRenderer.W1 * (1 + rr.nextInt(3)),
                                        BlockRenderer.W6 + (BlockRenderer.W1 * loc));
                                renderer.setRenderBoundsFromBlock(block);
                                renderer.renderStandardBlock(block, x, y, z);
                        }
                }

                block.setBlockBounds(0.475F, 0.5F, 0.475F, 0.525F, BlockRenderer.W10,
                        0.525F);
                renderer.setRenderBoundsFromBlock(block);
                renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, 0.8F,
                        0.8F, 0.8F);
                block.setBlockBounds(BlockRenderer.W6, 0.0F, BlockRenderer.W6,
                        BlockRenderer.W10, 0.5F, BlockRenderer.W10);
                renderer.setRenderBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.5F, 0.625F);
                return true;
        }

        @Override
        public int getRenderId() {
                return Nord.CandleRendererID;
        }

        @Override
        public boolean shouldRender3DInInventory(final int modelId) {
                return modelId == Nord.CandleRendererID;
        }
}