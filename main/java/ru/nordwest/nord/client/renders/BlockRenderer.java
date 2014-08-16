package ru.nordwest.nord.client.renders;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

public class BlockRenderer {
	protected static float W1 = 0.0625F;
	protected static float W2 = 0.125F;
	protected static float W3 = 0.1875F;
	protected static float W4 = 0.25F;
	protected static float W5 = 0.3125F;
	protected static float W6 = 0.375F;
	protected static float W7 = 0.4375F;
	protected static float W8 = 0.5F;
	protected static float W9 = 0.5625F;
	protected static float W10 = 0.625F;
	protected static float W11 = 0.6875F;
	protected static float W12 = 0.75F;
	protected static float W13 = 0.8125F;
	protected static float W15 = 0.9375F;

	public void drawFaces(final RenderBlocks renderblocks, final Block block,
			final int i, final boolean st) {
		this.drawFaces(renderblocks, block, i, i, i, i, i, i, st);
	}

	public void drawFaces(final RenderBlocks renderblocks, final Block block,
			final int i1, final int i2, final int i3, final int i4,
			final int i5, final int i6, final boolean solidtop) {
		final Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D,
				renderblocks.getBlockIconFromSide(block, i1));
		tessellator.draw();
		if (solidtop) {
			GL11.glDisable(3008);
		}
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D,
				renderblocks.getBlockIconFromSide(block, i2));
		tessellator.draw();
		if (solidtop) {
			GL11.glEnable(3008);
		}
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D,
				renderblocks.getBlockIconFromSide(block, i3));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D,
				renderblocks.getBlockIconFromSide(block, i4));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D,
				renderblocks.getBlockIconFromSide(block, i5));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D,
				renderblocks.getBlockIconFromSide(block, i6));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	public boolean shouldRender3DInInventory() {
		// TODO Auto-generated method stub
		return false;
	}
}