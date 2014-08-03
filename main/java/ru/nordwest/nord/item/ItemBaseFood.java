package ru.nordwest.nord.item;

import ru.nordwest.nord.Nord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;

public class ItemBaseFood extends ItemFood
{

    public ItemBaseFood( int par2, float par3, boolean par4)
    {
        super(par2, par3, par4); 
        this.setAlwaysEdible();
    }

    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Nord.MODID +":" + (this.getUnlocalizedName().substring(5)));
    }
    
}
