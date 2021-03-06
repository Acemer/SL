package ru.nordwest.nord.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class BlockMetadata extends BlockBase {
        private final int meta;
        private IIcon[] texture;

        public BlockMetadata(final Material par2Material, final int meta) {
                super(par2Material);
                this.meta = meta;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void getSubBlocks(final Item item, final CreativeTabs tab,
                                 final List subItems) {
                for (int i = 0; i < this.meta; i++) {
                        subItems.add(new ItemStack(item, 1, i));
                }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void registerBlockIcons(final IIconRegister par1IconRegister) {
                this.texture = new IIcon[this.meta];
                for (int i = 0; i < this.meta; i++) {
                        this.texture[i] = par1IconRegister.registerIcon(this
                                .getTextureName() + "." + i);
                }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public IIcon getIcon(final int side, final int meta) {
                return this.texture[meta];
        }

        @Override
        public int damageDropped(final int meta) {
                return meta;
        }
}
