package ru.nordwest.nord.common.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import ru.nordwest.nord.util.Fuel;

public class FlowingRecipes1I2O extends Recipes1I2O {
	public static void addRecipe(ItemStack input, ItemStack output1,
			ItemStack output2, int needEnergy, float percent, float exp) {
		Recipe1I2O recipe = new Recipe1I2O(input, output1, output2, needEnergy,
				percent, exp);
		recipes.add(recipe);
	}

}
