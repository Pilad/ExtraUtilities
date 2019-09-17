// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.worldgen.Underdark;

import com.rwtema.extrautils.EventHandlerServer;
import com.rwtema.extrautils.ExtraUtils;
import com.rwtema.extrautils.helper.XURandom;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;

import java.util.Random;

public class EventHandlerUnderdark {
    public static Random rand;

    static {
        EventHandlerUnderdark.rand = XURandom.getInstance();
    }

    @SubscribeEvent
    public void noMobs(final LivingSpawnEvent.CheckSpawn event) {
        if (event.getResult() == Event.Result.DEFAULT && event.world.provider.dimensionId == ExtraUtils.underdarkDimID && event.entity instanceof EntityMob) {
            if (EventHandlerUnderdark.rand.nextDouble() < Math.min(0.95, event.entity.posY / 80.0)) {
                event.setResult(Event.Result.DENY);
            } else {
                IAttributeInstance t = ((EntityMob) event.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth);
                t.setBaseValue(t.getBaseValue() * 2.0);
                ((EntityMob) event.entity).heal((float) t.getAttributeValue());
                t = ((EntityMob) event.entity).getEntityAttribute(SharedMonsterAttributes.attackDamage);
                t.setBaseValue(t.getBaseValue() * 2.0);
                if (!EventHandlerServer.isInRangeOfTorch(event.entity) && event.entityLiving.worldObj.checkNoEntityCollision(event.entityLiving.boundingBox) && event.entityLiving.worldObj.getCollidingBoundingBoxes(event.entityLiving, event.entityLiving.boundingBox).isEmpty() && !event.entityLiving.worldObj.isAnyLiquid(event.entityLiving.boundingBox)) {
                    event.setResult(Event.Result.ALLOW);
                }
            }
        }
    }

    @SubscribeEvent
    public void preventDoubleDecor(final DecorateBiomeEvent.Decorate decor) {
    }

    @SubscribeEvent
    public void noDirt(final OreGenEvent.GenerateMinable event) {
        if (event.world.provider.dimensionId == ExtraUtils.underdarkDimID) {
            switch (event.type) {
                case DIRT:
                case GRAVEL: {
                    event.setResult(Event.Result.DENY);
                    break;
                }
            }
        }
    }
}


