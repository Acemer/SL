package ru.nordwest.nord.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.client.lib.tabs.TabsNord;

import java.util.List;

public class ItemBagBase extends Item {
        public ItemBagBase() {
                super();
                this.maxStackSize = 1;
                this.hasSubtypes = true;
                this.setCreativeTab(TabsNord.tabBagsAndGifts);
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

        public void addSpecialInformation(ItemStack itemStack, EntityPlayer entityPlayer, List information) {
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
