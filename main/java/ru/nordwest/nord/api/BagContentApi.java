package ru.nordwest.nord.api;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Level;
import ru.nordwest.nord.common.items.BagContent;
import ru.nordwest.nord.common.items.ItemFood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BagContentApi extends BagContent {

        /**
         * Simple API that provides you with two simple methods you can use for
         * adding your own items or even item collections to bag content lists.
         *
         * Use this method for adding new Items (as ItemStacks) to bag content lists.
         * Tier is an integer which should be 0-3:
         * 0 - Common tier;
         * 1 - Uncommon tier;
         * 2 - Rare tier;
         * 3 - Unique tier.
         */
        public static void addItemToContentList(ItemStack itemStack, int tier) {
                if (itemStack == null) {
                        FMLLog.log(Level.INFO, "[N - Bags API] Can't add null item to the list.");
                        return;
                }
                switch (tier) {
                        case 0:
                                common.add(itemStack);
                                FMLLog.log(Level.INFO, "[N - Bags API] 1 item added to Common list. It contains " + common.size() + " items now.");
                                break;
                        case 1:
                                uncommon.add(itemStack);
                                FMLLog.log(Level.INFO, "[N - Bags API] 1 item added to Uncommon list. It contains " + uncommon.size() + " items now.");
                                break;
                        case 2:
                                rare.add(itemStack);
                                FMLLog.log(Level.INFO, "[N - Bags API] 1 item added to Rare list. It contains " + rare.size() + " items now.");
                                break;
                        case 3:
                                unique.add(itemStack);
                                FMLLog.log(Level.INFO, "[N - Bags API] 1 item added to Unique list. It contains " + unique.size() + " items now.");
                                break;
                        default:
                                FMLLog.log(Level.INFO, "[N - Bags API] Can't handle tier with ID: " + tier + ". It should be 0-3.");
                }
        }

        /**
         * Use this method for adding collections (lists, arrays) which extends ItemStack to bag content lists.
         * Tier is an integer which should be 0-3:
         * 0 - Common tier;
         * 1 - Uncommon tier;
         * 2 - Rare tier;
         * 3 - Unique tier.
         */
        public static void addCollectionToContentList(Collection<ItemStack> collection, int tier) {
                if (collection == null || collection.contains(null)) {
                        FMLLog.log(Level.INFO, "[N - Bags API] Can't add collection which contains null item(s) to the content list.");
                        return;
                }
                switch (tier) {
                        case 0:
                                common.addAll(collection);
                                FMLLog.log(Level.INFO, "[N - Bags API] " + collection.size() + " items added to Common list. It contains " + common.size() + " items now.");
                                break;
                        case 1:
                                uncommon.addAll(collection);
                                FMLLog.log(Level.INFO, "[N - Bags API] " + collection.size() + " items added to Uncommon list. It contains " + uncommon.size() + " items now.");
                                break;
                        case 2:
                                rare.addAll(collection);
                                FMLLog.log(Level.INFO, "[N - Bags API] " + collection.size() + " items added to Rare list. It contains " + rare.size() + " items now.");
                                break;
                        case 3:
                                unique.addAll(collection);
                                FMLLog.log(Level.INFO, "[N - Bags API] " + collection.size() + " items added to Unique list. It contains " + unique.size() + " items now.");
                                break;
                        default:
                                FMLLog.log(Level.INFO, "[N - Bags API] Can't handle tier with ID:" + tier + ". It should be 0-3.");
                }
        }

        /* Example. Adding some food to our content lists using collection. */
        private static List<ItemStack> commonNord = new ArrayList<ItemStack>();
        private static int[] stackSize = {2, 4};

        public static void registerNordItems() {
                for (int i = 0; i < 2; i++) {
                        commonNord.add(ItemFood.getFood("fish_pie", stackSize[i]));
                        commonNord.add(ItemFood.getFood("jam_pie", stackSize[i]));
                        commonNord.add(ItemFood.getFood("meat_pie", stackSize[i]));
                        commonNord.add(ItemFood.getFood("potatoes_pie", stackSize[i]));
                        commonNord.add(ItemFood.getFood("sorrel_pie", stackSize[i]));
                }
                addCollectionToContentList(commonNord, 0);
        }
}
