package ru.nordwest.nord.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import ru.nordwest.nord.common.lib.world.WorldGenTestTree;

import java.util.List;
import java.util.Random;

public class BlockTestTreeSapling extends BlockSapling {

        public BlockTestTreeSapling() {
                float f = 0.4F;
                this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
                this.setCreativeTab(CreativeTabs.tabDecorations);
                this.setBlockName("testTreeSapling");
        }

        @Override
        public void func_149878_d(World p_149878_1_, int p_149878_2_, int p_149878_3_, int p_149878_4_, Random p_149878_5_) {
                if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(p_149878_1_, p_149878_5_, p_149878_2_, p_149878_3_, p_149878_4_))
                        return; // Какая-то непонятная фигня

                int l = p_149878_1_.getBlockMetadata(p_149878_2_, p_149878_3_, p_149878_4_) & 7; // Первые (нижние) 2 бита метаданных блока
                WorldGenerator gen = new WorldGenTestTree(true);
                int i1 = 0;
                int j1 = 0;
                boolean flag = false;

                Block block = Blocks.air;

                p_149878_1_.setBlock(p_149878_2_, p_149878_3_, p_149878_4_, block, 0, 4);

                if (!gen.generate(p_149878_1_, p_149878_5_, p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1)) {
                        p_149878_1_.setBlock(p_149878_2_, p_149878_3_, p_149878_4_, this, l, 4);
                }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
                p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
        }
}
