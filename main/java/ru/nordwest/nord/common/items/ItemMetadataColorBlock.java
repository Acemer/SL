package ru.nordwest.nord.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemMetadataColorBlock extends ItemMetadataBlock {
        private final Block block;

        public ItemMetadataColorBlock(final Block block) {
                super(block);
                this.block = block;

        }

        @Override
        public String getUnlocalizedName(final ItemStack itemStack) {
                return super.getUnlocalizedName() + "." + itemStack.getItemDamage();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int getColorFromItemStack(final ItemStack item, final int par2) {
                return this.block.getRenderColor(item.getItemDamage());
        }

        @Override
        @SideOnly(Side.CLIENT)
        public boolean requiresMultipleRenderPasses() {
                return true;
        }

}
