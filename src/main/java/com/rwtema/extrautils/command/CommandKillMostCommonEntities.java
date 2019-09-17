// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

import java.util.HashMap;

public class CommandKillMostCommonEntities extends CommandKillEntities {
    public CommandKillMostCommonEntities() {
        super("common", null, false);
    }

    @Override
    public void processCommand(final ICommandSender icommandsender, final String[] astring) {
        int mx = -1;
        this.entityclass = null;
        final HashMap<Class, Integer> map = new HashMap<Class, Integer>();
        for (final WorldServer world : MinecraftServer.getServer().worldServers) {
            for (int i = 0; i < world.loadedEntityList.size(); ++i) {
                final Class clazz = world.loadedEntityList.get(i).getClass();
                Integer j = map.get(clazz);
                if (j == null) {
                    j = 0;
                }
                ++j;
                if (j > mx) {
                    mx = j;
                    this.entityclass = (Class<? extends Entity>) clazz;
                }
                map.put(clazz, j);
            }
        }
        if (this.entityclass == null) {
            return;
        }
        super.processCommand(icommandsender, astring);
    }
}


