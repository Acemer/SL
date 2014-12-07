package ru.nordwest.nord.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LightLampBlock extends MetadataBlock {
	private IIcon[] texture;
	private final int colors[] = Nord.colors;;
	private final int type = 16;
	private int index = 0;

	public LightLampBlock(final Material par2Material, final int index) {
		super(par2Material, 16);
		this.index = index;
		this.setBlockBounds(0.187F, 0.0F, 0.187F, 0.812F, 1.0F, 0.812F);
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
					+ this.getTextureName() + "/" + this.getTextureName() + "_"
					+ this.index);
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isBlockSolid(final IBlockAccess p_149747_1_,
			final int p_149747_2_, final int p_149747_3_,
			final int p_149747_4_, final int p_149747_5_) {
		return true;
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
	public boolean renderAsNormalBlock() {
		return true;
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(final int meta) {
		return Nord.colors[15 - meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(final IBlockAccess world, final int x,
			final int y, final int z) {
		return this.getRenderColor(world.getBlockMetadata(x, y, z));
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world,
			final int x, final int y, final int z) {
		return null;
	}

	private boolean canPlaceOn(final World par1World, final int par2,
			final int par3, final int par4) {
		if (World.doesBlockHaveSolidTopSurface(par1World, par2, par3, par4) || par1World.getBlock( par2, par3, par4)==this) {
			return true;
		} else {
			final Block block = par1World.getBlock(par2, par3, par4);
			return (block.canPlaceTorchOnTop(par1World, par2, par3, par4));
		}
	}

	@Override
	public boolean canPlaceBlockAt(final World par1World, final int par2,
			final int par3, final int par4) {
		return this.canPlaceOn(par1World, par2, par3 + 1, par4)
				|| this.canPlaceOn(par1World, par2, par3 - 1, par4);
	}
}
