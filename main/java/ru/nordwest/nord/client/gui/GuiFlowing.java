package ru.nordwest.nord.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.container.ContainerFlowing;
import ru.nordwest.nord.common.tileentity.TileEntityBrickFurnace;
import ru.nordwest.nord.common.tileentity.TileEntityFlowing;

public class GuiFlowing extends GuiContainer {
	private TileEntityFlowing tileEntity;
	private EntityPlayer player;
	private int fireCounter = 0;
	
	public GuiFlowing(InventoryPlayer iPlayer, TileEntityFlowing tileFlowing, EntityPlayer player) {
		super(new ContainerFlowing(iPlayer, tileFlowing));
		this.tileEntity = tileFlowing;
		this.player = player;
	}

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
    	// TODO add text
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	this.mc.renderEngine.bindTexture(new ResourceLocation(Nord.MODID + ":textures/gui/container/flowing.png"));
    	int x = (width - xSize) / 2;
    	int y = (height - ySize) / 2;
    	int k = (this.width - this.xSize) / 2;
    	int l = (this.height - this.ySize) / 2;
    	this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
            
    	int progress = this.tileEntity.getFlowProgressScaled(24);
    	this.drawTexturedModalRect(k + 78, l + 35, 180, 16, progress, 21);
                
    	progress = this.tileEntity.getEnergyProgressScaled(52);
    	this.drawTexturedModalRect(k + 11, l + 22, 183, 37, 3, 52); // h = 52
    	this.drawTexturedModalRect(k + 11, l + 22, 11, 22, 3, 52 - progress);
    	
    	if (this.tileEntity.isBurning())
    	{
    		++fireCounter;
    		if (fireCounter > 14 * 5)
    		{
    			fireCounter = 0;
    		}
    	
    		this.drawTexturedModalRect(k + 19, l + 41, 176, 2, 14, 14);
    		this.drawTexturedModalRect(k + 19, l + 41, 19, 41, 14, 14 - (fireCounter / 5));
    	}
    	else
    	{
    		fireCounter = 0;
    	}
    }

}
