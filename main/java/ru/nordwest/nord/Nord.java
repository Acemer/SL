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
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nordwest.nord.common.CommonProxy;
import ru.nordwest.nord.common.PacketPipeline;
import ru.nordwest.nord.common.handler.GuiHandler;
import ru.nordwest.nord.item.ItemMetadataFood;
import ru.nordwest.nord.util.CreativeTabNord;
import ru.nordwest.nord.util.Fuel;

import java.util.Random;

@Mod(modid = Nord.MODID, name = Nord.NAME, version = Nord.VERSION)
public class Nord {
        public static final String MODID = "nord";
        public static final String VERSION = "0.0.1";
        public static final String NAME = "Nord Mod";
        public final static CreativeTabs generalTab = new CreativeTabNord("generalTab");
        public final static CreativeTabs metalsTab = new CreativeTabNord("metalsTab");
        public final static CreativeTabs blocksTab = new CreativeTabNord("blocksTab");
        public final static CreativeTabs foodTab = new CreativeTabNord("foodTab");
        public final static CreativeTabs lightingTab = new CreativeTabNord("lightingTab");
        public final static CreativeTabs bagsAndGiftsTab = new CreativeTabNord("bagsAndGiftsTab");
        public final static Item itemScanner = Items.coal;
        public static final Block[] buildingBlocks = new Block[16];
        public static final Block[] lamps = new Block[3];
        public static final int[] colors = new int[]{0x1E1B1B, 0xB3312C, 0x3B511A,
                0x51301A, 0x253192, 0x7B2FBE, 0x287697, 0xABABAB, 0x434343,
                0xD88198, 0x41CD34, 0xDECF2A, 0x6689D3, 0xC354CD, 0xEB8844,
                0xF0F0F0};
        public static final int CandleRendererID = RenderingRegistry.getNextAvailableRenderId();
        public static final int guiIDSmelter = 20;
        public static final int guiIDBrickFurnace = 21;
        public static final int guiIDFlowing = 22;
        public static final int guiIDGreatFurnace = 23;
        public static final PacketPipeline packetPipeline = new PacketPipeline();

        @Instance(value = Nord.MODID)
        public static Nord NordInstance;

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
        public final Random rand = new Random();

        @EventHandler
        public void preInit(final FMLPreInitializationEvent event) {
                MetalRegister.preInit();
                MetalRegister.init();
                DecorationsRegister.init();
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
                FoodRegister.postInit();
        }

        private void tabPostInit() {
                ((CreativeTabNord) generalTab).setItem(new ItemStack(Items.bed));
                ((CreativeTabNord) blocksTab).setItem(new ItemStack(Nord.buildingBlocks[6], 1, 1));
                ((CreativeTabNord) lightingTab).setItem(new ItemStack(Nord.lamps[2], 1, 0));
                ((CreativeTabNord) metalsTab).setItem(MetalRegister.getMetallIngot("zing", 3, 1));
                ((CreativeTabNord) foodTab).setItem(ItemMetadataFood.getFood("potatoes_pie"));
                ((CreativeTabNord) bagsAndGiftsTab).setItem(new ItemStack(Items.leather));
        }
}
