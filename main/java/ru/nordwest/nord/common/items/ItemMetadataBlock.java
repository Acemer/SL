package ru.nordwest.nord.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemMetadataBlock extends ItemBlock {
        private final Block block;

        public ItemMetadataBlock(final Block block) {
                super(block);
                this.block = block;
                this.setMaxDamage(0);
                this.setHasSubtypes(true);
        }

        @Override
        public String getUnlocalizedName(final ItemStack itemStack) {
                return super.getUnlocalizedName() + "." + itemStack.getItemDamage();
        }

        @Override
        public int getMetadata(final int meta) {
                return meta;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public IIcon getIconFromDamage(final int meta) {
                return this.block.getIcon(0, meta);
        }
}
