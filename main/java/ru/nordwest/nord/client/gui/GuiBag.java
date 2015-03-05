package ru.nordwest.nord.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.container.ContainerBag;
import ru.nordwest.nord.common.container.InventoryBag;

public class GuiBag extends GuiContainer {
        private static final ResourceLocation textureLocation =
                new ResourceLocation(Nord.MODID + ":textures/gui/container/guiBag.png");

        private final InventoryBag inventory;

        public GuiBag(ContainerBag container) {
                super(container);
                this.inventory = container.inventory;
        }

        protected void drawGuiContainerForegroundLayer(int par1, int par2) {
                String name = inventory.getInvItem().getDisplayName();
                name = name.contains("Bag") ? name + " Inventory" : name;
                this.fontRendererObj.drawString(name, 8, 6, 4210752);
        }

        protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.mc.getTextureManager().bindTexture(textureLocation);
                int k = (this.width - this.xSize) / 2;
                int l = (this.height - this.ySize) / 2;
                this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 137);
                if (inventory.getInvItem().stackTagCompound != null) {
                        GL11.glColor4f(
                                (((inventory.getInvItem().getTagCompound().getInteger("color") & 0xFF0000) >> 16) / 255.0F),
                                (((inventory.getInvItem().getTagCompound().getInteger("color") & 0xFF00) >> 8) / 255.0F),
                                ((inventory.getInvItem().getTagCompound().getInteger("color") & 0xFF) / 255.0F),
                                1.0F);
                        this.drawTexturedModalRect(k + 5, l + 18, 0, 137, 166, 33);
                }
        }
}
