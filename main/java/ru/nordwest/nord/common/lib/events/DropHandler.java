package ru.nordwest.nord.common.lib.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.*;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.common.items.RegisterBags;

public class DropHandler {
        @SubscribeEvent
        public void onEntityDrop(LivingDropsEvent event) {
                int dropChance = Nord.rand.nextInt(10000);

                if (event.entityLiving instanceof EntityZombie) {
                        /* Bags Drop */
                        if (dropChance < 315) {
                                if (dropChance < 200)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(0, Nord.rand.nextInt(3)), 0);
                                else if (dropChance < 300)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(3)), 0);
                                else if (dropChance < 310)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(3)), 0);
                                else if (dropChance < 315)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(3)), 0);
                        }
                }

                if (event.entityLiving instanceof EntitySkeleton) {
                        /* Bags Drop */
                        if (dropChance < 380) {
                                if (dropChance < 200)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(0, Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 350)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 370)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(3)), 0);
                                else if (dropChance < 380)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(3)), 0);
                        }
                }

                if (event.entityLiving instanceof EntityPigZombie) {
                        /* Bags Drop */
                        if (dropChance < 490) {
                                if (dropChance < 250)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(0, Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 450)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 480)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 490)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(4)), 0);
                        }
                }

                if (event.entityLiving instanceof EntitySlime) {
                        /* Bags Drop */
                        if (dropChance < 500) {
                                if (dropChance < 250)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(0, Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 450)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 485)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 500)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(4)), 0);
                        }
                }

                if (event.entityLiving instanceof EntityWitch) {
                        /* Bags Drop */
                        if (dropChance < 1100) {
                                if (dropChance < 750)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(0, Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 975)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 1075)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 1100)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(4)), 0);
                        }
                }
        }

        public static void addStructuresLoot() {
                /* Add new loot (Params: itemstack, min, max, rarity) */
                /* Common/Uncommon Bags Generation rate */
                ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(4)), 1, 1, 10));
                ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(4)), 1, 1, 15));
                ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(4)), 1, 1, 15));
                ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(4)), 1, 1, 20));
                ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(3)), 1, 1, 15));
                ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(4)), 1, 1, 1));
                ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(3)), 1, 1, 15));

                /* Uncommon/Rare Bags Generation rate */
                ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(4)), 1, 1, 5));
                ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(4)), 1, 1, 10));
                ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(4)), 1, 1, 10));
                ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(4)), 1, 1, 15));
                ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(3)), 1, 1, 5));
                ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(4)), 1, 1, 1));
                ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(3)), 1, 1, 5));

                /* Rare/Unique Bags Generation rate */
                ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(4)), 1, 1, 2));
                ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(4)), 1, 1, 2));
                ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(4)), 1, 1, 2));
                ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(4)), 1, 1, 1));
                ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(3)), 1, 1, 1));
                ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(4)), 1, 1, 5));
                ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(3)), 1, 1, 1));
        }
}
