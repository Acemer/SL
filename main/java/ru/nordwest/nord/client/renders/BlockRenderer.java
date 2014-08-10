package ru.nordwest.nord.client.renders;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;

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
	   
	   public void drawFaces(RenderBlocks renderblocks, Block block, int i, boolean st)
	   {
	     drawFaces(renderblocks, block, i, i, i, i, i, i, st);
	   }
	 
	   public void drawFaces(RenderBlocks renderblocks, Block block, int i1, int i2, int i3, int i4, int i5, int i6, boolean solidtop)
	   {
	     Tessellator tessellator = Tessellator.instance;
	     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	     tessellator.startDrawingQuads();
	     tessellator.setNormal(0.0F, -1.0F, 0.0F);
	     renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSide(block, i1));
	     tessellator.draw();
	     if (solidtop) GL11.glDisable(3008);
	     tessellator.startDrawingQuads();
	     tessellator.setNormal(0.0F, 1.0F, 0.0F);
	     renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSide(block, i2));
	     tessellator.draw();
	     if (solidtop) GL11.glEnable(3008);
	     tessellator.startDrawingQuads();
	     tessellator.setNormal(0.0F, 0.0F, 1.0F);
	     renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSide(block, i3));
	     tessellator.draw();
	     tessellator.startDrawingQuads();
	     tessellator.setNormal(0.0F, 0.0F, -1.0F);
	     renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSide(block, i4));
	     tessellator.draw();
	     tessellator.startDrawingQuads();
	     tessellator.setNormal(1.0F, 0.0F, 0.0F);
	     renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSide(block, i5));
	     tessellator.draw();
	     tessellator.startDrawingQuads();
	     tessellator.setNormal(-1.0F, 0.0F, 0.0F);
	     renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSide(block, i6));
	     tessellator.draw();
	     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	   }

	public boolean shouldRender3DInInventory() {
		// TODO Auto-generated method stub
		return false;
	}
}