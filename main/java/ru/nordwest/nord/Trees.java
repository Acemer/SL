package ru.nordwest.nord;

import cpw.mods.fml.common.registry.GameRegistry;
import ru.nordwest.nord.block.TestTreeSapling;

public class Trees {

        public static void init() {
                Nord.testTreeSapling = new TestTreeSapling();
                GameRegistry.registerBlock(Nord.testTreeSapling, "testTreeSapling");

        }

}
