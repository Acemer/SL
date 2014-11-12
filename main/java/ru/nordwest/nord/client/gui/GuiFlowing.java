package ru.nordwest.nord.client.gui;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
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
	
	public GuiFlowing(InventoryPlayer iPlayer, TileEntityFlowing tileFlowing, EntityPlayer player) {
		super(new ContainerFlowing(iPlayer, tileFlowing));
		this.tileEntity = tileFlowing;
		this.player = player;
	}

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    	int xAxis = (mouseX - (width - xSize) / 2);
    	int yAxis = (mouseY - (height - ySize) / 2);
    	//this.fontRendererObj.drawString(String.valueOf(xAxis)+":"+String.valueOf(yAxis), xAxis, yAxis, 4210752);
    	String name = this.tileEntity.hasCustomInventoryName()
				? this.tileEntity.getInventoryName()
				: I18n.format(this.tileEntity.getInventoryName());
				
		int energy = this.tileEntity.energy / 16;
		int maxEnergy = this.tileEntity.maxEnergy / 16;
		this.fontRendererObj.drawString(name, 16, 6, 4210752);
		if (xAxis > 9 && xAxis < 13 && yAxis > 20 && yAxis < 74 ){
			this.drawCreativeTabHoveringText(String.valueOf(energy) + "/" + String.valueOf(maxEnergy) + " share" + (energy > 1 ? "s" : ""), xAxis, yAxis);
		}
    }
    
    @Override
    protected void drawCreativeTabHoveringText(String text, int x, int y)
    {
    	func_146283_a(Arrays.asList(new String[] {text}), x, y);
    }
    @Override
    protected void func_146283_a(List list, int x, int y)
    {
    	GL11.glPushAttrib(GL11.GL_ENABLE_BIT + GL11.GL_LIGHTING_BIT);
    	super.func_146283_a(list, x, y);
    	GL11.glPopAttrib();
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
    	this.drawTexturedModalRect(k + 11, l + 22, 183, 37, 3, 52); // Отрисовать полную текстуру огня
    	this.drawTexturedModalRect(k + 11, l + 22, 11, 22, 3, 52 - progress); // А поверх нее рисовать обычную текстуру (без огня)

    	progress = this.tileEntity.getBurnTimeRemainingScaled(14);
    	this.drawTexturedModalRect(k + 19, l + 41, 176, 2, 14, 14);
    	this.drawTexturedModalRect(k + 19, l + 41, 19, 41, 14, 14 - progress);
    }

}
