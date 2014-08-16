package ru.nordwest.nord;

import java.util.HashMap;

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
	public static String[] metall_list = new String[]{"alluminum", "chrome",
			"iron", "titan", "tungsten", "zing", "gold", "cobalt", "copper",
			"nickel", "tin", "platinum", "plumbum", "silver"};
	public static String[] ore_list = new String[]{"bauxite", "chromite",
			"crocoite", "uvarovite", "cobaltite", "skutterudite", "azurite",
			"brochantite", "copper", "almandine", "hematite", "limonite",
			"magnetite", "pyrite", "olivine", "gold_quartz", "millerite",
			"pentlandite", "proustite", "cerussite", "dundas", "vanadinite",
			"tin", "ilmenite", "titanite", "wolframite", "blende", "zincite",
			"argentite", "galena", "silver"};
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
		for (final String metall : MetallRegister.metall_list) {
			block = new BaseMetallBlock(Material.iron)
					.setBlockName(metall + "_block")
					.setCreativeTab(Nord.tabMetall)
					.setBlockTextureName(metall + "_block").setHardness(3.0F)
					.setHardness(5.0F);
			block.setHarvestLevel("pickaxe", 2);
			GameRegistry.registerBlock(block, ItemMetallBlock.class, metall
					+ "_block");
			MetallRegister.metall_block.put(metall, block);
			item = new ItemMetallIngot().setUnlocalizedName(metall + "_ingot")
					.setTextureName(metall + "_ingot");
			GameRegistry.registerItem(item, metall + "_ingot");
			MetallRegister.metall_ingot.put(metall, item);
			for (int i = 0; i < 4; i++) {
				GameRegistry.addRecipe(new ItemStack(block, 1, i), "xxx",
						"xxx", "xxx", 'x', new ItemStack(item, 1, i));
			}
		}
		/**
		 * Регестрируем руды, дропу, пыль и чистую пыль
		 */
		final Item drop = new ItemMetallDrop("drop").setUnlocalizedName("drop");
		GameRegistry.registerItem(drop, "drop");

		final Item powder = new ItemMetallDrop("powder")
				.setUnlocalizedName("powder");
		GameRegistry.registerItem(powder, "powder");

		final Item clear_powder = new ItemMetallDrop("clear_powder")
				.setUnlocalizedName("clear_powder");
		GameRegistry.registerItem(clear_powder, "clear_powder");

		for (int i = 0; i < MetallRegister.ore_list.length; i++) {
			final String name = MetallRegister.ore_list[i];
			if ((i % 16) == 0) {
				block = new BaseMetallOre(Material.rock, i)
						.setCreativeTab(Nord.tabMetall).setHardness(3.0F)
						.setResistance(5.0F).setBlockName("ore_" + i);
				GameRegistry.registerBlock(block, ItemMetallOre.class, "ore_"
						+ i);
			}
			if (block != null) {
				MetallRegister.metall_ore.put(name, new ItemStack(block, 1,
						i % 16));
				MetallRegister.metall_drop.put(name, new ItemStack(drop, 1, i));
				MetallRegister.metall_powder.put(name, new ItemStack(powder, 1,
						i));
				MetallRegister.metall_clear_powder.put(name, new ItemStack(
						clear_powder, 1, i));
			}
		}

	}

	public static ItemStack getMetallBlock(final String name, final int quality) {
		if (MetallRegister.metall_block.containsKey(name)) {
			return new ItemStack(MetallRegister.metall_block.get(name), 1,
					quality % 4);
		} else {
			System.err.println("Unknow metall: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}

	public static ItemStack getMetallIngot(final String name, final int quality) {
		if (MetallRegister.metall_ingot.containsKey(name)) {
			return new ItemStack(MetallRegister.metall_ingot.get(name), 1,
					quality % 4);
		} else {
			System.err.println("Unknow metall: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}

	public static ItemStack getMetallPowder(final String name) {
		if (MetallRegister.metall_ingot.containsKey(name)) {
			return new ItemStack(MetallRegister.metall_block.get(name), 1, 5);
		} else {
			System.err.println("Unknow metall: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}

	public static ItemStack getOre(final String name) {
		if (MetallRegister.metall_ore.containsKey(name)) {
			return MetallRegister.metall_ore.get(name);
		} else {
			System.err.println("Unknow Ore: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}

	public static ItemStack getOreDrop(final String name) {
		if (MetallRegister.metall_drop.containsKey(name)) {
			return MetallRegister.metall_drop.get(name);
		} else {
			System.err.println("Unknow Ore: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}

	public static ItemStack getOrePowder(final String name) {
		if (MetallRegister.metall_powder.containsKey(name)) {
			return MetallRegister.metall_powder.get(name);
		} else {
			System.err.println("Unknow Ore: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}

	public static ItemStack getOreClearPowder(final String name) {
		if (MetallRegister.metall_clear_powder.containsKey(name)) {
			return MetallRegister.metall_clear_powder.get(name);
		} else {
			System.err.println("Unknow Ore: " + name);
			System.err.println("Game has crashed:)");
			return null;
		}
	}
}
