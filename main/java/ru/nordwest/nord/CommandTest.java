package ru.nordwest.nord;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

/**
 * @author Dark32
 */
public class CommandTest extends CommandBase {
        public String getCommandName() {
                return "test";
        }

        public String getCommandUsage(ICommandSender sender) {
                return null;
        }

        public void processCommand(ICommandSender sender, String[] args) {

        }
}