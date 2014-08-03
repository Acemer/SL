package ru.nordwest.nord.item;

import net.minecraft.block.Block;

public class ItemFood extends ItemFood
{

    public ItemFood(int par1, int par2, float par3, boolean par4)
    {
        super(par1, par2, par3, par4); 
        this.setAlwaysEdible();
    }

    
    public void onFoodEaten(ItemFood par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        super.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
        if (!par2World.isRemote)
        { 
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 100, 0));
        }
 
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("OreSpawn:" + (this.getUnlocalizedName().substring(5)));
    }
    
}
