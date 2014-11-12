package ru.nordwest.nord.block;

import java.util.Random;

import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.tileentity.TileEntityGreatFurnace;
import ru.nordwest.nord.common.tileentity.TileEntityGreatFurnaceTech;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GreatFurnaceBlockTech extends BlockContainer {
	public GreatFurnaceBlockTech()
	{
		super(Material.rock);
		setHardness(2.0F);
        setResistance(5.0F);
        setBlockName("GreatFurnaceBlockTech");
        setBlockTextureName(Nord.MODID + ":greatFurnace/main_block");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGreatFurnaceTech();
	}
	
	public void dropItems(World world, int x, int y, int z) {
        Random rand = new Random();

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
                return;
        }
        
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack item = inventory.getStackInSlot(i);

                if (item != null && item.stackSize > 0) {
                        float rx = rand.nextFloat() * 0.8F + 0.1F;
                        float ry = rand.nextFloat() * 0.8F + 0.1F;
                        float rz = rand.nextFloat() * 0.8F + 0.1F;

                        EntityItem entityItem = new EntityItem(world,
                                        x + rx, y + ry, z + rz,
                                        item.copy());

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
