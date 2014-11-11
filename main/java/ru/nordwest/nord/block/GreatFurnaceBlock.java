package ru.nordwest.nord.block;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.tileentity.TileEntityGreatFurnace;
import ru.nordwest.nord.common.tileentity.TileEntityGreatFurnaceTech;
import ru.nordwest.nord.util.BlockCoord;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GreatFurnaceBlock extends BlockContainer {
	TileEntityGreatFurnace tileEntity;
	
	public GreatFurnaceBlock() {
		super(Material.rock);
		setHardness(2.0F);
        setResistance(5.0F);
        setBlockName("GreatFurnaceBlock");
        setBlockTextureName(Nord.MODID + ":greatFurnace/great_furnace_block"); // TODO create texture
        setCreativeTab(CreativeTabs.tabRedstone);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int metadata, float what, float these, float are)
	{
		TileEntityGreatFurnace tileEntity = (TileEntityGreatFurnace) world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking())
		{
			return false;
		}
		
		return tileEntity.onBlockActivated(world, x, y, z, player, metadata, what, these, are);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityGreatFurnace();
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		boolean ret = true;
		
		TileEntity ent = world.getTileEntity(x + 1, y, z);
		if (ent instanceof TileEntityGreatFurnaceTech)
		{
			return false;
		}
		if (ent instanceof TileEntityGreatFurnace && ((TileEntityGreatFurnace)ent).techBlock != null)
		{
			return false;
		}
		
		ent = world.getTileEntity(x - 1, y, z);
		if (ent instanceof TileEntityGreatFurnaceTech)
		{
			return false;
		}
		if (ent instanceof TileEntityGreatFurnace && ((TileEntityGreatFurnace)ent).techBlock != null)
		{
			return false;
		}
		
		ent = world.getTileEntity(x, y + 1, z);
		if (ent instanceof TileEntityGreatFurnaceTech)
		{
			return false;
		}
		if (ent instanceof TileEntityGreatFurnace && ((TileEntityGreatFurnace)ent).techBlock != null)
		{
			return false;
		}
		
		ent = world.getTileEntity(x, y - 1, z);
		if (ent instanceof TileEntityGreatFurnaceTech)
		{
			return false;
		}
		if (ent instanceof TileEntityGreatFurnace && ((TileEntityGreatFurnace)ent).techBlock != null)
		{
			return false;
		}
		
		ent = world.getTileEntity(x, y, z + 1);
		if (ent instanceof TileEntityGreatFurnaceTech)
		{
			return false;
		}
		if (ent instanceof TileEntityGreatFurnace && ((TileEntityGreatFurnace)ent).techBlock != null)
		{
			return false;
		}
		
		ent = world.getTileEntity(x, y, z - 1);
		if (ent instanceof TileEntityGreatFurnaceTech)
		{
			return false;
		}
		if (ent instanceof TileEntityGreatFurnace && ((TileEntityGreatFurnace)ent).techBlock != null)
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		TileEntity ent = world.getTileEntity(x + 1, y, z);
		if (ent instanceof TileEntityGreatFurnace)
		{
			((TileEntityGreatFurnace)ent).removeTechBlock(world);
			super.onBlockDestroyedByPlayer(world, x, y, z, meta);
			return;
		}
		
		ent =  world.getTileEntity(x - 1, y, z);
		if (ent instanceof TileEntityGreatFurnace)
		{
			((TileEntityGreatFurnace)ent).removeTechBlock(world);
			super.onBlockDestroyedByPlayer(world, x, y, z, meta);
			return;
		}
		
		ent =  world.getTileEntity(x, y + 1, z);
		if (ent instanceof TileEntityGreatFurnace)
		{
			((TileEntityGreatFurnace)ent).removeTechBlock(world);
			super.onBlockDestroyedByPlayer(world, x, y, z, meta);
			return;
		}
		
		ent =  world.getTileEntity(x, y - 1, z);
		if (ent instanceof TileEntityGreatFurnace)
		{
			((TileEntityGreatFurnace)ent).removeTechBlock(world);
			super.onBlockDestroyedByPlayer(world, x, y, z, meta);
			return;
		}
		
		ent =  world.getTileEntity(x, y, z + 1);
		if (ent instanceof TileEntityGreatFurnace)
		{
			((TileEntityGreatFurnace)ent).removeTechBlock(world);
			super.onBlockDestroyedByPlayer(world, x, y, z, meta);
			return;
		}
		
		ent =  world.getTileEntity(x, y, z - 1);
		if (ent instanceof TileEntityGreatFurnace)
		{
			((TileEntityGreatFurnace)ent).removeTechBlock(world);
			super.onBlockDestroyedByPlayer(world, x, y, z, meta);
			return;
		}
		
		super.onBlockDestroyedByPlayer(world, x, y, z, meta);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase ent, ItemStack item) {
		TileEntityGreatFurnace tileEnt = (TileEntityGreatFurnace)world.getTileEntity(x, y, z);
		if (tileEnt.tryToCreateTechBlock(world, x, y, z))
		{
			tileEnt.broadcastSetTechBlock(world, tileEnt.techBlock, tileEnt.techBlock);
		}
		super.onBlockPlacedBy(world, x, y, z, ent, item);
	}
}
