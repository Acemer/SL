package ru.nordwest.nord;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import ru.nordwest.nord.block.*;
import ru.nordwest.nord.common.recipe.FlowingRecipes1I2O;
import ru.nordwest.nord.common.recipe.SmelterRecipes2I2O;
import ru.nordwest.nord.common.tileentity.*;
import ru.nordwest.nord.item.ItemMetallBlock;

public class MachineRegister {
        public static void init() {
                Nord.smelter = new SmelterBlock().setBlockName("smelter")
                        .setCreativeTab(Nord.metalsTab).setBlockTextureName("smelter")
                        .setHardness(3.0F).setHardness(5.0F);
                GameRegistry.registerBlock(Nord.smelter, ItemMetallBlock.class, "smelter");
                GameRegistry.registerTileEntity(TileEntitySmelter2.class,
                        "TileEntitySmelter");
                GameRegistry.addRecipe(new ItemStack(Nord.smelter, 1), "xyx", "y0y", "yyy",
                        'x', new ItemStack(Blocks.furnace, 1), 'y', new ItemStack(
                                Blocks.cobblestone, 1));

                Nord.brickFurnace = new BrickFurnaceBlock().setBlockName("brickFurnace")
                        .setCreativeTab(Nord.metalsTab).setBlockTextureName("brickFurnace")
                        .setHardness(3.0F).setHardness(5.0F);
                GameRegistry.registerBlock(Nord.brickFurnace, ItemMetallBlock.class, "brickFurnace");
                GameRegistry.registerTileEntity(TileEntityBrickFurnace.class,
                        "TileEntityBrickFurnace");
                GameRegistry.addRecipe(new ItemStack(Nord.smelter, 1), "xxx", "x0x", "xxx",
                        'x', new ItemStack(Blocks.brick_block, 1));

                Nord.flowingBlock = new FlowingBlock();
                GameRegistry.registerBlock(Nord.flowingBlock, ItemBlock.class, "flowingBlock");
                GameRegistry.registerTileEntity(TileEntityFlowing.class, "TileEntityFlowing");
                GameRegistry.addRecipe(new ItemStack(Nord.flowingBlock, 1), "xxx", "x x", "xxx", // TODO fix recipe
                        'x', new ItemStack(Blocks.stone, 1));

                Nord.greatFurnace = new GreatFurnaceBlock();
                GameRegistry.registerBlock(Nord.greatFurnace, ItemBlock.class, "greatFurnace");
                GameRegistry.registerTileEntity(TileEntityGreatFurnace.class, "TileEntityGreatFurnace");

                Nord.greatFurnaceTech = new GreatFurnaceBlockTech();
                GameRegistry.registerBlock(Nord.greatFurnaceTech, ItemBlock.class, "greatFurnaceTech");
                GameRegistry.registerTileEntity(TileEntityGreatFurnaceTech.class, "TileEntityGreatFurnaceTech");

                Nord.foundryTech = new FoundryBlockTech();
                GameRegistry.registerBlock(Nord.foundryTech, ItemBlock.class, "FoundryBlockTech");
                GameRegistry.registerTileEntity(TileEntityFoundry.class, "TileEntityFoundry");
                ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFoundry.class, new TileEntityRendererFoundry());

                Nord.foundry = new Foundry();
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
