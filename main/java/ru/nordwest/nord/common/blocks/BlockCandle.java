package ru.nordwest.nord.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;

import java.util.List;
import java.util.Random;

public class BlockCandle extends BlockMetadata {

        private IIcon texture;

        public BlockCandle() {
                super(Material.cloth, 16);
                this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.5F, 0.625F);
        }

        @Override
        public int getRenderType() {
                return Nord.CandleRendererID;
        }

        @Override
        public boolean isOpaqueCube() {
                return false;
        }

        @Override
        public boolean renderAsNormalBlock() {
                return false;
        }

        @Override
        public void randomDisplayTick(final World par1World, final int x,
                                      final int y, final int z, final Random random) {
                final double a = x + 0.5F;
                final double b = y + 0.7F;
                final double c = z + 0.5F;

                par1World.spawnParticle("smoke", a, b, c, 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", a, b, c, 0.0D, 0.0D, 0.0D);
        }

        @Override
        public MovingObjectPosition collisionRayTrace(final World par1World,
                                                      final int par2, final int par3, final int par4,
                                                      final Vec3 par5Vec3, final Vec3 par6Vec3) {
                this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.5F, 0.625F);
                return super.collisionRayTrace(par1World, par2, par3, par4, par5Vec3,
                        par6Vec3);
        }

        @Override
        public void addCollisionBoxesToList(final World par1World, final int par2,
                                            final int par3, final int par4,
                                            final AxisAlignedBB par5AxisAlignedBB, final List par6List,
                                            final Entity par7Entity) {
                this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.5F, 0.625F);
                super.addCollisionBoxesToList(par1World, par2, par3, par4,
                        par5AxisAlignedBB, par6List, par7Entity);
        }

        private boolean canPlaceCandleOn(final World par1World, final int par2,
                                         final int par3, final int par4) {
                if (World.doesBlockHaveSolidTopSurface(par1World, par2, par3, par4)) {
                        return true;
                } else {
                        final Block block = par1World.getBlock(par2, par3, par4);
                        return (block.canPlaceTorchOnTop(par1World, par2, par3, par4));
                }
        }

        @Override
        public boolean canPlaceBlockAt(final World par1World, final int par2,
                                       final int par3, final int par4) {
                return this.canPlaceCandleOn(par1World, par2, par3 - 1, par4);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int getRenderColor(final int meta) {
                return Nord.colors[15 - meta];
        }

        @Override
        public int colorMultiplier(final IBlockAccess par1IBlockAccess,
                                   final int x, final int y, final int z) {
                return this.getRenderColor(par1IBlockAccess.getBlockMetadata(x, y, z));
        }

        public void onNeighborBlockChange(final World par1World, final int x,
                                          final int y, final int z, final int par5) {
                if (!this.canPlaceCandleOn(par1World, x, y - 1, z)) {
                        this.dropBlockAsItem(par1World, x, y, z,
                                par1World.getBlockMetadata(x, y, z), 0);
                        par1World.setBlockToAir(x, y, z);
                }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void registerBlockIcons(final IIconRegister par1IconRegister) {
                this.texture = par1IconRegister.registerIcon(Nord.MODID + ":candle/"
                        + this.getTextureName());
        }

        @Override
        @SideOnly(Side.CLIENT)
        public IIcon getIcon(final int side, final int meta) {
                return this.texture;
        }

}