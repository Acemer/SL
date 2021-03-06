package ru.nordwest.nord.common.lib.events;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.client.gui.*;
import ru.nordwest.nord.common.container.*;
import ru.nordwest.nord.common.tiles.TileBrickFurnace;
import ru.nordwest.nord.common.tiles.TileFlowing;
import ru.nordwest.nord.common.tiles.TileGreatFurnaceTech;
import ru.nordwest.nord.common.tiles.TileSmelter;

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
                                return new ContainerSmelter(player.inventory,
                                        (TileSmelter) tileEntity);

                        case Nord.guiIDBrickFurnace:
                                return new ContainerBrickFurnace(player.inventory,
                                        (TileBrickFurnace) tileEntity);

                        case Nord.guiIDFlowing:
                                return new ContainerFlowing(player.inventory,
                                        (TileFlowing) tileEntity);

                        case Nord.guiIDGreatFurnace:
                                return new ContainerGreatFurnace(player.inventory,
                                        (TileGreatFurnaceTech) tileEntity);
                        case Nord.guiIDBag:
                                return new ContainerBag(player, player.inventory, new InventoryBag(player.getHeldItem()));
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
                                return new GuiSmelter(player.inventory,
                                        (TileSmelter) tileEntity, player);

                        case Nord.guiIDBrickFurnace:
                                return new GuiBrickFurnace(player.inventory,
                                        (TileBrickFurnace) tileEntity, player);

                        case Nord.guiIDFlowing:
                                return new GuiFlowing(player.inventory,
                                        (TileFlowing) tileEntity, player);

                        case Nord.guiIDGreatFurnace:
                                return new GuiGreatFurnace(player.inventory, (TileGreatFurnaceTech) tileEntity, player);
                        case Nord.guiIDBag:
                                return new GuiBag((ContainerBag) new ContainerBag(player, player.inventory, new InventoryBag(player.getHeldItem())));
                }
                return null;
        }

}
