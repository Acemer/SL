package ru.nordwest.nord.common.tiles;

import ru.nordwest.nord.common.lib.recipes.FlowingRecipes1I2O;
import ru.nordwest.nord.common.lib.recipes.Interfaces.IRecipes1I2O;
import ru.nordwest.nord.common.tiles.abstracts.TileAbstractEnergyMachina;

public class TileFlowing extends TileAbstractEnergyMachina {


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
