package ru.nordwest.nord.block;

import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.tileentity.TileEntityGreatFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GreatFurnaceBlock extends BlockContainer {
	Block techBlock;
	
	public GreatFurnaceBlock() {
		super(Material.rock);
		setHardness(2.0F);
        setResistance(5.0F);
        setBlockName("GreatFurnaceBlock");
        setBlockTextureName(Nord.MODID + ":greatFurnace/great_furnace_block"); // TODO create texture
        setCreativeTab(CreativeTabs.tabRedstone);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGreatFurnace();
	}
	
	/*
	 * Proverit', pravil'no li postroena pechka
	 */
	public boolean tryToCreateTechBlock()
	{
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int metadata, float what, float these, float are)
	{
        if (techBlock == null) {
            if (!tryToCreateTechBlock())
            {
            	return false;
            }
            else
            {
            	return techBlock.onBlockActivated(world, x, y, z, player, metadata, what, these, are);
            }
        }

        return false;
	}

}
