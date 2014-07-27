package ru.nordwest.nord.item;

import java.util.List;

import ru.nordwest.nord.Nord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class ItemMetallIngot extends ItemBase {
	private String[] subname = new String[] { "dirty", "poor", "normal",
			"clear" };
	private IIcon[] texture;

	public ItemMetallIngot() {
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(Nord.tabMetall);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int index) {
		int j = MathHelper.clamp_int(index, 0, 4);
		return this.texture[j];
	}

	public String getUnlocalizedName(ItemStack item) {
		int i = MathHelper.clamp_int(item.getItemDamage(), 0, 4);
		return super.getUnlocalizedName() + "." + subname[i];
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 4; ++i) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister list) {
		this.texture = new IIcon[subname.length];

		for (int i = 0; i < texture.length; ++i) {
			this.texture[i] = list.registerIcon(Nord.MODID + ":"
					+ this.iconString + "/" + subname[i] + "_"
					+ this.iconString);

			//list.registerIcon(this.getIconString() + "_" + subname[i]);
		}
	}
}
