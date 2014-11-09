package ru.nordwest.nord.common.tileentity;

import org.apache.logging.log4j.Level;

import ru.nordwest.nord.flowingRecipes.FlowingRecipe;
import ru.nordwest.nord.flowingRecipes.FlowingRecipesList;
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
import net.minecraftforge.common.util.Constants;

// TODO update flowing system
public class TileEntityFlowing extends TileEntity implements IInventory {
	/**
	 * inv[0] - fuel
	 * inv[1] - item to flow
	 * inv[2] - result 1
	 * inv[3] - result 2
	 */
	private ItemStack inv[] = new ItemStack[4];
	public int energy;
	public int burnTime;
	public int currentItemEnergyProgress;
	public int currentItemEnergyNeed;
	
	public static int cookTimeLen = 200; // ticks
	public static int maxEnergy = 128000; // 8 parts of coal
	
    /*public TileEntityFlowing()  {
    	super();
        inv = new ItemStack[4];
    }*/
    
	@Override
	public int getSizeInventory() {
		return 4;
	}

	@Override
	public ItemStack getStackInSlot(int slotID) {
		return inv[slotID];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amount) {
            	setInventorySlotContents(slot, null);
            }
            else {
            	stack = stack.splitStack(amount);
            	if (stack.stackSize == 0) {
            		setInventorySlotContents(slot, null);
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
        }
        return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;
	}

	@Override
	public String getInventoryName() {
		return "Flowing";
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
                player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64.0D;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if (slot == 0 && getItemBurnTime(stack) == 0)
		{
			return false;
		}
		
		if (slot == 2 || slot == 3)
		{
			return false;
		}
		
		return true;
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
            
        	energy = tagCompound.getShort("energy");
        	burnTime = tagCompound.getShort("burnTime");
        	currentItemEnergyProgress = tagCompound.getShort("curEnergyProg");
        	currentItemEnergyNeed = tagCompound.getShort("curEnergyNeed");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
    	super.writeToNBT(tagCompound);
    	tagCompound.setShort("energy", (short)energy);
    	tagCompound.setShort("burnTime", (short)burnTime);
    	tagCompound.setShort("curBurnTime", (short)currentItemEnergyProgress);
    	tagCompound.setShort("curCookTime", (short)currentItemEnergyNeed);
    	
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
    
    @SideOnly(Side.CLIENT)
    public int getFlowProgressScaled(int val)
    {
    	if (!isFlowing())
    	{
    		return 0;
    	}
    	
        return currentItemEnergyProgress * val / currentItemEnergyNeed;
    }
    
    @SideOnly(Side.CLIENT)
    public int getEnergyProgressScaled(int val)
    {
        return energy * val / (maxEnergy);
    }
    
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int val)
    {
    	if (currentItemEnergyNeed == 0)
    	{
    		return val;
    	}
    	
        return currentItemEnergyProgress * val / currentItemEnergyNeed;
    }
    
    public boolean isBurning()
    {
        return burnTime > 0 && energy < maxEnergy; // stop burning when we reach max energy
    }
    
    public boolean isFlowing()
    {
    	return currentItemEnergyNeed > 0 && energy > 0;
    }
    
    public boolean canSmelt()
    {
    	// TODO add checks
    	if (energy >= maxEnergy)
    	{
    		return false;
    	}
    	
    	if (inv[0] == null)
    	{
    		return false;
    	}
    	
    	if (getItemBurnTime(inv[0]) == 0)
    	{
    		return false;
    	}
    	
    	if (burnTime > 0)
    	{
    		return false;
    	}
    	
    	return true;
    }
    
    public void smelt()
    {
        if (this.inv[0] != null)
        {       
            this.burnTime = getItemBurnTime(inv[0]);
            
            --this.inv[0].stackSize;

            if (this.inv[0].stackSize == 0)
            {
                this.inv[0] = null;
            }
        }
    }
    
    public boolean canFlow()
    {
    	FlowingRecipe rec = FlowingRecipesList.getRecipe(inv[1]);
    	if (rec == null)
    	{
    		return false;
    	}
    	
		FMLLog.log(Level.ERROR, "Not enough energy! %s", currentItemEnergyProgress);
		
    	if (currentItemEnergyProgress < rec.needEnergy)
    	{
    		return false;
    	}
    	
    	ItemStack out1 = rec.out1;
    	ItemStack out2 = rec.out2;
    	
    	if (inv[1].stackSize < rec.in.stackSize)
    	{
    		return false;
    	}
    	
    	if (out1 == null && out2 == null) // should never happen
    	{
    		return false;
    	}
    	
    	if (inv[2] == null && inv[3] == null)
    	{
    		return true;
    	}
    	
    	if (inv[2] != null && out1 != null && !out1.isItemEqual(inv[2]))
    	{
    		return false;
    	}
    	
    	if (inv[3] != null && out2 != null && !out2.isItemEqual(inv[3]))
    	{
    		return false;
    	}
    	
    	int result = (inv[2] != null ? inv[2].stackSize : 0) + (out1 != null ? out1.stackSize : 0);
    	int result2 = (inv[3] != null ? inv[3].stackSize : 0) + (out2 != null ? out2.stackSize : 0);
    	
    	return result <= getInventoryStackLimit() &&
    		   result2 <= getInventoryStackLimit();
    }
    
    /**
     * Обработка предмета
     */
    public void flow()
    {
    	FMLLog.log(Level.ERROR, "flowering: %s", worldObj.isRemote);
    	FlowingRecipe rec = FlowingRecipesList.getRecipe(inv[1]);
    	
    	if (rec == null)
    	{
    		return; // should never happen
    	}
    	
    	ItemStack out1 = rec.out1;
    	ItemStack out2 = rec.out2;
    	
    	if (out1 != null)
    	{
    		if (inv[2] != null)
    		{
    			inv[2].stackSize += out1.stackSize;
    		}
    		else
    		{
    			inv[2] = out1.copy();
    		}
    	}
    	
    	if (out2 != null)
    	{
    		if (inv[3] != null)
    		{
    			inv[3].stackSize += out2.stackSize;
    		}
    		else
    		{
    			inv[3] = out2.copy();
    		}
    	}
    	
    	inv[1].stackSize -= rec.in.stackSize;
    	
    	if (inv[1].stackSize == 0)
    	{
    		inv[1] = null;
    	}
    	
    	currentItemEnergyNeed = 0;
    	currentItemEnergyProgress = 0;
    }
    
    // TODO replace standard values
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
    
    public void updateEntity()
    {
    	// Энергия
    	if (isBurning())
    	{
    		--burnTime;
    		++energy;
    		
    		if (burnTime == 0)
    		{
        		if (canSmelt())
        		{
        			smelt();
        		}
    		}
    	}
    	else
    	{
    		if (canSmelt())
    		{
    			smelt();
    		}
    	}
    	
    	if (isBurning() || isFlowing())
    	{
    		this.markDirty();
    	}
    	
    	// Обработка предмета
    	if (isFlowing())
    	{
    		++currentItemEnergyProgress;
    		--energy;
    		
    		if (canFlow())
    		{
    			flow();
    		}
    	}
    	
    }
}
