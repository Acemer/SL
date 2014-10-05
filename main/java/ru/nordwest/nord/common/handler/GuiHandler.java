package ru.nordwest.nord.common.handler;

import ru.nordwest.nord.Nord;
import ru.nordwest.nord.client.gui.GuiCrusher;
import ru.nordwest.nord.common.container.ContainerCrusher;
import ru.nordwest.nord.common.tileentity.TileEntitySmelter;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		return getServerGui(ID, player, world, x, y, z);
	}

	public Container getServerGui(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		switch (ID) {
			case Nord.guiIDSmelter :
				return new ContainerCrusher(player.inventory,
						(TileEntitySmelter) tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return getClientGui(ID, player, world, x, y, z);
	}

	public GuiScreen getClientGui(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		switch (ID) {
			case Nord.guiIDSmelter :
				return new GuiCrusher(player.inventory,
						(TileEntitySmelter) tileEntity, player);
		}
		return null;
	}

}
