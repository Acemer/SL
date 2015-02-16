package ru.nordwest.nord.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import ru.nordwest.nord.MetalRegister;
import ru.nordwest.nord.Nord;

import java.util.List;

public class ItemMetalDrop extends ItemBase {
        private IIcon[] texture;
        private final String suffix;

        public ItemMetalDrop(final String suffix) {
                this.setHasSubtypes(true);
                this.setMaxDamage(0);
                this.setCreativeTab(Nord.metalsTab);
                this.suffix = suffix;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public IIcon getIconFromDamage(final int index) {
                final int j = MathHelper.clamp_int(index, 0,
                        MetalRegister.ore_list.length);
                return this.texture[j];
        }

        @Override
        public String getUnlocalizedName(final ItemStack item) {
                final int i = MathHelper.clamp_int(item.getItemDamage(), 0,
                        MetalRegister.ore_list.length);
                return super.getUnlocalizedName() + "." + MetalRegister.ore_list[i]
                        + "_" + this.suffix;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void getSubItems(final Item item, final CreativeTabs tab,
                                final List list) {
                for (int i = 0; i < MetalRegister.ore_list.length; ++i) {
                        list.add(new ItemStack(item, 1, i));
                }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void registerIcons(final IIconRegister list) {
                this.texture = new IIcon[MetalRegister.ore_list.length];

                for (int i = 0; i < this.texture.length; ++i) {
                        this.texture[i] = list.registerIcon(Nord.MODID + ":ore/"
                                + MetalRegister.ore_list[i] + "/"
                                + MetalRegister.ore_list[i] + "_" + this.suffix);
                }
        }
}
