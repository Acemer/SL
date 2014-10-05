package ru.nordwest.nord.client;

import ru.nordwest.nord.client.renders.BlockCandleRenderer;
import ru.nordwest.nord.common.CommonProxy;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerBlockHandler(new BlockCandleRenderer());
	}

}