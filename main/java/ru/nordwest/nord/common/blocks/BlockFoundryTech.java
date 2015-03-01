package ru.nordwest.nord.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.tiles.TileFoundry;

import java.util.List;

public class BlockFoundryTech extends BlockContainer {
        public IIcon[] icons = new IIcon[6];

        public BlockFoundryTech() {
                super(Material.rock);
                setHardness(2.0F);
                setResistance(5.0F);
                setBlockName("FoundryBlockTech");
                setBlockTextureName(Nord.MODID + ":flowing/hogger_up");
                setCreativeTab(CreativeTabs.tabRedstone);
        }

        @Override
        public TileEntity createNewTileEntity(World world, int meta) {
                return new TileFoundry();
        }

        @Override
        public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int meta) {
                return false;
        }

        @Override
        public boolean renderAsNormalBlock() {
                return false;
        }

        @Override
        public boolean isOpaqueCube() {
                return false;
        }

        // TODO Все переписать, добавить фейковые блоки вместо этого п***ца.
        public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_) {
                float f = 0.375F;
                float f1 = 0.625F;
                float f2 = 0.375F;
                float f3 = 0.625F;

                this.setBlockBounds(f, 0.0F, f2, f1, 3.0F, f3);
                super.addCollisionBoxesToList(world, x, y, z, p_149743_5_, p_149743_6_, p_149743_7_);
        }

        public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z) {
                float f = 0.375F;
                float f1 = 0.625F;
                float f2 = 0.375F;
                float f3 = 0.625F;

                this.setBlockBounds(f, 0.0F, f2, f1, 3.0F, f3);
        }
}
