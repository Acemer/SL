package ru.nordwest.nord.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.nordwest.nord.Nord;

public class BaseBlock extends Block {

        public BaseBlock(final Material par2Material) {
                super(par2Material);
                this.setCreativeTab(Nord.generalTab);
        }
}
