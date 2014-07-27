package ru.nordwest.nord.block;

import java.util.List;

import ru.nordwest.nord.Nord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BaseDecoStoneBlock extends MetadataBlock {
	private IIcon[] texture;
	private int colors[] = new int[] { 1973019, 11743532, 3887386, 5320730,
			2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372,
			14602026, 6719955, 12801229, 15435844, 15790320 };
	private int type = 16;
	private int index = 0;

	public BaseDecoStoneBlock(Material par2Material, int index) {
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
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return texture[meta];
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		/*switch (meta) {
		case 0:
			return 0xffffff;
		case 1:

			return 0x00ffff;
		default:
			return 0xffffff;
			}
		*/
		return colors[15-meta];
	

	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_,
			int p_149720_3_, int p_149720_4_) {
		return this.getRenderColor(p_149720_1_.getBlockMetadata(p_149720_2_,
				p_149720_3_, p_149720_4_));
	}

}
