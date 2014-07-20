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

public class BaseMetallBlock extends MetadataBlock {
	private String[] subname = new String[]{"dirty","poor","normal","clear"};
	private IIcon[] texture;
	private int type=4;
	public BaseMetallBlock(Material par2Material) {
		super(par2Material, 4);
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
			texture[i] = par1IconRegister.registerIcon(Nord.MODID + ":" + getTextureName()+"/"+subname[i]+"_"+getTextureName());
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
