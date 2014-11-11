package ru.nordwest.nord.common.container;

import org.apache.logging.log4j.Level;

import ru.nordwest.nord.common.recipe.FlowingRecipes;
import ru.nordwest.nord.common.tileentity.TileEntityFlowing;
import ru.nordwest.nord.common.tileentity.TileEntitySmelter;
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
            ItemStack stack = null;
            Slot slotObject = (Slot) inventorySlots.get(slot);
    		Slot fuelSlot = (Slot) this.inventorySlots.get(0);
         
            if (slotObject != null && slotObject.getHasStack()) {
                    ItemStack stackInSlot = slotObject.getStack();
                    stack = stackInSlot.copy();

                    //boolean check = false;

					if (slot == 2 || slot == 3) {
                            if (!this.mergeItemStack(stackInSlot, 4, 39, true)) {
                                    return null;
                            }
                       slotObject.onSlotChange(stackInSlot, stack);
                    }
                    else if(slot != 1 && slot != 0){
                    	boolean _check = FlowingRecipes.hasRecipe(stackInSlot);
						if (_check){
							if(!this.mergeItemStack(stackInSlot, 1, 2, false)) {
								return null;
							}
						}else if (TileEntityFlowing.getItemBurnTime(stackInSlot)>0  && (fuelSlot.getStack() == null || fuelSlot.getStack().stackSize < 64)){
							if (!this.mergeItemStack(stackInSlot, 0, 1, false))
								return null;
						}else if (slot >= 4 && slot <= 30)
						{
							if (!this.mergeItemStack(stackInSlot, 31, 40, false))
								return null;
						} else if (slot >= 31 && slot < 40 && !this.mergeItemStack(stackInSlot, 4, 31, false))
							return null;
							
                    }else if (!this.mergeItemStack(stackInSlot, 4, 40, false))
        				return null;
					
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
            return null;
    }
    
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}
}
