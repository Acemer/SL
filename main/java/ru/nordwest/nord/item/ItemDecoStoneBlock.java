package ru.nordwest.nord.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemDecoStoneBlock extends ItemBlockMetadata {
	public ItemDecoStoneBlock(Block block) {
		super(block);
		
	}
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return super.getUnlocalizedName() + "." +itemStack.getItemDamage();
	}
}
