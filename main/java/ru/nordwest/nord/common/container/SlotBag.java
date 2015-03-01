package ru.nordwest.nord.common.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBag extends Slot { //Name it SlotEmptyBag!

        public SlotBag(IInventory inventory, int slotIndex, int x, int y) {
                super(inventory, slotIndex, x, y);
        }

        @Override
        public boolean isItemValid(ItemStack itemStack) {
                return false;
        }
}
