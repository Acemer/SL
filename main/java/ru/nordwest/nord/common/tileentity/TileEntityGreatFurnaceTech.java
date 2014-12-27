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
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.FlowerEntry;
import net.minecraftforge.common.util.Constants;

// TODO Добавить сохранение предметов (ForcePush)
// TODO Проверить все на баги
// TODO Добавить обработку шифт-клика (Dark32)
public class TileEntityGreatFurnaceTech extends TileEntity implements IInventory {
	final ItemStack[] inv = new ItemStack[5];
	
	public int energy;
	public static final int maxEnergy = 12800;
	public int burnTime;
	public int fuelBurnTime;
	
	public int currentItemEnergyProgress;
	
	public static final int needEnergy = 200;
	
	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}
	
	public void setEnergy(int energy)
	{
		currentItemEnergyProgress = energy;
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amount) {
            	setInventorySlotContents(slot, null);
                if (slot == 1 || slot == 2)
                {
                	setEnergy(0);
                }
            }
            else {
            	stack = stack.splitStack(amount);
            	if (stack.stackSize == 0) {
            		setInventorySlotContents(slot, null);
                    if (slot == 1 || slot == 2)
                    {
                    	setEnergy(0);
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
            if (slot == 1 || slot == 2)
            {
            	setEnergy(0);
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
	
	/**
	 * Главная функция, прошу относиться к ней с любовью
	 */
	@Override
	public void updateEntity() {
		if (isBurning())
		{
			if (this.energy + 16 <= maxEnergy) // TODO сделать нормальную проверку
			{
				this.energy += 16;
				this.burnTime -= 16;
			
				if (this.burnTime <= 0)
				{
					this.energy -= -this.burnTime;
				}
			
				if (this.energy < 0)
				{
					this.energy = 0;
				}
			}
		}
		else if (canStartBurning())
		{
			this.fuelBurnTime = getItemBurnTime(inv[0]);
			this.burnTime = this.fuelBurnTime;
			--inv[0].stackSize;
			if (inv[0].stackSize <= 0)
			{
				inv[0] = null;
			}
		}
		
		boolean isSmelting = false;
		
		if (canStartSmelting())
		{
			if (inv[1] != null)
			{
				ItemStack item = FurnaceRecipes.smelting().getSmeltingResult(inv[1]);
				
				if (item != null && this.energy >= 4)
				{
					this.currentItemEnergyProgress += 4;
					isSmelting = true;
				}
			}
		
			if (inv[2] != null)
			{
				ItemStack item = FurnaceRecipes.smelting().getSmeltingResult(inv[2]);
			
				if (item != null && this.energy >= 4 && !isSmelting)
				{
					this.currentItemEnergyProgress += 4;
					isSmelting = true;
				}
			}
		
			if (isSmelting)
			{
				this.energy -= 4;
			}
		}
		
		trySmelt(canSmelt());
		
		super.updateEntity();
	}
	
	public int canSmelt()
	{
		int result = 0;
		ItemStack result1 = null;
		ItemStack result2 = null;
		
		if (this.inv[1] != null && this.currentItemEnergyProgress >= this.needEnergy)
		{
			result1 = FurnaceRecipes.smelting().getSmeltingResult(inv[1]);
			if (result1 != null)
			{
				if (this.inv[3] == null)
				{
					result |= 1;
				}
				else
				{
					if (this.inv[3].isItemEqual(result1))
					{
						int res = inv[3].stackSize + result1.stackSize;
						if (res <= getInventoryStackLimit())
						{
							result |= 1;
						}
					}
				}
			}
		}
		
		if (this.inv[2] != null && this.currentItemEnergyProgress >= this.needEnergy)
		{
			result2 = FurnaceRecipes.smelting().getSmeltingResult(inv[2]);
			if (result2 != null)
			{
				if (this.inv[4] == null)
				{
					result |= 2;
				}
				else
				{
					if (this.inv[4].isItemEqual(result2))
					{
						int res = inv[4].stackSize + result2.stackSize;
						if (res <= getInventoryStackLimit())
						{
							result |= 2;
						}
					}
				}
			}
		}
		
		return result;
	}
	
	public boolean canStartSmelting()
	{
		ItemStack result1 = null;
		ItemStack result2 = null;
		
		if (this.inv[1] != null)
		{
			result1 = FurnaceRecipes.smelting().getSmeltingResult(inv[1]);
			if (result1 != null)
			{
				if (this.inv[3] == null)
				{
					return true;
				}
				else
				{
					if (this.inv[3].isItemEqual(result1))
					{
						int res = inv[3].stackSize + result1.stackSize;
						if (res <= getInventoryStackLimit())
						{
							return true;
						}
					}
				}
			}
		}
		
		if (this.inv[2] != null)
		{
			result2 = FurnaceRecipes.smelting().getSmeltingResult(inv[2]);
			if (result2 != null)
			{
				if (this.inv[4] == null)
				{
					return true;
				}
				else
				{
					if (this.inv[4].isItemEqual(result2))
					{
						int res = inv[4].stackSize + result2.stackSize;
						if (res <= getInventoryStackLimit())
						{
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public void trySmelt(int id)
	{
		if (id == 0)
		{
			return;
		}
		
		if ((id & 1) != 0)
		{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inv[1]);

			if (this.inv[3] == null)
            {
                this.inv[3] = itemstack.copy();
            }
            else if (this.inv[3].getItem() == itemstack.getItem())
            {
                this.inv[3].stackSize += itemstack.stackSize;
            }

            --this.inv[1].stackSize;

            if (this.inv[1].stackSize <= 0)
            {
                this.inv[1] = null;
            }
            
            this.currentItemEnergyProgress = 0;
		}
		
		if ((id & 2) != 0)
		{
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inv[2]);

            if (this.inv[4] == null)
            {
                this.inv[4] = itemstack.copy();
            }
            else if (this.inv[4].getItem() == itemstack.getItem())
            {
                this.inv[4].stackSize += itemstack.stackSize;
            }

            --this.inv[2].stackSize;

            if (this.inv[2].stackSize <= 0)
            {
                this.inv[2] = null;
            }
            this.currentItemEnergyProgress = 0;
		}
	}
	
    public static boolean isItemFuel(ItemStack stack)
    {
    	return getItemBurnTime(stack) > 0;
    }
    
    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack == null)
        {
            return 0;
        }
        else
        {
            Item item = stack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab)
                {
                    return 150;
                }

                if (block.getMaterial() == Material.wood)
                {
                    return 300;
                }

                if (block == Blocks.coal_block)
                {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return GameRegistry.getFuelValue(stack);
        }
    }
    
	public boolean isBurning()
	{
		return this.energy < this.maxEnergy && this.burnTime > 0;	
	}
	
	public boolean canStartBurning()
	{
		return isItemFuel(this.inv[0]) && this.energy < this.maxEnergy;
	}
	/**
	 * Обрабатываем ли предметы в данный момент
	 */
	public boolean isSmelting()
	{
		return this.currentItemEnergyProgress > 0;
	}
	
	public int getSmeltProgressScaled(int val)
	{
		return this.currentItemEnergyProgress * val / this.needEnergy;
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
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		
        NBTTagList tagList = tagCompound.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);

        for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound tag = tagList.getCompoundTagAt(i);
                byte slot = tag.getByte("Slot");
        		inv[slot] = ItemStack.loadItemStackFromNBT(tag);
        }
        
        this.energy = (int)tagCompound.getShort("energy");
        this.burnTime = (int)tagCompound.getShort("burnTime");
        this.fuelBurnTime = (int)tagCompound.getShort("fBurnTime");
        this.currentItemEnergyProgress = (int)tagCompound.getShort("itemEnergyProg");
	}
	
	@Override
    public void writeToNBT(NBTTagCompound tagCompound) {
    	super.writeToNBT(tagCompound);
    	
    	tagCompound.setShort("energy", (short)this.energy);
    	tagCompound.setShort("burnTime", (short)this.burnTime);
    	tagCompound.setShort("fBurnTime", (short)this.fuelBurnTime);
    	tagCompound.setShort("itemEnergyProg", (short)this.currentItemEnergyProgress);
    	
    	NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < inv.length; i++) {
            ItemStack stack = inv[i];
            if (stack != null) {
            	NBTTagCompound tag = new NBTTagCompound();
            	tag.setByte("Slot", (byte) i);
            	stack.writeToNBT(tag);
            	itemList.appendTag(tag);
            }
        }
    	
    	tagCompound.setTag("Inventory", itemList);
    }
}
