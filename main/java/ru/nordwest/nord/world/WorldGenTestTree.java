package ru.nordwest.nord.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenTestTree extends WorldGenTrees {

	public int minTreeHeight;
	public boolean vinesGrow;
	public int metaLeaves;
	public int metaWood;

	public WorldGenTestTree(boolean p_i2027_1_) {
		super(p_i2027_1_);
		
        this.minTreeHeight = 10;
        this.metaWood = 0;
        this.metaLeaves = 0;
        this.vinesGrow = false;
	}
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
    {
		int height = rand.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

        if (y >= 1 && y + height + 1 <= 256) // Высота пригодна для роста дерева?
        {
            byte b0;
            int currentY;
            Block block;

            for (int i1 = y; i1 <= y + 1 + height; ++i1)
            {
                b0 = 1;

                if (i1 == y)
                {
                    b0 = 0;
                }

                if (i1 >= y + 1 + height - 2)
                {
                    b0 = 2;
                }

                for (int j1 = x - b0; j1 <= x + b0 && flag; ++j1)
                {
                    for (currentY = z - b0; currentY <= z + b0 && flag; ++currentY)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            block = world.getBlock(j1, i1, currentY);

                            if (!this.isReplaceable(world, j1, i1, currentY))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            
            Block block2 = world.getBlock(x, y - 1, z);

            boolean isSoil = block2.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling)Blocks.sapling);
            if (isSoil && y < 256 - height - 1)
            {
                block2.onPlantGrow(world, x, y - 1, z, x, y, z);
                b0 = 6; // Высота (от начала ствола), на которой начинает генерироваться листва
                int l1; // Радиус кроны
                int i2;
                int j2;
                int i3;

                for (currentY = y - b0 + height; currentY <= y + height; ++currentY) // Проходимся по стволу и генерируем листву
                {
                    i3 = currentY - (y + height);
                    l1 = 1 - i3 / 2 + rand.nextInt(3); // Радиус кроны на i3 от верхней точки ствола

                    for (i2 = x - l1; i2 <= x + l1; ++i2)
                    {
                        j2 = i2 - x;

                        for (int k2 = z - l1; k2 <= z + l1; ++k2)
                        {
                            int l2 = k2 - z;

                            if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || rand.nextInt(2) != 0 && i3 != 0)
                            {
                                Block block1 = world.getBlock(i2, currentY, k2);

                                if (block1.isAir(world, i2, currentY, k2) || block1.isLeaves(world, i2, currentY, k2))
                                {
                                    this.setBlockAndNotifyAdequately(world, i2, currentY, k2, Blocks.leaves, this.metaLeaves);
                                }
                            }
                        }
                    }
                }

                for (currentY = 0; currentY < height; ++currentY)
                {
                    block = world.getBlock(x, y + currentY, z);

                    if (block.isAir(world, x, y + currentY, z) || block.isLeaves(world, x, y + currentY, z))
                    {
                        this.setBlockAndNotifyAdequately(world, x, y + currentY, z, Blocks.log, this.metaWood);
                    }
                }
            }
            
            return true;
        } // if (y >= 1 && y + height + 1 <= 256)
        
        return false;
    } // generate()
}
