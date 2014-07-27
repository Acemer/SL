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

	@SidedProxy(clientSide = "ru.nordwest.nord.client.ClientProxy", serverSide = "ru.nordwest.nord.CommonProxy")
	public static CommonProxy proxy;
	public static Block alluminumBlock;
	public static Block chrome_block;
	public static Block iron_block;
	public static Block titan_block;
	public static Block tungsten_block;
	public static Block zing_block;
	private Item itemTest;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
			MetallRegister.init();
			//itemTest = new ItemBase().setUnlocalizedName("itemTest").setTextureName(MODID + ":" + "itemTest");
			//GameRegistry.registerItem(itemTest,"itemTest");
			
			itemTest = new ItemMetallIngot().setUnlocalizedName("chrome_ingot").setTextureName("chrome_ingot");
			GameRegistry.registerItem(itemTest,"chrome_ingot");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
