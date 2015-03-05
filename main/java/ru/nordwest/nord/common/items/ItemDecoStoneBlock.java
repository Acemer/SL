package ru.nordwest.nord.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemDecoStoneBlock extends ItemMetadataBlock {
        public ItemDecoStoneBlock(final Block block) {
                super(block);

        }

        @Override
        public String getUnlocalizedName(final ItemStack itemStack) {
                return super.getUnlocalizedName() + "." + itemStack.getItemDamage();
        }
}
