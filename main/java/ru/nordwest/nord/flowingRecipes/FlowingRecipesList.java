package ru.nordwest.nord.flowingRecipes;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FlowingRecipesList { // Такая вот криворукая реализация синглтона
	private static FlowingRecipe recipes[] = new FlowingRecipe[256];
	public static int recipesAmount = 0;
	
	public static void addRecipe(ItemStack in, ItemStack out1, ItemStack out2, int needEnergy)
	{
		recipes[recipesAmount] = new FlowingRecipe();
		recipes[recipesAmount].in = in;
		recipes[recipesAmount].out1 = out1;
		recipes[recipesAmount].out2 = out2;
		recipes[recipesAmount].needEnergy = needEnergy;
	}
	
	public static FlowingRecipe getRecipe(ItemStack in)
	{
		if (in == null)
		{
			return null;
		}
		
		int inID = Item.getIdFromItem(in.getItem());
		for (int i = 0; i <= recipesAmount; i++)
		{
			if (Item.getIdFromItem(recipes[i].in.getItem()) == inID)
			{
				return recipes[i];
			}
		}
		
		return null;
	}
}
