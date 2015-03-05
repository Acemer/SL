package ru.nordwest.nord.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RegisterFood {
        public static Item food;

        public static void init() {
                ItemFood.addFood(5, 0.8F, "fish_pie");
                ItemFood.addFood(5, 0.8F, "jam_pie");
                ItemFood.addFood(5, 1.0F, "meat_pie");
                ItemFood.addFood(5, 0.8F, "potatoes_pie");
                ItemFood.addFood(5, 1.0F, "onion_pie");
                ItemFood.addFood(5, 0.6F, "sorrel_pie");
                ItemFood.addFood(1, 0.5F, "dough");
                ItemFood.addFood(3, 0.8F, "dough_pitcher");
                ItemFood.addFood(2, 0.3F, "bun");
                ItemFood.addFood(4, 0.8F, "jam_bun");
                ItemFood.addFood(5, 1.0F, "big_jam_bun");
                ItemFood.addFood(4, 1.0F, "curd");
                ItemFood.addFood(4, 0.6F, "fritters");
                ItemFood.addFood(1, 0.3F, "sorrel");
                ItemFood.addFood(1, 0.3F, "onion");
                ItemFood.addFood(5, 0.5F, "pancake");
                ItemFood.addFood(5, 0.8F, "pancake_with_cottage_cheese");
                ItemFood.addFood(5, 0.8F, "pancake_with_curd");
                ItemFood.addFood(5, 0.8F, "pancake_with_fish");
                ItemFood.addFood(5, 0.8F, "pancake_with_jam");
                ItemFood.addFood(5, 1.2F, "pancake_with_meat");
                ItemFood.addFood(5, 0.8F, "pancake_with_onions_and_eggs");
                ItemFood.addFood(5, 0.8F, "pancake_with_potatoes");
                ItemFood.addFood(5, 0.8F, "pancake_with_sorrel");

                food = new ItemFood();
                GameRegistry.registerItem(food, "food");
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
                GameRegistry.addRecipe(getFood("dough_pitcher"), "___", "___", "yxy",
                        'x', new ItemStack(Items.egg, 1), 'y', new ItemStack(Items.milk_bucket, 2));
                GameRegistry.addRecipe(getFood("dough_pitcher"), "yxy", "___", "___",
                        'x', new ItemStack(Items.egg, 1), 'y', new ItemStack(Items.milk_bucket, 2));
                GameRegistry.addRecipe(getFood("dough_pitcher"), "___", "yxy", "___",
                        'x', new ItemStack(Items.egg, 1), 'y', new ItemStack(Items.milk_bucket, 2));
                GameRegistry.addRecipe(getFood("bun"), "___", "_x_", "_y_",
                        'x', new ItemStack(Items.egg, 1), 'y', new ItemStack(Items.milk_bucket, 1));
                GameRegistry.addRecipe(getFood("jam_bun"), "___", "zx_", "_y_",
                        'x', new ItemStack(Items.egg, 1), 'y', new ItemStack(Items.milk_bucket, 1), 'z', new ItemStack(Items.apple, 1));
                GameRegistry.addRecipe(getFood("big_jam_bun"), "___", "zxz", "_y_",
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
                return ItemFood.getFood(name, count);
        }

        private static ItemStack getFood(String name) {
                return getFood(name, 1);
        }
}

