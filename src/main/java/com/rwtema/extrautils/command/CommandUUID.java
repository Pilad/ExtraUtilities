// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.command;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

@SideOnly(Side.CLIENT)
public class CommandUUID extends CommandBase {
    public String getCommandName() {
        return "uuid";
    }

    public String getCommandUsage(final ICommandSender var1) {
        return "/uuid";
    }

    public boolean canCommandSenderUseCommand(final ICommandSender par1ICommandSender) {
        return true;
    }

    public void processCommand(final ICommandSender var1, final String[] var2) {
        var1.addChatMessage(new ChatComponentText("Username: " + Minecraft.getMinecraft().getSession().func_148256_e().getName() + " UUID: " + Minecraft.getMinecraft().getSession().func_148256_e().getId()));
    }
}


