package ru.nordwest.nord.common.lib.helpers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import ru.nordwest.nord.Nord;

public class RegisterHelper {
        public static void registerBlock(Block block) {
                GameRegistry.registerBlock(block, Nord.MODID + block.getUnlocalizedName().substring(5));
        }

        public static void registerItem(Item item) {
                GameRegistry.registerItem(item, Nord.MODID + item.getUnlocalizedName().substring(5));
        }
}