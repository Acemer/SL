package ru.nordwest.nord.item;

import ru.nordwest.nord.MetallRegister;
import ru.nordwest.nord.block.BaseMetallOre;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemMetallOre extends ItemBlockMetadata {
	private int shift = 0;
	public ItemMetallOre(Block block) {
		super(block);
		shift = ((BaseMetallOre)block).getShift();
		
	}
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return super.getUnlocalizedName() + "." +MetallRegister.ore_list[shift+itemStack.getItemDamage()];
	}
}
