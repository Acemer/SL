package ru.nordwest.nord.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.client.lib.tabs.TabsNord;

import java.util.List;

public class ItemBag extends Item {
        public ItemBag() {
                super();
                this.maxStackSize = 1;
                this.hasSubtypes = true;
                this.setCreativeTab(TabsNord.tabBagsAndGifts);
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
        public void onUpdate(ItemStack itemStack, World world, Entity entity, int metadata, boolean bool) {
                if (itemStack.stackTagCompound == null) setTagCompound(itemStack);
                if (!itemStack.getTagCompound().getBoolean("generated")) {
                        BagContent.generateInventory(itemStack);
                        itemStack.getTagCompound().setBoolean("generated", true);
                }
                if (!BagContent.validateInventory(itemStack)) {
                        itemStack.stackSize = 0;
                }
        }

        public static void setTagCompound(ItemStack itemStack) {
                setTagCompound(itemStack, 50 + Nord.rand.nextInt(180), 50 + Nord.rand.nextInt(180), 50 + Nord.rand.nextInt(180));
        }

        public static void setTagCompound(ItemStack itemStack, int r, int g, int b) {
                if (itemStack.stackTagCompound == null) {
                        NBTTagCompound nbt = new NBTTagCompound();
                        nbt.setInteger("color", (r << 16) | (g << 8) | (b));
                        nbt.setInteger("tipNumber", Nord.rand.nextInt(3));
                        nbt.setBoolean("closed", true);
                        nbt.setBoolean("generated", false);
                        itemStack.setTagCompound(nbt);
                }
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List information, boolean advancedTooltip) {
                information.add(
                        RegisterBags.TIERCOLORS[itemStack.getItemDamage() / 10] + "§o" + StatCollector.translateToLocal("tier." + RegisterBags.TIER[itemStack.getItemDamage() / 10])
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
                        if (entityPlayer.capabilities.isCreativeMode) {
                                if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                                        information.add(
                                                "§b§o" + "Debug Info:"
                                        );
                                        information.add(
                                                "§b" + "Color: " + itemStack.getTagCompound().getInteger("color")
                                        );
                                        information.add(
                                                "§b" + "(R: " + ((itemStack.getTagCompound().getInteger("color") & 0xFF0000) >> 16)
                                                        + "/G: " + ((itemStack.getTagCompound().getInteger("color") & 0xFF00) >> 8)
                                                        + "/B: " + (itemStack.getTagCompound().getInteger("color") & 0xFF) + ")"
                                        );
                                        information.add(
                                                "§b" + "Closed: " + itemStack.getTagCompound().getBoolean("closed")
                                        );
                                        information.add(
                                                "§b" + "Generated: " + itemStack.getTagCompound().getBoolean("generated")
                                        );
                                } else {
                                        information.add(
                                                "§b§o" + "Press SHIFT to see more information!"
                                        );
                                }
                        }
                }
        }

        @SideOnly(Side.CLIENT)
        public boolean requiresMultipleRenderPasses() {
                return true;
        }

        @Override
        public IIcon getIconFromDamageForRenderPass(int damage, int pass) {
                switch (pass) {
                        case 0:
                                return this.background[damage % 10];
                        case 1:
                                return this.ribbon[damage / 10];
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

        @Override
        @SideOnly(Side.CLIENT)
        public String getUnlocalizedName(ItemStack itemStack) {
                return "item." + RegisterBags.SIZES[itemStack.getItemDamage() % 10] + "_" + this.getUnlocalizedName().substring(5);
        }
}
