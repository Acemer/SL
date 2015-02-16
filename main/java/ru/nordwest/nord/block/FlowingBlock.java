package ru.nordwest.nord.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.block.avstrct.AbstractMachina;
import ru.nordwest.nord.common.tileentity.TileEntityFlowing;

public class FlowingBlock extends AbstractMachina {

        public FlowingBlock() {
                super(Material.rock);
                setHardness(2.0F);
                setResistance(5.0F);
                setBlockName("Flowing");
                setCreativeTab(Nord.generalTab);
        }

        @Override
        public boolean onBlockActivated(World world, int x, int y, int z,
                                        EntityPlayer player, int metadata, float what, float these, float are) {
                TileEntity tileEntity = world.getTileEntity(x, y, z);
                if (tileEntity == null || player.isSneaking()) {
                        return false;
                }
                player.openGui(Nord.NordInstance, Nord.guiIDFlowing, world, x, y, z);
                return true;
        }

        @Override
        public TileEntity createNewTileEntity(World world, int meta) {
                return new TileEntityFlowing();
        }

        @Override
        public void registerBlockIcons(IIconRegister iconRegister) {

                this.blockIcon = iconRegister.registerIcon(Nord.MODID + ":flowing/side");
                this.iconFront = iconRegister.registerIcon(Nord.MODID + ":flowing/front");
                this.iconFrontWork = iconRegister.registerIcon(Nord.MODID + ":flowing/front_on");
                this.iconTop = iconRegister.registerIcon(Nord.MODID + ":flowing/top");
                this.iconDown = iconRegister.registerIcon(Nord.MODID + ":flowing/down");
        }

}
