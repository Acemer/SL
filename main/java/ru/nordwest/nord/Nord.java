������ �����package ru.nordwest.nord;

import java.util.Random;

import ru.nordwest.nord.block.*;
import ru.nordwest.nord.common.*;
import ru.nordwest.nord.common.handler.*;
import ru.nordwest.nord.common.tileentity.*;
import ru.nordwest.nord.item.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
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
	@SidedProxy(clientSide = "ru.nordwest.nord.client.ClientProxy", serverSide = "ru.nordwest.nord.common.CommonProxy")
	public static CommonProxy proxy;
	public Random rand = new Random();
	public static Item item;
	public static Block[] deco1 = new Block[16];
	public static Block[] deco2 = new Block[3];
	public static Block oil_lamp;
	public static Block candle;
	public static Block smelter;
	public static Block brickFurnace;
	public static Item ifood;
	public static int[] colors = new int[]{0x1E1B1B, 0xB3312C, 0x3B511A,
			0x51301A, 0x253192, 0x7B2FBE, 0x287697, 0xABABAB, 0x434343,
			0xD88198, 0x41CD34, 0xDECF2A, 0x6689D3, 0xC354CD, 0xEB8844,
			0xF0F0F0};
	public static int CandleRendererID = RenderingRegistry
			.getNextAvailableRenderId();
	public static final int guiIDSmelter = 20;
	public static final int guiIDBrickFurnace = 21;
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

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		smelter = new SmelterBlock().setBlockName("smelter")
				.setCreativeTab(Nord.tabMetall).setBlockTextureName("smelter")
				.setHardness(3.0F).setHardness(5.0F);
		GameRegistry.registerBlock(smelter, ItemMetallBlock.class, "smelter");
		GameRegistry.registerTileEntity(TileEntitySmelter.class,
				"TileEntitySmelter");
		GameRegistry.addRecipe(new ItemStack(smelter, 1), "xyx", "y0y", "yyy",
				'x', new ItemStack(Blocks.furnace, 1), 'y', new ItemStack(
						Blocks.cobblestone, 1));

		brickFurnace = new BrickFurnaceBlock().setBlockName("brickFurnace")
				.setCreativeTab(Nord.tabMetall).setBlockTextureName("brickFurnace")
				.setHardness(3.0F).setHardness(5.0F);
		GameRegistry.registerBlock(brickFurnace, ItemMetallBlock.class, "brickFurnace");
		GameRegistry.registerTileEntity(TileEntityBrickFurnace.class,
				"TileEntityBrickFurnace");
		GameRegistry.addRecipe(new ItemStack(smelter, 1), "xxx", "x0x", "xxx",
				'x', new ItemStack(Blocks.brick_block, 1));
				
		ItemMetadataFood.addFood(0, 4.5F, "fish_pie", "fish_pie");
		ItemMetadataFood.addFood(0, 2.0F, "jam_pie", "jam_pie");
		ItemMetadataFood.addFood(0, 5.5F, "meat_pie", "meat_pie");
		ItemMetadataFood.addFood(0, 3.0F, "potatoes_pie", "potatoes_pie");
		ItemMetadataFood.addFood(0, 3.0F, "pie_with_onions_and_eggs", "pie_with_onions_and_eggs");
		ItemMetadataFood.addFood(0, 3.5F, "sorrel_pie", "sorrel_pie");
		ItemMetadataFood.addFood(0, 0.5F, "dough", "dough");
		ItemMetadataFood.addFood(0, 2.0F, "dough2", "pitcher_dough");
		ItemMetadataFood.addFood(0, 1.0F, "bun", "bun");
		ItemMetadataFood.addFood(0, 1.5F, "bun_jam", "bun_jam");
		ItemMetadataFood.addFood(0, 2.0F, "bun_jam2", "bun_jam2");
		ItemMetadataFood.addFood(0, 1.0F, "curd", "curd");
		ItemMetadataFood.addFood(0, 1.0F, "fritters", "fritters");
		ItemMetadataFood.addFood(0, 0.5F, "sorrel", "sorrel");
		ItemMetadataFood.addFood(0, 0.5F, "onion", "onion");
		ItemMetadataFood.addFood(0, 1.5F, "pancake", "pancake");
		ItemMetadataFood.addFood(0, 1.5F, "pancake_with_cottage_cheese", "pancake_with_cottage_cheese");
		ItemMetadataFood.addFood(0, 2.5F, "pancake_with_curd", "pancake_with_curd");
		ItemMetadataFood.addFood(0, 4.5F, "pancake_with_fish", "pancake_with_fish");
		ItemMetadataFood.addFood(0, 2.5F, "pancake_with_jam", "pancake_with_jam");
		ItemMetadataFood.addFood(0, 2.5F, "pancake_with_jam2", "pancake_with_jam2");
		ItemMetadataFood.addFood(0, 5.5F, "pancake_with_meat", "pancake_with_meat");
		ItemMetadataFood.addFood(0, 2.5F, "pancake_with_onions_and_eggs", "pancake_with_onions_and_eggs");
		ItemMetadataFood.addFood(0, 3.0F, "pancake_with_potatoes", "pancake_with_potatoes");
		ItemMetadataFood.addFood(0, 2.0F, "pancake_with_sorrel", "pancake_with_sorrel");
		
		ifood = new ItemMetadataFood().setUnlocalizedName("food").setCreativeTab(Nord.food);
        	GameRegistry.registerItem(ifood,"food");
        	
        	

	}

	@EventHandler
	public void postInit(final FMLPostInitializationEvent event) {
		packetPipeline.postInitialise();
		tabPostInit();
		
		GameRegistry.addRecipe(ItemMetadataFood.getFood("jam_pie"), "_x_", "_x_", "_y_", 
				  'x', new ItemStack(Items.apple, 2),'y',new ItemStack(Items.milk_bucket, 1));
		
		GameRegistry.addRecipe(ItemMetadataFood.getFood("fish_pie"), "___", "_x_", "_y_",
				  'x', new ItemStack(Items.cooked_fished, 1),'y',new ItemStack(Items.milk_bucket, 1));
		   GameRegistry.addRecipe(ItemMetadataFood.getFood("fish_pie"), "_x_", "_y_", "___",
				  'x', new ItemStack(Items.cooked_fished, 1),'y',new ItemStack(Items.milk_bucket, 1));
		
		GameRegistry.addRecipe(ItemMetadataFood.getFood("meat_pie"), "___", "_x_", "_y_",
				  'x', new ItemStack(Items.cooked_beef, 1),'y',new ItemStack(Items.milk_bucket, 1));
		   GameRegistry.addRecipe(ItemMetadataFood.getFood("meat_pie"), "_x_", "_y_", "___",
				  'x', new ItemStack(Items.cooked_beef, 1),'y',new ItemStack(Items.milk_bucket, 1));
		   
		GameRegistry.addRecipe(ItemMetadataFood.getFood("potatoes_pie"), "___", "_x_", "_y_",
				  'x', new ItemStack(Items.potato, 1),'y',new ItemStack(Items.milk_bucket, 1));
			GameRegistry.addRecipe(ItemMetadataFood.getFood("potatoes_pie"), "_x_", "_y_", "___",
				  'x', new ItemStack(Items.potato, 1),'y',new ItemStack(Items.milk_bucket, 1)); 			
		
		GameRegistry.addRecipe(ItemMetadataFood.getFood("dough"), "___", "___", "xyx",
				  'x', new ItemStack(Items.egg, 2),'y',new ItemStack(Items.milk_bucket, 1));
		   GameRegistry.addRecipe(ItemMetadataFood.getFood("dough"), "xyx", "___", "___",
				  'x', new ItemStack(Items.egg, 2),'y',new ItemStack(Items.milk_bucket, 1));
		   GameRegistry.addRecipe(ItemMetadataFood.getFood("dough"), "___", "xyx", "___",
				  'x', new ItemStack(Items.egg, 2),'y',new ItemStack(Items.milk_bucket, 1));
		   
		GameRegistry.addRecipe(ItemMetadataFood.getFood("pitcher_dough"), "___", "___", "yxy",
				  'x', new ItemStack(Items.egg, 1),'y',new ItemStack(Items.milk_bucket, 2));
		   GameRegistry.addRecipe(ItemMetadataFood.getFood("pitcher_dough"), "yxy", "___", "___",
				  'x', new ItemStack(Items.egg, 1),'y',new ItemStack(Items.milk_bucket, 2));
		   GameRegistry.addRecipe(ItemMetadataFood.getFood("pitcher_dough"), "___", "yxy", "___",
				  'x', new ItemStack(Items.egg, 1),'y',new ItemStack(Items.milk_bucket, 2));
		   		  
		GameRegistry.addRecipe(ItemMetadataFood.getFood("bun"), "___", "_x_", "_y_", 
				  'x', new ItemStack(Items.egg, 1),'y',new ItemStack(Items.milk_bucket,1));
		   
		GameRegistry.addRecipe(ItemMetadataFood.getFood("bun_jam"), "___", "zx_", "_y_", 
				  'x', new ItemStack(Items.egg, 1),'y',new ItemStack(Items.milk_bucket, 1),'z',new ItemStack(Items.apple, 1));
		   
	    GameRegistry.addRecipe(ItemMetadataFood.getFood("bun_jam2"), "___", "zxz", "_y_", 
				  'x', new ItemStack(Items.egg, 1),'y',new ItemStack(Items.milk_bucket, 1),'z',new ItemStack(Items.apple, 2));
		   
		GameRegistry.addRecipe(ItemMetadataFood.getFood("curd"), "_x_", "___", "___",  
				  'x', new ItemStack(Items.milk_bucket, 1));
		    GameRegistry.addRecipe(ItemMetadataFood.getFood("curd"), "___", "_x_", "___",
		    	  'x', new ItemStack(Items.milk_bucket, 1));
		    GameRegistry.addRecipe(ItemMetadataFood.getFood("curd"), "___", "___", "_x_",
		    	  'x', new ItemStack(Items.milk_bucket, 1));
		    
		 GameRegistry.addRecipe(ItemMetadataFood.getFood("fritters"), "_x_", "_x_", "___",  
				  'x', new ItemStack(Items.milk_bucket, 1));
			 GameRegistry.addRecipe(ItemMetadataFood.getFood("fritters"), "___", "_x_", "_x_",
			      'x', new ItemStack(Items.milk_bucket, 1));
		   
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
