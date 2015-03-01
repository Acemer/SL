package ru.nordwest.nord.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;
import ru.nordwest.nord.Nord;

public class ItemBaseFood extends ItemFood {

        public ItemBaseFood(final int par2, final float par3, final boolean par4) {
                super(par2, par3, par4);
                this.setAlwaysEdible();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void registerIcons(final IIconRegister iconRegister) {
                this.itemIcon = iconRegister.registerIcon(Nord.MODID + ":"
                        + (this.getUnlocalizedName().substring(5)));
        }

}
