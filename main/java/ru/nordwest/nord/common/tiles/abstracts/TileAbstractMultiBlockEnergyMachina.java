package ru.nordwest.nord.common.tiles.abstracts;

import net.minecraft.world.World;
import ru.nordwest.nord.common.tiles.interfaces.IStructure;
import ru.nordwest.nord.common.lib.utils.BlockAndMetadata;

abstract public class TileAbstractMultiBlockEnergyMachina extends TileAbstractEnergyMachina
        implements IStructure{
    public int getPatternHeight() {
        return getStructure().length;
    }
    public int getPatternWidthX() {
        return getStructure()[0].length;
    }
    public int getPatternWidthZ() {
        return getStructure()[0][0].length;
    }

        protected boolean checkStructure(World world, int xCoord, int yCoord, int zCoord){
            char[][][] structure = getStructure();
            int meta = world.getBlockMetadata(xCoord,yCoord,zCoord);
            int xWidth = getPatternWidthX();
            int zWidth = getPatternWidthZ();
            int height = getPatternHeight();
            int xOffset = xCoord - getOffsetX(meta);
            int yOffset = yCoord - getOffsetY(meta);
            int zOffset = zCoord - getOffsetZ(meta);
            for (byte px = 0; px < xWidth; px++) {
                for (byte py = 0; py < height; py++) {
                    for (byte pz = 0; pz < zWidth; pz++) {
                        int x = px + xOffset;
                        int y = py + yOffset;
                        int z = pz + zOffset;
                        BlockAndMetadata block ;
                            block = ( x==xCoord && y==yCoord && z==zCoord )     ?
                                            getController(structure[px][py][pz]):
                                            getAliass(structure[px][py][pz]);

                            if (world.getBlock(x,y,z) !=block.getBlock()
                                    || (world.getBlockMetadata(x,y,z) != block.getMetadata()
                                    && block.getMetadata()!=-1)){
//                                System.err.println((px-getOffsetX(meta))+" "
//                                                +(py-getOffsetY(meta))+" "
//                                                +(pz-getOffsetZ(meta))+" "
//                                                + world.getBlock(x,y,z).getLocalizedName()
//                                                +"("+block.getBlock().getLocalizedName()+") "
//                                                + world.getBlockMetadata(x,y,z)
//                                                + "("+block.getMetadata()+")");
                                return false;
                            }
                    }
                }
            }
         return false;
        }
}
