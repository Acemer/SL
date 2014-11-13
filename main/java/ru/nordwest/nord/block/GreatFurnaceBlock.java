package ru.nordwest.nord.block;

import java.util.List;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.tileentity.TileEntityGreatFurnace;
import ru.nordwest.nord.common.tileentity.TileEntityGreatFurnaceTech;
import ru.nordwest.nord.util.BlockCoord;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class GreatFurnaceBlock extends BlockContainer {
	TileEntityGreatFurnace tileEntity;
	public IIcon[] icons = new IIcon[12];
	
	public GreatFurnaceBlock() {
		super(Material.rock);
		setHardness(2.0F);
        setResistance(5.0F);
        setBlockName("GreatFurnaceBlock");
        setBlockTextureName(Nord.MODID + ":greatFurnace/main_block");
        setCreativeTab(CreativeTabs.tabRedstone);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
	    this.icons[0] = reg.registerIcon(Nord.MODID + ":greatFurnace/center");
	    this.icons[1] = reg.registerIcon(Nord.MODID + ":greatFurnace/command_block_on");
	    this.icons[2] = reg.registerIcon(Nord.MODID + ":greatFurnace/command_block_off");
	    this.icons[3] = reg.registerIcon(Nord.MODID + ":greatFurnace/down_left");
	    this.icons[4] = reg.registerIcon(Nord.MODID + ":greatFurnace/down_right");
	    this.icons[5] = reg.registerIcon(Nord.MODID + ":greatFurnace/down");
	    this.icons[6] = reg.registerIcon(Nord.MODID + ":greatFurnace/left");
	    this.icons[7] = reg.registerIcon(Nord.MODID + ":greatFurnace/main_block");
	    this.icons[8] = reg.registerIcon(Nord.MODID + ":greatFurnace/right");
	    this.icons[9] = reg.registerIcon(Nord.MODID + ":greatFurnace/up_left");
	    this.icons[10] = reg.registerIcon(Nord.MODID + ":greatFurnace/up_right");
	    this.icons[11] = reg.registerIcon(Nord.MODID + ":greatFurnace/up"); 
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return this.icons[7];
	}
	
	@Override
	public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side)
	{
		TileEntityGreatFurnace ent = (TileEntityGreatFurnace)block.getTileEntity(x, y, z);
		if (ent == null)
		{
			return this.icons[7];
		}
		
		int meta = ent.textureCode & 0x000000FF;
		int commandBlockSide = (ent.textureCode & 0x0000FF00) >> 8;
		
		if (meta == 0)
		{
			return this.icons[7];
		}
		
		// Передняя сторона
	    switch (meta)
	    {
	    case 1:          // Левый нижний блок
	    	switch(side)
	    	{
	    	case 0:
	    		return this.icons[3];
	    	case 3:
	    		return this.icons[3];
	    	case 4:
	    		return this.icons[4];
	    	}
	    	break;
	    	
	    case 2:        // Нижний блок
	    	switch(side)
	    	{
	    	case 0:
	    		return this.icons[5];
	    	case 3:
	    		return this.icons[5];
	    	}
	    	break;
	    	
	    case 3:     // Правый нижний блок
	    	switch(side)
	    	{
	    	case 0:
	    		return this.icons[4];
	    	case 3:
	    		return this.icons[4];
	    	case 4:
	    		return this.icons[0];
	    	case 5:
	    		return this.icons[3];
	    	}
	    	break;
	    	
	    case 4:     // Левый блок
	    	switch(side)
	    	{
	    	case 3:
	    		return this.icons[6];
	    	case 4:
	    		return this.icons[8];
	    	}
	    	break;

	    case 6:     // Правый блок
	    	switch(side)
	    	{
	    	case 3:
	    		return this.icons[8];
	    	case 5:
	    		return this.icons[6];
	    	}
	    	break;
	    	
	    case 7:     // Левый верхний блок
	    	switch(side)
	    	{
	    	case 0:
	    		return this.icons[3];
	    	case 1:
	    		return this.icons[3];
	    	case 3:
	    		return this.icons[9];
	    	case 4:
	    		return this.icons[10];
	    	}
	    	break;
	    	
	    case 8:     // Верхний блок
	    	switch(side)
	    	{
	    	case 1:
	    		return this.icons[5];
	    	case 3:
	    		return this.icons[11];
	    	}
	    	break;
	    	
	    case 9:     // Правый верхний блок
	    	switch(side)
	    	{
	    	case 1:
	    		return this.icons[4];
	    	case 3:
	    		return this.icons[10];
	    	case 5:
	    		return this.icons[9];
	    	}
	    	break;
	    }
	    
	    // Цетральная сторона
	    switch (meta)
	    {
	    case 10: // Левый нижний блок
	    	switch(side)
	    	{
	    	case 0:
	    		return this.icons[6];
	    	case 4:
	    		return this.icons[5];
	    	}
	    	break;
	    	
	    	// Правый нижний блок
	    case 12:
	    	switch(side)
	    	{
	    	case 0:
	    		return this.icons[8];
	    	case 5:
	    		return this.icons[5];
	    	}
	    	break;
	    	
	    case 16: // Левый верхний блок
	    	switch(side)
	    	{
	    	case 1:
	    		return this.icons[6];
	    	case 4:
	    		return this.icons[11];
	    	}
	    	break;
	    	
	    case 18: // Правый верхний блок
	    	switch(side)
	    	{
	    	case 1:
	    		return this.icons[8];
	    	case 5:
	    		return this.icons[11];
	    	}
	    	break;
	    	
	    }
	    
		// Задняя сторона
	    switch (meta)
	    {
	    case 19:          // Левый нижний блок
	    	switch(side)
	    	{
	    	case 0:
	    		return this.icons[9];
	    	case 2:
	    		return this.icons[4];
	    	case 4:
	    		return this.icons[3];
	    	}
	    	break;
	    	
	    case 20:        // Нижний блок
	    	switch(side)
	    	{
	    	case 0:
	    		return this.icons[11];
	    	case 2:
	    		return this.icons[5];
	    	}
	    	break;
	    	
	    case 21:     // Правый нижний блок
	    	switch(side)
	    	{
	    	case 0:
	    		return this.icons[10];
	    	case 2:
	    		return this.icons[3];
	    	case 5:
	    		return this.icons[4];
	    	}
	    	break;
	    	
	    case 22:     // Левый блок
	    	switch(side)
	    	{
	    	case 2:
	    		return this.icons[8];
	    	case 4:
	    		return this.icons[6];
	    	}
	    	break;
	    	
	    case 24:     // Правый блок
	    	switch(side)
	    	{
	    	case 2:
	    		return this.icons[6];
	    	case 5:
	    		return this.icons[8];
	    	}
	    	break;
	    	
	    case 25:     // Левый верхний блок
	    	switch(side)
	    	{
	    	case 0:
	    		return this.icons[0];
	    	case 1:
	    		return this.icons[9];
	    	case 2:
	    		return this.icons[10];
	    	case 4:
	    		return this.icons[9];
	    	}
	    	break;
	    	
	    case 26:     // Верхний блок
	    	switch(side)
	    	{
	    	case 1:
	    	case 2:
	    		return this.icons[11];
	    	}
	    	break;
	    	
	    case 27:     // Правый верхний блок
	    	switch(side)
	    	{
	    	case 1:
	    		return this.icons[10];
	    	case 2:
	    		return this.icons[9];
	    	case 5:
	    		return this.icons[10];
	    	}
	    	break;
	    	
	    case 28:    // Командный блок
	    	switch (side)
	    	{
	    	case 0:
	    		switch (commandBlockSide)
	    		{
	    		case 0:
	    			return this.icons[5];
	    		case 1:
	    			return this.icons[6];
	    		case 2:
	    			return this.icons[11];
	    		case 3:
	    			return this.icons[8];
	    		}
	    	}
	    	
	    	if (commandBlockSide == 0 && side == 3)
	    		return this.icons[2];
	    	
	    	if (commandBlockSide == 1 && side == 4)
	    		return this.icons[2];
	    	
	    	if (commandBlockSide == 2 && side == 2)
	    		return this.icons[2];
	    	
	    	if (commandBlockSide == 3 && side == 5)
	    		return this.icons[2];
	    }
	    
	    return this.icons[0];
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int metadata, float what, float these, float are)
	{
		TileEntityGreatFurnace tileEntity = (TileEntityGreatFurnace) world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking())
		{
			return false;
		}
		
		return tileEntity.onBlockActivated(world, x, y, z, player, metadata, what, these, are);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityGreatFurnace();
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		boolean ret = true;
		
		TileEntity ent = world.getTileEntity(x + 1, y, z);
		if (ent instanceof TileEntityGreatFurnaceTech)
		{
			return false;
		}
		if (ent instanceof TileEntityGreatFurnace && ((TileEntityGreatFurnace)ent).techBlock != null)
		{
			return false;
		}
		
		ent = world.getTileEntity(x - 1, y, z);
		if (ent instanceof TileEntityGreatFurnaceTech)
		{
			return false;
		}
		if (ent instanceof TileEntityGreatFurnace && ((TileEntityGreatFurnace)ent).techBlock != null)
		{
			return false;
		}
		
		ent = world.getTileEntity(x, y + 1, z);
		if (ent instanceof TileEntityGreatFurnaceTech)
		{
			return false;
		}
		if (ent instanceof TileEntityGreatFurnace && ((TileEntityGreatFurnace)ent).techBlock != null)
		{
			return false;
		}
		
		ent = world.getTileEntity(x, y - 1, z);
		if (ent instanceof TileEntityGreatFurnaceTech)
		{
			return false;
		}
		if (ent instanceof TileEntityGreatFurnace && ((TileEntityGreatFurnace)ent).techBlock != null)
		{
			return false;
		}
		
		ent = world.getTileEntity(x, y, z + 1);
		if (ent instanceof TileEntityGreatFurnaceTech)
		{
			return false;
		}
		if (ent instanceof TileEntityGreatFurnace && ((TileEntityGreatFurnace)ent).techBlock != null)
		{
			return false;
		}
		
		ent = world.getTileEntity(x, y, z - 1);
		if (ent instanceof TileEntityGreatFurnaceTech)
		{
			return false;
		}
		if (ent instanceof TileEntityGreatFurnace && ((TileEntityGreatFurnace)ent).techBlock != null)
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		TileEntity ent = world.getTileEntity(x + 1, y, z);
		if (ent instanceof TileEntityGreatFurnace)
		{
			((TileEntityGreatFurnace)ent).removeTechBlock(world);
			super.onBlockDestroyedByPlayer(world, x, y, z, meta);
			return;
		}
		
		ent =  world.getTileEntity(x - 1, y, z);
		if (ent instanceof TileEntityGreatFurnace)
		{
			((TileEntityGreatFurnace)ent).removeTechBlock(world);
			super.onBlockDestroyedByPlayer(world, x, y, z, meta);
			return;
		}
		
		ent =  world.getTileEntity(x, y + 1, z);
		if (ent instanceof TileEntityGreatFurnace)
		{
			((TileEntityGreatFurnace)ent).removeTechBlock(world);
			super.onBlockDestroyedByPlayer(world, x, y, z, meta);
			return;
		}
		
		ent =  world.getTileEntity(x, y - 1, z);
		if (ent instanceof TileEntityGreatFurnace)
		{
			((TileEntityGreatFurnace)ent).removeTechBlock(world);
			super.onBlockDestroyedByPlayer(world, x, y, z, meta);
			return;
		}
		
		ent =  world.getTileEntity(x, y, z + 1);
		if (ent instanceof TileEntityGreatFurnace)
		{
			((TileEntityGreatFurnace)ent).removeTechBlock(world);
			super.onBlockDestroyedByPlayer(world, x, y, z, meta);
			return;
		}
		
		ent =  world.getTileEntity(x, y, z - 1);
		if (ent instanceof TileEntityGreatFurnace)
		{
			((TileEntityGreatFurnace)ent).removeTechBlock(world);
			super.onBlockDestroyedByPlayer(world, x, y, z, meta);
			return;
		}
		
		((GreatFurnaceBlockTech) Nord.greatFurnaceTech).dropItems(world, x, y, z);
		
		super.onBlockDestroyedByPlayer(world, x, y, z, meta);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase ent, ItemStack item) {
		TileEntityGreatFurnace tileEnt = (TileEntityGreatFurnace)world.getTileEntity(x, y, z);
		int look = MathHelper.floor_double((double)(ent.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		int side = 0;
		switch (look)
		{
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
		
		if (tileEnt.tryToCreateTechBlock(world, x, y, z, side))
		{
			tileEnt.broadcastSetTechBlock(world, tileEnt.techBlock, tileEnt.techBlock);
		}
		super.onBlockPlacedBy(world, x, y, z, ent, item);
	}
}
