package ru.nordwest.nord.common.recipe;

import net.minecraft.item.ItemStack;

import java.util.*;

import ru.nordwest.nord.MetallRegister;

public class SmelterRecipes {
	private static final SmelterRecipes smelterBase = new SmelterRecipes();
	private List<ItemStack> firstSlot = new ArrayList();
	private List<ItemStack> secondSlot = new ArrayList();
	private List<ItemStack> output = new ArrayList();
	private List<ItemStack> second_output = new ArrayList();
	private List<Float> second_output_percent = new ArrayList();
	private List<Float> experience_output = new ArrayList();

	public static SmelterRecipes crushing() {
		return smelterBase;
	}

	private SmelterRecipes() {
		smeltItemStack("gold", 2, 1, "silver", 2, 1, "electrum", 2, 1, 10f);
		smeltItemStack("copper", 2, 3, "tin", 2, 1, "bronze", 2, 4, 10f);
		smeltItemStack("copper", 2, 2, "zing", 2, 1, "antimony", 2, 3, 10f);
		smeltItemStack("alluminum", 2, 8, "copper", 2, 1, "duralumin", 2, 9, 10f);		
	}

	public void smeltItemStack(ItemStack input1, ItemStack input2,
			ItemStack output1, ItemStack output2, float percent, float exp) {
		int index = getIndexResult(input1, input2);
		if (index == -1) {
			firstSlot.add(input1);
			secondSlot.add(input2);
			output.add(output1);
			second_output.add(output2);
			second_output_percent.add(percent);
			experience_output.add(exp);
		} else {
			System.out.println("Smelter recipe #" + index + " owerwrite");
			output.set(index, output1);
			second_output.set(index, output2);
			second_output_percent.set(index, percent);
			experience_output.set(index, exp);
		}
	}
	public void smeltItemStack(ItemStack input1, ItemStack input2,
			ItemStack output1, float exp) {
		smeltItemStack(input1, input2, output1, null, 0, exp);
	}

	public void smeltItemStack(String input1, int qual1, int quant1,
			String input2, int qual2, int quant2, String output1, int oqual1,
			int oquant1, String output2, int oqual2, int oquant2,
			float percent, float exp) {
		smeltItemStack(MetallRegister.getMetallIngot(input1, qual1, quant1),
				MetallRegister.getMetallIngot(input2, qual2, qual2),
				MetallRegister.getMetallIngot(output1, oqual1, oquant1),
				MetallRegister.getMetallIngot(output2, oqual2, oquant2),
				percent, exp);

	}

	public void smeltItemStack(String input1, int qual1, int quant1,
			String input2, int qual2, int quant2, String output1, int oqual1,
			int oquant1, float exp) {
		smeltItemStack(MetallRegister.getMetallIngot(input1, qual1, quant1),
				MetallRegister.getMetallIngot(input2, qual2, qual2),
				MetallRegister.getMetallIngot(output1, oqual1, oquant1), exp);

	}

	public int getIndexResult(ItemStack iStack1, ItemStack iStack2) {
		ItemStack item1 = null;
		ItemStack item2 = null;
		if (iStack1 != null) {
			item1 = iStack1;
			if (iStack2 != null) {
				item2 = iStack2;
			}
		} else {
			if (iStack2 != null) {
				item1 = iStack2;
			} else {
				return -1;
			}
		}
		for (int i = 0; i < firstSlot.size(); i++) {
			boolean match = (compareSizeble(item1, firstSlot.get(i)) && compareSizeble(
					item2, secondSlot.get(i)))
					|| (compareSizeble(item2, firstSlot.get(i)) && compareSizeble(
							item1, secondSlot.get(i)));
			if (match)
				return i;

		}
		return -1;
	}
	public int getIndexPartResult(ItemStack iStack) {
		for (int i = 0; i < firstSlot.size(); i++) {
			// System.out.println("--------->");
			// System.out.println(iStack);
			// System.out.println(firstSlot.get(i));
			// System.out.println("<---------");
			boolean match = compare(iStack, firstSlot.get(i))
					|| compare(iStack, secondSlot.get(i));
			if (match)
				return i;

		}
		return -1;
	}
	private boolean compare(ItemStack item1, ItemStack item2) {
		if (item1 == null || item2 == null) {
			return false;
		}
		boolean item = item1.getItem() == item2.getItem();
		boolean meta = item1.getItemDamage() == item2.getItemDamage();
		return item && meta;
	}

	private boolean compareSizeble(ItemStack currect, ItemStack need) {
		if (currect == null || need == null) {
			return false;
		}
		boolean size = currect.stackSize >= need.stackSize;
		boolean item = currect.getItem() == need.getItem();
		boolean meta = currect.getItemDamage() == need.getItemDamage();
		return size && item && meta;
	}

	public ItemStack getSmeltResult(ItemStack iStack1, ItemStack iStack2) {
		return getSmeltResult(getIndexResult(iStack1, iStack2));
	}

	public ItemStack getSmeltResult(int index) {
		return index != -1 ? output.get(index) : null;
	}

	public ItemStack getSmeltResultSecond(ItemStack iStack1, ItemStack iStack2) {
		return getSmeltResultSecond(getIndexResult(iStack1, iStack2));
	}

	public ItemStack getSmeltResultSecond(int index) {
		return index != -1 ? second_output.get(index) : null;
	}

	public float getSmeltResultSecondPercent(ItemStack iStack1,
			ItemStack iStack2) {
		return getSmeltResultSecondPercent(getIndexResult(iStack1, iStack2));
	}

	public float getSmeltResultSecondPercent(int index) {
		return index != -1 ? second_output_percent.get(index) : 0;
	}

	public boolean canSmelt(ItemStack input, ItemStack output) {
		return output.getItem() == input.getItem()
				&& (output.getItemDamage() == 32767 || output.getItemDamage() == input
						.getItemDamage());
	}

	public float getSmeltExp(ItemStack iStack1, ItemStack iStack2) {
		return getSmeltExp(getIndexResult(iStack1, iStack2));
	}

	public float getSmeltExp(int index) {
		return index != -1 ? this.experience_output.get(index) : 0;
	}

	public int getQuantaty(int index, int input) {
		if (index == -1) {
			return 0;
		}
		if (input == 1) {
			return firstSlot.get(index).stackSize;
		}
		if (input == 2) {
			return secondSlot.get(index).stackSize;
		}
		return 0;

	}
}