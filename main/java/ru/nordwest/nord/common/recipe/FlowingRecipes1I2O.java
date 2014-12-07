package ru.nordwest.nord.common.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import ru.nordwest.nord.util.Fuel;

public class FlowingRecipes1I2O implements IRecipes1I2O {
	private static List<Recipe1I2O> recipes = new ArrayList<Recipe1I2O>(64);

	public static void addRecipe(ItemStack input, ItemStack output1,
			ItemStack output2, int needEnergy, float percent, float exp) {
		Recipe1I2O recipe = new Recipe1I2O(input, output1, output2, needEnergy,
				percent, exp);
		recipes.add(recipe);
	}
	private static IRecipes1I2O INSTANCE;

	public static IRecipes1I2O INSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new FlowingRecipes1I2O();
		}
		return INSTANCE;
	}

	@Override
	public IRecipe1I2O getRecipe(ItemStack item) {
		return getRecipe(getIndexRecipe(item));
	}
	@Override
	public IRecipe1I2O getRecipe(int index) {
        if (index>-1)
		    return recipes.get(index);
        else
            return null;
	}
	@Override
	public int getIndexRecipe(ItemStack item) {
		if (item == null) {
			return -1;
		}

		boolean check = true;
		IRecipe1I2O recipe;
		for (int i = 0; i < recipes.size(); i++) {
			recipe = recipes.get(i);
			check &= (recipe.getInput().getItem() == item.getItem());
			check &= (recipe.getInput().getItemDamage() == item.getItemDamage());
			check &= (recipe.getInput().stackSize <= item.stackSize);
			if (check) {
				return i;
			}
		}
		return -1;
	}
}
