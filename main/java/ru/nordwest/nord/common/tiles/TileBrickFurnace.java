package ru.nordwest.nord.common.tiles;


import net.minecraft.init.Blocks;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.lib.recipes.GeatFurnaceRecipes1I2O;
import ru.nordwest.nord.common.lib.recipes.Interfaces.IRecipes1I2O;
import ru.nordwest.nord.common.lib.utils.BlockAndMetadata;
import ru.nordwest.nord.common.tiles.abstracts.TileAbstractMultiBlockEnergyMachina;

public class TileBrickFurnace extends TileAbstractMultiBlockEnergyMachina {

    private final BlockAndMetadata b =  new BlockAndMetadata(Blocks.brick_block);
    private final BlockAndMetadata e =  new BlockAndMetadata(Blocks.air);
    private final BlockAndMetadata c =  new BlockAndMetadata(Nord.brickFurnace,-1);

    private final char[][][] structure = new char[][][]{ //Y,X,Z
            {{'b','b','b'},{'b','b','b'},{'b','b','b'}},
            {{'b','b','b'},{'b','e','b'},{'b','b','b'}},
            {{'b','b','b'},{'b','b','b'},{'b','b','b'}},
    };

    @Override
    public char[][][] getStructure() {
        return structure;
    }

    @Override
    public BlockAndMetadata getAliass(char ch) {
        switch (ch){
            case 'b' : return b;
            case 'e' : return e;
            default  : return e;
        }
    }

    @Override
    public BlockAndMetadata getController(char ch) {
        return c;
    }

    @Override
    public int getOffsetY(int meta) {
        return 0;
    }
    @Override
    public int getOffsetX(int meta) {
        switch (meta){
            case 2:return 1;
            case 3:return 1;
            case 4:return 0;
            case 5:return 2;
        }
        return 0;
    }
    @Override
    public int getOffsetZ(int meta) {
        switch (meta){
            case 2:return 0;
            case 3:return 2;
            case 4:return 1;
            case 5:return 1;
        }
        return 1;
    }

    @Override
    public String getInventoryName() {
        return "Great Furnace";
    }

    @Override
    public int getMaxEnergy() {
        return 128000;
    }
    @Override
    public IRecipes1I2O getRecipes() {
        return GeatFurnaceRecipes1I2O.INSTANCE();
    }
    @Override
    public void updateEntity(){
        if (checkStructure(this.getWorldObj(),this.xCoord,this.yCoord,this.zCoord)){
            super.updateEntity();
        }
    }
}