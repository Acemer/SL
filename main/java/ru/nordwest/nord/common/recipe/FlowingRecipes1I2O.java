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
	@Override
	public IRecipes1I2O INSTANCE() {
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
		return recipes.get(index);
	}
	@Override
	public int getIndexRecipe(ItemStack item) {
		if (item == null) {
			return -1;
		}

		byte check = 0;
		IRecipe1I2O recipe = null;
		for (int i = 0; i < recipes.size(); i++) {
			check = 0;
			recipe = recipes.get(i);
			check ^= (recipe.getInput().getItem() == item.getItem())? 1 : 0 ;
			check ^= (recipe.getInput().getItemDamage() == item.getItemDamage())? 2 : 0;
			check ^= (recipe.getInput().stackSize <= item.stackSize)? 4 : 0;
			if ((check|0) == 0) {
				return i;
			}
		}
		return -check;
	}
}
