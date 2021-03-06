package ru.nordwest.nord.client.gui.abstracts;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.nordwest.nord.common.tiles.abstracts.TileAbstractEnergyMachina;

import java.util.Arrays;
import java.util.List;

public abstract class GuiMachine extends GuiContainer {

        protected GuiMachine(Container p_i1072_1_) {
                super(p_i1072_1_);
        }

        protected EntityPlayer player;
        protected TileAbstractEnergyMachina tileEntity;

    @Override
        protected void drawCreativeTabHoveringText(String text, int x, int y) {
                func_146283_a(Arrays.asList(new String[]{text}), x, y);
        }

        @Override
        protected void func_146283_a(List list, int x, int y) {
                GL11.glPushAttrib(GL11.GL_ENABLE_BIT + GL11.GL_LIGHTING_BIT);
                super.func_146283_a(list, x, y);
                GL11.glPopAttrib();
        }

        protected void drawOverText(int x, int y, int dx, int dy, int posX,
                                    int posY, String text) {
                if (posX > x && posX < x + dx && posY > y && posY < y + dy) {
                        this.drawCreativeTabHoveringText(text, posX, posY);
                }
        }


}
