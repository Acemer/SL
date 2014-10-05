package ru.nordwest.nord.common.container;

import ru.nordwest.nord.common.SmelterRecipes;
import ru.nordwest.nord.common.tileentity.TileEntityBrickFurnace;
import ru.nordwest.nord.common.tileentity.TileEntitySmelter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerBrickFurnace extends Container
{
	private TileEntityBrickFurnace tileCrusher;
	private int lastWorkTime;
	private int lastCrushTime;
	private int lastItemCrushTime;
	private int lastIEnerge;

	public ContainerBrickFurnace(InventoryPlayer iPlayer, TileEntityBrickFurnace tileEntityCrusher)
	{
		this.tileCrusher = tileEntityCrusher;
		this.addSlotToContainer(new Slot(tileEntityCrusher, 0, 35, 23));//input 1
		this.addSlotToContainer(new Slot(tileEntityCrusher, 1, 46, 59)); //fuel
        this.addSlotToContainer(new SlotFurnace(iPlayer.player, tileEntityCrusher, 2, 114, 23));// output 1
        this.addSlotToContainer(new SlotFurnace(iPlayer.player, tileEntityCrusher, 3, 135, 23));// output 2
		int i;

		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(iPlayer, j + i * 9 + 9, 8 + j * 18, 87 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(iPlayer, i, 8 + i * 18, 145));
		}
	}

	public void addCraftingToCrafters(ICrafting iCrafting)
	{
		super.addCraftingToCrafters(iCrafting);
		iCrafting.sendProgressBarUpdate(this, 0, this.tileCrusher.burnTime);
		iCrafting.sendProgressBarUpdate(this, 1, this.tileCrusher.furnaceFuelBurnTime);
		iCrafting.sendProgressBarUpdate(this, 2, this.tileCrusher.furnaceWorkedTime);
		iCrafting.sendProgressBarUpdate(this, 3, this.tileCrusher.energe);
	}

	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting iCrafting = (ICrafting) this.crafters.get(i);

			if (this.lastWorkTime != this.tileCrusher.burnTime)
			{
				iCrafting.sendProgressBarUpdate(this, 0, this.tileCrusher.burnTime);
			}
			if (this.lastCrushTime != this.tileCrusher.furnaceFuelBurnTime)
			{
				iCrafting.sendProgressBarUpdate(this, 1, this.tileCrusher.furnaceFuelBurnTime);
			}
			if (this.lastItemCrushTime != this.tileCrusher.furnaceWorkedTime)
			{
				iCrafting.sendProgressBarUpdate(this, 2, this.tileCrusher.furnaceWorkedTime);
			}
			if (this.lastIEnerge != this.tileCrusher.energe)
			{
				iCrafting.sendProgressBarUpdate(this, 2, this.tileCrusher.energe);
			}
		}

		this.lastWorkTime = this.tileCrusher.burnTime;
		this.lastCrushTime = this.tileCrusher.furnaceFuelBurnTime;
		this.lastItemCrushTime = this.tileCrusher.furnaceWorkedTime;
		this.lastIEnerge = this.tileCrusher.energe;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0)
			this.tileCrusher.burnTime = par2;
		if (par1 == 1)
			this.tileCrusher.furnaceFuelBurnTime = par2;
		if (par1 == 2)
			this.tileCrusher.furnaceWorkedTime = par2;
		if (par1 == 3)
			this.tileCrusher.energe = par2;
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1)
	{
		return this.tileCrusher.isUseableByPlayer(par1);
	}

	public ItemStack transferStackInSlot(EntityPlayer e, int parSlot)
	{
		System.out.println(parSlot);
		ItemStack iStack = null;
		Slot slot = (Slot) this.inventorySlots.get(parSlot);
		Slot fuelSlot = (Slot) this.inventorySlots.get(2);
		if (slot != null && slot.getHasStack())
		{
			ItemStack iStack1 = slot.getStack();
			iStack = iStack1.copy();

			if (parSlot == 3 || parSlot == 4 || parSlot == 2)
			{
				if (!this.mergeItemStack(iStack1, 5, 40, true))
				{
					return null;
				}

				slot.onSlotChange(iStack1, iStack);
			} else if (parSlot != 1 && parSlot != 0)
			{	
				int index = SmelterRecipes.crushing().getIndexPartResult(iStack1);
				if ( index> -1)
				{
					if (!this.mergeItemStack(iStack1, 0, 2, false))
						return null;
				} else
				if (TileEntitySmelter.isItemFuel(iStack1)  && (fuelSlot.getStack() == null || fuelSlot.getStack().stackSize < 64))
				{	
					System.out.println(true);
					if (!this.mergeItemStack(iStack1, 2, 3, false))
						return null;
				} else if (parSlot >= 5 && parSlot <= 31)
				{
					if (!this.mergeItemStack(iStack1, 32, 41, false))
						return null;
				} else if (parSlot >= 32 && parSlot < 41 && !this.mergeItemStack(iStack1, 5, 32, false))
					return null;
			} else if (!this.mergeItemStack(iStack1, 5, 41, false))
				return null;

			if (iStack1.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();

			if (iStack1.stackSize == iStack.stackSize)
				return null;

			slot.onPickupFromSlot(e, iStack1);
		}
		return iStack;
	}
}