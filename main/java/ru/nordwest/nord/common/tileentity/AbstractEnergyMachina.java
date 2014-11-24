package ru.nordwest.nord.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
/**
 * @author andrew
 * Абстрактная машина с 1 входом, 2 выходами и энергией
 */
public abstract class AbstractEnergyMachina extends TileEntity
		implements
			IInventory,
			IEnergyTile {
	/**
	 * inv[0] - fuel
	 * inv[1] - item to flow
	 * inv[2] - result 1
	 * inv[3] - result 2
	 */
	protected ItemStack inv[] = new ItemStack[4];
	private int energy;
	@Override
	public int setEnergy(int energy) {
		this.energy = energy;
		return this.energy;
	}

	@Override
	public int getEnergy() {
		return this.energy;
	}

	@Override
	public int addEnergy(int energy) {
		if (hasAddEnergy(energy))
			this.energy += energy;
		else
			this.energy = getMaxEnergy();
		return this.energy;
	}

	@Override
	public int subEnergy(int energy) {
		if (hasSubEnergy(energy))
			this.energy -= energy;
		else
			this.energy = 0;
		return this.energy;
	}

	@Override
	public boolean isAcepter() {
		return true;
	}

	@Override
	public boolean isDispenser() {
		return false;
	}

	@Override
	public boolean hasSubEnergy(int energy) {
		return this.energy >= energy;
	}

	@Override
	public boolean hasAddEnergy(int energy) {
		return this.energy + energy >= getMaxEnergy();
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
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this
				&& player.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
						zCoord + 0.5) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {

	}
	
	@Override
	public int getSizeInventory() {
		return 4;
	}

	@Override
	public ItemStack getStackInSlot(int slotID) {
		return inv[slotID];
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
        inv[slot] = stack;
	}
	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}
    @SideOnly(Side.CLIENT)
    public int getEnergyProgressScaled(int val)
    {
        return this.getEnergy() * val / (this.getMaxEnergy());
    }
}
