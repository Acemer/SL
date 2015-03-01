package ru.nordwest.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;

public class BlockFoundry extends Block {

        public BlockFoundry() {
                super(Material.rock);
                setHardness(2.0F);
                setResistance(5.0F);
                setBlockName("FoundryBlock");
                setBlockTextureName(Nord.MODID + ":flowing/hogger_down");
                setCreativeTab(CreativeTabs.tabRedstone);
        }

        @Override
        public boolean canPlaceBlockAt(World world, int x, int y, int z) {
                return Nord.foundryTech.canPlaceBlockAt(world, x, y, z);
        }

        @Override
        public void onBlockAdded(World world, int x, int y, int z) {
                world.setBlock(x, y, z, Nord.foundryTech);
        }
}
