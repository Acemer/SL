package ru.nordwest.nord.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;

import java.util.List;

public class ItemBag extends ItemBagBase {
        public ItemBag() {
                super();
                this.setUnlocalizedName("bag");
        }

        private IIcon[] background, ribbon;

        @Override
        @SideOnly(Side.CLIENT)
        public void getSubItems(Item item, CreativeTabs tab, List subItems) {
                int r, g, b;
                for (int tier = 0; tier < 4; tier++) {
                        r = 50 + Nord.rand.nextInt(180);
                        g = 50 + Nord.rand.nextInt(180);
                        b = 50 + Nord.rand.nextInt(180);
                        for (int size = 0; size < 4; size++) {
                                ItemStack itemStack = new ItemStack(item, 1, tier * 10 + size);
                                setTagCompound(itemStack, r, g, b);
                                subItems.add(itemStack);
                        }
                }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public String getUnlocalizedName(ItemStack itemStack) {
                int index = itemStack.getItemDamage() % 10;
                if (index > 3) index = 3;
                else if (index < 0) index = 0;
                return "item." + RegisterBags.SIZES[index] + "_" + this.getUnlocalizedName().substring(5);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List information, boolean advancedTooltip) {
                int index = itemStack.getItemDamage() / 10;
                if (index > 3) index = 3;
                else if (index < 0) index = 0;
                information.add(
                        RegisterBags.TIERCOLORS[index] + "§o" + StatCollector.translateToLocal("tier." + RegisterBags.TIER[index])
                );
                if (itemStack.getTagCompound() != null && itemStack.getTagCompound().getBoolean("generated")) {
                        if (itemStack.getTagCompound().getBoolean("closed"))
                                information.add(
                                        "§o" + StatCollector.translateToLocal("bags.tip.closed." + itemStack.getTagCompound().getInteger("tipNumber"))
                                );
                        else {
                                information.add(
                                        "§o" + StatCollector.translateToLocal("bags.tip.opened.line1")
                                );
                                information.add(
                                        "§o" + StatCollector.translateToLocal("bags.tip.opened.line2")
                                );
                        }
                        addSpecialInformation(itemStack, entityPlayer, information);
                }
        }

        @SideOnly(Side.CLIENT)
        public boolean requiresMultipleRenderPasses() {
                return true;
        }

        @Override
        public IIcon getIconFromDamageForRenderPass(int damage, int pass) {
                int background = damage % 10, ribbon = damage / 10;
                if (background > 3) background = 3;
                else if (background < 0) background = 0;
                if (ribbon > 3) ribbon = 3;
                else if (ribbon < 0) ribbon = 0;
                switch (pass) {
                        case 0:
                                return this.background[background];
                        case 1:
                                return this.ribbon[ribbon];
                }
                return null;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int getColorFromItemStack(ItemStack itemStack, int pass) {
                if (pass == 0 && itemStack.getTagCompound() != null) {
                        return itemStack.getTagCompound().getInteger("color");
                }
                return ((255 << 16) | (255 << 8) | 255);
        }

        @Override
        public void registerIcons(IIconRegister iconRegister) {
                background = new IIcon[4];
                ribbon = new IIcon[4];
                for (int i = 0; i < 4; i++) {
                        this.background[i] = iconRegister.registerIcon(Nord.MODID + ":bags/backgrounds/"
                                + RegisterBags.SIZES[i] + "Background");
                        for (int k = 0; k < 4; k++)
                                this.ribbon[k] = iconRegister.registerIcon(Nord.MODID + ":bags/ribbons/"
                                        + RegisterBags.TIER[k] + "Ribbon");
                }
        }

        /* Following method is necessary for GUI to work! */
        @Override
        public int getMaxItemUseDuration(ItemStack itemStack) {
                return 1;
        }

        @Override
        public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
                if (!world.isRemote) {
                        if (!player.isSneaking() && itemStack.getTagCompound() != null && itemStack.getTagCompound().getBoolean("generated")) {
                                player.openGui(Nord.instance, Nord.guiIDBag, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
                                itemStack.getTagCompound().setBoolean("closed", false);
                        }
                }
                return itemStack;
        }
}

