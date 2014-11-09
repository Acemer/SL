package ru.nordwest.nord;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by andrew on 09.11.14.
 */
public class CommandTest extends CommandBase {
    public String getCommandName(){
        return "test";
    }

    public String getCommandUsage(ICommandSender sender){
        return null;
    }

    public void processCommand(ICommandSender sender, String[] args){

    }
}
