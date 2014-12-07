package ru.nordwest.nord.common.tileentity;
import ru.nordwest.nord.common.recipe.FlowingRecipes1I2O;
import ru.nordwest.nord.common.recipe.IRecipes1I2O;

public class TileEntityFlowing extends AbstractEnergyMachina {


    public static int cookTimeLen = 200; // ticks


    @Override
    public String getInventoryName() {
        return "Hogger";
    }
    @Override
    public int getMaxEnergy() {
        return 12800;
    }
    @Override
    public IRecipes1I2O getRecipes() {
        return FlowingRecipes1I2O.INSTANCE();
    }


}
