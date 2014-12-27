package ru.nordwest.nord.common.container;

import ru.nordwest.nord.common.container.abstracts.AbstactMachineConteiner;
import ru.nordwest.nord.common.tileentity.TileEntityFlowing;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerFlowing extends AbstactMachineConteiner {

	
	public ContainerFlowing(InventoryPlayer invPlayer, TileEntityFlowing ent)
	{
		init(invPlayer,ent);
	}

}
