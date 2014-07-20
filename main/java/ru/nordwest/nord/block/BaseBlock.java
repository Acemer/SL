package ru.nordwest.nord.block;

import cpw.mods.fml.relauncher.SideOnly;
import ru.nordwest.nord.Nord;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BaseBlock extends Block {

	public BaseBlock(Material par2Material) {
		super(par2Material);
		setCreativeTab(Nord.tabBase);
	}
}
