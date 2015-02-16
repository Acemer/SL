package ru.nordwest.nord.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import ru.nordwest.nord.MetalRegister;
import ru.nordwest.nord.block.BaseMetalOre;

public class ItemMetalOre extends ItemBlockMetadata {
        private int shift = 0;

        public ItemMetalOre(final Block block) {
                super(block);
                this.shift = ((BaseMetalOre) block).getShift();

        }

        @Override
        public String getUnlocalizedName(final ItemStack itemStack) {
                return super.getUnlocalizedName()
                        + "."
                        + MetalRegister.ore_list[this.shift
                        + itemStack.getItemDamage()];
        }
}
