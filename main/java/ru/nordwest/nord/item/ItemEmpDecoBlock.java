package ru.nordwest.nord.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemEmpDecoBlock extends ItemBlockMetadata {
	private Block block;
	public ItemEmpDecoBlock(Block block) {
		super(block);
		this.block = block;

	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return super.getUnlocalizedName() + "." + itemStack.getItemDamage();
	}

	private int colors[] = new int[] { 1973019, 11743532, 3887386, 5320730,
			2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372,
			14602026, 6719955, 12801229, 15435844, 15790320 };

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		return colors[15 - meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack item, int par2) {
		return this.block.getRenderColor(item.getItemDamage());
	//	return getRenderColor(item.getItemDamage());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}


}
