package ru.nordwest.nord.block;

import java.util.List;
import java.util.Random;

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

public class OilLampBlock extends MetadataBlock {
	private IIcon[] texture;
	private final int colors[] = Nord.colors;
	private final int type = 1;

	public OilLampBlock(final Material par2Material) {
		super(par2Material, 1);
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
					+ this.getTextureName() + "/" + this.getTextureName() + "."
					+ i);
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
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world,
			final int x, final int y, final int z) {
		return null;
	}

	@Override
	public void randomDisplayTick(final World par1World, final int x,
			final int y, final int z, final Random random) {
		final double a = x + 0.5F;
		final double b = y + (random.nextFloat() * 0.2F) + 0.3F;
		final double c = z + 0.5F;
		final double d = 0.01D;
		final double e = 0.02D;
		par1World.spawnParticle("smoke", a, b, c,
				d - (random.nextDouble() * e), 0.0D, d
						- (random.nextDouble() * e));
		par1World.spawnParticle("flame", a, b, c,
				d - (random.nextDouble() * e), 0.0D, d
						- (random.nextDouble() * e));
	}

	private boolean canPlaceOn(final World par1World, final int par2,
			final int par3, final int par4) {
		if (World.doesBlockHaveSolidTopSurface(par1World, par2, par3, par4)) {
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
