package ru.nordwest.nord.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabNord extends CreativeTabs {
        ItemStack item;

        public CreativeTabNord(String lable) {
                super(lable);
        }

        public void setItem(ItemStack item) {
                this.item = item;
        }

        @Override
        public Item getTabIconItem() {
                return item.getItem();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
                return item;
        }
}
