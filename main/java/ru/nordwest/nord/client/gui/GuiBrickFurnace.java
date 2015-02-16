package ru.nordwest.nord.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.container.ContainerBrickFurnace;
import ru.nordwest.nord.common.tileentity.TileEntityBrickFurnace;

public class GuiBrickFurnace extends GuiContainer {
        private static final ResourceLocation crusherGuiTextures = new ResourceLocation(
                Nord.MODID + ":textures/gui/container/brick_furnace.png");
        private final TileEntityBrickFurnace tileCrusher;
        private final EntityPlayer player;

        public GuiBrickFurnace(InventoryPlayer iPlayer, TileEntityBrickFurnace tileCrusher,
                               EntityPlayer player) {

                super(new ContainerBrickFurnace(iPlayer, tileCrusher));
                this.tileCrusher = tileCrusher;
                this.player = player;

        }

        protected void drawGuiContainerForegroundLayer(int par1, int par2) {
                String name = this.tileCrusher.hasCustomInventoryName()
                        ? this.tileCrusher.getInventoryName()
                        : I18n.format(this.tileCrusher.getInventoryName(),
                        new Object[0]);
                this.fontRendererObj.drawString(name, 16, 6, 4210752);
        }

        protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                                                       int par3) {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.mc.getTextureManager().bindTexture(crusherGuiTextures);
                int k = (this.width - this.xSize) / 2;
                int l = (this.height - this.ySize) / 2;
                this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

                if (this.tileCrusher.isActive()) {
                        int i1 = this.tileCrusher.getCrushTimeRemainingScaled(13);
                        this.drawTexturedModalRect(k + 46, l + 42 + 12 - i1, 176, 12 - i1,
                                14, i1 + 1);
                        i1 = this.tileCrusher.getWorkedProgressScaled(24);
                        this.drawTexturedModalRect(k + 80, l + 19, 176, 16, i1 + 1, 20);
                }
        }
}