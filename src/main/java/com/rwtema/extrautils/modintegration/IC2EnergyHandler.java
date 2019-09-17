// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.modintegration;

import ic2.api.energy.prefab.BasicSink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class IC2EnergyHandler {
    private final TileEntity ic2EnergySink;

    public IC2EnergyHandler(final TileEntity parent1, final int capacity1, final int tier1) {
        this.ic2EnergySink = new BasicSink(parent1, capacity1, tier1);
    }

    public void updateEntity() {
        this.ic2EnergySink.updateEntity();
    }

    public void useEnergy(final double e) {
        ((BasicSink) this.ic2EnergySink).useEnergy(e);
    }

    public double getEnergyStored() {
        return ((BasicSink) this.ic2EnergySink).getEnergyStored();
    }

    public void onChunkUnload() {
        this.ic2EnergySink.onChunkUnload();
    }

    public void writeToNBT(final NBTTagCompound tagCompound) {
        try {
            this.ic2EnergySink.writeToNBT(tagCompound);
        } catch (Throwable t) {
        }
    }

    public void readFromNBT(final NBTTagCompound tagCompound) {
        try {
            this.ic2EnergySink.readFromNBT(tagCompound);
        } catch (Throwable t) {
        }
    }

    public void invalidate() {
        this.ic2EnergySink.invalidate();
    }
}


