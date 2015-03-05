package ru.nordwest.nord.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.client.lib.tabs.TabsNord;

import java.util.ArrayList;
import java.util.List;

public class ItemFood extends net.minecraft.item.ItemFood {
        private static final List<Integer> healAmount = new ArrayList<Integer>(80);
        private static final List<Float> saturationModifier = new ArrayList<Float>(80);
        private static final List<String> names = new ArrayList<String>(80);
        private static final List<String> textureNames = new ArrayList<String>(80);

        private static IIcon[] textures;

        public ItemFood() {
                super(0, 0, false);
                this.setMaxStackSize(16);
                this.setUnlocalizedName("food");
                this.setCreativeTab(TabsNord.tabFood);
        }

        @Override
        public String getUnlocalizedName(final ItemStack item) {
                int index = item.getItemDamage();
                index = healAmount.size() > index ? index : 0;
                return super.getUnlocalizedName() + "." + names.get(index);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void getSubItems(final Item item, final CreativeTabs tab, final List list) {
                for (int i = 0; i < names.size(); ++i) {
                        list.add(new ItemStack(item, 1, i));
                }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void registerIcons(final IIconRegister iconRegister) {
                textures = new IIcon[textureNames.size()];
                for (int i = 0; i < textureNames.size(); ++i) {
                        textures[i] = iconRegister.registerIcon(Nord.MODID + ":food/"
                                + (textureNames.get(i)));
                }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public IIcon getIconFromDamage(final int metadata) {
                int index = textures.length > metadata ? metadata : 0;
                return textures[index];
        }

        public int func_150905_g(ItemStack item) {
                int index = item.getItemDamage();
                index = healAmount.size() > index ? index : 0;
                return healAmount.get(index);
        }

        public float func_150906_h(ItemStack item) {
                int index = item.getItemDamage();
                index = saturationModifier.size() > index ? index : 0;
                return saturationModifier.get(index);
        }

        public static void addFood(final int heal, final float saturation,
                                   final String name) {
                addFood(heal, saturation, name, name);
        }

        public static void addFood(final int heal, final float saturation,
                                   final String texture, final String name) {
                healAmount.add(heal);
                saturationModifier.add(saturation);
                textureNames.add(texture);
                names.add(name);
        }

        public static ItemStack getFood(String name) {
                return getFood(name, 1);
        }

        public static ItemStack getFood(final String name, int count) {
                if (ItemFood.names.contains(name)) {
                        return new ItemStack(RegisterFood.food, count,
                                ItemFood.names.indexOf(name));
                } else {
                        System.err.println("Unknow food: " + name);
                        System.err.println("Game has crashed:)");
                        return null;
                }
        }
}
