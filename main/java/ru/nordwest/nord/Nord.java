package ru.nordwest.nord;

import java.util.Random;

import ru.nordwest.nord.common.*;
import ru.nordwest.nord.common.handler.*;
import ru.nordwest.nord.util.CreativeTabNord;
import ru.nordwest.nord.util.Fuel;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Nord.MODID, name = Nord.NAME, version = Nord.VERSION)
public class Nord {
	public static final String MODID = "nord";
	public static final String VERSION = "0.0.1";
	public static final String NAME = "Nord";
	@Instance(value = "nord")
	public static Nord instance;
	// ================================================================
	public final static CreativeTabs tabBase = new CreativeTabNord("tabBase");
	public final static CreativeTabs tabMetall = new CreativeTabNord("tabMetall");
	public final static CreativeTabs tabDeco = new CreativeTabNord("tabDeco");
	public final static CreativeTabs food = new CreativeTabNord("food");
	public final static CreativeTabs flashlights = new CreativeTabNord("flashlights");
	// ================================================================
	@SidedProxy(clientSide = "ru.nordwest.nord.client.ClientProxy", serverSide = "ru.nordwest.nord.common.CommonProxy")
	public static CommonProxy proxy;
    public final static Item itemScaner =  Items.coal;
    public final Random rand = new Random();
	public static Item item;
	public static final Block[] deco1 = new Block[16];
	public static final Block[] deco2 = new Block[3];
	public static Block oil_lamp;
	public static Block candle;
	public static Block smelter;
	public static Block brickFurnace;
	public static Block flowingBlock;
	public static Block greatFurnace;
	public static Block greatFurnaceTech;
//	public static Block greatFurnaceMain;
	public static Block foundryTech;
	public static Block foundry;
	public static Block testTreeSapling;
//	public static int greatFurnaceMainID;
	//public static Item ifood;
	public static final int[] colors = new int[]{0x1E1B1B, 0xB3312C, 0x3B511A,
			0x51301A, 0x253192, 0x7B2FBE, 0x287697, 0xABABAB, 0x434343,
			0xD88198, 0x41CD34, 0xDECF2A, 0x6689D3, 0xC354CD, 0xEB8844,
			0xF0F0F0};
	public static final int CandleRendererID = RenderingRegistry
			.getNextAvailableRenderId();
	public static final int guiIDSmelter = 20;
	public static final int guiIDBrickFurnace = 21;
	public static final int guiIDFlowing = 22;
	public static final int guiIDGreatFurnace = 23;
	public static final PacketPipeline packetPipeline = new PacketPipeline();


	@EventHandler
	public void preInit(final FMLPreInitializationEvent event) {
		MetallRegister.preInit();
		MetallRegister.init();
		DecoRegister.init();
		FoodRegister.init();
		MachineRegister.init();
        Fuel.init();
        Trees.init();
	}

	@EventHandler
	public void init(final FMLInitializationEvent event) {
		Nord.proxy.registerRenderers();
        Nord.proxy.init();
		packetPipeline.initialise();
		// packetPipeline.registerPacket(YourPacket.class);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

	}


	@EventHandler
	public void postInit(final FMLPostInitializationEvent event) {
		packetPipeline.postInitialise();
		tabPostInit();
		FoodRegister.posInit();
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
