package ru.nordwest.nord.common.container;

import ru.nordwest.nord.common.recipe.old.SmelterRecipes;
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

public class ContainerSmelter extends Container
{
	private final TileEntitySmelter tileCrusher;
	private int lastWorkTime;
	private int lastCrushTime;
	private int lastItemCrushTime;

	public ContainerSmelter(InventoryPlayer iPlayer, TileEntitySmelter tileEntityCrusher)
	{
		this.tileCrusher = tileEntityCrusher;
		this.addSlotToContainer(new Slot(tileEntityCrusher, 0, 35, 23));//input 1
		this.addSlotToContainer(new Slot(tileEntityCrusher, 1, 56, 23));//input 2
		this.addSlotToContainer(new Slot(tileEntityCrusher, 2, 46, 59)); //fuel
        this.addSlotToContainer(new SlotFurnace(iPlayer.player, tileEntityCrusher, 3, 114, 23));// output 1
        this.addSlotToContainer(new SlotFurnace(iPlayer.player, tileEntityCrusher, 4, 135, 23));// output 2
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
		iCrafting.sendProgressBarUpdate(this, 0, this.tileCrusher.smelterWorkedTime);
		iCrafting.sendProgressBarUpdate(this, 1, this.tileCrusher.smelterSmeltTime);
		iCrafting.sendProgressBarUpdate(this, 2, this.tileCrusher.smelterCurSmeltTime);
	}

	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting iCrafting = (ICrafting) this.crafters.get(i);

			if (this.lastWorkTime != this.tileCrusher.smelterWorkedTime)
			{
				iCrafting.sendProgressBarUpdate(this, 0, this.tileCrusher.smelterWorkedTime);
			}
			if (this.lastCrushTime != this.tileCrusher.smelterSmeltTime)
			{
				iCrafting.sendProgressBarUpdate(this, 1, this.tileCrusher.smelterSmeltTime);
			}
			if (this.lastItemCrushTime != this.tileCrusher.smelterCurSmeltTime)
			{
				iCrafting.sendProgressBarUpdate(this, 2, this.tileCrusher.smelterCurSmeltTime);
			}
		}

		this.lastWorkTime = this.tileCrusher.smelterWorkedTime;
		this.lastCrushTime = this.tileCrusher.smelterSmeltTime;
		this.lastItemCrushTime = this.tileCrusher.smelterCurSmeltTime;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0)
			this.tileCrusher.smelterWorkedTime = par2;
		if (par1 == 1)
			this.tileCrusher.smelterSmeltTime = par2;
		if (par1 == 2)
			this.tileCrusher.smelterCurSmeltTime = par2;
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1)
	{
		return this.tileCrusher.isUseableByPlayer(par1);
	}

	public ItemStack transferStackInSlot(EntityPlayer e, int parSlot)
	{
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