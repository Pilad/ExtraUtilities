// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity;

import cofh.api.energy.IEnergyReceiver;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityTrashCanEnergy extends TileEntity implements IEnergyReceiver {
    public int receiveEnergy(final ForgeDirection from, final int maxReceive, final boolean simulate) {
        return (int) Math.ceil(maxReceive * 0.9);
    }

    public int getEnergyStored(final ForgeDirection from) {
        return 0;
    }

    public int getMaxEnergyStored(final ForgeDirection from) {
        return 268435455;
    }

    public boolean canConnectEnergy(final ForgeDirection from) {
        return true;
    }
}


