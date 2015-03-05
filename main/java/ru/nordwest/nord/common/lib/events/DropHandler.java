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
                        if (dropChance < 320) {
                                if (dropChance < 135)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(0, Nord.rand.nextInt(3)), 0);
                                else if (dropChance < 230)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(3)), 0);
                                else if (dropChance < 260)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(3)), 0);
                                else if (dropChance < 270)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(3)), 0);
                                else if (dropChance < 320)
                                        event.entityLiving.entityDropItem(RegisterBags.getSpecialBag(0), 0);
                        }
                }

                if (event.entityLiving instanceof EntitySkeleton) {
                        /* Bags Drop */
                        if (dropChance < 375) {
                                if (dropChance < 200)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(0, Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 330)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 365)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(3)), 0);
                                else if (dropChance < 375)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(3)), 0);
                        }
                }

                if (event.entityLiving instanceof EntityPigZombie) {
                        /* Bags Drop */
                        if (dropChance < 450) {
                                if (dropChance < 225)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(0, Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 380)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 440)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 450)
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
                                if (dropChance < 650)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(0, Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 875)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2), Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 1055)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 1, Nord.rand.nextInt(4)), 0);
                                else if (dropChance < 1100)
                                        event.entityLiving.entityDropItem(RegisterBags.getBag(Nord.rand.nextInt(2) + 2, Nord.rand.nextInt(4)), 0);
                        }
                }

                if (event.entityLiving instanceof EntityCreeper) {
                        if (dropChance < 100)
                                event.entityLiving.entityDropItem(RegisterBags.getSpecialBag(0), 0);
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
