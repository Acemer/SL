package ru.nordwest.nord;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import ru.nordwest.nord.block.BaseMetallBlock;
import ru.nordwest.nord.item.ItemMetallBlock;
import ru.nordwest.nord.item.ItemMetallIngot;
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
	
		Nord.gold_block = new BaseMetallBlock(Material.iron).setBlockName("gold_block").setCreativeTab(Nord.tabBase).setBlockTextureName("gold_block");
		GameRegistry.registerBlock(Nord.gold_block, ItemMetallBlock.class,"gold_block");
		
		Nord.cobalt_block = new BaseMetallBlock(Material.iron).setBlockName("cobalt_block").setCreativeTab(Nord.tabBase).setBlockTextureName("cobalt_block");
		GameRegistry.registerBlock(Nord.cobalt_block, ItemMetallBlock.class,"cobalt_block");
		
		Nord.copper_block = new BaseMetallBlock(Material.iron).setBlockName("copper_block").setCreativeTab(Nord.tabBase).setBlockTextureName("copper_block");
		GameRegistry.registerBlock(Nord.copper_block, ItemMetallBlock.class,"copper_block");
		
		Nord.nickel_block = new BaseMetallBlock(Material.iron).setBlockName("nickel_block").setCreativeTab(Nord.tabBase).setBlockTextureName("nickel_block");
		GameRegistry.registerBlock(Nord.nickel_block, ItemMetallBlock.class,"nickel_block");
		
		Nord.tin_block = new BaseMetallBlock(Material.iron).setBlockName("tin_block").setCreativeTab(Nord.tabBase).setBlockTextureName("tin_block");
		GameRegistry.registerBlock(Nord.tin_block, ItemMetallBlock.class,"tin_block");
		
		Nord.platinum_block = new BaseMetallBlock(Material.iron).setBlockName("platinum_block").setCreativeTab(Nord.tabBase).setBlockTextureName("platinum_block");
		GameRegistry.registerBlock(Nord.platinum_block, ItemMetallBlock.class,"platinum_block");
		
		Nord.plumbum_block = new BaseMetallBlock(Material.iron).setBlockName("plumbum_block").setCreativeTab(Nord.tabBase).setBlockTextureName("plumbum_block");
		GameRegistry.registerBlock(Nord.plumbum_block, ItemMetallBlock.class,"plumbum_block");
		
		Nord.chrome_ingot= new ItemMetallIngot().setUnlocalizedName("chrome_ingot").setTextureName("chrome_ingot");
		GameRegistry.registerItem(Nord.chrome_ingot,"chrome_ingot");

		for (int i = 0; i < 16; i++) {
			GameRegistry.addRecipe(new ItemStack(Nord.chrome_block,1,i), "xxx", "xxx", "xxx",
			        'x', new ItemStack(Nord.chrome_ingot,1,i));
		}
		
		
	}
}
