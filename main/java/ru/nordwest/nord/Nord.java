package ru.nordwest.nord;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import ru.nordwest.nord.api.BagContentApi;
import ru.nordwest.nord.client.lib.tabs.TabsNord;
import ru.nordwest.nord.common.CommonProxy;
import ru.nordwest.nord.common.blocks.RegisterDecorations;
import ru.nordwest.nord.common.blocks.RegisterMachine;
import ru.nordwest.nord.common.blocks.RegisterMetal;
import ru.nordwest.nord.common.blocks.RegisterTrees;
import ru.nordwest.nord.common.items.BagContent;
import ru.nordwest.nord.common.items.RegisterBags;
import ru.nordwest.nord.common.items.RegisterFood;
import ru.nordwest.nord.common.lib.events.DropHandler;
import ru.nordwest.nord.common.lib.events.GuiHandler;
import ru.nordwest.nord.common.lib.network.PacketPipeline;
import ru.nordwest.nord.common.lib.utils.Fuel;

import java.util.Random;

@Mod(modid = Nord.MODID, name = Nord.NAME, version = Nord.VERSION)
public class Nord {
        public static final String MODID = "nord";
        public static final String VERSION = "0.0.1";
        public static final String NAME = "Nord Mod";
        public final static Item itemScanner = net.minecraft.init.Items.coal;
        public static final Block[] buildingBlocks = new Block[16];
        public static final Block[] lamps = new Block[3];
        public static final int[] colors = new int[]{0x1E1B1B, 0xB3312C, 0x3B511A,
                0x51301A, 0x253192, 0x7B2FBE, 0x287697, 0xABABAB, 0x434343,
                0xD88198, 0x41CD34, 0xDECF2A, 0x6689D3, 0xC354CD, 0xEB8844,
                0xF0F0F0};
        public static final int CandleRendererID = RenderingRegistry.getNextAvailableRenderId();
        public static final int guiIDSmelter = 0;
        public static final int guiIDBrickFurnace = 1;
        public static final int guiIDFlowing = 2;
        public static final int guiIDGreatFurnace = 3;
        public static final int guiIDBag = 4;
        public static final PacketPipeline packetPipeline = new PacketPipeline();

        @Instance(value = Nord.MODID)
        public static Nord instance;

        @SidedProxy(clientSide = "ru.nordwest.nord.client.ClientProxy", serverSide = "ru.nordwest.nord.common.CommonProxy")
        public static CommonProxy proxy;

        public static Item item;
        public static Block oil_lamp;
        public static Block candle;
        public static Block smelter;
        public static Block brickFurnace;
        //public static int greatFurnaceMainID;
        public static Block flowingBlock;
        public static Block greatFurnace;
        public static Block greatFurnaceTech;
        //public static Block greatFurnaceMain;
        public static Block foundryTech;
        public static Block foundry;
        public static Block testTreeSapling;
        public static Random rand = new Random();

        @EventHandler
        public void preInit(final FMLPreInitializationEvent event) {
                RegisterMetal.preInit();
                RegisterMetal.init();
                RegisterDecorations.init();
                RegisterFood.init();
                RegisterMachine.init();
                Fuel.init();
                RegisterTrees.init();
                RegisterBags.init();
                MinecraftForge.EVENT_BUS.register(new DropHandler());
        }

        @EventHandler
        public void init(final FMLInitializationEvent event) {
                Nord.proxy.registerRenderers();
                Nord.proxy.init();
                packetPipeline.initialise();
                NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
                DropHandler.addStructuresLoot();
                BagContent.registerItems();
                BagContentApi.registerNordItems();
        }

        @EventHandler
        public void postInit(final FMLPostInitializationEvent event) {
                packetPipeline.postInitialise();
                TabsNord.postInit();
                RegisterFood.postInit();
        }
}
