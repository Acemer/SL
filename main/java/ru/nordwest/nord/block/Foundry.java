package ru.nordwest.nord.block;

import ru.nordwest.nord.Nord;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class Foundry extends Block {

	public Foundry() {
		super(Material.rock);
		setHardness(2.0F);
        setResistance(5.0F);
        setBlockName("FoundryBlock");
        setBlockTextureName(Nord.MODID + ":flowing/hogger_down");
        setCreativeTab(CreativeTabs.tabRedstone);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return Nord.foundryTech.canPlaceBlockAt(world, x, y, z);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		world.setBlock(x, y, z, Nord.foundryTech);
	}
}
