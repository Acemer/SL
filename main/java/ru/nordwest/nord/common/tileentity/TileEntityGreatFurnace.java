package ru.nordwest.nord.common.tileentity;

import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import ru.nordwest.nord.Nord;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase.FlowerEntry;
import net.minecraftforge.common.util.Constants;
import ru.nordwest.nord.util.BlockCoord;

import java.util.HashSet;

public class TileEntityGreatFurnace extends TileEntity {
	public BlockCoord techBlock;
	public boolean isPartOfMultiblock;
	
	private static int abs(int x)
	{
		if (x > 0)
		{
			return x;
		}
		
		if (x < 0)
		{
			return -x;
		}
		
		return 0;
	}
	
	public int isFurnaceBlock(World world, int x, int y, int z)
	{
		return world.getBlock(x, y, z) == Nord.greatFurnace ? 1 : 0;
	}
	
	public int isFurnaceBlock(World world, BlockCoord block)
	{
		return world.getBlock(block.x, block.y, block.z) == Nord.greatFurnace ? 1 : 0;
	}
	
	public boolean isValidStruct(World world, int x, int y, int z)
	{
		// Проверка блоков по сторонам
		int result = 0;
		result += isFurnaceBlock(world, x + 1, y, z);
		result += isFurnaceBlock(world, x - 1, y, z);
		result += isFurnaceBlock(world, x, y + 1, z);
		result += isFurnaceBlock(world, x, y - 1, z);
		result += isFurnaceBlock(world, x, y, z + 1);
		result += isFurnaceBlock(world, x, y, z - 1);
		
		if (result != 6)
		{   
			return false;
		}
		
		// Проверка блоков по углам
		result = 0;
		result += isFurnaceBlock(world, x + 1, y + 1, z + 1);
		result += isFurnaceBlock(world, x + 1, y + 1, z - 1);
		result += isFurnaceBlock(world, x + 1, y - 1, z + 1);
		result += isFurnaceBlock(world, x + 1, y - 1, z - 1);
		result += isFurnaceBlock(world, x - 1, y + 1, z + 1);
		result += isFurnaceBlock(world, x - 1, y + 1, z - 1);
		result += isFurnaceBlock(world, x - 1, y - 1, z + 1);
		result += isFurnaceBlock(world, x - 1, y - 1, z - 1);
		
		if (result != 8)
		{
			return false;
		}
		
		result = 0;
		result += isFurnaceBlock(world, x + 1, y, z + 1);
		result += isFurnaceBlock(world, x + 1, y, z - 1);
		result += isFurnaceBlock(world, x - 1, y, z + 1);
		result += isFurnaceBlock(world, x - 1, y, z - 1);
		
		return result == 4;
	}
	
	public boolean isValidStruct(World world, BlockCoord block)
	{
		// Проверка блоков по сторонам
		int result = 0;
		result += isFurnaceBlock(world, block.x + 1, block.y, block.z);
		result += isFurnaceBlock(world, block.x - 1, block.y, block.z);
		result += isFurnaceBlock(world, block.x, block.y + 1, block.z);
		result += isFurnaceBlock(world, block.x, block.y - 1, block.z);
		result += isFurnaceBlock(world, block.x, block.y, block.z + 1);
		result += isFurnaceBlock(world, block.x, block.y, block.z - 1);
		
		if (result != 6)
		{   
			return false;
		}
		
		// Проверка блоков по углам
		result = 0;
		result += isFurnaceBlock(world, block.x + 1, block.y + 1, block.z + 1);
		result += isFurnaceBlock(world, block.x + 1, block.y + 1, block.z - 1);
		result += isFurnaceBlock(world, block.x + 1, block.y - 1, block.z + 1);
		result += isFurnaceBlock(world, block.x + 1, block.y - 1, block.z - 1);
		result += isFurnaceBlock(world, block.x - 1, block.y + 1, block.z + 1);
		result += isFurnaceBlock(world, block.x - 1, block.y + 1, block.z - 1);
		result += isFurnaceBlock(world, block.x - 1, block.y - 1, block.z + 1);
		result += isFurnaceBlock(world, block.x - 1, block.y - 1, block.z - 1);
		
		if (result != 8)
		{
			return false;
		}
		
		result = 0;
		result += isFurnaceBlock(world, block.x + 1, block.y, block.z + 1);
		result += isFurnaceBlock(world, block.x + 1, block.y, block.z - 1);
		result += isFurnaceBlock(world, block.x - 1, block.y, block.z + 1);
		result += isFurnaceBlock(world, block.x - 1, block.y, block.z - 1);
		
		if (result != 4)
		{
			return false;
		}
		
		result = 0;
		result += isFurnaceBlock(world, block.x + 1, block.y + 1, block.z);
		result += isFurnaceBlock(world, block.x - 1, block.y + 1, block.z);
		result += isFurnaceBlock(world, block.x, block.y + 1, block.z + 1);
		result += isFurnaceBlock(world, block.x, block.y + 1, block.z - 1);
		result += isFurnaceBlock(world, block.x + 1, block.y - 1, block.z);
		result += isFurnaceBlock(world, block.x - 1, block.y - 1, block.z);
		result += isFurnaceBlock(world, block.x, block.y - 1, block.z + 1);
		result += isFurnaceBlock(world, block.x, block.y - 1, block.z - 1);
		// TOTAL: 26
		return result == 8;
	}
	
	public BlockCoord recFindTechBlock(World world, BlockCoord start, BlockCoord cur, HashSet path)
	{
		if (abs(cur.x - start.x) > 1 || abs(cur.y - start.y) > 1 || abs(cur.z - start.z) > 1)
		{
			return null;
		}
		
		if (isValidStruct(world, cur))
		{
			return cur;
		}
		
		BlockCoord ret = null;
		
		BlockCoord tmp = new BlockCoord(cur.x + 1, cur.y, cur.z);
		if (isFurnaceBlock(world, tmp) == 1 && !path.contains(tmp))
		{
			path.add(tmp);
			ret = recFindTechBlock(world, start, tmp, path);
			if (ret != null)
			{
				return ret;
			}
		}
		
		tmp = new BlockCoord(cur.x - 1, cur.y, cur.z);
		if (isFurnaceBlock(world, tmp) == 1 && !path.contains(tmp))
		{
			path.add(tmp);
			ret = recFindTechBlock(world, start, tmp, path);
			if (ret != null)
			{
				return ret;
			}
		}
		
		tmp = new BlockCoord(cur.x, cur.y + 1, cur.z);
		if (isFurnaceBlock(world, tmp) == 1 && !path.contains(tmp))
		{
			path.add(tmp);
			ret = recFindTechBlock(world, start, tmp, path);
			if (ret != null)
			{
				return ret;
			}
		}
		
		tmp = new BlockCoord(cur.x, cur.y - 1, cur.z);
		if (isFurnaceBlock(world, tmp) == 1 && !path.contains(tmp))
		{
			path.add(tmp);
			ret = recFindTechBlock(world, start, tmp, path);
			if (ret != null)
			{
				return ret;
			}
		}
		
		tmp = new BlockCoord(cur.x - 1, cur.y, cur.z + 1);
		if (isFurnaceBlock(world, tmp) == 1 && !path.contains(tmp))
		{
			path.add(tmp);
			ret = recFindTechBlock(world, start, tmp, path);
			if (ret != null)
			{
				return ret;
			}
		}
		
		tmp = new BlockCoord(cur.x - 1, cur.y, cur.z - 1);
		if (isFurnaceBlock(world, tmp) == 1 && !path.contains(tmp))
		{
			path.add(tmp);
			ret = recFindTechBlock(world, start, tmp, path);
			if (ret != null)
			{
				return ret;
			}
		}	
		
		return null;
	}
	
	/**
	 * Проверить, правильно ли построена печка, и если да -
	 * создать в центре технический блок, TileEntity которого
	 * будет выполнять всю работу печки.
	 * @return
	 * false: блок не создан, печка построена неверно
	 */
	public boolean tryToCreateTechBlock(World world, int xp, int yp, int zp)
	{
		HashSet<BlockCoord> path = new HashSet();
		BlockCoord cur = new BlockCoord(xp, yp, zp);
		path.add(cur);
		
		BlockCoord find = recFindTechBlock(world, cur, cur, path);
		if (find != null)
		{
			this.techBlock = new BlockCoord(find.x, find.y, find.z);
			Block block = world.getBlock(find.x, find.y, find.z);
			world.removeTileEntity(find.x, find.y, find.z);
			
			world.setBlockMetadataWithNotify(this.techBlock.x - 1, this.techBlock.y - 1, this.techBlock.z + 1, 1, 0);
			world.setBlockMetadataWithNotify(this.techBlock.x    , this.techBlock.y - 1, this.techBlock.z + 1, 2, 0);
			world.setBlockMetadataWithNotify(this.techBlock.x + 1, this.techBlock.y - 1, this.techBlock.z + 1, 3, 0);
			
			world.setBlockMetadataWithNotify(this.techBlock.x - 1, this.techBlock.y    , this.techBlock.z + 1, 4, 0);
			world.setBlockMetadataWithNotify(this.techBlock.x    , this.techBlock.y    , this.techBlock.z + 1 , 5, 0);
			world.setBlockMetadataWithNotify(this.techBlock.x + 1, this.techBlock.y    , this.techBlock.z + 1, 6, 0);
			
			world.setBlockMetadataWithNotify(this.techBlock.x - 1, this.techBlock.y + 1, this.techBlock.z + 1, 7, 0);
			world.setBlockMetadataWithNotify(this.techBlock.x    , this.techBlock.y + 1, this.techBlock.z + 1, 8, 0);
			world.setBlockMetadataWithNotify(this.techBlock.x + 1, this.techBlock.y + 1, this.techBlock.z + 1, 9, 0);
			
			return world.setBlock(find.x, find.y, find.z, Nord.greatFurnaceTech);
		}
		
		return false;
	}
	
	public void setTechBlock(BlockCoord techBlock) {
		this.techBlock = techBlock;
		if (techBlock == null) // Печь разрушена
		{
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 0);
		}
	}
	
	/**
	 * Установить заданный techBlock всем TileEntity печки.
	 */
	public static void broadcastSetTechBlock(World world, BlockCoord center, BlockCoord newTechBlock)
	{
		TileEntity ent;
		
		// Углы
		ent = world.getTileEntity(center.x + 1, center.y + 1, center.z + 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
			
		ent = world.getTileEntity(center.x + 1, center.y + 1, center.z - 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x + 1, center.y - 1, center.z + 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x + 1, center.y - 1, center.z - 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x - 1, center.y + 1, center.z + 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x - 1, center.y + 1, center.z - 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x - 1, center.y - 1, center.z + 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x - 1, center.y - 1, center.z - 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		// Стороны
		ent = world.getTileEntity(center.x + 1, center.y, center.z);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x - 1, center.y, center.z);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x, center.y + 1, center.z);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x, center.y - 1, center.z);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x, center.y, center.z + 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x, center.y, center.z - 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		// Какие-то еще центры, не знаю, как их назвать :D
		ent = world.getTileEntity(center.x + 1, center.y, center.z + 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x + 1, center.y, center.z - 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x - 1, center.y, center.z + 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x - 1, center.y, center.z - 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x + 1, center.y + 1, center.z);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x - 1, center.y + 1, center.z);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x + 1, center.y - 1, center.z);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x - 1, center.y - 1, center.z);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x, center.y + 1, center.z + 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x, center.y + 1, center.z - 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x, center.y - 1, center.z + 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
		
		ent = world.getTileEntity(center.x, center.y - 1, center.z - 1);
		if (ent != null)
			((TileEntityGreatFurnace)ent).setTechBlock(newTechBlock);
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int metadata, float what, float these, float are)
	{
		if (this.techBlock == null)
		{
			if (!tryToCreateTechBlock(world, x, y, z))
			{
				return false;
			}
		}
		
		TileEntity ent = world.getTileEntity(this.techBlock.x, this.techBlock.y, this.techBlock.z);
		if (ent == null)
		{
			return false;
		}
		
		broadcastSetTechBlock(world, this.techBlock, this.techBlock);

		return ((TileEntityGreatFurnaceTech)ent).clicked(player, world, this.techBlock.x, this.techBlock.y, this.techBlock.z);
	}

	public void removeTechBlock(World world) {
		if (this.techBlock == null)
		{
			return;
		}
		
		if (this.techBlock != null)
		{
			world.removeTileEntity(this.techBlock.x, this.techBlock.y, this.techBlock.z);
			world.setBlock(this.techBlock.x, this.techBlock.y, this.techBlock.z, Nord.greatFurnace);
			
			broadcastSetTechBlock(world, this.techBlock, null);
		}
	}
}
