package ru.nordwest.nord;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Nord.MODID, name = Nord.NAME, version = Nord.VERSION)
public class Nord {
	public static final String MODID = "nord";
	public static final String VERSION = "0.0.1";
	public static final String NAME = "0.0.1";
	@Instance(value = "GenericModID")
	public static Nord instance;
	// ================================================================
	public static CreativeTabs tabBase = new CreativeTabs("tabBase") {
		@Override
		public Item getTabIconItem() {
			return Items.bed;
		}
	};
	public static CreativeTabs tabMetall = new CreativeTabs("tabMetall") {
		@Override
		public Item getTabIconItem() {
			return null;
		}
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getIconItemStack() {
			return MetallRegister.getMetallIngot("gold", 4);
		}
	};
	public static CreativeTabs tabDeco = new CreativeTabs("tabDeco") {
		@Override
		public Item getTabIconItem() {
			return null;
		}
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getIconItemStack() {
			return new ItemStack(Nord.deco1[1], 1, 8);
		}
	};
	public static CreativeTabs food = new CreativeTabs("food") {
		@Override
		public Item getTabIconItem() {
			return Items.apple;
		}
	};
	public static CreativeTabs flashlights = new CreativeTabs("flashlights") {
		@Override
		public Item getTabIconItem() {
			return null;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getIconItemStack() {
			return new ItemStack(Nord.candle, 1, 8);
		}
	};
	// ================================================================
	@SidedProxy(clientSide = "ru.nordwest.nord.client.ClientProxy", serverSide = "ru.nordwest.nord.CommonProxy")
	public static CommonProxy proxy;
	public static Block[] deco1 = new Block[16];
	public static Block[] deco2 = new Block[3];
	public static Block oil_lamp;
	public static Block candle;

	public static int[] colors = new int[]{0x1E1B1B, 0x0B3312C, 0x03B511A,
			0x051301A, 0x0253192, 0x07B2FBE, 0x0287697, 0x0ABABAB, 0x0434343,
			0x0D88198, 0x041CD34, 0x0DECF2A, 0x06689D3, 0x0C354CD, 0x0EB8844,
			0xF0F0F0};
	public static int CandleRendererID = RenderingRegistry
			.getNextAvailableRenderId();

	@EventHandler
	public void preInit(final FMLPreInitializationEvent event) {
		MetallRegister.init();
		DecoRegister.init();

	}

	@EventHandler
	public void init(final FMLInitializationEvent event) {
		Nord.proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(final FMLPostInitializationEvent event) {

	}
}
