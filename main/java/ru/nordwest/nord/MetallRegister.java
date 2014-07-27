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
		Nord.alluminum_block = new BaseMetallBlock(Material.iron).setBlockName("alluminum_block").setCreativeTab(Nord.tabBase).setBlockTextureName("alluminium_block");
		GameRegistry.registerBlock(Nord.alluminum_block, ItemMetallBlock.class,"alluminum_block");
		
		Nord.chrome_block = new BaseMetallBlock(Material.iron).setBlockName("chrome_block").setCreativeTab(Nord.tabMetall).setBlockTextureName("chrome_block");
		GameRegistry.registerBlock(Nord.chrome_block, ItemMetallBlock.class,"chrome_block");
		
		Nord.iron_block = new BaseMetallBlock(Material.iron).setBlockName("iron_block").setCreativeTab(Nord.tabMetall).setBlockTextureName("iron_block");
		GameRegistry.registerBlock(Nord.iron_block, ItemMetallBlock.class,"iron_block");
		
		Nord.titan_block = new BaseMetallBlock(Material.iron).setBlockName("titan_block").setCreativeTab(Nord.tabMetall).setBlockTextureName("titan_block");
		GameRegistry.registerBlock(Nord.titan_block, ItemMetallBlock.class,"titan_block");
		
		Nord.tungsten_block = new BaseMetallBlock(Material.iron).setBlockName("tungsten_block").setCreativeTab(Nord.tabMetall).setBlockTextureName("tungsten_block");
		GameRegistry.registerBlock(Nord.tungsten_block, ItemMetallBlock.class,"tungsten_block");
		
		Nord.zing_block = new BaseMetallBlock(Material.iron).setBlockName("zing_block").setCreativeTab(Nord.tabMetall).setBlockTextureName("zing_block");
		GameRegistry.registerBlock(Nord.zing_block, ItemMetallBlock.class,"zing_block");
	
		Nord.gold_block = new BaseMetallBlock(Material.iron).setBlockName("gold_block").setCreativeTab(Nord.tabMetall).setBlockTextureName("gold_block");
		GameRegistry.registerBlock(Nord.gold_block, ItemMetallBlock.class,"gold_block");
		
		Nord.cobalt_block = new BaseMetallBlock(Material.iron).setBlockName("cobalt_block").setCreativeTab(Nord.tabMetall).setBlockTextureName("cobalt_block");
		GameRegistry.registerBlock(Nord.cobalt_block, ItemMetallBlock.class,"cobalt_block");
		
		Nord.copper_block = new BaseMetallBlock(Material.iron).setBlockName("copper_block").setCreativeTab(Nord.tabMetall).setBlockTextureName("copper_block");
		GameRegistry.registerBlock(Nord.copper_block, ItemMetallBlock.class,"copper_block");
		
		Nord.nickel_block = new BaseMetallBlock(Material.iron).setBlockName("nickel_block").setCreativeTab(Nord.tabMetall).setBlockTextureName("nickel_block");
		GameRegistry.registerBlock(Nord.nickel_block, ItemMetallBlock.class,"nickel_block");
		
		Nord.tin_block = new BaseMetallBlock(Material.iron).setBlockName("tin_block").setCreativeTab(Nord.tabMetall).setBlockTextureName("tin_block");
		GameRegistry.registerBlock(Nord.tin_block, ItemMetallBlock.class,"tin_block");
		
		Nord.platinum_block = new BaseMetallBlock(Material.iron).setBlockName("platinum_block").setCreativeTab(Nord.tabMetall).setBlockTextureName("platinum_block");
		GameRegistry.registerBlock(Nord.platinum_block, ItemMetallBlock.class,"platinum_block");
		
		Nord.plumbum_block = new BaseMetallBlock(Material.iron).setBlockName("plumbum_block").setCreativeTab(Nord.tabMetall).setBlockTextureName("plumbum_block");
		GameRegistry.registerBlock(Nord.plumbum_block, ItemMetallBlock.class,"plumbum_block");
		
		Nord.chrome_ingot= new ItemMetallIngot().setUnlocalizedName("chrome_ingot").setTextureName("chrome_ingot");
		GameRegistry.registerItem(Nord.chrome_ingot,"chrome_ingot");
		
		Nord.iron_ingot= new ItemMetallIngot().setUnlocalizedName("iron_ingot").setTextureName("iron_ingot");
		GameRegistry.registerItem(Nord.iron_ingot,"iron_ingot");

		Nord.titan_ingot= new ItemMetallIngot().setUnlocalizedName("titan_ingot").setTextureName("titan_ingot");
		GameRegistry.registerItem(Nord.titan_ingot,"titan_ingot");
		
		Nord.tungsten_ingot= new ItemMetallIngot().setUnlocalizedName("tungsten_ingot").setTextureName("tungsten_ingot");
		GameRegistry.registerItem(Nord.tungsten_ingot,"tungsten_ingot");
		
		Nord.zing_ingot= new ItemMetallIngot().setUnlocalizedName("zing_ingot").setTextureName("zing_ingot");
		GameRegistry.registerItem(Nord.zing_ingot,"zing_ingot");
		
		Nord.alluminum_ingot= new ItemMetallIngot().setUnlocalizedName("alluminum_ingot").setTextureName("alluminum_ingot");
		GameRegistry.registerItem(Nord.alluminum_ingot,"alluminum_ingot");
		
		Nord.gold_ingot= new ItemMetallIngot().setUnlocalizedName("gold_ingot").setTextureName("gold_ingot");
		GameRegistry.registerItem(Nord.gold_ingot,"gold_ingot");
		
		Nord.cobalt_ingot= new ItemMetallIngot().setUnlocalizedName("cobalt_ingot").setTextureName("cobalt_ingot");
		GameRegistry.registerItem(Nord.cobalt_ingot,"cobalt_ingot");
		
		Nord.copper_ingot= new ItemMetallIngot().setUnlocalizedName("copper_ingot").setTextureName("copper_ingot");
		GameRegistry.registerItem(Nord.copper_ingot,"copper_ingot");
		
		Nord.nickel_ingot= new ItemMetallIngot().setUnlocalizedName("nickel_ingot").setTextureName("nickel_ingot");
		GameRegistry.registerItem(Nord.nickel_ingot,"nickel_ingot");
		
		Nord.platinum_ingot= new ItemMetallIngot().setUnlocalizedName("platinum_ingot").setTextureName("platinum_ingot");
		GameRegistry.registerItem(Nord.platinum_ingot,"platinum_ingot");
		
		Nord.plumbum_ingot= new ItemMetallIngot().setUnlocalizedName("plumbum_ingot").setTextureName("plumbum_ingot");
		GameRegistry.registerItem(Nord.plumbum_ingot,"plumbum_ingot");
		
		Nord.tin_ingot= new ItemMetallIngot().setUnlocalizedName("tin_ingot").setTextureName("tin_ingot");
		GameRegistry.registerItem(Nord.tin_ingot,"tin_ingot");
		
		for (int i = 0; i < 16; i++) {
			GameRegistry.addRecipe(new ItemStack(Nord.chrome_block,1,i), "xxx", "xxx", "xxx",
			        'x', new ItemStack(Nord.chrome_ingot,1,i));
			GameRegistry.addRecipe(new ItemStack(Nord.ingot_block,1,i), "xxx", "xxx", "xxx",
			        'x', new ItemStack(Nord.ingot_ingot,1,i));
			GameRegistry.addRecipe(new ItemStack(Nord.titan_block,1,i), "xxx", "xxx", "xxx",
			        'x', new ItemStack(Nord.titan_ingot,1,i));
			GameRegistry.addRecipe(new ItemStack(Nord.tungsten_block,1,i), "xxx", "xxx", "xxx",
			        'x', new ItemStack(Nord.tungsten_ingot,1,i));
			GameRegistry.addRecipe(new ItemStack(Nord.zing_block,1,i), "xxx", "xxx", "xxx",
			        'x', new ItemStack(Nord.zing_ingot,1,i));
			GameRegistry.addRecipe(new ItemStack(Nord.alluminum_block,1,i), "xxx", "xxx", "xxx",
			        'x', new ItemStack(Nord.alluminium_ingot,1,i));
			GameRegistry.addRecipe(new ItemStack(Nord.gold_block,1,i), "xxx", "xxx", "xxx",
			        'x', new ItemStack(Nord.gold_ingot,1,i));
			GameRegistry.addRecipe(new ItemStack(Nord.cobalt_block,1,i), "xxx", "xxx", "xxx",
			        'x', new ItemStack(Nord.cobalt_ingot,1,i));
			GameRegistry.addRecipe(new ItemStack(Nord.copper_block,1,i), "xxx", "xxx", "xxx",
			        'x', new ItemStack(Nord.copper_ingot,1,i));
			GameRegistry.addRecipe(new ItemStack(Nord.nickel_block,1,i), "xxx", "xxx", "xxx",
			        'x', new ItemStack(Nord.nickel_ingot,1,i));
			GameRegistry.addRecipe(new ItemStack(Nord.platinum_block,1,i), "xxx", "xxx", "xxx",
			        'x', new ItemStack(Nord.platinum_ingot,1,i));
			GameRegistry.addRecipe(new ItemStack(Nord.plumbum_block,1,i), "xxx", "xxx", "xxx",
			        'x', new ItemStack(Nord.plumbum_ingot,1,i));
			GameRegistry.addRecipe(new ItemStack(Nord.tin_block,1,i), "xxx", "xxx", "xxx",
			        'x', new ItemStack(Nord.tin_ingot,1,i));
			
		

		
		}
		
		
	}
}
