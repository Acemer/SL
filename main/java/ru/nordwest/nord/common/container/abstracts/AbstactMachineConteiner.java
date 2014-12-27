package ru.nordwest.nord.common.container.abstracts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import ru.nordwest.nord.common.recipe.Interfaces.IRecipes1I2O;
import ru.nordwest.nord.common.tileentity.interfaces.IMachine;
import ru.nordwest.nord.util.Fuel;

abstract public class AbstactMachineConteiner extends Container {
    private IMachine tileEntity;

    private int lastEnergy;
    private int lastBurnTime;
    private int lastFuelBurnTime;
    private int lastCurrentItemEnergyProgress;
    private int lastCurrentItemEnergyNeed;

    public void init(InventoryPlayer invPlayer, IMachine ent)
    {
        tileEntity = ent;

        addSlotToContainer(new Slot(tileEntity, 0, 18, 58)); // fuel
        addSlotToContainer(new Slot(tileEntity, 1, 53, 38)); // item to work
        addSlotToContainer(new SlotFurnace(invPlayer.player, tileEntity, 2, 107, 39)); // result1
        addSlotToContainer(new SlotFurnace(invPlayer.player, tileEntity, 3, 128, 39)); // result2

        bindPlayerInventory(invPlayer);
    }

    @Override
    public void addCraftingToCrafters(ICrafting icrafter)
    {
        super.addCraftingToCrafters(icrafter);
        icrafter.sendProgressBarUpdate(this, 0, this.tileEntity.getEnergy());
        icrafter.sendProgressBarUpdate(this, 1, this.tileEntity.getBurnTime());
        icrafter.sendProgressBarUpdate(this, 2, this.tileEntity.getCurrentItemEnergyProgress());
        icrafter.sendProgressBarUpdate(this, 3, this.tileEntity.getCurrentItemEnergyNeed());
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastEnergy != this.tileEntity.getEnergy())
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.getEnergy());
            }

            if (this.lastBurnTime != this.tileEntity.getBurnTime())
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntity.getBurnTime());
            }

            if (this.lastCurrentItemEnergyProgress != this.tileEntity.getCurrentItemEnergyProgress())
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileEntity.getCurrentItemEnergyProgress());
            }

            if (this.lastCurrentItemEnergyNeed != this.tileEntity.getCurrentItemEnergyNeed())
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tileEntity.getCurrentItemEnergyNeed());
            }

            if (this.lastFuelBurnTime != this.tileEntity.getFuelBurnTime())
            {
                icrafting.sendProgressBarUpdate(this, 4, this.tileEntity.getFuelBurnTime());
            }
        }

        this.lastEnergy = this.tileEntity.getEnergy();
        this.lastBurnTime = this.tileEntity.getBurnTime();
        this.lastCurrentItemEnergyProgress = this.tileEntity.getCurrentItemEnergyProgress();
        this.lastCurrentItemEnergyNeed = this.tileEntity.getCurrentItemEnergyNeed();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int var, int val)
    {
        super.updateProgressBar(var, val);
        switch(var)
        {
            case 0:
                this.tileEntity.setEnergy(val);
                break;
            case 1:
                this.tileEntity.setBurnTime(val);
                break;
            case 2:
                this.tileEntity.setCurrentItemEnergyProgress(val);
                break;
            case 3:
                this.tileEntity.setCurrentItemEnergyNeed(val);
                break;
            case 4:
                this.tileEntity.setFuelBurnTime(val);
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
                boolean _check = ((IRecipes1I2O)tileEntity.getRecipes()).getIndexRecipe(stackInSlot)!=-1;
                if (_check){
                    if(!this.mergeItemStack(stackInSlot, 1, 2, false)) {
                        return null;
                    }
                }else if (Fuel.getInstance().getEnergy(stackInSlot)>0  && (fuelSlot.getStack() == null || fuelSlot.getStack().stackSize < 64)){
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
