package ru.nordwest.nord.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import ru.nordwest.nord.MetalRegister;
import ru.nordwest.nord.Nord;

import java.util.List;
import java.util.Random;

public class BaseMetalOre extends MetadataBlock {
        private IIcon[] texture;
        private int type = 16;
        private final int shift;


        public BaseMetalOre(final Material par2Material, final int shift) {
                super(par2Material, 16);
                this.shift = shift;
                this.type = MetalRegister.ore_list.length > (shift + 16)
                        ? 16
                        : MetalRegister.ore_list.length % 16;
        }

        public int getShift() {
                return this.shift;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void getSubBlocks(final Item item, final CreativeTabs tab,
                                 final List subItems) {
                for (int i = 0; i < this.type; i++) {
                        subItems.add(new ItemStack(item, 1, i));
                }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void registerBlockIcons(final IIconRegister par1IconRegister) {
                this.texture = new IIcon[this.type];
                for (int i = 0; i < this.type; i++) {
                        this.texture[i] = par1IconRegister.registerIcon(Nord.MODID
                                + ":ore/" + MetalRegister.ore_list[this.shift + i]);
                }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public IIcon getIcon(final int side, final int meta) {
                return this.texture[meta];
        }

        @Override
        public int damageDropped(final int meta) {
                return meta + shift;
        }

        @Override
        public float getBlockHardness(World world, int x, int y, int z) {
                return MetalRegister.ores.get(shift + world.getBlockMetadata(x, y, z)).hard;
        }

        @Override
        public Item getItemDropped(int metadata, Random rand, int fortune) {
                final String name = MetalRegister.ores.get(0).name;
                return MetalRegister.getOreDrop(name, 1).getItem();
        }

        @Override
        public int quantityDroppedWithBonus(int i, Random random) {
                if (i > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, random, i))
                        if (random.nextInt(100) < 21 * i)
                                return this.quantityDropped(random) * 2;
                return this.quantityDropped(random);

        }
}

