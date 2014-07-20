package ru.nordwest.nord.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class MetadataBlock extends BaseBlock {
	private int meta;
	private IIcon[] texture;

	public MetadataBlock(Material par2Material, int meta) {
		super(par2Material);
		this.meta = meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List subItems) {
		for (int i = 0; i < this.meta; i++) {
			subItems.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		texture = new IIcon[this.meta];
		for (int i = 0; i < this.meta; i++) {
			texture[i] = par1IconRegister.registerIcon(getTextureName() + "."
					+ i);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return texture[meta];
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}
}
