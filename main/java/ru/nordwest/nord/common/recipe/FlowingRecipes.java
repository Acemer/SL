package ru.nordwest.nord.common.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FlowingRecipes { // Takaya vot krivorukaya realizaciya singleton'a
    // Это не синголтон, это статик класс. д32
    private static List<FlowingRecipe> recipes = new ArrayList<FlowingRecipe>(64);

    public static void addRecipe(ItemStack input, ItemStack output1, ItemStack output2, int needEnergy) {
        FlowingRecipe recipe = new FlowingRecipe();
        recipe.input = input;
        recipe.output1 = output1;
        recipe.output2 = output2;
        recipe.needEnergy = needEnergy;
        recipes.add(recipe);
    }

    public static FlowingRecipe getRecipe(ItemStack input) {
        if (input == null) {
            return null;
        }

        boolean check = true;
        FlowingRecipe recipe = null;
        for (int i = 0; i < recipes.size(); i++) {
            recipe = recipes.get(i);
            check &= recipe.input.getItem() == input.getItem();
            check &= recipe.input.getItemDamage() == input.getItemDamage();
            if (check) {
                return recipe;
            }
        }

        return null;
    }

    public static class FlowingRecipe {
        public ItemStack input;
        public ItemStack output1;
        public ItemStack output2;
        public int needEnergy;

        public FlowingRecipe() {
        }

        public FlowingRecipe(ItemStack input,
                             ItemStack output1,
                             ItemStack output2,
                             int needEnergy) {
            this.input = input;
            this.output1 = output1;
            this.output2 = output2;
            this.needEnergy = needEnergy;
        }
    }
}

