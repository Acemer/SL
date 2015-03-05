package ru.nordwest.nord.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nordwest.nord.common.lib.helpers.RegisterHelper;

public class RegisterBags {
        private static Item bag = new ItemBag();
        private static Item bagSpecial = new ItemBagSpecial();

        public static final String[] TIER = {"common", "uncommon", "rare", "unique"};
        public static final String[] TIERCOLORS = {"§f", "§a", "§9", "§6"};
        public static final String[] SIZES = {"small", "medium", "large", "veryLarge"};
        public static final String[] NAMES = {"bomber"};

        public static ItemStack getSpecialBag(int quality) {
                ItemStack itemStack = new ItemStack(RegisterBags.bagSpecial, 1, quality);
                ItemBagBase.setTagCompound(itemStack, 196, 74, 85);
                return itemStack;
        }

        public static ItemStack getBag(int quality, int size) {
                ItemStack itemStack = new ItemStack(RegisterBags.bag, 1, quality * 10 + size);
                ItemBagBase.setTagCompound(itemStack);
                return itemStack;
        }

        public static ItemStack getBag(int quality, int size, int r, int g, int b) {
                ItemStack itemStack = new ItemStack(RegisterBags.bag, 1, quality * 10 + size);
                ItemBagBase.setTagCompound(itemStack, r, g, b);
                return itemStack;
        }

        public static void init() {
                RegisterHelper.registerItem(bag);
                RegisterHelper.registerItem(bagSpecial);
        }
}
