package ru.nordwest.nord.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.client.lib.tabs.TabsNord;
import ru.nordwest.nord.common.blocks.abstracts.BlockAbstractMachina;
import ru.nordwest.nord.common.tiles.TileBrickFurnace;

import java.util.Random;

public class BlockBrickFurnace extends BlockAbstractMachina {
        public BlockBrickFurnace() {
            super(Material.rock);
            setHardness(2.0F);
            setResistance(5.0F);
            setBlockName("brickFurnace");
            setCreativeTab(TabsNord.tabGeneral);
        }

        @Override
        public void registerBlockIcons(IIconRegister iconRegister) {
            this.blockIcon = iconRegister.registerIcon(Nord.MODID + ":brickFurnace/center");
            this.iconFront = iconRegister.registerIcon(Nord.MODID + ":brickFurnace/command_block_off");
            this.iconFrontWork = iconRegister.registerIcon(Nord.MODID + ":brickFurna/cecommand_block_on");
            this.iconTop = iconRegister.registerIcon(Nord.MODID + ":brickFurnace/center");
            this.iconDown = iconRegister.registerIcon(Nord.MODID + ":brickFurnace/center");
        }

        @Override
        public boolean onBlockActivated(World world, int x, int y, int z,
                                    EntityPlayer player, int metadata, float what, float these, float are) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if (tileEntity == null || player.isSneaking()) {
               return false;
            }
            player.openGui(Nord.instance, Nord.guiIDBrickFurnace, world, x, y, z);
            return true;
        }

        @Override
        public TileEntity createNewTileEntity(World world, int i) {
                return new TileBrickFurnace();
        }
}
