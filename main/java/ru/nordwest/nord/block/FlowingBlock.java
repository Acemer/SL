package ru.nordwest.nord.block;

import java.util.Random;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.tileentity.TileEntityFlowing;
import ru.nordwest.nord.common.tileentity.TileEntityGreatFurnace;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FlowingBlock extends BlockContainer {
	public IIcon[] icons = new IIcon[11];
	
	public FlowingBlock() {
		super(Material.rock);
		setHardness(2.0F);
        setResistance(5.0F);
        setBlockName("Flowing");
        setBlockTextureName(Nord.MODID + ":flowing/hogger_down");
        setCreativeTab(Nord.tabBase);

	}

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
                    EntityPlayer player, int metadata, float what, float these, float are) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking()) {
                return false;
        }

        player.openGui(Nord.instance, Nord.guiIDFlowing, world, x, y, z);
        return true;
	}
    
    private void dropItems(World world, int x, int y, int z) {
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
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
            dropItems(world, x, y, z);
            super.breakBlock(world, x, y, z, par5, par6);
    }
    
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityFlowing();
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		this.icons[0] = reg.registerIcon(Nord.MODID + ":flowing/hogger_down");
		this.icons[1] = reg.registerIcon(Nord.MODID + ":flowing/hogger_side_1");
		this.icons[2] = reg.registerIcon(Nord.MODID + ":flowing/hogger_side_2");
		this.icons[3] = reg.registerIcon(Nord.MODID + ":flowing/hogger_side_3");
		this.icons[4] = reg.registerIcon(Nord.MODID + ":flowing/hogger_side_4");
		this.icons[5] = reg.registerIcon(Nord.MODID + ":flowing/hogger_side");
		this.icons[6] = reg.registerIcon(Nord.MODID + ":flowing/hogger_up_1");
		this.icons[7] = reg.registerIcon(Nord.MODID + ":flowing/hogger_up_2");
		this.icons[8] = reg.registerIcon(Nord.MODID + ":flowing/hogger_up_3");
		this.icons[9] = reg.registerIcon(Nord.MODID + ":flowing/hogger_up_4");
		this.icons[10] = reg.registerIcon(Nord.MODID + ":flowing/hogger_up");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		switch (side)
		{
		case 0:
			return this.icons[0];
		case 1:
			return this.icons[10];
		case 3:
			return this.icons[1];
		}
		
		return this.icons[5];
	}

	/*
	 * TODO Сделать второй технический блок с текстурой включенного дробителя
	 */
	@Override
	public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side)
	{
		return getIcon(side, 0);
	}

}
