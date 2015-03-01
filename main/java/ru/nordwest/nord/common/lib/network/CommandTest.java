package ru.nordwest.nord.common.lib.network;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

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