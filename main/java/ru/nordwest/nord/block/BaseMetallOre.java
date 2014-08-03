package ru.nordwest.nord.block;

import java.util.List;

import ru.nordwest.nord.MetallRegister;
import ru.nordwest.nord.Nord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BaseMetallOre extends MetadataBlock {
	private IIcon[] texture;
	private int type = 16;
	private int shift;

	public BaseMetallOre(Material par2Material, int shift) {
		super(par2Material, 16);
		this.shift = shift;
		type = (MetallRegister.ore_list.length - shift) % 16;
		type = type == 0 && MetallRegister.ore_list.length !=0 ? 16 : type;
	}

	public int getShift() {
		return this.shift;
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
			texture[i] = par1IconRegister.registerIcon(Nord.MODID + ":ore/"
					+ MetallRegister.ore_list[shift + i]);
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
