package ru.nordwest.nord.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.nordwest.nord.common.items.BagContent;

public class ContainerBag extends Container {

        public final InventoryBag inventory;
        private int currentSlot;

        private static final int INV_START = InventoryBag.INV_SIZE,
                INV_END = INV_START + 26,
                HOTBAR_START = INV_END + 1,
                HOTBAR_END = HOTBAR_START + 8;

        public ContainerBag(EntityPlayer par1Player, InventoryPlayer inventoryPlayer, InventoryBag inventory) {
                this.inventory = inventory;
                this.currentSlot = inventoryPlayer.currentItem;

                int i;

                /* Bag Inventory */
                for (i = 0; i < InventoryBag.INV_SIZE; ++i) {
                        this.addSlotToContainer(new SlotBag(this.inventory, i, 44 + i * 18, 26));
                }

                /* Player Main Inventory */
                for (i = 0; i < 3; ++i) {
                        for (int j = 0; j < 9; ++j) {
                                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 55 + i * 18));
                        }
                }

                /* Player Hotbar */
                for (i = 0; i < 9; ++i) {
                        this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 113));
                }
        }

        @Override
        public boolean canInteractWith(EntityPlayer player) {
                return inventory.isUseableByPlayer(player);
        }

        /**
         * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
         * Only real change we make to this is to set needsUpdate to true at the end.
         */
        public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
                ItemStack itemstack = null;
                Slot slot = (Slot) this.inventorySlots.get(par2);

                if (slot != null && slot.getHasStack()) {
                        ItemStack itemstack1 = slot.getStack();
                        itemstack = itemstack1.copy();

                        if (par2 < INV_START) {
                                if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END + 1, true)) {
                                        return null;
                                }

                                slot.onSlotChange(itemstack1, itemstack);
                        } else {
                                if (par2 >= INV_START && par2 < HOTBAR_START) {
                                        /* Item in Player Inventory, move to the Hot Bar */
                                        if (!this.mergeItemStack(itemstack1, HOTBAR_START, HOTBAR_END + 1, false)) {
                                                return null;
                                        }
                                }
                                /* Item in the Hot Bar, move it to Player Inventory */
                                else if (par2 >= HOTBAR_START && par2 < HOTBAR_END + 1) {
                                        if (!this.mergeItemStack(itemstack1, INV_START, INV_END + 1, false)) {
                                                return null;
                                        }
                                }
                        }

                        if (itemstack1.stackSize == 0) {
                                slot.putStack((ItemStack) null);
                        } else {
                                slot.onSlotChanged();
                        }

                        if (itemstack1.stackSize == itemstack.stackSize) {
                                return null;
                        }

                        slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
                }

                return itemstack;
        }

        @Override
        public void onContainerClosed(EntityPlayer player) {
                if (!player.worldObj.isRemote) {
                        if (player.inventory.mainInventory[currentSlot] != null && !BagContent.validateInventory(player.inventory.mainInventory[currentSlot])) {
                                player.inventory.setInventorySlotContents(currentSlot, null);
                        }
                }
                super.onContainerClosed(player);
        }

        /**
         * NOTE: The following is necessary if you want to prevent the player from moving the item while the
         * inventory is open; if you don't and the player moves the item, the inventory will not be able to save properly
         */
        @Override
        public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {
                // This will prevent the player from interacting with the item that opened the inventory:
                if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()) {
                        return null;
                }
                return super.slotClick(slot, button, flag, player);
        }
}
