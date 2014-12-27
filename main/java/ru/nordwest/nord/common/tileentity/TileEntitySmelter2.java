package ru.nordwest.nord.common.tileentity;

import net.minecraft.item.ItemStack;
import ru.nordwest.nord.common.recipe.Interfaces.IAbstractRecipes;
import ru.nordwest.nord.common.recipe.Interfaces.IRecipes1I2O;
import ru.nordwest.nord.common.recipe.Interfaces.IRecipes2I2O;
import ru.nordwest.nord.common.recipe.SmelterRecipes2I2O;
import ru.nordwest.nord.common.tileentity.abstracts.AbstractEnergyMachina;

public class TileEntitySmelter2 extends AbstractEnergyMachina {
	private static final int[] slotsTop = new int[]{0, 1};
	private static final int[] slotsBottom = new int[]{3, 4};
	private static final int[] slotsSide = new int[]{2};
	private int input1 = 0;
	private int input2 = 1;
	private int fuel = 2;
	private int output1 = 3;
	private int output2 = 4;
	private ItemStack[] smelterItemStacks = new ItemStack[5];

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
}