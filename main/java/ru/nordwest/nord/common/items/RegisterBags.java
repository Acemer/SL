package ru.nordwest.nord.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nordwest.nord.common.lib.helpers.RegisterHelper;

public class RegisterBags {
        public static Item bag = new ItemBag();
        public static final String[] TIER = {"common", "uncommon", "rare", "unique"};
        public static final String[] TIERCOLORS = {"§f", "§a", "§9", "§6"};
        public static final String[] SIZES = {"small", "medium", "large", "veryLarge"};

        public static ItemStack getBag(int quality, int size) {
                ItemStack itemStack = new ItemStack(RegisterBags.bag, 1, quality * 10 + size);
                ItemBag.setTagCompound(itemStack);
                return itemStack;
        }

        public static ItemStack getBag(int quality, int size, int r, int g, int b) {
                ItemStack itemStack = new ItemStack(RegisterBags.bag, 1, quality * 10 + size);
                ItemBag.setTagCompound(itemStack, r, g, b);
                return itemStack;
        }

        public static void init() {
                RegisterHelper.registerItem(bag);
        }
}
