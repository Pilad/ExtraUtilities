// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.item;

import com.rwtema.extrautils.helper.XURandom;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class ItemBlockQED extends ItemBlockMetadata {
    public static Random rand;
    public static long prevTime;
    public static int curRand;

    static {
        ItemBlockQED.rand = XURandom.getInstance();
        ItemBlockQED.prevTime = -2147483648L;
        ItemBlockQED.curRand = -1;
    }

    public ItemBlockQED(final Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    @Override
    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        if (par1ItemStack.getItemDamage() == 0 && FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            final StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if ("net.minecraft.item.Item".equals(stackTrace[1].getClassName())) {
                final long curTime = System.currentTimeMillis();
                if (curTime - ItemBlockQED.prevTime > 1000L || ItemBlockQED.curRand == -1) {
                    ItemBlockQED.curRand = ItemBlockQED.rand.nextInt(17);
                }
                ItemBlockQED.prevTime = curTime;
                return "tile.extrautils:qed.rand." + ItemBlockQED.curRand;
            }
        }
        return super.getUnlocalizedName(par1ItemStack);
    }
}


