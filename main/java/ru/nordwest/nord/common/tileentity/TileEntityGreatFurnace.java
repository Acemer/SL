package ru.nordwest.nord.common.tileentity;

import java.util.HashSet;

import org.apache.logging.log4j.Level;

import ru.nordwest.nord.Nord;
import ru.nordwest.nord.flowingRecipes.FlowingRecipe;
import ru.nordwest.nord.flowingRecipes.FlowingRecipesList;
import ru.nordwest.nord.util.BlockCoord;
import scala.runtime.AbstractFunction21;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.FlowerEntry;
import net.minecraftforge.common.util.Constants;

public class TileEntityGreatFurnace extends TileEntity {
	ItemStack inv[] = new ItemStack[5];
	public BlockCoord techBlock;
	
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
		
		return result == 4;
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
	public boolean tryToCreateTechBlock(World world, int x, int y, int z)
	{
		HashSet<BlockCoord> path = new HashSet();
		BlockCoord cur = new BlockCoord(x, y, z);
		path.add(cur);
		
		BlockCoord find = recFindTechBlock(world, cur, cur, path);
		if (find != null)
		{
			techBlock = 
		}
		else
		{
			FMLLog.log(Level.ERROR, "not found");
		}
		return false;
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int metadata, float what, float these, float are)
	{
		return tryToCreateTechBlock(world, x, y, z);
	}
}
