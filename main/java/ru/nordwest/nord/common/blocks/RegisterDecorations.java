package ru.nordwest.nord.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.client.lib.tabs.TabsNord;
import ru.nordwest.nord.common.items.ItemDecoStoneBlock;
import ru.nordwest.nord.common.items.ItemMetadataBlock;
import ru.nordwest.nord.common.items.ItemMetadataColorBlock;

public class RegisterDecorations {
        static public void init() {
                // Блоки камня.
                // Определитесь уже, пишите вы комментарии на русском языке или английском. Английский предпочтительнее - GitHub его любит :)
                for (int i = 0; i < Nord.buildingBlocks.length; i++) {
                        Nord.buildingBlocks[i] = new BlockDecorationStone(Material.rock, i)
                                .setBlockName("stone_" + i)
                                .setCreativeTab(TabsNord.tabBlocks)
                                .setBlockTextureName("stone");
                        GameRegistry.registerBlock(Nord.buildingBlocks[i], ItemDecoStoneBlock.class,
                                "stone_" + i);
                }

                // Фонари
                for (int i = 0; i < Nord.lamps.length; i++) {
                        Nord.lamps[i] = new BlockLamp(Material.glass, i)
                                .setBlockName("lamp_" + i)
                                .setCreativeTab(TabsNord.tabLighting)
                                .setBlockTextureName("lamp")
                                .setStepSound(Block.soundTypeSnow)
                                .setLightLevel(0.9375F);
                        GameRegistry.registerBlock(Nord.lamps[i],
                                ItemMetadataColorBlock.class, "lamp_" + i);
                }

                // Масляная лампа
                Nord.oil_lamp = new BlockOilLamp(Material.glass)
                        .setBlockName("oil_lamp")
                        .setCreativeTab(TabsNord.tabLighting)
                        .setBlockTextureName("oil_lamp")
                        .setStepSound(Block.soundTypeGlass)
                        .setLightLevel(0.9375F);
                GameRegistry.registerBlock(Nord.oil_lamp, ItemMetadataBlock.class,
                        "oil_lamp");

                // Свечи
                Nord.candle = new BlockCandle().setBlockName("candle")
                        .setCreativeTab(TabsNord.tabLighting)
                        .setBlockTextureName("candle")
                        .setStepSound(Block.soundTypeSnow)
                        .setLightLevel(0.9375F);
                GameRegistry.registerBlock(Nord.candle, ItemMetadataColorBlock.class,
                        "candle");

        }
}
