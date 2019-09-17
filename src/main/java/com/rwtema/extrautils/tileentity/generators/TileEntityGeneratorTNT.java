// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.generators;

import com.rwtema.extrautils.helper.XURandom;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class TileEntityGeneratorTNT extends TileEntityGeneratorFurnace {
    private static Random rand;

    static {
        TileEntityGeneratorTNT.rand = XURandom.getInstance();
    }

    @Override
    public int transferLimit() {
        return 400;
    }

    @Override
    public double getFuelBurn(final ItemStack item) {
        if (item == null) {
            return 0.0;
        }
        if (item.getItem() == Items.gunpowder) {
            return 400.0;
        }
        if (item.getItem() == Item.getItemFromBlock(Blocks.tnt)) {
            return 6000.0;
        }
        return 0.0;
    }

    @Override
    public double genLevel() {
        return 80.0;
    }

    @Override
    public void doSpecial() {
        if (this.coolDown > 0.0 && TileEntityGeneratorTNT.rand.nextInt(80) == 0) {
            this.worldObj.createExplosion(null, this.xCoord + TileEntityGeneratorTNT.rand.nextDouble() * 2.0 - 0.5, this.yCoord + TileEntityGeneratorTNT.rand.nextDouble() * 2.0 - 0.5, this.zCoord + TileEntityGeneratorTNT.rand.nextDouble() * 2.0 - 0.5, 1.0f, false);
        }
    }

    @Override
    public boolean processInput() {
        if (super.processInput()) {
            this.worldObj.createExplosion(null, this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5, 5.0f, false);
            return true;
        }
        return false;
    }
}


