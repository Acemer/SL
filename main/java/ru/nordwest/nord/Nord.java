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
import net.minecraft.world.World;
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
			return Items.stone_pickaxe;
		}
	};
	public static CreativeTabs food = new CreativeTabs("food") {
		public Item getTabIconItem() {
			return Items.apple;
		}
	};
	public static CreativeTabs flashlights = new CreativeTabs("flashlights") {
		public Item getTabIconItem() {
			return Items.gold_ingot;
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
	public static Block[] deco1 = new Block[16];
	public static Block[] deco2 = new Block[3];
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
	public static Item copper_ingot;
	public static Item nickel_ingot;
	public static Item platinum_ingot;
	public static Item plumbum_ingot;
	public static Item tin_ingot;
	public static Item bun;
	public static Item bun_jam;
	public static Item bun_jam2;
	public static Block emp_bamboo_block;
	public static Block oil_lamp;
	public static int[] clolors = new int[] { 0x1E1B1B, 0x0B3312C, 0x03B511A,
			0x051301A, 0x0253192, 0x07B2FBE, 0x0287697, 0x0ABABAB, 0x0434343,
			0x0D88198, 0x041CD34, 0x0DECF2A, 0x06689D3, 0x0C354CD, 0x0EB8844,
			0xF0F0F0 };

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MetallRegister.init();
		for (int i = 0; i < 16; i++) {
			Nord.deco1[i] = new BaseDecoStoneBlock(Material.rock, i)
					.setBlockName("stone_" + i).setCreativeTab(Nord.tabDeco)
					.setBlockTextureName("stone");
			GameRegistry.registerBlock(Nord.deco1[i], ItemDecoStoneBlock.class,
					"stone_" + i);
		}
		for (int i = 0; i < 3; i++) {
			Nord.deco2[i] = new BaseEmpDecoBlock(Material.grass, i)
					.setBlockName("flashlight_" + i)
					.setCreativeTab(Nord.flashlights)
					.setBlockTextureName("flashlight")
					.setStepSound(Block.soundTypeSnow);
			GameRegistry.registerBlock(Nord.deco2[i], ItemDecoStoneBlock.class,
					"flashlight_" + i).setLightLevel(0.9375F);
		}
		Nord.oil_lamp = new BaseEmpDecoBlock(Material.glass, 0)
				.setBlockName("oil_lamp_").setCreativeTab(Nord.flashlights)
				.setBlockTextureName("oil_lamp")
				.setStepSound(Block.soundTypeGlass);
		GameRegistry.registerBlock(Nord.oil_lamp, ItemDecoStoneBlock.class,
				"oil_lamp_").setLightLevel(0.9375F);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
