package ru.nordwest.nord;

import ru.nordwest.nord.block.BaseMetallBlock;
import ru.nordwest.nord.block.SmelterBlock;
import ru.nordwest.nord.item.ItemMetallBlock;
import tile_entity.GuiHandler;
import tile_entity.SmelterRecipes;
import tile_entity.TileEntitySmelter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Nord.MODID, name = Nord.NAME, version = Nord.VERSION)
public class Nord {
	public static final String MODID = "nord";
	public static final String VERSION = "0.0.1";
	public static final String NAME = "Nord";
	@Instance(value = "nord")
	public static Nord instance;
	// ================================================================
	public static CreativeTabs tabBase = new CreativeTabNord("tabBase");
	public static CreativeTabs tabMetall = new CreativeTabNord("tabMetall");
	public static CreativeTabs tabDeco = new CreativeTabNord("tabDeco");
	public static CreativeTabs food = new CreativeTabNord("food");
	public static CreativeTabs flashlights = new CreativeTabNord("flashlights");
	// ================================================================
	@SidedProxy(clientSide = "ru.nordwest.nord.client.ClientProxy", serverSide = "ru.nordwest.nord.CommonProxy")
	public static CommonProxy proxy;
	public static Block[] deco1 = new Block[16];
	public static Block[] deco2 = new Block[3];
	public static Block oil_lamp;
	public static Block candle;
	public static Block smelter;
	public static int[] colors = new int[]{0x1E1B1B, 0xB3312C, 0x3B511A,
			0x51301A, 0x253192, 0x7B2FBE, 0x287697, 0xABABAB, 0x434343,
			0xD88198, 0x41CD34, 0xDECF2A, 0x6689D3, 0xC354CD, 0xEB8844,
			0xF0F0F0};
	public static int CandleRendererID = RenderingRegistry
			.getNextAvailableRenderId();
	public static final int guiIDSmelter = 20;
	public static final PacketPipeline packetPipeline = new PacketPipeline();

	@EventHandler
	public void preInit(final FMLPreInitializationEvent event) {
		MetallRegister.init();
		DecoRegister.init();

	}

	@EventHandler
	public void init(final FMLInitializationEvent event) {
		Nord.proxy.registerRenderers();

		packetPipeline.initialise();
		// packetPipeline.registerPacket(YourPacket.class); //Ну и другие ваши
		// пакеты аналогично.
		smelter = new SmelterBlock().setBlockName("smelter")
				.setCreativeTab(Nord.tabMetall).setBlockTextureName("smelter")
				.setHardness(3.0F).setHardness(5.0F);
		GameRegistry.registerBlock(smelter, ItemMetallBlock.class, "smelter");

		GameRegistry.registerTileEntity(TileEntitySmelter.class,
				"TileEntitySmelter");
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}

	@EventHandler
	public void postInit(final FMLPostInitializationEvent event) {
		packetPipeline.postInitialise();
		tabPostInit();
	}

	private void tabPostInit() {
		((CreativeTabNord) tabBase).setItem(new ItemStack(Items.bed));
		((CreativeTabNord) tabMetall).setItem(MetallRegister.getMetallIngot(
				"gold", 4, 1));
		((CreativeTabNord) tabDeco).setItem(new ItemStack(Nord.deco1[1], 1, 8));
		((CreativeTabNord) food).setItem(new ItemStack(Items.apple));
		((CreativeTabNord) flashlights)
				.setItem(new ItemStack(Nord.candle, 1, 8));
	}
}
