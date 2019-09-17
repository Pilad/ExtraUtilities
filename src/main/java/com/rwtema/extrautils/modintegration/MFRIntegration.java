// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.modintegration;

import com.rwtema.extrautils.ExtraUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;

public class MFRIntegration {
    private static MFRIntegration instance;

    static {
        MFRIntegration.instance = null;
    }

    public static void registerMFRIntegration() {
        if (MFRIntegration.instance != null) {
            throw new IllegalStateException("Already registered");
        }
        if (!ExtraUtils.enderLilyEnabled) {
            return;
        }
        MFRIntegration.instance = new MFRIntegration();
        FactoryRegistry.sendMessage("registerHarvestable_Crop", new ItemStack(ExtraUtils.enderLily, 1, 7));
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setString("seed", Item.itemRegistry.getNameForObject(Item.getItemFromBlock(ExtraUtils.enderLily)));
        tag.setString("crop", Block.blockRegistry.getNameForObject(ExtraUtils.enderLily));
        FactoryRegistry.sendMessage("registerPlantable_Standard", tag);
    }
}


