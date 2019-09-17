// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.worldgen.Underdark;

import com.rwtema.extrautils.ExtraUtils;
import com.rwtema.extrautils.damgesource.DamageSourceDarkness;
import com.rwtema.extrautils.helper.XURandom;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.Random;

public class DarknessTickHandler {
    public static float maxDarkTime;
    public static Random random;
    public static int maxLevel;

    static {
        DarknessTickHandler.maxDarkTime = 100.0f;
        DarknessTickHandler.random = XURandom.getInstance();
        DarknessTickHandler.maxLevel = 20 + DarknessTickHandler.random.nextInt(120);
    }

    @SubscribeEvent
    public void tickStart(final TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START && !event.player.worldObj.isRemote && event.player instanceof EntityPlayerMP) {
            final EntityPlayerMP player = (EntityPlayerMP) event.player;
            if (!player.worldObj.isRemote && player.worldObj.provider.dimensionId == ExtraUtils.underdarkDimID && player.worldObj.getTotalWorldTime() % 10L == 0L) {
                int time = 0;
                if (player.getEntityData().hasKey("XU|DarkTimer")) {
                    time = player.getEntityData().getInteger("XU|DarkTimer");
                }
                if (player.getBrightness(1.0f) < 0.03) {
                    if (time > 100) {
                        player.attackEntityFrom(DamageSourceDarkness.darkness, 1.0f);
                    } else {
                        ++time;
                    }
                } else if (time > 0) {
                    --time;
                }
                player.getEntityData().setInteger("XU|DarkTimer", time);
            }
        }
    }
}


