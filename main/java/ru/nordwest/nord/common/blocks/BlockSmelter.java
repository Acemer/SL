package ru.nordwest.nord.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.blocks.abstracts.BlockAbstractMachina;
import ru.nordwest.nord.common.tiles.TileSmelter2;

import java.util.Random;

public class BlockSmelter extends BlockAbstractMachina {

        public BlockSmelter() {
                super(Material.rock);
                setTickRandomly(true);
        }

        @SideOnly(Side.CLIENT)
        public void registerBlockIcons(IIconRegister iconRegister) {
                this.blockIcon = iconRegister.registerIcon(Nord.MODID + ":smelter/side");
                this.iconFront = iconRegister.registerIcon(Nord.MODID + ":smelter/front_off");
                this.iconFrontWork = iconRegister.registerIcon(Nord.MODID + ":smelter/front_on");
                this.iconTop = iconRegister.registerIcon(Nord.MODID + ":smelter/top");
                this.iconDown = iconRegister.registerIcon(Nord.MODID + ":smelter/top");

        }


        @Override
        public Item getItemDropped(int i, Random random, int j) {
                return Item.getItemFromBlock(Nord.smelter);
        }

        @Override
        public boolean onBlockActivated(World world, int x, int y, int z,
                                        EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
                if (!world.isRemote) {
                        TileEntity tileentity = world.getTileEntity(x, y, z);
                        if (tileentity != null) {
                                player.openGui(Nord.instance, Nord.guiIDSmelter, world, x, y, z);
                                return true;
                        }
                }
                return false;
        }

        @Override
        public TileEntity createNewTileEntity(World world, int i) {
                return new TileSmelter2();
        }

//    @Override
//	@SideOnly(Side.CLIENT)
//	public void randomDisplayTick(World world, int x, int y, int z,
//			Random random) {
//        TileEntitySmelter2 tile = (TileEntitySmelter2)world.getTileEntity(x, y, z);
//        this.isActive = tile !=null && tile.isWork();
//		if (this.isActive) {
//			int direction = world.getBlockMetadata(x, y, z);
//
//			float x1 = (float) x + 0.5F;
//			float y1 = (float) y + random.nextFloat();
//			float z1 = (float) z + 0.5F;
//
//			float f = 0.52F;
//			float f1 = random.nextFloat() * 0.6F - 0.3F;
//
//			if (direction == 4) {
//				world.spawnParticle("smoke", (double) (x1 - f), (double) (y1),
//						(double) (z1 + f1), 0D, 0D, 0D);
//			}
//
//			if (direction == 5) {
//				world.spawnParticle("smoke", (double) (x1 + f), (double) (y1),
//						(double) (z1 + f1), 0D, 0D, 0D);
//			}
//
//			if (direction == 2) {
//				world.spawnParticle("smoke", (double) (x1 + f1), (double) (y1),
//						(double) (z1 - f), 0D, 0D, 0D);
//			}
//
//			if (direction == 3) {
//				world.spawnParticle("smoke", (double) (x1 + f1), (double) (y1),
//						(double) (z1 + f), 0D, 0D, 0D);
//			}
//		}
//	}

        private static boolean keepInventory;

//    @Override
//	public static void updateBlockState(boolean active,
//			World worldObj, int xCoord, int yCoord, int zCoord) {
//		int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
//
//		TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
//		keepInventory = true;
//        System.err.println("test");
//		/**
//		 * if(active) { worldObj.setBlock(xCoord, yCoord, zCoord,
//		 * recipesmod.cuttingunitActive); }else{ worldObj.setBlock(xCoord,
//		 * yCoord, zCoord, recipesmod.cuttingunitIdle); }
//		 **/
//		keepInventory = false;
//
//		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
//
//		if (tileentity != null) {
//			tileentity.validate();
//			worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
//		}
//	}


        public Item getItem(World world, int x, int y, int z) {
                return Item.getItemFromBlock(Nord.smelter);
        }
}
