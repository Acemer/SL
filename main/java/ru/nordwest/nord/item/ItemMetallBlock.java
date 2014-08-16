package ru.nordwest.nord.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemMetallBlock extends ItemBlockMetadata {
	private final String[] subname = new String[]{"dirty", "poor", "normal",
			"clear"};

	public ItemMetallBlock(final Block p_i45328_1_) {
		super(p_i45328_1_);

	}
	@Override
	public String getUnlocalizedName(final ItemStack itemStack) {
		return super.getUnlocalizedName() + "."
				+ this.subname[itemStack.getItemDamage()];
	}
}
