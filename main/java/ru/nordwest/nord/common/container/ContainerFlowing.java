package ru.nordwest.nord.common.container;

import org.apache.logging.log4j.Level;

import ru.nordwest.nord.common.tileentity.TileEntityFlowing;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerFlowing extends Container {
	protected TileEntityFlowing tileEntity;
	
	private int lastEnergy;
	private int lastBurnTime;
	private int lastFuelBurnTime;
	private int lastCurrentItemEnergyProgress;
	private int lastCurrentItemEnergyNeed;
	
	public ContainerFlowing(InventoryPlayer invPlayer, TileEntityFlowing ent)
	{
		tileEntity = ent;
		
        addSlotToContainer(new Slot(tileEntity, 0, 18, 58)); // fuel
        addSlotToContainer(new Slot(tileEntity, 1, 53, 38)); // item to flow
        addSlotToContainer(new SlotFurnace(invPlayer.player, tileEntity, 2, 107, 39)); // result1
        addSlotToContainer(new SlotFurnace(invPlayer.player, tileEntity, 3, 128, 39)); // result2
        
        bindPlayerInventory(invPlayer);
	}
	
    public void addCraftingToCrafters(ICrafting icrafter)
    {
        super.addCraftingToCrafters(icrafter);
        icrafter.sendProgressBarUpdate(this, 0, this.tileEntity.energy);
        icrafter.sendProgressBarUpdate(this, 1, this.tileEntity.burnTime);
        icrafter.sendProgressBarUpdate(this, 2, this.tileEntity.currentItemEnergyProgress);
        icrafter.sendProgressBarUpdate(this, 3, this.tileEntity.currentItemEnergyNeed);
    }
    
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastEnergy != this.tileEntity.energy)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.energy);
            }

            if (this.lastBurnTime != this.tileEntity.burnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntity.burnTime);
            }

            if (this.lastCurrentItemEnergyProgress != this.tileEntity.currentItemEnergyProgress)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileEntity.currentItemEnergyProgress);
            }
            
            if (this.lastCurrentItemEnergyNeed != this.tileEntity.currentItemEnergyNeed)
            {
            	icrafting.sendProgressBarUpdate(this, 3, this.tileEntity.currentItemEnergyNeed);
            }
            
            if (this.lastFuelBurnTime != this.tileEntity.fuelBurnTime)
            {
            	icrafting.sendProgressBarUpdate(this, 4, this.tileEntity.fuelBurnTime);
            }
        }

    	this.lastEnergy = this.tileEntity.energy;
    	this.lastBurnTime = this.tileEntity.burnTime;
    	this.lastCurrentItemEnergyProgress = this.tileEntity.currentItemEnergyProgress;
    	this.lastCurrentItemEnergyNeed = this.tileEntity.currentItemEnergyNeed;
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int var, int val)
    {
    	super.updateProgressBar(var, val);
        switch(var)
        {
        case 0:
        	this.tileEntity.energy = val;
        	break;
        case 1:
        	this.tileEntity.burnTime = val;
        	break;
        case 2:
        	this.tileEntity.currentItemEnergyProgress = val;
        	break;
        case 3:
        	this.tileEntity.currentItemEnergyNeed = val;
        	break;
        case 4:
        	this.tileEntity.fuelBurnTime = val;
        	break;
        }
    }
    
    protected void bindPlayerInventory(InventoryPlayer invPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
            	addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 87 + i * 18));
            }
        }
        
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 145));
        }
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
    	// TODO fix it!
            ItemStack stack = null;
            Slot slotObject = (Slot) inventorySlots.get(slot);

            if (slotObject != null && slotObject.getHasStack()) {
                    ItemStack stackInSlot = slotObject.getStack();
                    stack = stackInSlot.copy();

                    if (slot < 4) {
                            if (!this.mergeItemStack(stackInSlot, 0, 35, true)) {
                                    return null;
                            }
                    }
                    
                    else if (!this.mergeItemStack(stackInSlot, 0, 2, false)) {
                            return null;
                    }

                    if (stackInSlot.stackSize == 0) {
                            slotObject.putStack(null);
                    } else {
                            slotObject.onSlotChanged();
                    }

                    if (stackInSlot.stackSize == stack.stackSize) {
                            return null;
                    }
                    slotObject.onPickupFromSlot(player, stackInSlot);
            }
            return stack;
    }
    
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}
}
