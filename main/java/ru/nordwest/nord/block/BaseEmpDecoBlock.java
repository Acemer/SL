package ru.nordwest.nord.block;

import java.util.List;

import ru.nordwest.nord.Nord;
import ru.nordwest.nord.item.ItemEmpDecoBlock;
import ru.nordwest.nord.item.ItemMetallBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BaseEmpDecoBlock extends MetadataBlock {
	private IIcon[] texture;
	private int colors[] = Nord.clolors;;
	private int type = 16;
	private int index = 0;

	public BaseEmpDecoBlock(Material par2Material, int index) {
		super(par2Material, 16);
		this.index = index;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List subItems) {
		for (int i = 0; i < this.type; i++) {
			subItems.add(new ItemStack(item, 1, i));
		}
	}
	

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		texture = new IIcon[this.type];
		for (int i = 0; i < this.type; i++) {
			texture[i] = par1IconRegister.registerIcon(Nord.MODID + ":"
					+ getTextureName() + "/" + getTextureName() + "_"
					+ this.index);
		}
	}
	
	@Override
	public boolean isOpaqueCube()
	{
	return false;
	}
	@Override
	 public boolean isBlockSolid(IBlockAccess p_149747_1_, int p_149747_2_, int p_149747_3_, int p_149747_4_, int p_149747_5_)
	    {
	        return true;
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
	@Override	
	public boolean renderAsNormalBlock()
    {
        return true;
    }
	@Override
    public int getRenderType()
    {
        return 1;
    }
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		return colors[15-meta];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_,
			int p_149720_3_, int p_149720_4_) {
		return this.getRenderColor(p_149720_1_.getBlockMetadata(p_149720_2_,
				p_149720_3_, p_149720_4_));
	}
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }
      
}

