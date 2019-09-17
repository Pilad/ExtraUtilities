// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.command;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.rwtema.extrautils.LogHelper;
import com.rwtema.extrautils.nei.InfoData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemStack;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;

@SideOnly(Side.CLIENT)
public class CommandDumpNEIInfo2 extends CommandBase {
    public String getCommandName() {
        return "dumpneidocs2";
    }

    public String getCommandUsage(final ICommandSender icommandsender) {
        return "/dumpneidocs2";
    }

    public void processCommand(final ICommandSender icommandsender, final String[] astring) {
        final File f = new File(Minecraft.getMinecraft().mcDataDir, "en_US_doc.lang");
        String t = "";
        Collections.sort(InfoData.data, cmpData.instance);
        for (final InfoData data : InfoData.data) {
            String id;
            if (data.precise) {
                id = data.item.getUnlocalizedName();
            } else {
                id = data.item.getItem().getUnlocalizedName();
            }
            t = t + "doc." + id + ".name=" + data.name + "\n";
            if (data.info.length == 1) {
                t = t + "doc." + id + ".info=" + data.info[0].replace('\n', ' ') + "\n";
            } else {
                for (int i = 0; i < data.info.length; ++i) {
                    t = t + "doc." + id + ".info." + i + "=" + data.info[i].replace('\n', ' ') + "\n";
                }
            }
        }
        try {
            Files.write(t, f, Charsets.UTF_8);
            LogHelper.info("Dumped Extra Utilities NEI info data to " + f.getAbsolutePath());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static class cmpData implements Comparator<InfoData> {
        public static cmpData instance;

        static {
            cmpData.instance = new cmpData();
        }

        public static String getItem(final ItemStack i) {
            final ItemStack t = new ItemStack(i.getItem(), 1, 0);
            return t.getDisplayName();
        }

        @Override
        public int compare(final InfoData arg0, final InfoData arg1) {
            return arg0.item.getUnlocalizedName().compareTo(arg1.item.getUnlocalizedName());
        }
    }
}


