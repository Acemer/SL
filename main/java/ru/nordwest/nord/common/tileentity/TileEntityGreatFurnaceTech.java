package ru.nordwest.nord.common.tileentity;

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

public class TileEntityGreatFurnaceTech extends TileEntity implements IInventory {
	ItemStack inv[] = new ItemStack[5];
	
	public int energy;
	public static final int maxEnergy = 12800;
	public int burnTime;
	public int fuelBurnTime;
	
	public int currentItemEnergyProgress1;
	public int currentItemEnergyProgress2;
	
	public static final int needEnergy = 200;
	
	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}
	
	public void setEnergy(int item, int energy)
	{
		switch(item)
		{
		case 0:
			currentItemEnergyProgress1 = energy;
			break;
		case 1:
			currentItemEnergyProgress2 = energy;
			break;
		default:
			currentItemEnergyProgress1 = energy;
			currentItemEnergyProgress2 = energy;
			break;
		}
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amount) {
            	setInventorySlotContents(slot, null);
                if (slot == 1)
                {
                	setEnergy(0, 0);
                }
                else if (slot == 2)
                {
                	setEnergy(1, 0);
                }
            }
            else {
            	stack = stack.splitStack(amount);
            	if (stack.stackSize == 0) {
            		setInventorySlotContents(slot, null);
                    if (slot == 1)
                    {
                    	setEnergy(0, 0);
                    }
                    else if (slot == 2)
                    {
                    	setEnergy(1, 0);
                    }
            	}
            }
        }
        
        return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
        	setInventorySlotContents(slot, null);
            if (slot == 1)
            {
            	setEnergy(0, 0);
            }
            else if (slot == 2)
            {
            	setEnergy(1, 0);
            }
        }
        return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item) {		
		inv[slot] = item;
	}

	@Override
	public String getInventoryName() {
		return "Great Furnace";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
                player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		// TODO добавить проверку на валидный предмет/топливо
		if (slot == 3 || slot == 4)
		{
			return false;
		}
		
		return true;
	}

	public boolean clicked(EntityPlayer player, World world, int x, int y, int z) {
		player.openGui(Nord.instance, Nord.guiIDGreatFurnace, world, x, y, z);
		return true;
	}
	
	@Override
	public void updateEntity() {
		
		super.updateEntity();
	}
	
	public int getSmeltProgressScaled(int val, int item)
	{
		if (item == 0)
		{
			if (this.currentItemEnergyProgress1 == 0)
			{
				return 0;
			}
			
			return this.currentItemEnergyProgress1 * val / this.needEnergy;
		}
		else
		{
			if (this.currentItemEnergyProgress2 == 0)
			{
				return 0;
			}
			
			return this.currentItemEnergyProgress2 * val / this.needEnergy;
		}
	}
	
	public int getEnergyProgressScaled(int val)
	{
		if (this.energy == 0)
		{
			return 0;
		}
		
		return this.energy * val / this.maxEnergy;
	}
	
	public int getBurnTimeRemainingScaled(int val)
	{
		if (this.burnTime == 0)
		{
			return 0;
		}
		
		return this.burnTime * val / this.fuelBurnTime;
	}
}
