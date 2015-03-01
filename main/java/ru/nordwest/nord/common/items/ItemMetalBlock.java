package ru.nordwest.nord.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemMetalBlock extends ItemBlockMetadata {
        private final String[] subname = new String[]{"dirty", "poor", "normal",
                "clear"};

        public ItemMetalBlock(final Block p_i45328_1_) {
                super(p_i45328_1_);

        }

        @Override
        public String getUnlocalizedName(final ItemStack itemStack) {
                return super.getUnlocalizedName() + "."
                        + this.subname[itemStack.getItemDamage()];
        }
}
