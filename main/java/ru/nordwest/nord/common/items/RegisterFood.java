package ru.nordwest.nord.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nordwest.nord.client.lib.tabs.TabsNord;

public class RegisterFood {
        public static Item ifood;

        public static void init() {
                ItemMetadataFood.addFood(0, 4.5F, "fish_pie", "fish_pie");
                ItemMetadataFood.addFood(0, 2.0F, "jam_pie", "jam_pie");
                ItemMetadataFood.addFood(0, 5.5F, "meat_pie", "meat_pie");
                ItemMetadataFood.addFood(0, 3.0F, "potatoes_pie", "potatoes_pie");
                ItemMetadataFood.addFood(0, 3.0F, "pie_with_onions_and_eggs", "pie_with_onions_and_eggs");
                ItemMetadataFood.addFood(0, 3.5F, "sorrel_pie", "sorrel_pie");
                ItemMetadataFood.addFood(0, 0.5F, "dough", "dough");
                ItemMetadataFood.addFood(0, 2.0F, "dough2", "pitcher_dough");
                ItemMetadataFood.addFood(0, 1.0F, "bun", "bun");
                ItemMetadataFood.addFood(0, 1.5F, "bun_jam", "bun_jam");
                ItemMetadataFood.addFood(0, 2.0F, "bun_jam2", "bun_jam2");
                ItemMetadataFood.addFood(0, 1.0F, "curd", "curd");
                ItemMetadataFood.addFood(0, 1.0F, "fritters", "fritters");
                ItemMetadataFood.addFood(0, 0.5F, "sorrel", "sorrel");
                ItemMetadataFood.addFood(0, 0.5F, "onion", "onion");
                ItemMetadataFood.addFood(0, 1.5F, "pancake", "pancake");
                ItemMetadataFood.addFood(0, 1.5F, "pancake_with_cottage_cheese", "pancake_with_cottage_cheese");
                ItemMetadataFood.addFood(0, 2.5F, "pancake_with_curd", "pancake_with_curd");
                ItemMetadataFood.addFood(0, 4.5F, "pancake_with_fish", "pancake_with_fish");
                ItemMetadataFood.addFood(0, 2.5F, "pancake_with_jam", "pancake_with_jam");
                ItemMetadataFood.addFood(0, 2.5F, "pancake_with_jam2", "pancake_with_jam2");
                ItemMetadataFood.addFood(0, 5.5F, "pancake_with_meat", "pancake_with_meat");
                ItemMetadataFood.addFood(0, 2.5F, "pancake_with_onions_and_eggs", "pancake_with_onions_and_eggs");
                ItemMetadataFood.addFood(0, 3.0F, "pancake_with_potatoes", "pancake_with_potatoes");
                ItemMetadataFood.addFood(0, 2.0F, "pancake_with_sorrel", "pancake_with_sorrel");
                ifood = new ItemMetadataFood().setUnlocalizedName("food").setCreativeTab(TabsNord.tabFood);
                GameRegistry.registerItem(ifood, "food");
        }

        public static void postInit() {
                GameRegistry.addRecipe(getFood("jam_pie"), "_x_", "_x_", "_y_",
                        'x', new ItemStack(Items.apple, 2), 'y', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("fish_pie"), "___", "_x_", "_y_",
                        'x', new ItemStack(Items.cooked_fished, 1), 'y', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("fish_pie"), "_x_", "_y_", "___",
                        'x', new ItemStack(Items.cooked_fished, 1), 'y', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("meat_pie"), "___", "_x_", "_y_",
                        'x', new ItemStack(Items.cooked_beef, 1), 'y', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("meat_pie"), "_x_", "_y_", "___",
                        'x', new ItemStack(Items.cooked_beef, 1), 'y', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("potatoes_pie"), "___", "_x_", "_y_",
                        'x', new ItemStack(Items.potato, 1), 'y', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("potatoes_pie"), "_x_", "_y_", "___",
                        'x', new ItemStack(Items.potato, 1), 'y', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("dough"), "___", "___", "xyx",
                        'x', new ItemStack(Items.egg, 2), 'y', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("dough"), "xyx", "___", "___",
                        'x', new ItemStack(Items.egg, 2), 'y', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("dough"), "___", "xyx", "___",
                        'x', new ItemStack(Items.egg, 2), 'y', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("pitcher_dough"), "___", "___", "yxy",
                        'x', new ItemStack(Items.egg, 1), 'y', new ItemStack(Items.milk_bucket, 2));
                GameRegistry.addRecipe(getFood("pitcher_dough"), "yxy", "___", "___",
                        'x', new ItemStack(Items.egg, 1), 'y', new ItemStack(Items.milk_bucket, 2));
                GameRegistry.addRecipe(getFood("pitcher_dough"), "___", "yxy", "___",
                        'x', new ItemStack(Items.egg, 1), 'y', new ItemStack(Items.milk_bucket, 2));
                GameRegistry.addRecipe(getFood("bun"), "___", "_x_", "_y_",
                        'x', new ItemStack(Items.egg, 1), 'y', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("bun_jam"), "___", "zx_", "_y_",
                        'x', new ItemStack(Items.egg, 1), 'y', new ItemStack(Items.milk_bucket, 1), 'z', new ItemStack(Items.apple, 1));
                GameRegistry.addRecipe(getFood("bun_jam2"), "___", "zxz", "_y_",
                        'x', new ItemStack(Items.egg, 1), 'y', new ItemStack(Items.milk_bucket, 1), 'z', new ItemStack(Items.apple, 2));
                GameRegistry.addRecipe(getFood("curd"), "_x_", "___", "___",
                        'x', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("curd"), "___", "_x_", "___",
                        'x', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("curd"), "___", "___", "_x_",
                        'x', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("fritters"), "_x_", "_x_", "___",
                        'x', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("fritters"), "___", "_x_", "_x_",
                        'x', new ItemStack(Items.milk_bucket, 1));
        }

        private static ItemStack getFood(String name, int count) {
                return ItemMetadataFood.getFood(name, count);
        }

        private static ItemStack getFood(String name) {
                return getFood(name, 1);
        }
}

