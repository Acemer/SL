package ru.nordwest.nord.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import ru.nordwest.nord.common.container.abstracts.ContainerAbstactMachine;
import ru.nordwest.nord.common.lib.recipes.Interfaces.IRecipes1I2O;
import ru.nordwest.nord.common.lib.utils.Fuel;
import ru.nordwest.nord.common.tiles.TileSmelter;
import ru.nordwest.nord.common.tiles.interfaces.IMachine;

public class ContainerSmelter extends ContainerAbstactMachine {


        protected int slot_fuel = 0;
        protected int slot_input = 1;
        protected int slot_input2 = 2;
        protected int slot_result1 = 3;
        protected int slot_result2 = 4;

        public ContainerSmelter(InventoryPlayer invPlayer, TileSmelter ent) {
                init(invPlayer, ent);
        }

        @Override
        public void init(InventoryPlayer invPlayer, IMachine ent) {
                tileEntity = ent;

                addSlotToContainer(new Slot(tileEntity, slot_fuel, 18, 58)); // fuel
                addSlotToContainer(new Slot(tileEntity, slot_input, 45, 38)); // item to work
                addSlotToContainer(new Slot(tileEntity, slot_input2, 66, 38)); // item to work
                addSlotToContainer(new SlotFurnace(invPlayer.player, tileEntity, slot_result1, 124, 38)); // result1
                addSlotToContainer(new SlotFurnace(invPlayer.player, tileEntity, slot_result2, 145, 38)); // result2

                bindPlayerInventory(invPlayer);
        }

        @Override
        public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
                System.err.println(slot);
                ItemStack stack = null;
                Slot slotObject = (Slot) inventorySlots.get(slot);
                Slot fuelSlot = (Slot) this.inventorySlots.get(0);

                if (slotObject != null && slotObject.getHasStack()) {
                        ItemStack stackInSlot = slotObject.getStack();
                        stack = stackInSlot.copy();

                        //boolean check = false;

                        if (slot == slot_result1 || slot == slot_result2) {
                                if (!this.mergeItemStack(stackInSlot, 4, 39, true)) {
                                        return null;
                                }
                                slotObject.onSlotChange(stackInSlot, stack);
                        } else if (slot != slot_input && slot != slot_input2 && slot != slot_fuel) {
                                boolean _check = ((IRecipes1I2O) tileEntity.getRecipes()).getIndexRecipe(stackInSlot) != -1;
                                if (_check) {
                                        if (!this.mergeItemStack(stackInSlot, 1, 3, false)) {
                                                return null;
                                        }
                                } else if (Fuel.getInstance().getEnergy(stackInSlot) > 0 && (fuelSlot.getStack() == null || fuelSlot.getStack().stackSize < 64)) {
                                        if (!this.mergeItemStack(stackInSlot, 0, 1, false))
                                                return null;
                                } else if (slot >= 5 && slot <= 31) {
                                        if (!this.mergeItemStack(stackInSlot, 32, 41, false))
                                                return null;
                                } else if (slot >= 32 && slot < 41 && !this.mergeItemStack(stackInSlot, 5, 32, false))
                                        return null;

                        } else if (!this.mergeItemStack(stackInSlot, 5, 41, false))
                                return null;

                        if (stackInSlot.stackSize == 0) {
                                slotObject.putStack(null);
                        } else {
                                slotObject.onSlotChanged();
                        }

                        if (stackInSlot.stackSize == stack.stackSize) {
                                return null;
                        }
                        slotObject.onPickupFromSlot(player, stackInSlot);
                }
                return null;
        }
}
