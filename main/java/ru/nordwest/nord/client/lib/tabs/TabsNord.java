package ru.nordwest.nord.client.lib.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.blocks.RegisterMetal;
import ru.nordwest.nord.common.items.ItemFood;
import ru.nordwest.nord.common.items.RegisterBags;

public class TabsNord {
        public static final CreativeTabs tabGeneral = new TabNord("tabGeneral");
        public static final CreativeTabs tabMetals = new TabNord("tabMetals");
        public static final CreativeTabs tabBlocks = new TabNord("tabBlocks");
        public static final CreativeTabs tabFood = new TabNord("tabFood");
        public static final CreativeTabs tabLighting = new TabNord("tabLighting");
        public static final CreativeTabs tabBagsAndGifts = new TabNord("tabBagsAndGifts");

        public static void postInit() {
                ((TabNord) tabGeneral).setTabIconItem(new ItemStack(Items.bed));
                ((TabNord) tabBlocks).setTabIconItem(new ItemStack(Nord.buildingBlocks[6], 1, 1));
                ((TabNord) tabLighting).setTabIconItem(new ItemStack(Nord.lamps[2], 1, 0));
                ((TabNord) tabMetals).setTabIconItem(RegisterMetal.getMetallIngot("zing", 3, 1));
                ((TabNord) tabFood).setTabIconItem(ItemFood.getFood("potatoes_pie"));
                ((TabNord) tabBagsAndGifts).setTabIconItem(RegisterBags.getBag(3, 2, 196, 74, 85));
        }
}