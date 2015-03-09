package ru.nordwest.nord.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import ru.nordwest.nord.common.container.abstracts.ContainerAbstactMachine;
import ru.nordwest.nord.common.tiles.TileBrickFurnace;

public class ContainerBrickFurnace extends ContainerAbstactMachine {
    public ContainerBrickFurnace(InventoryPlayer invPlayer, TileBrickFurnace ent) {
        init(invPlayer, ent);
    }

}