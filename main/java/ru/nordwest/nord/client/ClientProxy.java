package ru.nordwest.nord.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import ru.nordwest.nord.client.renders.BlockCandleRenderer;
import ru.nordwest.nord.common.CommonProxy;
import ru.nordwest.nord.common.handler.OverlayHandler;

public class ClientProxy extends CommonProxy {

        @Override
        public void registerRenderers() {
                RenderingRegistry.registerBlockHandler(new BlockCandleRenderer());
        }

        @Override
        public void init() {
                MinecraftForge.EVENT_BUS.register(new OverlayHandler());
                FMLCommonHandler.instance().bus().register(new OverlayHandler());
        }
}