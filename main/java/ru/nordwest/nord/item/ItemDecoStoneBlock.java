package ru.nordwest.nord.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemDecoStoneBlock extends ItemBlockMetadata {
	public ItemDecoStoneBlock(final Block block) {
		super(block);

	}
	@Override
	public String getUnlocalizedName(final ItemStack itemStack) {
		return super.getUnlocalizedName() + "." + itemStack.getItemDamage();
	}
}
