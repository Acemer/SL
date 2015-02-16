package ru.nordwest.nord.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import ru.nordwest.nord.FoodRegister;
import ru.nordwest.nord.Nord;

import java.util.ArrayList;
import java.util.List;

public class ItemMetadataFood extends ItemBaseFood {
        private static final List<Integer> healAmount = new ArrayList<Integer>(80);
        private static final List<Float> saturationModifier = new ArrayList<Float>(
                80);
        private static IIcon[] textures;
        private static final List<String> names = new ArrayList<String>(80);
        private static final List<String> texture_name = new ArrayList<String>(80);

        public ItemMetadataFood() {
                super(0, 0, false);
                maxStackSize = 16;
                this.setAlwaysEdible();
        }

        @Override
        public String getUnlocalizedName(final ItemStack item) {
                int meta = item.getItemDamage();
                meta = healAmount.size() > meta ? meta : 0;
                return super.getUnlocalizedName() + "." + names.get(meta);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void getSubItems(final Item item, final CreativeTabs tab,
                                final List list) {
                for (int i = 0; i < names.size(); ++i) {
                        list.add(new ItemStack(item, 1, i));
                }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void registerIcons(final IIconRegister iconRegister) {
                textures = new IIcon[texture_name.size()];
                for (int i = 0; i < texture_name.size(); ++i) {
                        textures[i] = iconRegister.registerIcon(Nord.MODID + ":food/"
                                + (texture_name.get(i)));
                }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public IIcon getIconFromDamage(final int index) {
                int meta = index;
                meta = textures.length > meta ? meta : 0; // если метадата больше чем текстур, то берём 0 текстуру
                return textures[meta];
        }

        public int func_150905_g(ItemStack item) {
                int meta = item.getItemDamage();
                meta = healAmount.size() > meta ? meta : 0;
                return healAmount.get(meta);
        }

        public float func_150906_h(ItemStack item) {
                int meta = item.getItemDamage();
                meta = saturationModifier.size() > meta ? meta : 0;
                return saturationModifier.get(meta);
        }

        public static void addFood(final int heal, final float sturation,
                                   final String texture, final String name) {
                healAmount.add(heal);
                saturationModifier.add(sturation);
                texture_name.add(texture);
                names.add(name);

        }

        public static ItemStack getFood(final String name, int count) {
                if (ItemMetadataFood.names.contains(name)) {
                        return new ItemStack(FoodRegister.ifood, count,
                                ItemMetadataFood.names.indexOf(name));
                } else {
                        System.err.println("Unknow food: " + name);
                        System.err.println("Game has crashed:)");
                        return null;
                }
        }

        public static ItemStack getFood(String name) {
                return getFood(name, 1);
        }
}
