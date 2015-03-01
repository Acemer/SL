package ru.nordwest.nord.common.blocks;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.client.lib.tabs.TabsNord;
import ru.nordwest.nord.common.items.ItemMetalBlock;
import ru.nordwest.nord.common.lib.recipes.FlowingRecipes1I2O;
import ru.nordwest.nord.common.lib.recipes.SmelterRecipes2I2O;
import ru.nordwest.nord.common.tiles.*;

public class RegisterMachine {
        public static void init() {
                Nord.smelter = new BlockSmelter().setBlockName("smelter")
                        .setCreativeTab(TabsNord.tabMetals).setBlockTextureName("smelter")
                        .setHardness(3.0F).setHardness(5.0F);
                GameRegistry.registerBlock(Nord.smelter, ItemMetalBlock.class, "smelter");
                GameRegistry.registerTileEntity(TileSmelter2.class,
                        "TileEntitySmelter");
                GameRegistry.addRecipe(new ItemStack(Nord.smelter, 1), "xyx", "y0y", "yyy",
                        'x', new ItemStack(Blocks.furnace, 1), 'y', new ItemStack(
                                Blocks.cobblestone, 1));

                Nord.brickFurnace = new BlockBrickFurnace().setBlockName("brickFurnace")
                        .setCreativeTab(TabsNord.tabMetals).setBlockTextureName("brickFurnace")
                        .setHardness(3.0F).setHardness(5.0F);
                GameRegistry.registerBlock(Nord.brickFurnace, ItemMetalBlock.class, "brickFurnace");
                GameRegistry.registerTileEntity(TileBrickFurnace.class,
                        "TileEntityBrickFurnace");
                GameRegistry.addRecipe(new ItemStack(Nord.smelter, 1), "xxx", "x0x", "xxx",
                        'x', new ItemStack(Blocks.brick_block, 1));

                Nord.flowingBlock = new BlockFlowing();
                GameRegistry.registerBlock(Nord.flowingBlock, ItemBlock.class, "flowingBlock");
                GameRegistry.registerTileEntity(TileFlowing.class, "TileEntityFlowing");
                GameRegistry.addRecipe(new ItemStack(Nord.flowingBlock, 1), "xxx", "x x", "xxx", // TODO fix recipe
                        'x', new ItemStack(Blocks.stone, 1));

                Nord.greatFurnace = new BlockGreatFurnace();
                GameRegistry.registerBlock(Nord.greatFurnace, ItemBlock.class, "greatFurnace");
                GameRegistry.registerTileEntity(TileGreatFurnace.class, "TileEntityGreatFurnace");

                Nord.greatFurnaceTech = new BlockGreatFurnaceTech();
                GameRegistry.registerBlock(Nord.greatFurnaceTech, ItemBlock.class, "greatFurnaceTech");
                GameRegistry.registerTileEntity(TileGreatFurnaceTech.class, "TileEntityGreatFurnaceTech");

                Nord.foundryTech = new BlockFoundryTech();
                GameRegistry.registerBlock(Nord.foundryTech, ItemBlock.class, "FoundryBlockTech");
                GameRegistry.registerTileEntity(TileFoundry.class, "TileEntityFoundry");
                ClientRegistry.bindTileEntitySpecialRenderer(TileFoundry.class, new TileRendererFoundry());

                Nord.foundry = new BlockFoundry();
                GameRegistry.registerBlock(Nord.foundry, ItemBlock.class, "FoundryBlock");
                recipesInit();
        }

        public static void recipesInit() {
                FlowingRecipes1I2O.addRecipe(
                        new ItemStack(Item.getItemFromBlock(Blocks.stone), 1),
                        new ItemStack(Item.getItemFromBlock(Blocks.dirt), 2),
                        new ItemStack(Items.diamond, 5),
                        60, 30, 5);
                SmelterRecipes2I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.stone), 1),
                        new ItemStack(Item.getItemFromBlock(Blocks.stone), 1),
                        new ItemStack(Item.getItemFromBlock(Blocks.dirt), 1),
                        new ItemStack(Items.diamond, 5),
                        400, 30, 5, true);
        }

}
