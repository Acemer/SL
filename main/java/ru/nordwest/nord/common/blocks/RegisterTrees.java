package ru.nordwest.nord.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import ru.nordwest.nord.Nord;

public class RegisterTrees {

        public static void init() {
                Nord.testTreeSapling = new BlockTestTreeSapling();
                GameRegistry.registerBlock(Nord.testTreeSapling, "testTreeSapling");

        }

}
