package ru.nordwest.nord.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import ru.nordwest.nord.MetallRegister;
import ru.nordwest.nord.Nord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMetaDataSimple extends Item {
	private static IIcon[] textures;
	private static final List<String> names = new ArrayList<String>(80);
	private static final List<String> texture_name = new ArrayList<String>(80);

	public ItemMetaDataSimple() {
		maxStackSize = 64;
	}

	@Override
	public String getUnlocalizedName(final ItemStack item) {
		int meta = item.getItemDamage();
		meta = names.size() > meta ? meta : 0;
		return super.getUnlocalizedName() + "." + names.get(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(final Item item, final CreativeTabs tab,
			final List list) {
		for (int i = 0; i < names.size(); ++i) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(final IIconRegister iconRegister) {
		 textures = new IIcon[texture_name.size()];
		for (int i = 0; i < texture_name.size(); ++i) {
			textures[i] = iconRegister.registerIcon(Nord.MODID + ":item/"
					+ (texture_name.get(i)));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(final int index) {
		int meta = index;
		meta = textures.length > meta ? meta : 0; // если метадата больше чем текстур, то берём 0 текстуру
		return textures[meta];
	}



	public static void addItem(final String texture, final String name) {
		texture_name.add(texture);
		names.add(name);
		
	}
	public static ItemStack getItem(final String name) {
		if (ItemMetaDataSimple.names.contains(name)) {
			return new ItemStack(Nord.item, 1,
					ItemMetaDataSimple.names.indexOf(name));
		} else {
			System.err.println("Unknow item: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}
}
