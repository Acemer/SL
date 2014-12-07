package ru.nordwest.nord.common.tileentity;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import ru.nordwest.nord.common.recipe.FlowingRecipes;
import ru.nordwest.nord.common.recipe.*;
import ru.nordwest.nord.util.Fuel;

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
	 * inv[1] - item to work
	 * inv[2] - result 1
	 * inv[3] - result 2
	 */
    private int fuel_slot = 0;
    private int input_slot = 1;
    private int result_slot = 2;
    private int second_result_slot = 3;
	protected ItemStack inv[] = new ItemStack[4];
	private int energy;
    public int burnTime;
    public int fuelBurnTime;
    public int currentItemEnergyProgress;
    public int currentItemEnergyNeed;
    protected int burnSpeed = 16;
    protected  int workSpeed = 4;
    abstract protected IRecipes1I2O getRecipes();

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
		return this.energy + energy <= getMaxEnergy();
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
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);

        NBTTagList tagList = tagCompound.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);

        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            inv[slot] = ItemStack.loadItemStackFromNBT(tag);
        }

        this.setEnergy(tagCompound.getShort("energy"));
        burnTime = tagCompound.getShort("burnTime");
        currentItemEnergyProgress = tagCompound.getShort("curEnergyProg");
        currentItemEnergyNeed = tagCompound.getShort("curEnergyNeed");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setShort("energy", (short)this.getEnergy());
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
    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
                if (slot == 1)
                {
                    currentItemEnergyProgress = 0;
                    currentItemEnergyNeed = 0;
                }
            }
            else {
                stack = stack.splitStack(amount);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                    if (slot == 1)
                    {
                        currentItemEnergyProgress = 0;
                        currentItemEnergyNeed = 0;
                    }
                }
            }
        }

        return stack;
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

        IRecipe1I2O rec = getRecipes().getRecipe(stack);

        return rec != null;
    }
    public static int getItemBurnTime(ItemStack stack)
    {
       return Fuel.getInstance().getEnergy(stack);
    }

    @SideOnly(Side.CLIENT)
    public int getProgressScaled(int val)
    {
        if (currentItemEnergyNeed == 0)
        {
            return 0;
        }

        return currentItemEnergyProgress * val / currentItemEnergyNeed;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int val)
    {
        if (fuelBurnTime == 0)
        {
            return 0;
        }

        return burnTime * val / fuelBurnTime;
    }

    public boolean isBurning()
    {
        return burnTime > 0 && this.getEnergy() < this.getMaxEnergy();
    }

    public boolean isWork()
    {
        return currentItemEnergyNeed > 0 && this.getEnergy() > 0;
    }

    public boolean canWork() {
        if (this.getEnergy() >= this.getMaxEnergy() ||
                inv[fuel_slot] == null ||
                getItemBurnTime(inv[fuel_slot]) == 0 ||
                burnTime > 0)
            return false;

        return true;
    }

    public void burn() {
        if (this.inv[fuel_slot] != null) {
            this.burnTime = getItemBurnTime(inv[fuel_slot]);
            this.fuelBurnTime = this.burnTime;
            --this.inv[fuel_slot].stackSize;
            if (this.inv[fuel_slot].stackSize == 0) {
                this.inv[fuel_slot] = null;
            }
        }
    }

    public boolean canWorkResult() {
        IRecipe1I2O rec = getRecipes().getRecipe(inv[input_slot]);
        if (rec == null) { // нет рецепта
            currentItemEnergyProgress = 0;
            currentItemEnergyNeed = 0;
            return false;
        }

        if (currentItemEnergyProgress < rec.getEnergy()) { // нет энергии
            return false;
        }else{
            return canStartWorking();
        }

    }

    public boolean canStartWorking() {
        IRecipe1I2O rec = getRecipes().getRecipe(inv[input_slot]);
        if (rec == null) {
            currentItemEnergyProgress = 0;
            currentItemEnergyNeed = 0;
            return false;
        }

        ItemStack out1 = rec.getResult();
        ItemStack out2 = rec.getSecondResult();

        if (inv[input_slot].stackSize < rec.getInput().stackSize) { // не достаточно входных вещей
            return false;
        }

        if (inv[result_slot] == null && inv[second_result_slot] == null) { // выходные слоты пусты
            return true;
        }

// в выходном слоте не наша вещь
        if ((inv[result_slot] != null && out1 != null && !out1.isItemEqual(inv[result_slot])) ||
                (inv[second_result_slot] != null && out2 != null && !out2.isItemEqual(inv[second_result_slot]))) {
            return false;
        }
// хватает ли места
        int result = (inv[result_slot] != null ? inv[result_slot].stackSize : 0) + (out1 != null ? out1.stackSize : 0);
        int result2 = (inv[second_result_slot] != null ? inv[second_result_slot].stackSize : 0) + (out2 != null ? out2.stackSize : 0);

        boolean ret = result <= getInventoryStackLimit() &&
                result2 <= getInventoryStackLimit();

        if (!ret) {
            currentItemEnergyProgress = 0;
            currentItemEnergyNeed = 0;
        }

        return ret;
    }
    @Override
    public void updateEntity() {
        boolean updated = false;
        if (isBurning()) {
            burnTime -= burnSpeed;
            this.addEnergy(burnSpeed);
            updated = true;
            if (burnTime == 0) {
                if (canWork()) {
                    burn();
                }
            }
        } else {
            fuelBurnTime = 0;
            if (canWork()) {
                updated = true;
                burn();
            }
        }


        if (isWork() && canStartWorking()) {
            currentItemEnergyProgress += workSpeed;
            this.subEnergy(workSpeed);
            updated = true;
            if (canWorkResult()) {
                work();
            }
        } else if (canStartWorking()) {
            IRecipe1I2O rec = getRecipes().getRecipe(inv[input_slot]);
            if (rec != null) {
                currentItemEnergyNeed = rec.getEnergy();
                updated = true;
            } else {
                currentItemEnergyNeed = 0;
            }
        }

        if (updated) {
            this.markDirty();
        }
    }

    /**
     * Обработать предмет
     */
    public void work() {
        IRecipe1I2O rec = getRecipes().getRecipe(inv[input_slot]);
        if (rec == null) {
            return; 
        }

        ItemStack out1 = rec.getResult();
        ItemStack out2 = rec.getSecondResult();

        if (out1 != null) {
            if (inv[result_slot] != null) {
                inv[result_slot].stackSize += out1.stackSize;
            } else {
                inv[result_slot] = out1.copy();
            }
        }

        if (out2 != null) {
            if (inv[second_result_slot] != null) {
                inv[second_result_slot].stackSize += out2.stackSize;
            } else {
                inv[second_result_slot] = out2.copy();
            }
        }

        inv[input_slot].stackSize -= rec.getInput().stackSize;

        if (inv[input_slot].stackSize == 0) {
            inv[input_slot] = null;
        }

        IRecipe1I2O recNext = getRecipes().getRecipe(inv[input_slot]);
        if (recNext != null) {
            currentItemEnergyNeed = recNext.getEnergy();
        } else {
            currentItemEnergyNeed = 0;
        }

        currentItemEnergyProgress = 0;
    }

}
