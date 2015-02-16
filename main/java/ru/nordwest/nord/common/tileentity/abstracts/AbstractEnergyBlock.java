package ru.nordwest.nord.common.tileentity.abstracts;

import net.minecraft.tileentity.TileEntity;
import ru.nordwest.nord.common.tileentity.interfaces.IEnergyTile;

public abstract class AbstractEnergyBlock extends TileEntity
        implements IEnergyTile {
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
                return this.energy + energy <= getMaxEnergy();
        }

}
