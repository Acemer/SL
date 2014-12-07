package ru.nordwest.nord.common.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

abstract public class Recipes1I2O implements IRecipes1I2O {
	protected static List<Recipe1I2O> recipes = new ArrayList<Recipe1I2O>(64);

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
    private static IRecipes1I2O INSTANCE;

    public static IRecipes1I2O INSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new FlowingRecipes1I2O();
        }
        return INSTANCE;
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
