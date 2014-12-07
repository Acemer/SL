package ru.nordwest.nord.common.tileentity;

import net.minecraft.inventory.IInventory;
import ru.nordwest.nord.common.recipe.IRecipes1I2O;

/**
 * Created by andrew on 07.12.14.
 */
public interface IMachine  extends  IInventory,IEnergyTile {
    int getburnTime();
    int getCurrentItemEnergyProgress();
    int getCurrentItemEnergyNeed();
    void setburnTime(int val);
    void setCurrentItemEnergyProgress(int val);
    void setCurrentItemEnergyNeed(int val);
    void setFuelBurnTime(int val);
    int getFuelBurnTime();

    IRecipes1I2O getRecipes();
}
