package ru.nordwest.nord.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import ru.nordwest.nord.Nord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseDecoStoneBlock extends MetadataBlock {
	private IIcon[] texture;
	private final int colors[] = Nord.colors;
	private final int type = 16;
	private int index = 0;

	public BaseDecoStoneBlock(final Material par2Material, final int index) {
		super(par2Material, 16);
		this.index = index;
        setHardness(3);
        setResistance(3);
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
			this.texture[i] = par1IconRegister.registerIcon(Nord.MODID + ":"
					+ "stone/" + this.getTextureName() + "_"
					+ this.index);
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

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(final int meta) {
		return this.colors[15 - meta];

	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(final IBlockAccess p_149720_1_,
			final int p_149720_2_, final int p_149720_3_, final int p_149720_4_) {
		return this.getRenderColor(p_149720_1_.getBlockMetadata(p_149720_2_,
				p_149720_3_, p_149720_4_));
	}

}
