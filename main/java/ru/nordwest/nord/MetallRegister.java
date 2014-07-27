package ru.nordwest.nord;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.nordwest.nord.block.BaseMetallBlock;
import ru.nordwest.nord.item.ItemMetallBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class MetallRegister {
	static public void init(){
		Nord.alluminumBlock = new BaseMetallBlock(Material.iron).setBlockName("alluminum_block").setCreativeTab(Nord.tabBase).setBlockTextureName("alluminium_block");
		GameRegistry.registerBlock(Nord.alluminumBlock, ItemMetallBlock.class,"alluminum_block");
		
		Nord.chrome_block = new BaseMetallBlock(Material.iron).setBlockName("chrome_block").setCreativeTab(Nord.tabBase).setBlockTextureName("chrome_block");
		GameRegistry.registerBlock(Nord.chrome_block, ItemMetallBlock.class,"chrome_block");
		
		Nord.iron_block = new BaseMetallBlock(Material.iron).setBlockName("iron_block").setCreativeTab(Nord.tabBase).setBlockTextureName("iron_block");
		GameRegistry.registerBlock(Nord.iron_block, ItemMetallBlock.class,"iron_block");
		
		Nord.titan_block = new BaseMetallBlock(Material.iron).setBlockName("titan_block").setCreativeTab(Nord.tabBase).setBlockTextureName("titan_block");
		GameRegistry.registerBlock(Nord.titan_block, ItemMetallBlock.class,"titan_block");
		
		Nord.tungsten_block = new BaseMetallBlock(Material.iron).setBlockName("tungsten_block").setCreativeTab(Nord.tabBase).setBlockTextureName("tungsten_block");
		GameRegistry.registerBlock(Nord.tungsten_block, ItemMetallBlock.class,"tungsten_block");
		
		Nord.zing_block = new BaseMetallBlock(Material.iron).setBlockName("zing_block").setCreativeTab(Nord.tabBase).setBlockTextureName("zing_block");
		GameRegistry.registerBlock(Nord.zing_block, ItemMetallBlock.class,"zing_block");
	
		
	}
}
