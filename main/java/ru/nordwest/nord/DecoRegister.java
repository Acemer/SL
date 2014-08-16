package ru.nordwest.nord;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.nordwest.nord.block.BaseDecoStoneBlock;
import ru.nordwest.nord.block.CandleBlock;
import ru.nordwest.nord.block.LightLampBlock;
import ru.nordwest.nord.block.OilLampBlock;
import ru.nordwest.nord.item.ItemBlockMetadata;
import ru.nordwest.nord.item.ItemColorMetaDataBlock;
import ru.nordwest.nord.item.ItemDecoStoneBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class DecoRegister {
	static public void init() {
		// Stone
		for (int i = 0; i < Nord.deco1.length; i++) {
			Nord.deco1[i] = new BaseDecoStoneBlock(Material.rock, i)
					.setBlockName("stone_" + i).setCreativeTab(Nord.tabDeco)
					.setBlockTextureName("stone");
			GameRegistry.registerBlock(Nord.deco1[i], ItemDecoStoneBlock.class,
					"stone_" + i);
		}

		// Фонари
		for (int i = 0; i < Nord.deco2.length; i++) {
			Nord.deco2[i] = new LightLampBlock(Material.cloth, i)
					.setBlockName("flashlight_" + i)
					.setCreativeTab(Nord.flashlights)
					.setBlockTextureName("flashlight")
					.setStepSound(Block.soundTypeSnow).setLightLevel(0.9375F);
			GameRegistry.registerBlock(Nord.deco2[i],
					ItemColorMetaDataBlock.class, "flashlight_" + i);
		}

		// Масляная лампа
		Nord.oil_lamp = new OilLampBlock(Material.glass)
				.setBlockName("oil_lamp").setCreativeTab(Nord.flashlights)
				.setBlockTextureName("oil_lamp")
				.setStepSound(Block.soundTypeGlass).setLightLevel(0.9375F);
		GameRegistry.registerBlock(Nord.oil_lamp, ItemBlockMetadata.class,
				"oil_lamp");

		// Свечи
		Nord.candle = new CandleBlock().setBlockName("candle")
				.setCreativeTab(Nord.flashlights).setBlockTextureName("candle")
				.setStepSound(Block.soundTypeGlass).setLightLevel(0.9375F);
		GameRegistry.registerBlock(Nord.candle, ItemColorMetaDataBlock.class,
				"candle");

	}
}
