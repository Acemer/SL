package ru.nordwest.nord.common.items;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Level;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.container.InventoryBag;

import java.util.ArrayList;
import java.util.List;

public class BagContent {
        protected static List<ItemStack> common = new ArrayList<ItemStack>();
        protected static List<ItemStack> uncommon = new ArrayList<ItemStack>();
        protected static List<ItemStack> rare = new ArrayList<ItemStack>();
        protected static List<ItemStack> unique = new ArrayList<ItemStack>();

        private static int[] stackSize = {1, 2, 4, 8, 16, 24, 32, 48, 64};

        public static void registerItems() {
                int i, k;
                /* Common List */
                /* Common Blocks */
                for (i = 0; i < 2; i++) {
                        for (k = 0; k < 4; k++)
                                common.add(new ItemStack(Blocks.log, stackSize[3 + i], k));
                        for (k = 0; k < 2; k++)
                                common.add(new ItemStack(Blocks.sand, stackSize[3 + i], k));
                        common.add(new ItemStack(Blocks.log2, stackSize[3 + i]));
                        common.add(new ItemStack(Blocks.wool, stackSize[2 + i]));
                        common.add(new ItemStack(Blocks.cactus, stackSize[1 + i]));
                }
                for (i = 0; i < 3; i++) {
                        common.add(new ItemStack(Blocks.ladder, stackSize[2 + i]));
                        common.add(new ItemStack(Blocks.torch, stackSize[3 + i]));
                        common.add(new ItemStack(Nord.oil_lamp, stackSize[i]));
                }
                common.add(new ItemStack(Blocks.chest, 2));
                common.add(new ItemStack(Blocks.crafting_table, 1));
                common.add(new ItemStack(Blocks.bed, 1));
                /* Common Items */
                for (i = 0; i < 3; i++) {
                        for (k = 0; k < 2; k++)
                                common.add(new ItemStack(Items.coal, stackSize[2 + i], k));
                        common.add(new ItemStack(Items.flint, stackSize[2 + i], k));
                        common.add(new ItemStack(Items.dye, stackSize[1 + i], 0));
                        common.add(new ItemStack(Items.dye, stackSize[1 + i], 3));
                        common.add(new ItemStack(Items.wheat, stackSize[3 + i]));
                        common.add(new ItemStack(Items.wheat_seeds, stackSize[1 + i]));
                        common.add(new ItemStack(Items.arrow, stackSize[1 + i]));
                        common.add(new ItemStack(Items.apple, stackSize[1 + i]));
                        common.add(new ItemStack(Items.rotten_flesh, stackSize[1 + i]));
                }
                for (i = 0; i < 2; i++) {
                        for (k = 0; k < 4; k++)
                                common.add(new ItemStack(Items.fish, stackSize[1 + i], k));
                        common.add(new ItemStack(Items.bone, stackSize[1 + i]));
                        common.add(new ItemStack(Items.string, stackSize[1 + i]));
                        common.add(new ItemStack(Items.feather, stackSize[2 + i]));
                        common.add(new ItemStack(Items.egg, stackSize[3 + i]));
                        common.add(new ItemStack(Items.cookie, stackSize[2 + i]));
                        common.add(new ItemStack(Items.sugar, stackSize[2 + i]));
                }
                common.add(new ItemStack(Items.boat, 1));
                FMLLog.log(Level.INFO, "[N - Bags] " + common.size() + " items added to Common content list.");

                /* Uncommon List*/
                /* Uncommon Blocks*/
                for (i = 0; i < 4; i++) {
                        uncommon.add(new ItemStack(Blocks.mossy_cobblestone, stackSize[3 + i]));
                }
                for (i = 0; i < 2; i++) {
                        uncommon.add(new ItemStack(Blocks.tnt, stackSize[1 + i]));
                        uncommon.add(new ItemStack(Blocks.piston, stackSize[1 + i]));
                        uncommon.add(new ItemStack(Blocks.pumpkin, stackSize[1 + i]));
                        uncommon.add(new ItemStack(Blocks.melon_block, stackSize[1 + i]));
                        uncommon.add(new ItemStack(Blocks.noteblock, stackSize[1 + i]));
                }
                /* Uncommon Items*/
                for (i = 0; i < 3; i++) {
                        uncommon.add(new ItemStack(Items.leather, stackSize[1 + i]));
                        uncommon.add(new ItemStack(Items.redstone, stackSize[2 + i]));
                        uncommon.add(new ItemStack(Items.book, stackSize[1 + i]));
                        uncommon.add(new ItemStack(Items.ender_pearl, stackSize[1 + i]));
                        uncommon.add(new ItemStack(Items.gunpowder, stackSize[2 + i]));
                        uncommon.add(new ItemStack(Items.clay_ball, stackSize[2 + i]));
                }
                for (i = 0; i < 2; i++) {
                        for (k = 0; k < 2; k++)
                                uncommon.add(new ItemStack(Items.cooked_fished, stackSize[2 + i], k));
                        uncommon.add(new ItemStack(Items.experience_bottle, stackSize[2 + i]));
                        uncommon.add(new ItemStack(Items.slime_ball, stackSize[1 + i]));
                        uncommon.add(new ItemStack(Items.cooked_beef, stackSize[2 + i]));
                        uncommon.add(new ItemStack(Items.cooked_chicken, stackSize[1 + i]));
                        uncommon.add(new ItemStack(Items.cooked_porkchop, stackSize[2 + i]));
                        uncommon.add(new ItemStack(Items.pumpkin_pie, stackSize[2 + i]));
                        uncommon.add(new ItemStack(Items.potato, stackSize[2 + i]));
                        uncommon.add(new ItemStack(Items.carrot, stackSize[2 + i]));
                        uncommon.add(new ItemStack(Items.bread, stackSize[2 + i]));
                        uncommon.add(new ItemStack(Items.fermented_spider_eye, stackSize[1 + i]));
                        uncommon.add(new ItemStack(Items.speckled_melon, stackSize[1 + i]));
                        uncommon.add(new ItemStack(Items.blaze_powder, stackSize[1 + i]));
                        uncommon.add(new ItemStack(Items.item_frame, stackSize[1 + i]));
                        uncommon.add(new ItemStack(Items.iron_ingot, stackSize[1 + i]));
                }
                uncommon.add(new ItemStack(Items.bucket, 1));
                uncommon.add(new ItemStack(Items.compass, 1));
                uncommon.add(new ItemStack(Items.lava_bucket, 1));
                uncommon.add(new ItemStack(Items.water_bucket, 1));
                uncommon.add(new ItemStack(Items.milk_bucket, 1));
                uncommon.add(new ItemStack(Items.flint_and_steel, 1));
                uncommon.add(new ItemStack(Items.map, 1));
                uncommon.add(new ItemStack(Items.potionitem, 1, 0));
                uncommon.add(new ItemStack(Items.potionitem, 1, 8194));
                uncommon.add(new ItemStack(Items.potionitem, 1, 8227));
                uncommon.add(new ItemStack(Items.potionitem, 1, 8261));
                uncommon.add(new ItemStack(Items.potionitem, 1, 8237));
                uncommon.add(new ItemStack(Items.potionitem, 1, 8268));
                uncommon.add(new ItemStack(Items.potionitem, 1, 8234));
                uncommon.add(new ItemStack(Items.potionitem, 1, 8232));
                uncommon.add(new ItemStack(Items.melon_seeds, 2));
                uncommon.add(new ItemStack(Items.pumpkin_seeds, 2));
                uncommon.add(new ItemStack(Items.mushroom_stew, 1));
                uncommon.add(new ItemStack(Items.writable_book, 1));
                uncommon.add(new ItemStack(Items.minecart, 1));
                uncommon.add(new ItemStack(Items.record_11, 1));
                FMLLog.log(Level.INFO, "[N - Bags] " + uncommon.size() + " items added to Uncommon content list.");

                /* Rare List */
                /* Rare Blocks */
                for (i = 0; i < 2; i++) {
                        rare.add(new ItemStack(Blocks.sponge, stackSize[3 + i]));
                        rare.add(new ItemStack(Blocks.bookshelf, stackSize[3 + i]));
                        rare.add(new ItemStack(Blocks.obsidian, stackSize[2 + i]));
                        rare.add(new ItemStack(Blocks.sticky_piston, stackSize[1 + i]));
                }
                rare.add(new ItemStack(Blocks.lapis_block, 1));
                rare.add(new ItemStack(Blocks.jukebox, 1));
                rare.add(new ItemStack(Blocks.anvil, 1));
                rare.add(new ItemStack(Blocks.ender_chest, 1));
                rare.add(new ItemStack(Blocks.enchanting_table, 1));
                /* Rare Items */
                for (i = 0; i < 3; i++) {
                        rare.add(new ItemStack(Items.glowstone_dust, stackSize[3 + i]));
                        rare.add(new ItemStack(Items.gunpowder, stackSize[3 + i]));
                        rare.add(new ItemStack(Items.quartz, stackSize[4 + i]));
                        rare.add(new ItemStack(Items.brick, stackSize[5 + i]));
                }
                for (i = 0; i < 2; i++) {
                        for (k = 90; k < 96; k++)
                                rare.add(new ItemStack(Items.spawn_egg, stackSize[i], k));
                        rare.add(new ItemStack(Items.spawn_egg, stackSize[i], 57));
                        rare.add(new ItemStack(Items.spawn_egg, stackSize[i], 98));
                        rare.add(new ItemStack(Items.dye, stackSize[2 + i], 4));
                        rare.add(new ItemStack(Items.diamond, stackSize[1 + i]));
                        rare.add(new ItemStack(Items.gold_ingot, stackSize[2 + i]));
                        rare.add(new ItemStack(Items.iron_ingot, stackSize[3 + i]));
                        rare.add(new ItemStack(Items.ender_pearl, stackSize[3 + i]));
                        rare.add(new ItemStack(Items.slime_ball, stackSize[2 + i]));
                        rare.add(new ItemStack(Items.blaze_rod, stackSize[1 + i]));
                }
                rare.add(new ItemStack(Items.iron_horse_armor, 1));
                rare.add(new ItemStack(Items.golden_horse_armor, 1));
                rare.add(new ItemStack(Items.potionitem, 1, 8193));
                rare.add(new ItemStack(Items.potionitem, 1, 8225));
                rare.add(new ItemStack(Items.potionitem, 1, 8226));
                rare.add(new ItemStack(Items.potionitem, 1, 8259));
                rare.add(new ItemStack(Items.potionitem, 1, 8230));
                rare.add(new ItemStack(Items.potionitem, 1, 8201));
                rare.add(new ItemStack(Items.potionitem, 1, 8233));
                rare.add(new ItemStack(Items.potionitem, 1, 8237));
                rare.add(new ItemStack(Items.potionitem, 1, 16386));
                rare.add(new ItemStack(Items.potionitem, 1, 16419));
                rare.add(new ItemStack(Items.potionitem, 1, 16424));
                rare.add(new ItemStack(Items.potionitem, 1, 16460));
                rare.add(new ItemStack(Items.brewing_stand, 1));
                rare.add(new ItemStack(Items.cauldron, 1));
                rare.add(new ItemStack(Items.record_13, 1));
                rare.add(new ItemStack(Items.record_far, 1));
                rare.add(new ItemStack(Items.record_blocks, 1));
                rare.add(new ItemStack(Items.record_chirp, 1));
                rare.add(new ItemStack(Items.record_mall, 1));
                rare.add(new ItemStack(Items.record_mellohi, 1));
                rare.add(new ItemStack(Items.record_stal, 1));
                rare.add(new ItemStack(Items.record_strad, 1));
                rare.add(new ItemStack(Items.record_ward, 1));
                rare.add(new ItemStack(Items.record_wait, 1));
                rare.add(new ItemStack(Items.compass, 1));
                rare.add(new ItemStack(Items.clock, 1));
                rare.add(new ItemStack(Items.saddle, 1));
                rare.add(new ItemStack(Items.cake, 1));
                rare.add(RegisterBags.getBag(0, 2));
                FMLLog.log(Level.INFO, "[N - Bags] " + rare.size() + " items added to Rare content list.");

                /* Unique List */
                /* Unique Blocks */
                unique.add(new ItemStack(Blocks.diamond_block, 1));
                unique.add(new ItemStack(Blocks.emerald_block, 1));
                unique.add(new ItemStack(Blocks.beacon, 1));
                /* Unique Items */
                for (i = 0; i < 2; i++) {
                        for (k = 50; k < 53; k++)
                                unique.add(new ItemStack(Items.spawn_egg, stackSize[i], k));
                        for (k = 54; k < 57; k++)
                                unique.add(new ItemStack(Items.spawn_egg, stackSize[i], k));
                        unique.add(new ItemStack(Items.spawn_egg, stackSize[i], 58));
                        unique.add(new ItemStack(Items.spawn_egg, stackSize[i], 59));
                        unique.add(new ItemStack(Items.spawn_egg, stackSize[i], 61));
                        unique.add(new ItemStack(Items.spawn_egg, stackSize[i], 62));
                        unique.add(new ItemStack(Items.spawn_egg, stackSize[i], 66));
                        unique.add(new ItemStack(Items.spawn_egg, stackSize[i], 95));
                        unique.add(new ItemStack(Items.spawn_egg, stackSize[i], 96));
                        unique.add(new ItemStack(Items.spawn_egg, stackSize[i], 98));
                        unique.add(new ItemStack(Items.spawn_egg, stackSize[i], 100));
                        unique.add(new ItemStack(Items.spawn_egg, stackSize[i], 120));
                        unique.add(new ItemStack(Items.blaze_rod, stackSize[3 + i]));
                        unique.add(new ItemStack(Items.diamond, stackSize[3 + i]));
                        unique.add(new ItemStack(Items.emerald, stackSize[2 + i]));
                }
                unique.add(new ItemStack(Items.nether_star, 1));
                unique.add(new ItemStack(Items.golden_apple, 1, 1));
                unique.add(new ItemStack(Items.potionitem, 1, 8257));
                unique.add(new ItemStack(Items.potionitem, 1, 8258));
                unique.add(new ItemStack(Items.potionitem, 1, 8259));
                unique.add(new ItemStack(Items.potionitem, 1, 8229));
                unique.add(new ItemStack(Items.potionitem, 1, 8262));
                unique.add(new ItemStack(Items.potionitem, 1, 8265));
                unique.add(new ItemStack(Items.potionitem, 1, 8269));
                unique.add(new ItemStack(Items.potionitem, 1, 8238));
                unique.add(new ItemStack(Items.potionitem, 1, 8270));
                unique.add(new ItemStack(Items.potionitem, 1, 16449));
                unique.add(new ItemStack(Items.potionitem, 1, 16450));
                unique.add(new ItemStack(Items.potionitem, 1, 16451));
                unique.add(new ItemStack(Items.potionitem, 1, 16457));
                unique.add(RegisterBags.getBag(2, 2));
                FMLLog.log(Level.INFO, "[N - Bags] " + unique.size() + " items added to Unique content list.");
        }

        private static void addItemToInventory(ItemStack itemStack, int quality, int slot) {
                if (quality < 0) quality = 0;
                else if (quality > 3) quality = 3;
                switch (quality) {
                        case 0:
                                new InventoryBag(itemStack).setInventorySlotContents(slot, common.get(Nord.rand.nextInt(common.size())));
                                break;
                        case 1:
                                new InventoryBag(itemStack).setInventorySlotContents(slot, uncommon.get(Nord.rand.nextInt(uncommon.size())));
                                break;
                        case 2:
                                new InventoryBag(itemStack).setInventorySlotContents(slot, rare.get(Nord.rand.nextInt(rare.size())));
                                break;
                        case 3:
                                new InventoryBag(itemStack).setInventorySlotContents(slot, unique.get(Nord.rand.nextInt(unique.size())));
                                break;
                        default:
                                break;
                }
        }

        public static void generateInventory(ItemStack itemStack) {
                int s = itemStack.getItemDamage() % 10;
                int q = itemStack.getItemDamage() / 10;
                int slot = 0;
                /* First and Second Slots */
                /* Always have 100% chance to get items in them */
                addItemToInventory(itemStack, q, slot++);
                addItemToInventory(itemStack, q, slot++);
                /* Third Slot */
                /* Has 100% chance to get item for medium+ bags and 10% for small one */
                if (s != 0) addItemToInventory(itemStack, q, slot++);
                else if (Nord.rand.nextInt(100) < 10) addItemToInventory(itemStack, q, slot++);
                /* Fourth Slot */
                /* Has 100% chance to get item for large+ bags and 10% for medium one */
                if (s > 1) addItemToInventory(itemStack, q - 1, slot++);
                else if (s == 1 && Nord.rand.nextInt(100) < 10) addItemToInventory(itemStack, q, slot++);
                /* Fifth Slot */
                /* Has 100% chance to get item for very large bag and 10% for large one */
                if (s > 2) addItemToInventory(itemStack, q + 1, slot);
                else if (s == 2 && Nord.rand.nextInt(100) < 10) addItemToInventory(itemStack, q, slot++);
        }

        public static boolean validateInventory(ItemStack itemStack) {
                for (int i = 0; i < 5; i++) {
                        if (new InventoryBag(itemStack).getStackInSlot(i) != null) return true;
                }
                return false;
        }
}
