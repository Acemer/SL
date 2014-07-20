package ru.nordwest.nord.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemMetallBlock extends ItemBlockMetadata {
	private String[] subname = new String[]{"dirty","poor","normal","clear"};
	
	public ItemMetallBlock(Block p_i45328_1_) {
		super(p_i45328_1_);
		
	}
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return super.getUnlocalizedName() + "." +subname[itemStack.getItemDamage()];
	}
}
