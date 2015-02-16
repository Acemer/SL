package ru.nordwest.nord.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import ru.nordwest.nord.Nord;

import java.util.List;

public class ItemMetalIngot extends ItemBase {
        private final String[] subname = new String[]{"dirty", "poor", "normal",
                "clear", "powder"};
        private IIcon[] texture;

        public ItemMetalIngot() {
                this.setHasSubtypes(true);
                this.setMaxDamage(0);
                this.setCreativeTab(Nord.metalsTab);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public IIcon getIconFromDamage(final int index) {
                final int j = MathHelper.clamp_int(index, 0, 5);
                return this.texture[j];
        }

        @Override
        public String getUnlocalizedName(final ItemStack item) {
                final int i = MathHelper.clamp_int(item.getItemDamage(), 0, 5);
                return super.getUnlocalizedName() + "." + this.subname[i];
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void getSubItems(final Item item, final CreativeTabs tab,
                                final List list) {
                for (int i = 0; i < 5; ++i) {
                        list.add(new ItemStack(item, 1, i));
                }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void registerIcons(final IIconRegister list) {
                this.texture = new IIcon[this.subname.length];

                for (int i = 0; i < this.texture.length; ++i) {
                        this.texture[i] = list.registerIcon(Nord.MODID + ":"
                                + this.iconString + "/" + this.subname[i] + "_"
                                + this.iconString);
                }
        }
}
