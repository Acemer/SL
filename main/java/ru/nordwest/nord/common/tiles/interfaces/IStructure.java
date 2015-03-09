package ru.nordwest.nord.common.tiles.interfaces;

import ru.nordwest.nord.common.lib.utils.BlockAndMetadata;

/**
 * Created by andrew on 09.03.15.
 */
public interface IStructure {
    /**
     * Y,X,Z
     * @return char[][]
     */
    char[][][] getStructure();

    BlockAndMetadata getAliass(char ch );
    BlockAndMetadata getController(char ch );

    /**
     * Относительное положение контролера
     * @return
     */
    int getOffsetX(int meta);
    int getOffsetY(int meta);
    int getOffsetZ(int meta);

}
