package ru.nordwest.nord.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import ru.nordwest.nord.common.blocks.BlockMetalOre;
import ru.nordwest.nord.common.blocks.RegisterMetal;

public class ItemMetalOre extends ItemMetadataBlock {
        private int shift = 0;

        public ItemMetalOre(final Block block) {
                super(block);
                this.shift = ((BlockMetalOre) block).getShift();

        }

        @Override
        public String getUnlocalizedName(final ItemStack itemStack) {
                return super.getUnlocalizedName()
                        + "."
                        + RegisterMetal.ore_list[this.shift
                        + itemStack.getItemDamage()];
        }
}
