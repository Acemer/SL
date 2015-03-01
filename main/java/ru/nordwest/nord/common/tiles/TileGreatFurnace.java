package ru.nordwest.nord.common.tiles;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.lib.utils.BlockCoordinates;

import java.util.HashSet;

public class TileGreatFurnace extends TileEntity {
        public BlockCoordinates techBlock;
        public int textureCode;

        private static int abs(int x) {
                if (x > 0) {
                        return x;
                }

                if (x < 0) {
                        return -x;
                }

                return 0;
        }

        public int isFurnaceBlock(World world, int x, int y, int z) {
                return world.getBlock(x, y, z) == Nord.greatFurnace ? 1 : 0;
        }

        public int isFurnaceBlock(World world, BlockCoordinates block) {
                return world.getBlock(block.x, block.y, block.z) == Nord.greatFurnace ? 1 : 0;
        }

        public boolean isValidStruct(World world, int x, int y, int z) {
                // Проверка блоков по сторонам
                int result = 0;
                result += isFurnaceBlock(world, x + 1, y, z);
                result += isFurnaceBlock(world, x - 1, y, z);
                result += isFurnaceBlock(world, x, y + 1, z);
                result += isFurnaceBlock(world, x, y - 1, z);
                result += isFurnaceBlock(world, x, y, z + 1);
                result += isFurnaceBlock(world, x, y, z - 1);

                if (result != 6) {
                        return false;
                }

                // Проверка блоков по углам
                result = 0;
                result += isFurnaceBlock(world, x + 1, y + 1, z + 1);
                result += isFurnaceBlock(world, x + 1, y + 1, z - 1);
                result += isFurnaceBlock(world, x + 1, y - 1, z + 1);
                result += isFurnaceBlock(world, x + 1, y - 1, z - 1);
                result += isFurnaceBlock(world, x - 1, y + 1, z + 1);
                result += isFurnaceBlock(world, x - 1, y + 1, z - 1);
                result += isFurnaceBlock(world, x - 1, y - 1, z + 1);
                result += isFurnaceBlock(world, x - 1, y - 1, z - 1);

                if (result != 8) {
                        return false;
                }

                result = 0;
                result += isFurnaceBlock(world, x + 1, y, z + 1);
                result += isFurnaceBlock(world, x + 1, y, z - 1);
                result += isFurnaceBlock(world, x - 1, y, z + 1);
                result += isFurnaceBlock(world, x - 1, y, z - 1);

                return result == 4;
        }

        public boolean isValidStruct(World world, BlockCoordinates block) {
                // Проверка блоков по сторонам
                int result = 0;
                result += isFurnaceBlock(world, block.x + 1, block.y, block.z);
                result += isFurnaceBlock(world, block.x - 1, block.y, block.z);
                result += isFurnaceBlock(world, block.x, block.y + 1, block.z);
                result += isFurnaceBlock(world, block.x, block.y - 1, block.z);
                result += isFurnaceBlock(world, block.x, block.y, block.z + 1);
                result += isFurnaceBlock(world, block.x, block.y, block.z - 1);

                if (result != 6) {
                        return false;
                }

                // Проверка блоков по углам
                result = 0;
                result += isFurnaceBlock(world, block.x + 1, block.y + 1, block.z + 1);
                result += isFurnaceBlock(world, block.x + 1, block.y + 1, block.z - 1);
                result += isFurnaceBlock(world, block.x + 1, block.y - 1, block.z + 1);
                result += isFurnaceBlock(world, block.x + 1, block.y - 1, block.z - 1);
                result += isFurnaceBlock(world, block.x - 1, block.y + 1, block.z + 1);
                result += isFurnaceBlock(world, block.x - 1, block.y + 1, block.z - 1);
                result += isFurnaceBlock(world, block.x - 1, block.y - 1, block.z + 1);
                result += isFurnaceBlock(world, block.x - 1, block.y - 1, block.z - 1);

                if (result != 8) {
                        return false;
                }

                result = 0;
                result += isFurnaceBlock(world, block.x + 1, block.y, block.z + 1);
                result += isFurnaceBlock(world, block.x + 1, block.y, block.z - 1);
                result += isFurnaceBlock(world, block.x - 1, block.y, block.z + 1);
                result += isFurnaceBlock(world, block.x - 1, block.y, block.z - 1);

                if (result != 4) {
                        return false;
                }

                result = 0;
                result += isFurnaceBlock(world, block.x + 1, block.y + 1, block.z);
                result += isFurnaceBlock(world, block.x - 1, block.y + 1, block.z);
                result += isFurnaceBlock(world, block.x, block.y + 1, block.z + 1);
                result += isFurnaceBlock(world, block.x, block.y + 1, block.z - 1);
                result += isFurnaceBlock(world, block.x + 1, block.y - 1, block.z);
                result += isFurnaceBlock(world, block.x - 1, block.y - 1, block.z);
                result += isFurnaceBlock(world, block.x, block.y - 1, block.z + 1);
                result += isFurnaceBlock(world, block.x, block.y - 1, block.z - 1);
                // TOTAL: 26
                return result == 8;
        }

        public BlockCoordinates recFindTechBlock(World world, BlockCoordinates start, BlockCoordinates cur, HashSet path) {
                if (abs(cur.x - start.x) > 1 || abs(cur.y - start.y) > 1 || abs(cur.z - start.z) > 1) {
                        return null;
                }

                if (isValidStruct(world, cur)) {
                        return cur;
                }

                BlockCoordinates ret = null;

                BlockCoordinates tmp = new BlockCoordinates(cur.x + 1, cur.y, cur.z);
                if (isFurnaceBlock(world, tmp) == 1 && !path.contains(tmp)) {
                        path.add(tmp);
                        ret = recFindTechBlock(world, start, tmp, path);
                        if (ret != null) {
                                return ret;
                        }
                }

                tmp = new BlockCoordinates(cur.x - 1, cur.y, cur.z);
                if (isFurnaceBlock(world, tmp) == 1 && !path.contains(tmp)) {
                        path.add(tmp);
                        ret = recFindTechBlock(world, start, tmp, path);
                        if (ret != null) {
                                return ret;
                        }
                }

                tmp = new BlockCoordinates(cur.x, cur.y + 1, cur.z);
                if (isFurnaceBlock(world, tmp) == 1 && !path.contains(tmp)) {
                        path.add(tmp);
                        ret = recFindTechBlock(world, start, tmp, path);
                        if (ret != null) {
                                return ret;
                        }
                }

                tmp = new BlockCoordinates(cur.x, cur.y - 1, cur.z);
                if (isFurnaceBlock(world, tmp) == 1 && !path.contains(tmp)) {
                        path.add(tmp);
                        ret = recFindTechBlock(world, start, tmp, path);
                        if (ret != null) {
                                return ret;
                        }
                }

                tmp = new BlockCoordinates(cur.x - 1, cur.y, cur.z + 1);
                if (isFurnaceBlock(world, tmp) == 1 && !path.contains(tmp)) {
                        path.add(tmp);
                        ret = recFindTechBlock(world, start, tmp, path);
                        if (ret != null) {
                                return ret;
                        }
                }

                tmp = new BlockCoordinates(cur.x - 1, cur.y, cur.z - 1);
                if (isFurnaceBlock(world, tmp) == 1 && !path.contains(tmp)) {
                        path.add(tmp);
                        ret = recFindTechBlock(world, start, tmp, path);
                        if (ret != null) {
                                return ret;
                        }
                }

                return null;
        }

        /**
         * Проверить, правильно ли построена печка, и если да -
         * создать в центре технический блок, TileEntity которого
         * будет выполнять всю работу печки.
         *
         * @return false: блок не создан, печка построена неверно
         */
        public boolean tryToCreateTechBlock(World world, int xp, int yp, int zp, int side) {
                HashSet<BlockCoordinates> path = new HashSet();
                BlockCoordinates cur = new BlockCoordinates(xp, yp, zp);
                path.add(cur);

                BlockCoordinates find = recFindTechBlock(world, cur, cur, path);
                if (find != null) {
                        this.techBlock = new BlockCoordinates(find.x, find.y, find.z);
                        Block block = world.getBlock(find.x, find.y, find.z);
                        world.removeTileEntity(find.x, find.y, find.z);

                        // Передняя сторона
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y - 1, this.techBlock.z + 1)).textureCode = 1;
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x, this.techBlock.y - 1, this.techBlock.z + 1)).textureCode =
                                (side == 0 ? (0 << 8) | (28) : 2);
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y - 1, this.techBlock.z + 1)).textureCode = 3;

                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y, this.techBlock.z + 1)).textureCode = 4;
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x, this.techBlock.y, this.techBlock.z + 1)).textureCode = 5;
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y, this.techBlock.z + 1)).textureCode = 6;

                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y + 1, this.techBlock.z + 1)).textureCode = 7;
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x, this.techBlock.y + 1, this.techBlock.z + 1)).textureCode = 8;
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y + 1, this.techBlock.z + 1)).textureCode = 9;


                        // Центральная сторона
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y - 1, this.techBlock.z)).textureCode =
                                (side == 1 ? (1 << 8) | (28) : 10);
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x, this.techBlock.y - 1, this.techBlock.z)).textureCode = 11;
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y - 1, this.techBlock.z)).textureCode =
                                (side == 3 ? (3 << 8) | (28) : 12);

                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y, this.techBlock.z)).textureCode = 13;
                        //((TileEntityGreatFurnace) world.getTileEntity(this.techBlock.x    , this.techBlock.y    , this.techBlock.z    )).textureCode = 14; // Технический блок
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y, this.techBlock.z)).textureCode = 15;

                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y + 1, this.techBlock.z)).textureCode = 16;
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x, this.techBlock.y + 1, this.techBlock.z)).textureCode = 17;
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y + 1, this.techBlock.z)).textureCode = 18;


                        // Заднаяя сторона
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y - 1, this.techBlock.z - 1)).textureCode = 19;
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x, this.techBlock.y - 1, this.techBlock.z - 1)).textureCode =
                                (side == 2 ? (2 << 8) | (28) : 20);
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y - 1, this.techBlock.z - 1)).textureCode = 21;

                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y, this.techBlock.z - 1)).textureCode = 22;
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x, this.techBlock.y, this.techBlock.z - 1)).textureCode = 23;
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y, this.techBlock.z - 1)).textureCode = 24;

                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x - 1, this.techBlock.y + 1, this.techBlock.z - 1)).textureCode = 25;
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x, this.techBlock.y + 1, this.techBlock.z - 1)).textureCode = 26;
                        ((TileGreatFurnace) world.getTileEntity(this.techBlock.x + 1, this.techBlock.y + 1, this.techBlock.z - 1)).textureCode = 27;

                        return world.setBlock(find.x, find.y, find.z, Nord.greatFurnaceTech);
                }

                return false;
        }

        public void setTechBlock(BlockCoordinates techBlock) {
                this.techBlock = techBlock;
                if (techBlock == null) // Печь разрушена
                {
                        this.textureCode = 0;
                }
        }

        /**
         * Установить заданный techBlock всем TileEntity печки.
         */
        public static void broadcastSetTechBlock(World world, BlockCoordinates center, BlockCoordinates newTechBlock) {
                TileEntity ent;

                // Углы
                ent = world.getTileEntity(center.x + 1, center.y + 1, center.z + 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x + 1, center.y + 1, center.z - 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x + 1, center.y - 1, center.z + 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x + 1, center.y - 1, center.z - 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x - 1, center.y + 1, center.z + 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x - 1, center.y + 1, center.z - 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x - 1, center.y - 1, center.z + 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x - 1, center.y - 1, center.z - 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                // Стороны
                ent = world.getTileEntity(center.x + 1, center.y, center.z);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x - 1, center.y, center.z);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x, center.y + 1, center.z);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x, center.y - 1, center.z);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x, center.y, center.z + 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x, center.y, center.z - 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                // Какие-то еще центры, не знаю, как их назвать :D
                ent = world.getTileEntity(center.x + 1, center.y, center.z + 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x + 1, center.y, center.z - 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x - 1, center.y, center.z + 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x - 1, center.y, center.z - 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x + 1, center.y + 1, center.z);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x - 1, center.y + 1, center.z);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x + 1, center.y - 1, center.z);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x - 1, center.y - 1, center.z);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x, center.y + 1, center.z + 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x, center.y + 1, center.z - 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x, center.y - 1, center.z + 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);

                ent = world.getTileEntity(center.x, center.y - 1, center.z - 1);
                if (ent != null)
                        ((TileGreatFurnace) ent).setTechBlock(newTechBlock);
        }

        public boolean onBlockActivated(World world, int x, int y, int z,
                                        EntityPlayer player, int metadata, float what, float these, float are) {
                if (this.techBlock == null) {
                        int look = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

                        int side = 0;
                        switch (look) {
                                case 2:
                                        side = 0;
                                        break;
                                case 3:
                                        side = 1;
                                        break;
                                case 0:
                                        side = 2;
                                        break;
                                case 1:
                                        side = 3;
                                        break;
                        }

                        if (!tryToCreateTechBlock(world, x, y, z, side)) {
                                return false;
                        }
                }

                TileEntity ent = world.getTileEntity(this.techBlock.x, this.techBlock.y, this.techBlock.z);
                if (ent == null) {
                        return false;
                }

                broadcastSetTechBlock(world, this.techBlock, this.techBlock);

                return ((TileGreatFurnaceTech) ent).clicked(player, world, this.techBlock.x, this.techBlock.y, this.techBlock.z);
        }

        public void removeTechBlock(World world) {
                if (this.techBlock == null) {
                        return;
                }

                if (this.techBlock != null) {
                        world.removeTileEntity(this.techBlock.x, this.techBlock.y, this.techBlock.z);
                        world.setBlock(this.techBlock.x, this.techBlock.y, this.techBlock.z, Nord.greatFurnace);

                        broadcastSetTechBlock(world, this.techBlock, null);
                }
        }

        @Override
        public void writeToNBT(NBTTagCompound tag) {
                super.writeToNBT(tag);
                this.writeSyncableDataToNBT(tag);
        }

        void writeSyncableDataToNBT(NBTTagCompound tag) {
                tag.setShort("textCode", (short) textureCode);
                if (techBlock != null) {
                        tag.setInteger("tech_x", techBlock.x);
                        tag.setShort("tech_y", (short) techBlock.y);
                        tag.setInteger("tech_z", techBlock.z);
                }
        }

        @Override
        public void readFromNBT(NBTTagCompound tag) {
                super.readFromNBT(tag);
                this.readSyncableDataFromNBT(tag);
        }

        @Override
        public Packet getDescriptionPacket() {
                NBTTagCompound syncData = new NBTTagCompound();
                this.writeSyncableDataToNBT(syncData);
                return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
        }

        @Override
        public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
                readSyncableDataFromNBT(pkt.func_148857_g());
        }

        public void readSyncableDataFromNBT(NBTTagCompound tag) {
                textureCode = tag.getInteger("textCode");

                if (tag.hasKey("tech_x")) {
                        techBlock = new BlockCoordinates(tag.getInteger("tech_x"), (int) tag.getShort("tech_y"), tag.getInteger("tech_z"));
                }
        }
}
