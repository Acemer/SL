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
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase.FlowerEntry;
import net.minecraftforge.common.util.Constants;
import ru.nordwest.nord.util.BlockCoord;

import java.util.HashSet;

public class TileEntityGreatFurnace extends TileEntity {
	public BlockCoord techBlock;
	public int textureCode;
	
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
			
			// Передняя сторона
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y - 1, this.techBlock.z + 1)).textureCode = 1;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x    , this.techBlock.y - 1, this.techBlock.z + 1)).textureCode = 2;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y - 1, this.techBlock.z + 1)).textureCode = 3;
			
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y    , this.techBlock.z + 1)).textureCode = 4;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x    , this.techBlock.y    , this.techBlock.z + 1)).textureCode = 5;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y    , this.techBlock.z + 1)).textureCode = 6;
			
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y + 1, this.techBlock.z + 1)).textureCode = 7;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x    , this.techBlock.y + 1, this.techBlock.z + 1)).textureCode = 8;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y + 1, this.techBlock.z + 1)).textureCode = 9;
			
			
			// Центральная сторона
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y - 1, this.techBlock.z    )).textureCode = 10;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x    , this.techBlock.y - 1, this.techBlock.z    )).textureCode = 11;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y - 1, this.techBlock.z    )).textureCode = 12;
			
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y    , this.techBlock.z    )).textureCode = 13;
		  //((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x    , this.techBlock.y    , this.techBlock.z    )).textureCode = 14; // Технический блок
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y    , this.techBlock.z    )).textureCode = 15;
			
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y + 1, this.techBlock.z    )).textureCode = 16;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x    , this.techBlock.y + 1, this.techBlock.z    )).textureCode = 17;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y + 1, this.techBlock.z    )).textureCode = 18;
			
			
			// Заднаяя сторона
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y - 1, this.techBlock.z - 1)).textureCode = 19;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x    , this.techBlock.y - 1, this.techBlock.z - 1)).textureCode = 20;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y - 1, this.techBlock.z - 1)).textureCode = 21;
			
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y    , this.techBlock.z - 1)).textureCode = 22;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x    , this.techBlock.y    , this.techBlock.z - 1)).textureCode = 23;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y    , this.techBlock.z - 1)).textureCode = 24;
			
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y + 1, this.techBlock.z - 1)).textureCode = 25;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x    , this.techBlock.y + 1, this.techBlock.z - 1)).textureCode = 26;
			((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y + 1, this.techBlock.z - 1)).textureCode = 27;
			
			return world.setBlock(find.x, find.y, find.z, Nord.greatFurnaceTech);
		}
		
		return false;
	}
	
	public void setTechBlock(BlockCoord techBlock) {
		this.techBlock = techBlock;
		if (techBlock == null) // Печь разрушена
		{
			this.textureCode = 0;
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
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		this.writeSyncableDataToNBT(tag);
	}
	
	void writeSyncableDataToNBT(NBTTagCompound tag)
	{
		tag.setShort("textCode", (short)textureCode);
		if (techBlock != null)
		{
			tag.setInteger("tech_x", techBlock.x);
			tag.setShort("tech_y", (short)techBlock.y);
			tag.setInteger("tech_z", techBlock.z);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.readSyncableDataFromNBT(tag);
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeSyncableDataToNBT(syncData);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readSyncableDataFromNBT(pkt.func_148857_g());
	}

	public void readSyncableDataFromNBT(NBTTagCompound tag) {
		textureCode = tag.getInteger("textCode");
		
		if (tag.hasKey("tech_x"))
		{
			techBlock = new BlockCoord(tag.getInteger("tech_x"), (int)tag.getShort("tech_y"), tag.getInteger("tech_z"));
		}
	}
}
