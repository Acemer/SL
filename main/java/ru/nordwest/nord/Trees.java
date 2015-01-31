package ru.nordwest.nord;

import ru.nordwest.nord.block.TestTreeSapling;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Trees {

	public static void init() {
		Nord.testTreeSapling = new TestTreeSapling();
        GameRegistry.registerBlock(Nord.testTreeSapling, "testTreeSapling");
		
	}

}
