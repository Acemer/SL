package ru.nordwest.nord.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import ru.nordwest.nord.CommonProxy;
import net.minecraftforge.client.MinecraftForgeClient;
import ru.nordwest.nord.client.renders.BlockCandleRenderer;


public class ClientProxy extends CommonProxy {
        
        @Override
        public void registerRenderers() {
        	 RenderingRegistry.registerBlockHandler(new BlockCandleRenderer());
                // This is for rendering entities and so forth later on
        }
        
        
}