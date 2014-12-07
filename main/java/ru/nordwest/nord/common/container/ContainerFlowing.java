package ru.nordwest.nord.common.container;

import org.apache.logging.log4j.Level;

import ru.nordwest.nord.common.recipe.FlowingRecipes;
import ru.nordwest.nord.common.recipe.FlowingRecipes1I2O;
import ru.nordwest.nord.common.tileentity.TileEntityFlowing;
import ru.nordwest.nord.common.tileentity.TileEntitySmelter;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerFlowing extends AbstactMachineConteiner {

	
	public ContainerFlowing(InventoryPlayer invPlayer, TileEntityFlowing ent)
	{
		init(invPlayer,ent);
	}

}
