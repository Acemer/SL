package ru.nordwest.nord;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nordwest.nord.block.BaseMetallBlock;
import ru.nordwest.nord.block.BaseMetallOre;
import ru.nordwest.nord.item.ItemMetallBlock;
import ru.nordwest.nord.item.ItemMetallDrop;
import ru.nordwest.nord.item.ItemMetallIngot;
import ru.nordwest.nord.item.ItemMetallOre;
import cpw.mods.fml.common.registry.GameRegistry;

public class MetallRegister {
	public static String[] metall_list = new String[] { "alluminum", "chrome",
			"iron", "titan", "tungsten", "zing", "gold", "cobalt", "copper",
			"nickel", "tin", "platinum", "plumbum" };
	public static String[] ore_list = new String[] { "bauxite", "chromite",
			"crocoite", "uvarovite", "cobaltite", "skutterudite", "azurite", "brochantite", "copper", 
			"almandine", "hematite", "limonite", "magnetite", "pyrite", "olivine", "gold_quartz",
			"millerite", "pentlandite", "proustite", "cerussite", "dundas", "vanadinite", "tin", "ilmenite",
			"titanite", "wolframite", "blende", "zincite", };
	private static HashMap<String, Block> metall_block = new HashMap<String, Block>();
	private static HashMap<String, Item> metall_ingot = new HashMap<String, Item>();

	private static HashMap<String, ItemStack> metall_ore = new HashMap<String, ItemStack>();
	private static HashMap<String, ItemStack> metall_drop = new HashMap<String, ItemStack>();
	private static HashMap<String, ItemStack> metall_powder = new HashMap<String, ItemStack>();
	private static HashMap<String, ItemStack> metall_clear_powder = new HashMap<String, ItemStack>();

	static public void init() {
		/*
		 * Регестрируем Металлы: блоки, слитки, пыль, рецепт
		 */
		Item item;
		Block block = null;
		for (String metall : metall_list) {
			block = new BaseMetallBlock(Material.iron)
					.setBlockName(metall + "_block")
					.setCreativeTab(Nord.tabMetall)
					.setBlockTextureName(metall + "_block").setHardness(3.0F)
					.setHardness(5.0F);
			block.setHarvestLevel("pickaxe", 2);
			GameRegistry.registerBlock(block, ItemMetallBlock.class, metall
					+ "_block");
			metall_block.put(metall, block);
			item = new ItemMetallIngot().setUnlocalizedName(metall + "_ingot")
					.setTextureName(metall + "_ingot");
			GameRegistry.registerItem(item, metall + "_ingot");
			metall_ingot.put(metall, item);
			for (int i = 0; i < 4; i++) {
				GameRegistry.addRecipe(new ItemStack(block, 1, i), "xxx",
						"xxx", "xxx", 'x', new ItemStack(item, 1, i));
			}
		}
		/**
		 * Регестрируем руды, дропу, пыль и чистую пыль
		 */
		Item drop = new ItemMetallDrop("drop").setUnlocalizedName("drop");
		GameRegistry.registerItem(drop, "drop");

		Item powder = new ItemMetallDrop("powder").setUnlocalizedName("powder");
		GameRegistry.registerItem(powder, "powder");

		Item clear_powder = new ItemMetallDrop("clear_powder")
				.setUnlocalizedName("clear_powder");
		GameRegistry.registerItem(clear_powder, "clear_powder");

		for (int i = 0; i < ore_list.length; i++) {
			String name = ore_list[i];
			if (i % 16 == 0) {
				System.err.println("test shift " + i);
				block = new BaseMetallOre(Material.rock, i)
						.setCreativeTab(Nord.tabMetall).setHardness(3.0F)
						.setResistance(5.0F).setBlockName("ore_" + i);
				GameRegistry.registerBlock(block, ItemMetallOre.class, "ore_"
						+ i);
			}
			if (block != null) {
				metall_ore.put(name, new ItemStack(block, 1, i % 16));
				metall_drop.put(name, new ItemStack(drop, 1, i));
				metall_powder.put(name, new ItemStack(powder, 1, i));
				metall_clear_powder
						.put(name, new ItemStack(clear_powder, 1, i));
			}
		}

	}

	public static ItemStack getMetallBlock(String name, int quality) {
		if (metall_block.containsKey(name)) {
			return new ItemStack(metall_block.get(name), 1, quality % 4);
		} else {
			System.err.println("Unknow metall: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}

	public static ItemStack getMetallIngot(String name, int quality) {
		if (metall_ingot.containsKey(name)) {
			return new ItemStack(metall_block.get(name), 1, quality % 4);
		} else {
			System.err.println("Unknow metall: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}

	public static ItemStack getMetallPowder(String name) {
		if (metall_ingot.containsKey(name)) {
			return new ItemStack(metall_block.get(name), 1, 5);
		} else {
			System.err.println("Unknow metall: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}

	public static ItemStack getOre(String name) {
		if (metall_ore.containsKey(name)) {
			return metall_ore.get(name);
		} else {
			System.err.println("Unknow Ore: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}

	public static ItemStack getOreDrop(String name) {
		if (metall_drop.containsKey(name)) {
			return metall_drop.get(name);
		} else {
			System.err.println("Unknow Ore: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}

	public static ItemStack getOrePowder(String name) {
		if (metall_powder.containsKey(name)) {
			return metall_powder.get(name);
		} else {
			System.err.println("Unknow Ore: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}

	public static ItemStack getOreClearPowder(String name) {
		if (metall_clear_powder.containsKey(name)) {
			return metall_clear_powder.get(name);
		} else {
			System.err.println("Unknow Ore: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}
}
