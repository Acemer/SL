package ru.nordwest.nord.common.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.client.gui.GuiBrickFurnace;
import ru.nordwest.nord.client.gui.GuiFlowing;
import ru.nordwest.nord.client.gui.GuiGreatFurnace;
import ru.nordwest.nord.client.gui.GuiSmelter2;
import ru.nordwest.nord.common.container.ContainerBrickFurnace;
import ru.nordwest.nord.common.container.ContainerFlowing;
import ru.nordwest.nord.common.container.ContainerGreatFurnace;
import ru.nordwest.nord.common.container.ContainerSmelter2;
import ru.nordwest.nord.common.tileentity.TileEntityBrickFurnace;
import ru.nordwest.nord.common.tileentity.TileEntityFlowing;
import ru.nordwest.nord.common.tileentity.TileEntityGreatFurnaceTech;
import ru.nordwest.nord.common.tileentity.TileEntitySmelter2;

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
                        case Nord.guiIDSmelter:
                                return new ContainerSmelter2(player.inventory,
                                        (TileEntitySmelter2) tileEntity);

                        case Nord.guiIDBrickFurnace:
                                return new ContainerBrickFurnace(player.inventory,
                                        (TileEntityBrickFurnace) tileEntity);

                        case Nord.guiIDFlowing:
                                return new ContainerFlowing(player.inventory,
                                        (TileEntityFlowing) tileEntity);

                        case Nord.guiIDGreatFurnace:
                                return new ContainerGreatFurnace(player.inventory,
                                        (TileEntityGreatFurnaceTech) tileEntity);
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
                        case Nord.guiIDSmelter:
                                return new GuiSmelter2(player.inventory,
                                        (TileEntitySmelter2) tileEntity, player);

                        case Nord.guiIDBrickFurnace:
                                return new GuiBrickFurnace(player.inventory,
                                        (TileEntityBrickFurnace) tileEntity, player);

                        case Nord.guiIDFlowing:
                                return new GuiFlowing(player.inventory,
                                        (TileEntityFlowing) tileEntity, player);

                        case Nord.guiIDGreatFurnace:
                                return new GuiGreatFurnace(player.inventory, (TileEntityGreatFurnaceTech) tileEntity, player);
                }
                return null;
        }

}
