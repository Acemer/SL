package ru.nordwest.nord;

import ru.nordwest.nord.block.*;
import ru.nordwest.nord.item.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Nord.MODID, name = Nord.NAME, version = Nord.VERSION)
public class Nord {
	public static final String MODID = "nord";
	public static final String VERSION = "0.0.1";
	public static final String NAME = "0.0.1";
	@Instance(value = "GenericModID")
	public static Nord instance;
	public static CreativeTabs tabBase = new CreativeTabs("tabBase") {
		public Item getTabIconItem() {
			return Items.bed;
		}
	};
	public static CreativeTabs tabMetall = new CreativeTabs("tabMetall") {
		public Item getTabIconItem() {
			return Items.iron_ingot;
		}
	};
	public static CreativeTabs tabDeco = new CreativeTabs("tabDeco") {
		public Item getTabIconItem() {
			return Items.apple;
		}
	};
	@SidedProxy(clientSide = "ru.nordwest.nord.client.ClientProxy", serverSide = "ru.nordwest.nord.CommonProxy")

	public static CommonProxy proxy;
	public static Block alluminum_block;
	public static Block chrome_block;
	public static Block iron_block;
	public static Block titan_block;
	public static Block tungsten_block;
	public static Block zing_block;
	public static Block gold_block;
	public static Block cobalt_block;
	public static Block copper_block;
	public static Block nickel_block;
	public static Block tin_block;
	public static Block platinum_block;
	public static Block plumbum_block;
	public static Item chrome_ingot;
	public static Block[] deco1= new Block[16];
	public static Item iron_ingot;
	public static Block ingot_block;
	public static Block ingot_ingot;
	public static Item titan_ingot;
	public static Item tungsten_ingot;
	public static Item zing_ingot;
	public static Item alluminium_ingot;
	public static Item alluminum_ingot;
	public static Item gold_ingot;
	public static Item cobalt_ingot;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
			MetallRegister.init();
			for (int i = 0; i < 16; i++) {
				Nord.deco1[i] = new BaseDecoStoneBlock(Material.rock,i).setBlockName("stone_"+i).setCreativeTab(Nord.tabDeco).setBlockTextureName("stone");
				GameRegistry.registerBlock(Nord.deco1[i], ItemDecoStoneBlock.class,"stone_"+i);
			}
			
			
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
