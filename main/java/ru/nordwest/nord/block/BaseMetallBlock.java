package ru.nordwest.nord.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import ru.nordwest.nord.Nord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseMetallBlock extends MetadataBlock {
	private final String[] subname = new String[]{"dirty", "poor", "normal",
			"clear"};
	private IIcon[] texture;
	private final int type = 4;
	public BaseMetallBlock(final Material par2Material) {
		super(par2Material, 4);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final Item item, final CreativeTabs tab,
			final List subItems) {
		for (int i = 0; i < this.type; i++) {
			subItems.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister par1IconRegister) {
		this.texture = new IIcon[this.type];
		for (int i = 0; i < this.type; i++) {
			this.texture[i] = par1IconRegister.registerIcon(Nord.MODID
					+ ":metall_block/" + this.getTextureName() + "/"
					+ this.subname[i] + "_" + this.getTextureName());
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int side, final int meta) {
		return this.texture[meta];
	}

	@Override
	public int damageDropped(final int meta) {
		return meta;
	}
}
