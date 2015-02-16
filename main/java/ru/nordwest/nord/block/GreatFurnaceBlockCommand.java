package ru.nordwest.nord.block;

import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import ru.nordwest.nord.common.tileentity.TileEntityGreatFurnace;

public class GreatFurnaceBlockCommand extends GreatFurnaceBlock {
        public GreatFurnaceBlockCommand() {
                setHardness(2.0F);
                setResistance(5.0F);
                setBlockName("GreatFurnaceBlock");
        }

        @Override
        public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side) {
                TileEntityGreatFurnace ent = (TileEntityGreatFurnace) block.getTileEntity(x, y, z);
                if (ent == null) {
                        return this.icons[7];
                }

                int meta = ent.textureCode & 0x000000FF;
                int commandBlockSide = (ent.textureCode & 0x0000FF00) >> 8;

                if (meta == 0) {
                        return this.icons[7];
                }

                if (meta != 28) {
                        return super.getIcon(block, x, y, z, side);
                }

                switch (side) {
                        case 0:
                                switch (commandBlockSide) {
                                        case 0:
                                                return this.icons[5];
                                        case 1:
                                                return this.icons[6];
                                        case 2:
                                                return this.icons[11];
                                        case 3:
                                                return this.icons[8];
                                }
                }

                if (commandBlockSide == 0 && side == 3)
                        return this.icons[1];

                if (commandBlockSide == 1 && side == 4)
                        return this.icons[1];

                if (commandBlockSide == 2 && side == 2)
                        return this.icons[1];

                if (commandBlockSide == 3 && side == 5)
                        return this.icons[1];

                return this.icons[0];
        }
}
