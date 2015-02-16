package ru.nordwest.nord.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import ru.nordwest.nord.common.container.abstracts.AbstactMachineConteiner;
import ru.nordwest.nord.common.tileentity.TileEntityFlowing;

public class ContainerFlowing extends AbstactMachineConteiner {


        public ContainerFlowing(InventoryPlayer invPlayer, TileEntityFlowing ent) {
                init(invPlayer, ent);
        }

}
