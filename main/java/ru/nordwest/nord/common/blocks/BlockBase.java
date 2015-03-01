package ru.nordwest.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.nordwest.nord.client.lib.tabs.TabsNord;

public class BlockBase extends Block {

        public BlockBase(final Material par2Material) {
                super(par2Material);
                this.setCreativeTab(TabsNord.tabGeneral);
        }
}
