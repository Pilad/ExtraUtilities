// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.command;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.rwtema.extrautils.LogHelper;
import com.rwtema.extrautils.nei.InfoData;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemStack;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;

public class CommandDumpNEILang extends CommandBase {
    public String getCommandName() {
        return "dumpneilang";
    }

    public String getCommandUsage(final ICommandSender icommandsender) {
        return "/dumpneilang";
    }

    public void processCommand(final ICommandSender icommandsender, final String[] astring) {
        final File f = new File(Minecraft.getMinecraft().mcDataDir, "en_neilang.txt");
        String t = "";
        Collections.sort(InfoData.data, cmpData.instance);
        boolean blocks = true;
        t = "[spoiler='New Blocks']\n";
        for (final InfoData data : InfoData.data) {
            if (blocks && !data.isBlock) {
                blocks = false;
                t += "[/spoiler]\n\n";
                t += "[spoiler='New Items']\n";
            }
            t = t + "[spoiler='" + data.name + "']\n";
            if (data.url != null) {
                t = t + "[center][img]" + data.url + "[/img][/center]\n";
            }
            boolean extraSpoilerTag = false;
            for (final String s : data.info) {
                if (s.startsWith("Spoilers:")) {
                    extraSpoilerTag = true;
                    t += "[spoiler='Spoilers!']\n";
                } else {
                    t = t + s + "\n";
                }
            }
            if (extraSpoilerTag) {
                t += "[/spoiler]\n";
            }
            t += "[/spoiler]\n\n";
        }
        t += "[/spoiler]\n";
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
            if (arg0.isBlock && !arg1.isBlock) {
                return -1;
            }
            if (arg1.isBlock && !arg0.isBlock) {
                return 1;
            }
            return arg0.name.compareTo(arg1.name);
        }
    }
}


