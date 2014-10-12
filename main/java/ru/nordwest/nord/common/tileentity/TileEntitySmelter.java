package ru.nordwest.nord.common.tileentity;

import ru.nordwest.nord.Nord;
import ru.nordwest.nord.block.SmelterBlock;
import ru.nordwest.nord.common.SmelterRecipes;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySmelter extends TileEntity implements ISidedInventory {
	private static final int[] slotsTop = new int[]{0, 1};
	private static final int[] slotsBottom = new int[]{3, 4};
	private static final int[] slotsSide = new int[]{2};
	private int input1 = 0;
	private int input2 = 1;
	private int fuel = 2;
	private int output1 = 3;
	private int output2 = 4;
	private ItemStack[] smelterItemStacks = new ItemStack[5];

	public int smelterSmeltTime;
	public int smelterCurSmeltTime;
	public int smelterWorkedTime;
	private String name;

	// public void getName(String displayName) {
	// }

	public int getSizeInventory() {
		return this.smelterItemStacks.length;
	}

	public ItemStack getStackInSlot(int slot) {
		return this.smelterItemStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.smelterItemStacks[par1] != null) {
			ItemStack iStack;

			if (this.smelterItemStacks[par1].stackSize <= par2) {
				iStack = this.smelterItemStacks[par1];
				this.smelterItemStacks[par1] = null;
				return iStack;
			} else {
				iStack = this.smelterItemStacks[par1].splitStack(par2);

				if (this.smelterItemStacks[par1].stackSize == 0) {
					this.smelterItemStacks[par1] = null;
				}

				return iStack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.smelterItemStacks[par1] != null) {
			ItemStack iStack = this.smelterItemStacks[par1];
			this.smelterItemStacks[par1] = null;
			return iStack;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2) {
		this.smelterItemStacks[par1] = par2;
		if (par2 != null && par2.stackSize > this.getInventoryStackLimit())
			par2.stackSize = this.getInventoryStackLimit();
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.name : "Smelter";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.name != null && this.name.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		NBTTagList nbtTagList = par1.getTagList("Items", 10);
		this.smelterItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbtTagList.tagCount(); ++i) {
			NBTTagCompound nbtTagCompound = nbtTagList.getCompoundTagAt(i);
			byte b0 = nbtTagCompound.getByte("Slot");

			if (b0 >= 0 && b0 < this.smelterItemStacks.length) {
				this.smelterItemStacks[b0] = ItemStack
						.loadItemStackFromNBT(nbtTagCompound);
			}
		}

		this.smelterSmeltTime = par1.getShort("SmeltTime");
		this.smelterWorkedTime = par1.getShort("WorkedTime");
		this.smelterCurSmeltTime = getItemCrushTime(this.smelterItemStacks[1]);

		if (par1.hasKey("CustomName", 8)) {
			this.name = par1.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound par1) {
		super.writeToNBT(par1);
		par1.setShort("SmeltTime", (short) this.smelterSmeltTime);
		par1.setShort("WorkedTime", (short) this.smelterWorkedTime);
		NBTTagList nbtTagList = new NBTTagList();

		for (int i = 0; i < this.smelterItemStacks.length; ++i) {
			if (this.smelterItemStacks[i] != null) {
				NBTTagCompound nbtTagCompound = new NBTTagCompound();
				nbtTagCompound.setByte("Slot", (byte) i);
				this.smelterItemStacks[i].writeToNBT(nbtTagCompound);
				nbtTagList.appendTag(nbtTagCompound);
			}
		}

		par1.setTag("Items", nbtTagList);

		if (this.hasCustomInventoryName()) {
			par1.setString("CustomName", this.name);
		}
	}

	public int getWorkedProgressScaled(int par1) {
		return this.smelterWorkedTime * par1 / 400;
	}

	public int getCrushTimeRemainingScaled(int par1) {
		if (this.smelterCurSmeltTime == 0) {
			this.smelterCurSmeltTime = 400;
		}

		return this.smelterSmeltTime * par1 / this.smelterCurSmeltTime;
	}

	public boolean isActive() {
		return this.smelterSmeltTime > 0;
	}

	public void updateEntity() {
		boolean flag = this.smelterSmeltTime > 0;
		boolean flag1 = false;

		if (this.smelterSmeltTime > 0) {
			--this.smelterSmeltTime;
		}

		if (!this.worldObj.isRemote) {
			if (this.smelterSmeltTime != 0
					|| this.smelterItemStacks[1] != null
					&& (this.smelterItemStacks[input1] != null || this.smelterItemStacks[input2] != null)) {
				if (this.smelterSmeltTime == 0 && this.canSmelt()) {
					this.smelterCurSmeltTime = this.smelterSmeltTime = getItemCrushTime(this.smelterItemStacks[fuel]);

					if (this.smelterSmeltTime > 0) {
						flag1 = true;

						if (this.smelterItemStacks[fuel] != null) {
							--this.smelterItemStacks[fuel].stackSize;
							if (this.smelterItemStacks[fuel].stackSize == 0) {
								this.smelterItemStacks[fuel] = smelterItemStacks[fuel]
										.getItem().getContainerItem(
												smelterItemStacks[fuel]);
							}
						}
					}
				}

				if (this.isActive() && this.canSmelt()) {
					++this.smelterWorkedTime;

					if (this.smelterWorkedTime == 400) {
						this.smelterWorkedTime = 0;
						this.crushItem();
						flag1 = true;
					}
				} else {
					this.smelterWorkedTime = 0;
				}
			}

			if (flag != this.smelterSmeltTime > 0) {
				flag1 = true;
				SmelterBlock.updateBlockState(this.smelterSmeltTime > 0,
						this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (flag1)
			this.markDirty();

	}

	private boolean canSmelt() {
		if (this.smelterItemStacks[input1] == null
				&& this.smelterItemStacks[input2] == null)
			return false;
		else {
			ItemStack iStack = SmelterRecipes.crushing().getSmeltResult(
					this.smelterItemStacks[input1],
					this.smelterItemStacks[input2]);
			// boolean second = Nord.instance.rand.nextFloat() < SmelterRecipes
			// .crushing().getSmeltResultSecondPercent(
			// this.smelterItemStacks[input1],
			// this.smelterItemStacks[input2]);
			ItemStack iStack2 = SmelterRecipes.crushing().getSmeltResultSecond(
					this.smelterItemStacks[input1],
					this.smelterItemStacks[input2]);

			if (iStack == null)
				return false;

			if (this.smelterItemStacks[output1] == null
					&& this.smelterItemStacks[output2] == null)
				return true;

			if (this.smelterItemStacks[output1] != null
					&& !this.smelterItemStacks[output1].isItemEqual(iStack)
					&& this.smelterItemStacks[output2] != null
					&& !this.smelterItemStacks[output2].isItemEqual(iStack2))
				return false;

			int result = -1;
			if (this.smelterItemStacks[output1] != null) {
				result = smelterItemStacks[output1].stackSize
						+ 7;
			}

			int result2 = -1;
			if (this.smelterItemStacks[output2] != null) {
				result2 = smelterItemStacks[output2].stackSize
						+ iStack2.stackSize;
			}

			boolean size1 = result <= getInventoryStackLimit()
					&& (result == -1 || result <= this.smelterItemStacks[output1]
							.getMaxStackSize());

			boolean size2 = result2 <= getInventoryStackLimit()
					&& (result2 == -1 || result2 <= this.smelterItemStacks[output2]
							.getMaxStackSize());

			return size1 && size2;
		}
	}
	public void crushItem() {
		if (this.canSmelt()) {
			int index = SmelterRecipes.crushing().getIndexResult(
					this.smelterItemStacks[input1],
					this.smelterItemStacks[input2]);
			ItemStack iStack1 = SmelterRecipes.crushing().getSmeltResult(index);
			ItemStack iStack2 = SmelterRecipes.crushing().getSmeltResultSecond(
					index);
			boolean second = Nord.instance.rand.nextFloat() < SmelterRecipes
					.crushing().getSmeltResultSecondPercent(index);

			if (this.smelterItemStacks[output1] == null)
				this.smelterItemStacks[output1] = iStack1.copy();
			else if (this.smelterItemStacks[output1].getItem() == iStack1
					.getItem())
				this.smelterItemStacks[output1].stackSize += iStack1.stackSize;

			if (iStack2 != null &&  second) {
				if (this.smelterItemStacks[output2] == null)
					this.smelterItemStacks[output2] = iStack2.copy();
				else if (this.smelterItemStacks[output2].getItem() == iStack2
						.getItem()){
					this.smelterItemStacks[output2].stackSize += iStack2.stackSize;
					}
			}

			int quant1 = SmelterRecipes.crushing().getQuantaty(index, 1);
			int quant2 = SmelterRecipes.crushing().getQuantaty(index, 2);
			if (this.smelterItemStacks[input1] != null) {
				this.smelterItemStacks[input1].stackSize -= quant1;
				if (this.smelterItemStacks[input1].stackSize <= 0)
					this.smelterItemStacks[input1] = null;
			}
			if (this.smelterItemStacks[input2] != null) {
				this.smelterItemStacks[input2].stackSize -= quant2;
				if (this.smelterItemStacks[input2].stackSize <= 0)
					this.smelterItemStacks[input2] = null;
			}
		}
	}

	public static int getItemCrushTime(ItemStack iStack) {
		if (iStack == null) {
			return 0;
		} else {
			Item item = iStack.getItem();
			if (item instanceof ItemBlock
					&& Block.getBlockFromItem(item) != Blocks.air) {
				Block block = Block.getBlockFromItem(item);
				// add specific blocks to crusher fuel line
			}

			// add blocks/Items to crusher fuel lineiStack2
			if (item == Items.coal)
				return 1600;
			return GameRegistry.getFuelValue(iStack);
		}
	}

	public static boolean isItemFuel(ItemStack iStack) {
		return getItemCrushTime(iStack) > 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false : par1.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack iStack) {
		return slot == 2 ? false : (slot == 1 ? isItemFuel(iStack) : true);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 0 ? slotsBottom : (side == 1 ? slotsTop : slotsSide);
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack iStack, int par3) {
		return this.isItemValidForSlot(slot, iStack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack iStack, int par3) {
		return par3 != 0 || slot != 1;
	}
}