package ru.nordwest.nord.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;

import java.util.List;

public class ItemBagSpecial extends ItemBagBase {
        public ItemBagSpecial() {
                super();
                this.setUnlocalizedName("bagSpecial");
        }

        private IIcon[] icon;

        @Override
        @SideOnly(Side.CLIENT)
        public void getSubItems(Item item, CreativeTabs tab, List subItems) {
                ItemStack itemStack = new ItemStack(item, 1, 0);
                setTagCompound(itemStack, 196, 74, 85);
                subItems.add(itemStack);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public String getUnlocalizedName(ItemStack itemStack) {
                int index = itemStack.getItemDamage();
                if (index > 0) index = 0;
                else if (index < 0) index = 0;
                return "item." + RegisterBags.NAMES[index] + "_" + this.getUnlocalizedName().substring(5);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List information, boolean advancedTooltip) {
                information.add(
                        RegisterBags.TIERCOLORS[3] + "§o" + StatCollector.translateToLocal("tier." + RegisterBags.TIER[3])
                );
                if (itemStack.getTagCompound() != null && itemStack.getTagCompound().getBoolean("generated")) {
                        if (itemStack.getTagCompound().getBoolean("closed"))
                                information.add(
                                        "§c§o" + StatCollector.translateToLocal("bags.tip.closed.bomber")
                                );
                        else {
                                information.add(
                                        "§o" + StatCollector.translateToLocal("bags.tip.opened.bomber.line1")
                                );
                                information.add(
                                        "§o" + StatCollector.translateToLocal("bags.tip.opened.bomber.line2")
                                );
                        }
                        addSpecialInformation(itemStack, entityPlayer, information);
                }
        }

        @Override
        public IIcon getIconFromDamage(int damage) {
                if (damage == 0) {
                        return this.icon[0];
                }
                return null;
        }

        @Override
        public void registerIcons(IIconRegister iconRegister) {
                icon = new IIcon[4];
                this.icon[0] = iconRegister.registerIcon(Nord.MODID + ":bags/icons/"
                        + RegisterBags.NAMES[0] + "Icon");
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
                                if (itemStack.getTagCompound().getBoolean("closed"))
                                        if (Nord.rand.nextInt(100) < 90)
                                                world.createExplosion((Entity) null, player.posX, player.posY, player.posZ, 1.5F, false);
                                player.openGui(Nord.instance, Nord.guiIDBag, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
                                itemStack.getTagCompound().setBoolean("closed", false);
                        }
                }
                return itemStack;
        }
}

