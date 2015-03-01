package ru.nordwest.nord.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.lib.utils.BlockCoordinates;
import ru.nordwest.nord.common.tiles.TileGreatFurnace;
import ru.nordwest.nord.common.tiles.TileGreatFurnaceTech;

import java.util.Random;

public class BlockGreatFurnaceTech extends BlockContainer {
        public BlockGreatFurnaceTech() {
                super(Material.rock);
                setHardness(2.0F);
                setResistance(5.0F);
                setBlockName("GreatFurnaceBlockTech");
                setBlockTextureName(Nord.MODID + ":greatFurnace/main_block");
        }

        @Override
        public TileEntity createNewTileEntity(World world, int meta) {
                return new TileGreatFurnaceTech();
        }

        public void dropItems(World world, TileGreatFurnace ent) {
                Random rand = new Random();
                int x = ent.xCoord;
                int y = ent.yCoord;
                int z = ent.zCoord;

                if (ent == null || ent.techBlock == null) {
                        return;
                }

                BlockCoordinates techBlock = ent.techBlock;

                IInventory inventory = (IInventory) (world.getTileEntity(techBlock.x, techBlock.y, techBlock.z));

                for (int i = 0; i < inventory.getSizeInventory(); i++) {
                        ItemStack item = inventory.getStackInSlot(i);

                        if (item != null && item.stackSize > 0) {
                                float rx = rand.nextFloat() * 0.8F + 0.1F;
                                float ry = rand.nextFloat() * 0.8F + 0.1F;
                                float rz = rand.nextFloat() * 0.8F + 0.1F;

                                EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, item.copy());

                                if (item.hasTagCompound()) {
                                        entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                                }

                                float factor = 0.05F;
                                entityItem.motionX = rand.nextGaussian() * factor;
                                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                                entityItem.motionZ = rand.nextGaussian() * factor;
                                world.spawnEntityInWorld(entityItem);
                                item.stackSize = 0;
                        }
                }
        }
}
