// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.item;

import com.rwtema.extrautils.ExtraUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBlockBedrockium extends ItemBlock {
    public ItemBlockBedrockium(final Block p_i45328_1_) {
        super(p_i45328_1_);
        this.setCreativeTab(ExtraUtils.creativeTabExtraUtils);
    }

    public void onUpdate(final ItemStack itemStack, final World world, final Entity entity, final int i, final boolean b) {
        super.onUpdate(itemStack, world, entity, i, b);
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 10, 6));
        }
    }

    public int getEntityLifespan(final ItemStack itemStack, final World world) {
        return 2147473647;
    }
}


