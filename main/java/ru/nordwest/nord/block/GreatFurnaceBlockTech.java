package ru.nordwest.nord.block;

import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.tileentity.TileEntityGreatFurnace;
import ru.nordwest.nord.common.tileentity.TileEntityGreatFurnaceTech;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GreatFurnaceBlockTech extends BlockContainer {
	public GreatFurnaceBlockTech()
	{
		super(Material.rock);
		setHardness(2.0F);
        setResistance(5.0F);
        setBlockName("GreatFurnaceBlockTech");
        setBlockTextureName(Nord.MODID + ":greatFurnace/great_furnace_block_tech"); // TODO create texture
        setCreativeTab(CreativeTabs.tabRedstone);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGreatFurnaceTech();
	}
}
