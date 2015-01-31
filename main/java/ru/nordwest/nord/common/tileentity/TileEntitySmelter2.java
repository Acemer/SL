package ru.nordwest.nord.common.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.recipe.Interfaces.IAbstractRecipes;
import ru.nordwest.nord.common.recipe.Interfaces.IRecipe1I2O;
import ru.nordwest.nord.common.recipe.Interfaces.IRecipe2I2O;
import ru.nordwest.nord.common.recipe.Interfaces.IRecipes1I2O;
import ru.nordwest.nord.common.recipe.Interfaces.IRecipes2I2O;
import ru.nordwest.nord.common.recipe.SmelterRecipes2I2O;
import ru.nordwest.nord.common.tileentity.abstracts.AbstractEnergyMachina;
import ru.nordwest.nord.util.Fuel;

public class TileEntitySmelter2 extends AbstractEnergyMachina {
	private static final int[] slotsTop = new int[]{0, 1};
	private static final int[] slotsBottom = new int[]{3, 4};
	private static final int[] slotsSide = new int[]{2};

	private int fuel_slot    = 0;
	private int input_slot  = 1;
	private int input2_slot  = 2;
	private int result_slot = 3;
	private int second_result_slot = 4;
//	private ItemStack[] smelterItemStacks = new ItemStack[5];
	protected final ItemStack[] inv = new ItemStack[5];
	
	public int smelterSmeltTime;
	public int smelterCurSmeltTime;
	public int smelterWorkedTime;
	private String name;

    @Override
    public IAbstractRecipes getRecipes() {
        return SmelterRecipes2I2O.INSTANCE();
    }

    @Override
    public int getMaxEnergy() {
        return 12800;
    }

    @Override
    public String getInventoryName() {
        return "Smelter";
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
    public boolean canWork() {
    	
        return !(this.getEnergy() >= this.getMaxEnergy() ||
                inv[fuel_slot] == null ||
                getItemBurnTime(inv[fuel_slot]) == 0 ||
                burnTime > 0);

    } 
	@Override
    public void burn() {
        if (this.inv[fuel_slot] != null) {
            this.burnTime = getItemBurnTime(inv[fuel_slot]);
            this.fuelBurnTime = this.burnTime;
            this.inv[fuel_slot]=Fuel.getInstance().burn(this.inv[fuel_slot]);            
        }
    }

    protected IRecipe2I2O getRecipe(ItemStack stack,ItemStack stack2){
        return ((IRecipes2I2O)this.getRecipes()).getRecipe(stack, stack2);
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
            IRecipe2I2O rec = getRecipe(inv[input_slot],inv[input2_slot]);
            
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

    public boolean canStartWorking() {
        IRecipe2I2O rec = getRecipe(inv[input_slot],inv[input2_slot]);
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
        if (inv[input2_slot].stackSize < rec.getSecondInput().stackSize) { // не достаточно входных вещей
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
    
    /**
     * Обработать предмет
     */
    public void work() {
        IRecipe2I2O rec = getRecipe(inv[input_slot],inv[input2_slot]);;
        if (rec == null) {
            return; 
        }

        ItemStack out1 = rec.getResult();
        ItemStack out2 = rec.getSecondResult();
        boolean second = Nord.instance.rand.nextFloat()*100 < rec.getPercent();
        if (out1 != null) {
            if (inv[result_slot] != null) {
                inv[result_slot].stackSize += out1.stackSize;
            } else {
                inv[result_slot] = out1.copy();
            }
        }

        if (out2 != null && second) {
            if (inv[second_result_slot] != null) {
                inv[second_result_slot].stackSize += out2.stackSize;
            } else {
                inv[second_result_slot] = out2.copy();
            }
        }

        inv[input_slot].stackSize -= rec.getInput().stackSize;
        inv[input2_slot].stackSize -= rec.getSecondInput().stackSize;
        
        if (inv[input_slot].stackSize == 0) {
            inv[input_slot] = null;
        }
        if (inv[input2_slot].stackSize == 0) {
            inv[input2_slot] = null;
        }
        
        IRecipe2I2O recNext = getRecipe(inv[input_slot],inv[input2_slot]);
        if (recNext != null) {
            currentItemEnergyNeed = recNext.getEnergy();
        } else {
            currentItemEnergyNeed = 0;
        }

        currentItemEnergyProgress = 0;
    }
    public boolean canWorkResult() {
        IRecipe2I2O rec = getRecipe(inv[input_slot],inv[input2_slot]);
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
}