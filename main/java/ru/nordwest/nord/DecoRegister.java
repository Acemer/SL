package ru.nordwest.nord;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.nordwest.nord.block.BaseDecoStoneBlock;
import ru.nordwest.nord.block.CandleBlock;
import ru.nordwest.nord.block.LightLampBlock;
import ru.nordwest.nord.block.OilLampBlock;
import ru.nordwest.nord.item.ItemBlockMetadata;
import ru.nordwest.nord.item.ItemColorMetaDataBlock;
import ru.nordwest.nord.item.ItemDecoStoneBlock;

public class DecoRegister {
        static public void init() {
                // Блоки камня.
                // Определитесь уже, пишите вы комментарии на русском языке или английском. Английский предпочтительнее - GitHub его любит :)
                for (int i = 0; i < Nord.buildingBlocks.length; i++) {
                        Nord.buildingBlocks[i] = new BaseDecoStoneBlock(Material.rock, i)
                                .setBlockName("stone_" + i).setCreativeTab(Nord.blocksTab)
                                .setBlockTextureName("stone");
                        GameRegistry.registerBlock(Nord.buildingBlocks[i], ItemDecoStoneBlock.class,
                                "stone_" + i);
                }

                // Фонари
                for (int i = 0; i < Nord.lamps.length; i++) {
                        Nord.lamps[i] = new LightLampBlock(Material.glass, i)
                                .setBlockName("flashlight_" + i)
                                .setCreativeTab(Nord.lightingTab)
                                .setBlockTextureName("flashlight")
                                .setStepSound(Block.soundTypeSnow).setLightLevel(0.9375F);
                        GameRegistry.registerBlock(Nord.lamps[i],
                                ItemColorMetaDataBlock.class, "flashlight_" + i);
                }

                // Масляная лампа
                Nord.oil_lamp = new OilLampBlock(Material.glass)
                        .setBlockName("oil_lamp").setCreativeTab(Nord.lightingTab)
                        .setBlockTextureName("oil_lamp")
                        .setStepSound(Block.soundTypeGlass).setLightLevel(0.9375F);
                GameRegistry.registerBlock(Nord.oil_lamp, ItemBlockMetadata.class,
                        "oil_lamp");

                // Свечи
                Nord.candle = new CandleBlock().setBlockName("candle")
                        .setCreativeTab(Nord.lightingTab).setBlockTextureName("candle")
                        .setStepSound(Block.soundTypeSnow).setLightLevel(0.9375F);
                GameRegistry.registerBlock(Nord.candle, ItemColorMetaDataBlock.class,
                        "candle");

        }
}
